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
    private String latitude;
    private String longitude;
    
    /**
     * Constructor.
     * @param shippingID The ShippingPoint's ID.
     * @param shippingPoint String representation of the ShippingPoint.
     * @param postalCode String representation of the ShippingPoint's postal code.
     * @param countryAbbr The countryAbbr of the ShippingPoint
     * @param latitude The latitude value of the ShippingPoint.
     * @param longitude The longitude value of the ShippingPoint.
     */
    public ShippingPoint(int shippingID, String shippingPoint, String postalCode, String countryAbbr, String latitude, String longitude)
    {
        this.shippingID = shippingID;
        this.shippingPoint = shippingPoint;
        this.postalCode = postalCode;
        this.countryAbbr = countryAbbr;
        this.latitude = latitude;
        this.longitude = longitude;
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

    /**
     * @return the latitude
     */
    public String getLatitude()
    {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude()
    {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
}
