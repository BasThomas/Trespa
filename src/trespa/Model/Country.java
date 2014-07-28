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
public class Country
{
    private String country;
    private String countryAbbr;
    private String regionDesc;
    private Boolean hasRegionDesc;
    
    /**
     * Constructor.
     * @param country String representation of Country.
     * @param countryAbbr String representation of Country, shortened.
     * @param regionDesc String representation of the regionDescription.
     */
    public Country(String country, String countryAbbr, String regionDesc)
    {
        this.country = country;
        this.countryAbbr = countryAbbr;
        this.regionDesc = regionDesc;
        
        this.hasRegionDesc = true;
    }
    
    /**
     * Constructor.
     * @param country String representation of Country.
     * @param countryAbbr String representation of Country, shortened.
     */
    public Country(String country, String countryAbbr)
    {
        this.country = country;
        this.countryAbbr = countryAbbr;
        this.regionDesc = countryAbbr + "/UNKNOWN";
        
        this.hasRegionDesc = false;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        if (hasRegionDesc)
        {
            return country + " (" + countryAbbr + ") - " + regionDesc;
        }
        else
        {
            return country + " (" + countryAbbr + ")";
        }
    }

    /**
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country)
    {
        this.country = country;
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
     * @return the countryDesc
     */
    public String getRegionDesc()
    {
        return regionDesc;
    }

    /**
     * @param regionDesc the regionDesc to set
     */
    public void setRegionDesc(String regionDesc)
    {
        this.regionDesc = regionDesc;
    }

    /**
     * @return the hasRegionDesc
     */
    public Boolean getHasRegionDesc()
    {
        return hasRegionDesc;
    }

    /**
     * @param hasRegionDesc the hasRegionDesc to set
     */
    public void setHasRegionDesc(Boolean hasRegionDesc)
    {
        this.hasRegionDesc = hasRegionDesc;
    }
}
