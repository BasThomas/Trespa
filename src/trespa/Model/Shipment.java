/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Model;

import java.util.List;

/**
 *
 * @author Bas
 */
public class Shipment
{
    private int shipmentID;
    private List<Placement> placements;
    
    /**
     * Constructor.
     * @param shipmentID The Shipment's ID.
     * @param placements List of Placements in Shipment.
     */
    public Shipment(int shipmentID, List<Placement> placements)
    {
        this.shipmentID = shipmentID;
        this.placements = placements;
    }
    
    /**
     * Gets a String representation of the instance.
     * @return String representation of the instance.
     */
    @Override
    public String toString()
    {
        return "[" + shipmentID + "] ";
    }

    /**
     * @return the shipmentID
     */
    public int getShipmentID()
    {
        return shipmentID;
    }

    /**
     * @param shipmentID the shipmentID to set
     */
    public void setShipmentID(int shipmentID)
    {
        this.shipmentID = shipmentID;
    }

    /**
     * @return the placements
     */
    public List<Placement> getPlacements()
    {
        return placements;
    }

    /**
     * @param placements the placements to set
     */
    public void setPlacements(List<Placement> placements)
    {
        this.placements = placements;
    }
}
