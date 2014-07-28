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
public class Productline
{
    private String productline;
    
    /**
     * Constructor.
     * @param productline String representation of the productline.
     */
    public Productline(String productline)
    {
        this.productline = productline;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        return productline;
    }

    /**
     * @return the productline
     */
    public String getProductline()
    {
        return productline;
    }

    /**
     * @param productline the productline to set
     */
    public void setProductline(String productline)
    {
        this.productline = productline;
    }
}
