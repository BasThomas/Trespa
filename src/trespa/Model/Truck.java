/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Model;

/**
 *
 * @author Bas
 */
public class Truck
{
    private int truckID;
    private float maxWeight;
    private float length;
    private float height;
    
    public Truck(int truckID, float maxWeight, float length, float height)
    {
        this.truckID = truckID;
        this.maxWeight = maxWeight;
        this.length = length;
        this.height = height;
    }
    
    @Override
    public String toString()
    {
        return "[" + truckID + "] mW: " + maxWeight + "t, l: " + length + "m, h: " + height + "m";
    }

    /**
     * @return the truckID
     */
    public int getTruckID()
    {
        return truckID;
    }

    /**
     * @param truckID the truckID to set
     */
    public void setTruckID(int truckID)
    {
        this.truckID = truckID;
    }

    /**
     * @return the maxWeight
     */
    public float getMaxWeight()
    {
        return maxWeight;
    }

    /**
     * @param maxWeight the maxWeight to set
     */
    public void setMaxWeight(float maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    /**
     * @return the length
     */
    public float getLength()
    {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(float length)
    {
        this.length = length;
    }

    /**
     * @return the height
     */
    public float getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height)
    {
        this.height = height;
    }
}
