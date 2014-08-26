/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.*;
import trespa.Model.*;
import trespa.Model.Tuples.*;

/**
 *
 * @author Bas
 */
public class LaunchGUIViewController
{
    private final Database database;
    
    /**
     * Constructor.
     */
    public LaunchGUIViewController()
    {
        database = new Database();
    }
    
    public void getCust()
    {
        database.getCustomers();
    }
    
    /**
     * Gets the amount of stops for a certain route.
     * @param c Country
     * @param s ShippingPoint
     * @return the amount of stops.
     */
    public int amountOfStops(Country c, ShippingPoint s)
    {
        return database.amountOfStopsForRoute(c, s);
    }
    
    /**
     * Gets a list of placements for a certain route.
     * @param c Country
     * @param s ShippingPoint
     * @return list of placements.
     */
    public List<Placement> possiblePlacementsForRoute(Country c, ShippingPoint s)
    {
        return database.getPossiblePlacementsForRoute(c, s);
    }
    
    /**
     * Gets the amount of stops and placements for a certain route.
     * @param c Country
     * @param s ShippingPoint
     * @return Pair containing the amount of stops and
     *          the amount of placements.
     */
    public Pair stopsAndPlacementAmount(Country c, ShippingPoint s)
    {
        int amountOfStops = this.amountOfStops(c, s);
        int amountOfPlacements = this.possiblePlacementsForRoute(c, s).size();
        
        Pair pair = new Pair(amountOfStops, amountOfPlacements);
        
        return pair;
    }
    
    /**
     * Gathers all information for a certain Shipment.
     * @param c Country
     * @param s ShippingPoint
     * @param t Truck
     * @return Septet containing total pallets for shipment,
     *          total height for shipment,
     *          total weight for shipment,
     *          total loading meters for shipment,
     *          the amount of pallets,
     *          the amount of stops and
     *          the amount of kilometers.
     */
    public Septet totalForShipment(Country c, ShippingPoint s, Truck t)
    {
        int totalPalletsForShipment = 0;
        float totalHeightForShipment;
        float totalWeightForShipment;
        int totalLoadingMetersForShipment;
        
        List<Placement> placements = this.possiblePlacementsForRoute(c, s);
        
        int totalPalletHeightIncludingPlacement = 0;
        int totalPalletWeightIncludingPlacement = 0;
        int totalLoadingMeters = 0;
        
        int currentPalletsForPlacement;
        float currentPalletWeightIncludingPlacement;
        float currentPalletHeightIncludingPlacement;
        
        int currentQuantity;
        int currentPanelsPer;
        int currentPanelsOnPallet;
        int currentPalletLength;
        
        List<LoadedPallet> pallets = new ArrayList<>();
        
        // Might change!
        int palletWeight = 40; // In kilograms
        
        for(Placement p : placements)
        {
            currentQuantity = p.getQuantity();
            currentPanelsPer = p.getPanelsPer();
            
            if((currentQuantity % currentPanelsPer) == 0)
            {
                currentPalletsForPlacement = (currentQuantity / currentPanelsPer);
                totalPalletsForShipment += currentPalletsForPlacement;
            }
            else
            {
                currentPalletsForPlacement = (currentQuantity / currentPanelsPer + 1);
                totalPalletsForShipment += currentPalletsForPlacement;
            }
            
            for(int pallet = 0; pallet < currentPalletsForPlacement; pallet++)
            {
                if(currentQuantity > currentPanelsPer)
                {
                    currentPalletHeightIncludingPlacement = p.getPalletHeight() + (p.getThickness() * currentPanelsPer);
                    currentPalletWeightIncludingPlacement = palletWeight + (currentPanelsPer * p.getGrossWeight());
                    currentQuantity -= currentPanelsPer;
                    currentPanelsOnPallet = currentPanelsPer;
                }
                else
                {
                    currentPalletHeightIncludingPlacement = p.getPalletHeight() + (p.getThickness() * currentQuantity);
                    currentPalletWeightIncludingPlacement = palletWeight + (currentQuantity * p.getGrossWeight());
                    currentPanelsOnPallet = currentQuantity;
                }
                
                pallets.add(new LoadedPallet(p.getCustomer(), currentPalletWeightIncludingPlacement, currentPalletHeightIncludingPlacement, currentPanelsOnPallet, p.getThickness(), p.getLength()));
                
                totalPalletHeightIncludingPlacement += currentPalletHeightIncludingPlacement;
                totalPalletWeightIncludingPlacement += currentPalletWeightIncludingPlacement;
            }
        }
        
        totalHeightForShipment = ((float)totalPalletHeightIncludingPlacement / 1000); // In meters
        totalWeightForShipment = totalPalletWeightIncludingPlacement; // In kilograms
        totalLoadingMetersForShipment = Math.round(totalLoadingMeters * (totalHeightForShipment / t.getHeight()));
        
        // Calculate stops
        int stops = amountOfStops(c, s);
        
        // Calculate kilometers
        float kilometers = 0f;
        
        Collections.sort(pallets, new CustomerIDSorter());
        
        Septet septet = new Septet(totalPalletsForShipment, totalHeightForShipment, totalWeightForShipment, totalLoadingMetersForShipment, pallets, stops, kilometers);
        
        return septet;
    }
    
    /**
     * Calculates the total cost for a trip.
     * @param stops amount of stops.
     * @param kilometers the kilometers.
     * @return the total cost for a trip.
     */
    public float calculateTotalCost(int stops, float kilometers)
    {
        int stoppingCosts = 90;
        float kilometerCosts = 1.10f;
        System.out.println("stops:" + stops + ", kms: " + kilometers);
        return (stops * stoppingCosts) + (kilometers * kilometerCosts);
    }
    
    /**
     * Gets the geolocation for a specific address.
     * @param geoStart geolocation of start address.
     * @param geoEnd geolocation of end address.
     * @return total distance.
     */
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
        
        float distance = handleRequest(request);
        
        return distance;
    }
    
    /**
     * Handles the json of given request.
     * @param request url containing API request.
     */
    public float handleRequest(String request)
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
     * Gets a list of ShippingPoints.
     * @return list of ShippingPoints.
     */
    public List<ShippingPoint> fillComboBoxSP()
    {
        return database.getShippingPoints();
    }
    
    /**
     * Gets a list of Trucks.
     * @return list of trucks.
     */
    public List<Truck> fillComboBoxTruck()
    {
        return database.getTrucks();
    }
    
    /**
     * Gets a list of Countries.
     * @return list of countries.
     */
    public List<Country> fillComboBoxCountry()
    {
        return database.getCountries();
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
}
