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
    private String countryAbbr;
    
    /**
     * Constructor.
     * @param shippingID The ShippingPoint's ID.
     * @param shippingPoint String representation of the ShippingPoint.
     * @param postalCode String representation of the ShippingPoint's postal code.
     */
    public ShippingPoint(int shippingID, String shippingPoint, String postalCode, String countryAbbr)
    {
        this.shippingID = shippingID;
        this.shippingPoint = shippingPoint;
        this.postalCode = postalCode;
        this.countryAbbr = countryAbbr;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
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

    /**
     * @return the countryAbbr
     */
    public String getCountryAbbr()
    {
        return countryAbbr;
    }

    /**
     * @param countryAbbr the countryAbbr to set
     */
    public void setCountryAbbr(String countryAbbr)
    {
        this.countryAbbr = countryAbbr;
    }
}
