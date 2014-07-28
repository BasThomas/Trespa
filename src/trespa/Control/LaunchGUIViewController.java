/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.util.ArrayList;
import java.util.List;
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
     * @return Quintet containing total pallets for shipment,
     *          total height for shipment,
     *          total weight for shipment,
     *          total loading meters for shipment and
     *          the amount of pallets.
     */
    public Quintet totalForShipment(Country c, ShippingPoint s, Truck t)
    {
        int totalPalletsForShipment = 0;
        float totalHeightForShipment;
        float totalWeightForShipment;
        int totalLoadingMetersForShipment;
        
        List<Placement> placements = this.possiblePlacementsForRoute(c, s);
        
        int totalPalletHeightIncludingPlacement = 0;
        int totalPalletWeightIncludingPlacement = 0;
        
        int currentPalletsForPlacement;
        float currentPalletWeightIncludingPlacement;
        float currentPalletHeightIncludingPlacement;
        
        int currentQuantity;
        int currentPanelsPer;
        
        List<LoadedPallet> pallets = new ArrayList<>();
        
        // Might change!
        int palletWeight = 40; // In kilograms
        int palletLength = 7; // In meters
        
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
                }
                else
                {
                    currentPalletHeightIncludingPlacement = p.getPalletHeight() + (p.getThickness() * currentQuantity);
                    currentPalletWeightIncludingPlacement = palletWeight + (currentQuantity * p.getGrossWeight());
                }
                
                pallets.add(new LoadedPallet(currentPalletWeightIncludingPlacement, currentPalletHeightIncludingPlacement));
                
                totalPalletHeightIncludingPlacement += currentPalletHeightIncludingPlacement;
                totalPalletWeightIncludingPlacement += currentPalletWeightIncludingPlacement;
            }
        }
        
        totalHeightForShipment = ((float)totalPalletHeightIncludingPlacement / 1000); // In meters
        totalWeightForShipment = totalPalletWeightIncludingPlacement; // In kilograms
        totalLoadingMetersForShipment = Math.round(palletLength * (totalHeightForShipment / t.getHeight()));
        
        Quintet quintet = new Quintet(totalPalletsForShipment, totalHeightForShipment, totalWeightForShipment, totalLoadingMetersForShipment, pallets);
        
        return quintet;
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
}
