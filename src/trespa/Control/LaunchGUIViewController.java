/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.util.List;
import trespa.Model.*;
import trespa.Model.Tuples.*;

/**
 *
 * @author Bas
 */
public class LaunchGUIViewController
{
    private Database database;
    
    public LaunchGUIViewController()
    {
        database = new Database();
    }
    
    public int amountOfStops(Country country, ShippingPoint shippingPoint)
    {
        return database.amountOfStopsForRoute(country, shippingPoint);
    }
    
    public List<Placement> possiblePlacementsForRoute(Country country, ShippingPoint shippingPoint)
    {
        return database.getPossiblePlacementsForRoute(country, shippingPoint);
    }
    
    public Pair stopsAndPlacementAmount(Country c, ShippingPoint s)
    {
        int amountOfStops = this.amountOfStops(c, s);
        int amountOfPlacements = this.possiblePlacementsForRoute(c, s).size();
        
        return new Pair(amountOfStops, amountOfPlacements);
    }
    
    public Triplet totalForShipment(Country c, ShippingPoint s)
    {
        int totalPalletsForShipment = 0;
        float totalHeightForShipment = 0;
        float totalWeightForShipment = 0;
        
        List<Placement> placements = this.possiblePlacementsForRoute(c, s);
        
        int totalPalletHeight = 0;
        int totalPlacementHeight = 0;
        int totalPalletWeight = 0;
        int totalPlacementWeight = 0;
        
        int currentPalletsForPlacement;
        
        // Might change!
        int palletWeight = 500;
        
        for(Placement p : placements)
        {
            if((p.getQuantity() % p.getPanelsPer()) == 0)
            {
                currentPalletsForPlacement = (p.getQuantity() / p.getPanelsPer());
                totalPalletsForShipment += currentPalletsForPlacement;
            }
            else
            {
                currentPalletsForPlacement = (p.getQuantity() / p.getPanelsPer() + 1);
                totalPalletsForShipment += currentPalletsForPlacement;
            }
            
            totalPalletHeight += p.getPalletHeight();
            totalPlacementHeight += (p.getThickness() * currentPalletsForPlacement);
            totalPalletWeight += (currentPalletsForPlacement * palletWeight);
            totalPlacementWeight += p.getGrossWeight();
        }
        
        totalWeightForShipment = totalPlacementWeight + ((float)totalPalletWeight / 1000);
        totalHeightForShipment = (((float)totalPalletHeight + (float)totalPlacementHeight) / 1000);
        
        return new Triplet(totalPalletsForShipment, totalHeightForShipment, totalWeightForShipment);
    }
    
    public List<ShippingPoint> fillComboBoxSP()
    {
        return database.getShippingPoints();
    }
    
    public List<Truck> fillComboBoxTruck()
    {
        return database.getTrucks();
    }
    
    public List<Country> fillComboBoxCountry()
    {
        return database.getCountries();
    }
}
