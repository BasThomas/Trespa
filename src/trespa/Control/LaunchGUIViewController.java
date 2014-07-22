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
        
        Pair pair = new Pair(amountOfStops, amountOfPlacements);
        
        return pair;
    }
    
    public Quintet totalForShipment(Country c, ShippingPoint s)
    {
        int totalPalletsForShipment = 0;
        float totalHeightForShipment;
        float totalWeightForShipment;
        float totalLoadingMetersForShipment = 0;
        
        List<Placement> placements = this.possiblePlacementsForRoute(c, s);
        
        int totalPalletHeight = 0;
        int totalPlacementHeight = 0;
        int totalPalletWeight = 0;
        int totalPlacementWeight = 0;
        
        int currentPalletsForPlacement;
        float currentPalletWeightIncludingPlacement = 0.0f;
        float currentPalletHeightIncludingPlacement = 0.0f;
        
        int currentQuantity;
        int currentPanelsPer;
        
        List<LoadedPallet> pallets = new ArrayList<>();
        
        // Might change!
        int palletWeight = 500;
        
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
            
            totalPalletHeight += p.getPalletHeight();
            totalPlacementHeight += (p.getThickness() * currentQuantity);
            totalPalletWeight += (currentPalletsForPlacement * palletWeight);
            totalPlacementWeight += p.getGrossWeight();
            
            for(int pallet = 0; pallet < currentPalletsForPlacement; pallet++)
            {
                // TODO
                currentPalletWeightIncludingPlacement = 1f;
                
                if(currentQuantity > currentPanelsPer)
                {
                    currentPalletHeightIncludingPlacement = p.getPalletHeight() + (p.getThickness() * currentPanelsPer);
                    currentQuantity -= currentPanelsPer;
                }
                else
                {
                    currentPalletHeightIncludingPlacement = p.getPalletHeight() + (p.getThickness() * currentQuantity);
                }
                
                pallets.add(new LoadedPallet(currentPalletWeightIncludingPlacement, currentPalletHeightIncludingPlacement));
            }
        }
        
        totalWeightForShipment = totalPlacementWeight + ((float)totalPalletWeight / 1000);
        totalHeightForShipment = (((float)totalPalletHeight + (float)totalPlacementHeight) / 1000);
        totalLoadingMetersForShipment = 1;
        
        Quintet quintet = new Quintet(totalPalletsForShipment, totalHeightForShipment, totalWeightForShipment, totalLoadingMetersForShipment, pallets);
        
        return quintet;
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
