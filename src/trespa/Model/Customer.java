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
public class Customer
{
    private int customerID;
    private String location;
    private String postalCode;
    private Country country;
    
    /**
     * Constructor.
     * @param customerID the Customer's ID.
     * @param location String representation of the Customer's location.
     * @param postalCode String representation of the Customer's postal code.
     * @param country Country-object where customer is located.
     */
    public Customer(int customerID, String location, String postalCode, Country country)
    {
        this.customerID = customerID;
        this.location = location;
        this.postalCode = postalCode;
        this.country = country;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        if(getCountry().getRegionDesc() != null)
        {
            return "[" + getCustomerID() + "] " + getLocation() + ", " + getCountry().getRegionDesc() + " (" + getPostalCode() + ", " + getCountry().getCountry() + ")";
        }
        else
        {
            return "[" + getCustomerID() + "] " + getLocation() + " (" + getPostalCode() + ", " + getCountry().getCountry() + ")";
        }
    }

    /**
     * @return the customerID
     */
    public int getCustomerID()
    {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
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
     * @return the country
     */
    public Country getCountry()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country)
    {
        this.country = country;
    }
}
