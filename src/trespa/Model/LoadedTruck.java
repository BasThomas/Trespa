/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trespa.Model;

import java.util.List;

/**
 *
 * @author Bas
 */
public class LoadedTruck extends Truck
{
    private List<LoadedPallet> pallets;
    
    public LoadedTruck(int truckID, float maxWeight, float length, float height, List<LoadedPallet> pallets)
    {
        super(truckID, maxWeight, length, height);
        this.pallets = pallets;
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }

    /**
     * @return the pallets
     */
    public List<LoadedPallet> getPallets()
    {
        return pallets;
    }

    /**
     * @param pallets the pallets to set
     */
    public void setPallets(List<LoadedPallet> pallets)
    {
        this.pallets = pallets;
    }
}
