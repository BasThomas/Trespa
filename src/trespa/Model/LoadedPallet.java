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
    
    public LoadedPallet(float weight, float height)
    {
        this.weight = weight;
        this.height = height;
    }
    
    @Override
    public String toString()
    {
        return "Pallet withWeight: " + weight + ", withHeight: " + height;
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
}
