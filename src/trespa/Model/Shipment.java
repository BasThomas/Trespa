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
    
    public Shipment(int shipmentID, List<Placement> placements)
    {
        this.shipmentID = shipmentID;
        this.placements = placements;
    }
    
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
