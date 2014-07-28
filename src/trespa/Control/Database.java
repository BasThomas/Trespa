/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import trespa.Model.*;

/**
 *
 * @author Bas
 */
public class Database
{
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    String host = "jdbc:mysql://localhost:3306/trespa";
    String username = "root";
    String password = "root";
    
    public Database()
    {
        openConnection();
    }
    
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
                
                ShippingPoint shippingPointObj = new ShippingPoint(shippingID, shippingPoint, postalCode);
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
                
                Country countryObj = new Country(country, countryAbbr, regionDesc);
                
                Customer customer = new Customer(customerID, location, postalCode, countryObj);
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
    
    public int amountOfStopsForRoute(Country country, ShippingPoint shippingPoint)
    {
        try
        {
            openConnection();
            
            String sql =
                    "SELECT COUNT(DISTINCT(p.customerID)) AS amountOfStops\n" +
                    "FROM placement p, customer cu, shippingPoint s\n" +
                    "WHERE p.customerID = cu.customerID\n" +
                    "AND cu.countryAbbr = ?\n" +
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
                    "AND pl.placementID = 1143730\n" + // FOR NOW FOR NOW FOR NOW
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
                
                Customer customerObj = new Customer(customerID, location, postalCode, countryObj);
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