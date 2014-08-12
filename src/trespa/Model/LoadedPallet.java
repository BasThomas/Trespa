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
public class LoadedPallet
{
    private float weight;
    private float height;
    private int amount;
    private float thickness;
    private int length;
    
    /**
     * Constructor.
     * @param weight Weight of the pallet.
     * @param height Height of the pallet.
     * @param amount Amount of panels on pallet.
     * @param thickness Thickness of panel.
     * @param length Length of panel.
     */
    public LoadedPallet(float weight, float height, int amount, float thickness, int length)
    {
        this.weight = weight;
        this.height = height;
        this.amount = amount;
        this.thickness = thickness;
        this.length = length;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        if (this.amount < 10)
        {
            return String.format("0%d - %.1fmm - %dmm - %.3fmm - %.1fmm", amount, thickness, length, weight, height);
        }
        
        return String.format("%d - %.1fmm - %dmm - %.3fmm - %.1fmm", amount, thickness, length, weight, height);
    }

    /**
     * @return the weight
     */
    public float getWeight()
    {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight)
    {
        this.weight = weight;
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

    /**
     * @return the amount
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    /**
     * @return the thickness
     */
    public float getThickness()
    {
        return thickness;
    }

    /**
     * @param thickness the thickness to set
     */
    public void setThickness(float thickness)
    {
        this.thickness = thickness;
    }

    /**
     * @return the length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length)
    {
        this.length = length;
    }
}
