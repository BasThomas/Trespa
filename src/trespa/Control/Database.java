/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
import trespa.Model.*;
import trespa.Model.Tuples.*;

/**
 *
 * @author Bas
 */
public class Database
{
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private String host = "jdbc:mysql://localhost:3306/trespa";
    private String username = "root";
    private String password = "root";
    
    private boolean withAddress;
    
    /**
     * Constructor.
     */
    public Database()
    {
        openConnection();
    }
    
    /**
     * Check if coordinates have to be generated.
     * @param latitude the latitude of the location.
     * @param longitude the longitude of the location.
     * @return if the coordinates have to be generated or not.
     */
    public boolean checkCoordinates(String latitude, String longitude)
    {
        boolean getCoordinates = false;
        
        if (latitude == null || latitude.equals(""))
        {
            // Get the latitude and longitude of the location.
            getCoordinates = true;
        }
        
        return getCoordinates;
    }
    
    public void calcDistance()
    {
        List<ShippingPoint> sp = getShippingPoints();
            
        Pair pairFrom = new Pair(sp.get(0).getLongitude(), sp.get(0).getLatitude());
        int fromPoint = sp.get(0).getShippingID();
        
        List<Customer> cus = getCustomers();
        
        Pair pairTo;
        int toPoint;
        
        for (Customer c: cus)
        {
            pairTo = new Pair(c.getLongitude(), c.getLatitude());
            toPoint = c.getCustomerID();
            
            float dist = getDistance(pairFrom, pairTo);
            
            try
            {
                openConnection();
                
                String sql =
                        "INSERT INTO routeTableFR VALUES(?, ?, ?)";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, fromPoint);
                ps.setInt(2, toPoint);
                ps.setFloat(3, dist);

                ps.executeUpdate();
                
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
    }
    
    public float getDistance(Pair geoStart, Pair geoEnd)
    {
        String language = "nl";
        
        String geoStartString = geoStart.a.toString() + "," + geoStart.b.toString();
        String geoEndString = geoEnd.a.toString() + "," + geoEnd.b.toString();
        
        String request;
        request = String.format(
                "http://maps.googleapis.com/maps/api/distancematrix/json?" +
                "origins=%s" +
                "&destinations=%s" +
                "&language=%s", geoStartString, geoEndString, language);
        System.out.println("URL: " + request);
        
        float distance = handleDisRequest(request);
        
        return distance;
    }
    
    public float handleDisRequest(String request)
    {
        float distance = 0f;
        
        try
        {
            String jsonAsText = readUrl(request);
            
            JSONObject json = new JSONObject(jsonAsText);
            // Check status
            if (!json.getString("status").equals("OK"))
            {
                return 0f;
            }
            JSONObject elements = json.getJSONArray("rows").getJSONObject(0);
            JSONObject moreElements = elements.getJSONArray("elements").getJSONObject(0);
            JSONObject distanceJSON = moreElements.getJSONObject("distance");
            
            String distanceText = distanceJSON.getString("text");
            
            distance = Float.parseFloat(distanceText.substring(0, distanceText.indexOf(' ')));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return distance;
    }
    
    /**
     * Checks if the distance from this point has to be calculated.
     * @param id the ID of the location to check.
     * @param country the current Country.
     * @return if the distance has to be calculated or not.
     */
    public boolean checkRouteTable(int id, String country)
    {
        boolean getDistance = true;
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT *\n" +
                    "FROM ?\n" +
                    "WHERE fromPoint = ?\n" +
                    "OR toPoint = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, "routeTable" + country);
            ps.setInt(2, id);
            ps.setInt(3, id);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                System.out.println("@@@@@@@@@@@@@@@@@@@@");
                // Point already in table; no calculating needed.
                getDistance = false;
                break;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return getDistance;
    }
    
    /**
     * Calculate the distances between points.
     * @param id the point's ID.
     */
    public void calculateDistances(int id)
    {
        // Get the customers and shippingPoints.
        List<Customer> customers = getCustomers();
        List<ShippingPoint> shippingPoints = getShippingPoints();
        
        for (Customer c : customers)
        {
            // Check if you're calculating distance to yourself.
            if (c.getCustomerID() != id)
            {
                // Calculate the distance here.
            }
        }
        
        for (ShippingPoint s : shippingPoints)
        {
            // Check if you're calculating distance to yourself.
            if (s.getShippingID() != id)
            {
                // Calculate the distance here.
            }
        }
    }
    
    /**
     * Gets the geolocation for a specific address.
     * @param address the address of the location.
     * @param postalCode the postalCode of the location.
     * @param countryAbbr the abbreviation code for the country.
     * @return pair containing longitude and altitude;
     */
    public Pair getGeoLocation(String address, String postalCode, String countryAbbr)
    {   
        String language = "nl";
        
        String formattedAddress = address.replaceAll("\\s", "+");
        String formattedPostalCode = postalCode.replaceAll("\\s", "");
        
        String request;
        
        if (withAddress)
        {
            request = String.format(
                "http://maps.googleapis.com/maps/api/geocode/json?" +
                "address=%s," +
                "&components=postal_code:%s" +
                "|country:%s" +
                "&language=%s", formattedAddress, formattedPostalCode, countryAbbr, language);
        }
        else
        {
            request = String.format(
                "http://maps.googleapis.com/maps/api/geocode/json?" +
                "address=%s" +
                "&components=postal_code:%s" +
                "&language=%s", formattedAddress, formattedPostalCode, language);
            
            withAddress = true;
        }
        
        System.out.println("URL: " + request);
        
        if (handleRequest(request).a.toString().equals(""))
        {
            withAddress = false;
            getGeoLocation(address, postalCode, countryAbbr);
        }
        
        return handleRequest(request);
    }
    
    /**
     * Handles the json of given request.
     * @param request url containing API request.
     * @return 
     */
    public Pair handleRequest(String request)
    {
        String longitude = "";
        String latitude = "";
        try
        {
            String jsonAsText = readUrl(request);
            
            JSONObject json = new JSONObject(jsonAsText);
            
            // Check status
            if (!json.getString("status").equals("OK"))
            {
                return new Pair("", "");
            }
            
            JSONObject results = json.getJSONArray("results").getJSONObject(0);
            JSONObject location = results.getJSONObject("geometry").getJSONObject("location");
            
            longitude = (String)location.get("lng").toString();
            latitude = (String)location.get("lat").toString();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return new Pair(longitude, latitude);
    }
    
    /**
     * Read contents from url.
     * @param urlString the url.
     * @return the contents of the url as a String.
     */
    private static String readUrl(String urlString) throws Exception
    {
        BufferedReader reader = null;
        try
        {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
            {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
    }
    
    /**
     * Opens the connection with the database.
     */
    public final void openConnection()
    {
        try
        {
            conn = DriverManager.getConnection(host, username, password);
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
    }
    
    /**
     * Closes the connection with the database.
     */
    public void closeConnection()
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                System.out.printf(e.getMessage() + "\n");
            }
        }
        
        if(ps != null)
        {
            try
            {
                ps.close();
            }
            catch(SQLException e)
            {
                System.out.printf(e.getMessage() + "\n");
            }
        }
        
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                System.out.printf(e.getMessage() + "\n");
            }
        }
    }
    
    public void insertGeoLocation(Pair geolocation, int identifier, String table)
    {
        try
        {
            openConnection();
            
            String sql;
            
            switch (table)
            {
                case "ShippingPoint":
                    sql =
                            "UPDATE ShippingPoint " +
                            "SET latitude = ?, " +
                            "longitude = ? " +
                            "WHERE shippingID = ?";
                    break;
                    
                case "Customer":
                    sql =
                            "UPDATE Customer " +
                            "SET latitude = ?, " +
                            "longitude = ? " +
                            "WHERE customerID = ?";
                    break;
                    
                default:
                    return;
            }
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, geolocation.a.toString());
            ps.setString(2, geolocation.b.toString());
            ps.setInt(3, identifier);
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.printf("test" + e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
    }
    
    /**
     * Gets a list of ShippingPoints.
     * @return list of ShippingPoints.
     */
    public List<ShippingPoint> getShippingPoints()
    {
        List<ShippingPoint> shippingPoints = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT *\n" +
                    "FROM shippingPoint;";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                int shippingID = rs.getInt("shippingID");
                String shippingPoint = rs.getString("shippingPoint");
                String postalCode = rs.getString("postalCode");
                String countryAbbr = rs.getString("countryAbbr");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                
                if (checkCoordinates(latitude, longitude))
                {
                    insertGeoLocation(getGeoLocation("", postalCode, countryAbbr), shippingID, "ShippingPoint");
                    getShippingPoints();
                }
                
                // check if found in routeTable
                /*
                if (checkRouteTable(shippingID, countryAbbr))
                {
                    calculateDistances(shippingID);
                }
                */
                
                ShippingPoint shippingPointObj = new ShippingPoint(shippingID, shippingPoint, postalCode, countryAbbr, latitude, longitude);
                shippingPoints.add(shippingPointObj);
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return shippingPoints;
    }
    
    public List<Triplet> getDistances()
    {
        List<Triplet> distances = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT *\n" +
                    "FROM routeTableFR\n" +
                    "ORDER BY distance;";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                int fromID = rs.getInt("fromPoint");
                int toID = rs.getInt("toPoint");
                float distance = rs.getFloat("distance");
                
                Triplet t = new Triplet(fromID, toID, distance);
                distances.add(t);
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return distances;
    }
    
    /**
     * Get a list of Customers.
     * @return list of Customers.
     */
    public List<Customer> getCustomers()
    {
        List<Customer> customers = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT cu.*, co.country\n" +
                    "FROM customer cu, country co\n" +
                    "WHERE cu.countryAbbr = co.countryAbbr;";
            
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                int customerID = rs.getInt("customerID");
                String location = rs.getString("location");
                String postalCode = rs.getString("postalCode");
                String country = rs.getString("country");
                String countryAbbr = rs.getString("countryAbbr");
                String regionDesc = rs.getString("regionDesc");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                
                if (checkCoordinates(latitude, longitude))
                {
                    insertGeoLocation(getGeoLocation(location, postalCode, countryAbbr), customerID, "Customer");
                    getCustomers();
                }
                
                Country countryObj = new Country(country, countryAbbr, regionDesc);
                
                Customer customer = new Customer(customerID, location, postalCode, countryObj, latitude, longitude);
                customers.add(customer);
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return customers;
    }
    
    /**
     * Gets a list of Trucks.
     * @return list of Trucks.
     */
    public List<Truck> getTrucks()
    {
        List<Truck> trucks = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT *\n" +
                    "FROM truck;";
            
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                int truckID = rs.getInt("truckID");
                float maxWeight = rs.getFloat("maxWeight");
                float length = rs.getFloat("length");
                float height = rs.getFloat("height");
                
                Truck truck = new Truck(truckID, maxWeight, length, height);
                trucks.add(truck);
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return trucks;
    }
    
    /**
     * Gets a list of Countries.
     * @return list of Countries.
     */
    public List<Country> getCountries()
    {
        List<Country> countries = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT *\n" +
                    "FROM country;";
            
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                String countryAbbr = rs.getString("countryAbbr");
                String country = rs.getString("country");
                
                Country countryObj = new Country(country, countryAbbr);
                countries.add(countryObj);
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return countries;
    }
    
    /**
     * Gets the amount of stops needed for a certain route.
     * @param country Country
     * @param shippingPoint ShippingPoint
     * @return amount of stops.
     */
    public int amountOfStopsForRoute(Country country, ShippingPoint shippingPoint)
    {
        try
        {
            openConnection();
            
            String sql =
                    "SELECT COUNT(DISTINCT(pl.customerID)) AS amountOfStops\n" +
                    "FROM placement pl, customer cu, shippingPoint s\n" +
                    "WHERE pl.customerID = cu.customerID\n" +
                    "AND cu.countryAbbr = ?\n" +
                    //"AND pl.placementID = 1143730\n" + // FOR NOW FOR NOW FOR NOW
                    "AND s.shippingID = ?;";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, country.getCountryAbbr());
            ps.setInt(2, shippingPoint.getShippingID());
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                return rs.getInt("amountOfStops");
            }
        }
        catch(SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        
        return 0;
    }
    
    /**
     * Gets a list of possible placements for a certain route.
     * @param country Country
     * @param shippingPoint ShippingPoint
     * @return list of Placements.
     */
    public List<Placement> getPossiblePlacementsForRoute(Country country, ShippingPoint shippingPoint)
    {
        List<Placement> placements = new ArrayList<>();
        
        try
        {
            openConnection();
            
            String sql =
                    "SELECT pl.placementID, pl.deliveryItem, pl.issueDate, pl.thickness, pl.width, pl.length, pl.quantity, pa.panelsPer, pl.grossWeight, pa.height AS palletHeight, pl.material, cu.*, pr.*, co.country\n" +
                    "FROM placement pl, customer cu, productline pr, country co, shippingPoint s, pallet pa\n" +
                    "WHERE pl.customerID = cu.customerID\n" +
                    "AND pl.productline = pr.productline\n" +
                    "AND cu.countryAbbr = co.countryAbbr\n" +
                    "AND pl.packCode = pa.packCode\n" +
                    "AND cu.countryAbbr = ?\n" +
                    "AND s.shippingID = ?\n" +
                    //"AND pl.placementID = 1143730\n" + // TESTING
                    "ORDER BY pl.issueDate ASC;";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, country.getCountryAbbr());
            ps.setInt(2, shippingPoint.getShippingID());
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                String countryString = rs.getString("country");
                String countryAbbr = rs.getString("countryAbbr");
                String regionDesc = rs.getString("regionDesc");
                
                int customerID = rs.getInt("customerID");
                String location = rs.getString("location");
                String postalCode = rs.getString("postalCode");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                
                String productline = rs.getString("productline");
                
                int placementID = rs.getInt("placementID");
                int deliveryItem = rs.getInt("deliveryItem");
                Date issueDate = rs.getDate("issueDate");
                float thickness = rs.getFloat("thickness");
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int quantity = rs.getInt("quantity");
                int panelsPer = rs.getInt("panelsPer");
                float grossWeight = rs.getFloat("grossWeight");
                int palletHeight = rs.getInt("palletHeight");
                int material = rs.getInt("material");
                
                Country countryObj = new Country(countryString, countryAbbr, regionDesc);
                
                Customer customerObj = new Customer(customerID, location, postalCode, countryObj, latitude, longitude);
                Productline productlineObj = new Productline(productline);
                
                Placement placement = new Placement(placementID, deliveryItem, customerObj, issueDate, productlineObj, thickness, width, length, quantity, panelsPer, grossWeight, palletHeight, material);
                placements.add(placement);
            }
        }
        catch (SQLException e)
        {
            System.out.printf(e.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        return placements;
    }
}