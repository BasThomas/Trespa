/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trespa.Model;

import java.sql.Date;

/**
 *
 * @author Bas
 */
public class Placement
{
    private int placementID;
    private int deliveryItem;
    private Customer customer;
    private Date issueDate;
    private Productline productline;
    private float thickness;
    private int width;
    private int length;
    private int quantity;
    private int panelsPer;
    private float grossWeight;
    private int palletHeight;
    private int material;
    
    /**
     * Constructor.
     * @param placementID The Placement's ID.
     * @param deliveryItem The Placement's deliveryItem.
     * @param customer The Placement's Customer.
     * @param issueDate The Placement's issueDate.
     * @param productline The Placement's productline.
     * @param thickness The Placement's thickness per panel.
     * @param width The Placement's width per panel.
     * @param length The Placement's length per panel.
     * @param quantity The Placement's panel quantity.
     * @param panelsPer The Placement's pallet max. panels (Pallet???)
     * @param grossWeight The Placement's gross weight per pallet.
     * @param palletHeight The Placement's palletheight.
     * @param material The Placement's panel material.
     */
    public Placement(int placementID, int deliveryItem, Customer customer, Date issueDate, Productline productline, float thickness, int width, int length, int quantity, int panelsPer, float grossWeight, int palletHeight, int material)
    {
        this.placementID = placementID;
        this.deliveryItem = deliveryItem;
        this.customer = customer;
        this.issueDate = issueDate;
        this.productline = productline;
        this.thickness = thickness;
        this.width = width;
        this.length = length;
        this.quantity = quantity;
        this.panelsPer = panelsPer;
        this.grossWeight = grossWeight;
        this.palletHeight = palletHeight;
        this.material = material;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        return issueDate + " | (" + length + "x" + width + "x" + thickness + ") * " + quantity + " on " + panelsPer + "p.p.; weighs " + grossWeight + "kg | [" + placementID + " / " + deliveryItem + "] by " + customer.toString() + " on " + productline.toString() + " (material: " + material + ")";
    }

    /**
     * @return the placementID
     */
    public int getPlacementID()
    {
        return placementID;
    }

    /**
     * @param placementID the placementID to set
     */
    public void setPlacementID(int placementID)
    {
        this.placementID = placementID;
    }

    /**
     * @return the deliveryItem
     */
    public int getDeliveryItem()
    {
        return deliveryItem;
    }

    /**
     * @param deliveryItem the deliveryItem to set
     */
    public void setDeliveryItem(int deliveryItem)
    {
        this.deliveryItem = deliveryItem;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     * @return the issueDate
     */
    public Date getIssueDate()
    {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(Date issueDate)
    {
        this.issueDate = issueDate;
    }

    /**
     * @return the productline
     */
    public Productline getProductline()
    {
        return productline;
    }

    /**
     * @param productline the productline to set
     */
    public void setProductline(Productline productline)
    {
        this.productline = productline;
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
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
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

    /**
     * @return the grossWeight
     */
    public float getGrossWeight()
    {
        return grossWeight;
    }

    /**
     * @param grossWeight the grossWeight to set
     */
    public void setGrossWeight(float grossWeight)
    {
        this.grossWeight = grossWeight;
    }

    /**
     * @return the material
     */
    public int getMaterial()
    {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(int material)
    {
        this.material = material;
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * @return the panelsPer
     */
    public int getPanelsPer()
    {
        return panelsPer;
    }

    /**
     * @param panelsPer the panelsPer to set
     */
    public void setPanelsPer(int panelsPer)
    {
        this.panelsPer = panelsPer;
    }

    /**
     * @return the palletHeight
     */
    public int getPalletHeight()
    {
        return palletHeight;
    }

    /**
     * @param palletHeight the palletHeight to set
     */
    public void setPalletHeight(int palletHeight)
    {
        this.palletHeight = palletHeight;
    }
}