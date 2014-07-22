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
public class ShippingPoint
{
    private int shippingID;
    private String shippingPoint;
    private String postalCode;
    
    public ShippingPoint(int shippingID, String shippingPoint, String postalCode)
    {
        this.shippingID = shippingID;
        this.shippingPoint = shippingPoint;
        this.postalCode = postalCode;
    }
    
    @Override
    public String toString()
    {
        return "[" + shippingID + "] " + shippingPoint + " (" + postalCode +  ")";
    }

    /**
     * @return the shippingID
     */
    public int getShippingID()
    {
        return shippingID;
    }

    /**
     * @param shippingID the shippingID to set
     */
    public void setShippingID(int shippingID)
    {
        this.shippingID = shippingID;
    }

    /**
     * @return the shippingPoint
     */
    public String getShippingPoint()
    {
        return shippingPoint;
    }

    /**
     * @param shippingPoint the shippingPoint to set
     */
    public void setShippingPoint(String shippingPoint)
    {
        this.shippingPoint = shippingPoint;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
}
