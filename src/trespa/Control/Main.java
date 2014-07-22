/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Control;

import java.util.List;
import trespa.Model.*;
import trespa.View.*;

/**
 *
 * @author Bas
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LaunchGUI gui = new LaunchGUI();
        gui.setVisible(true);
        
        //printTest();
    }
    
    public static void printTest()
    {
        Database database = new Database();
        
        List<ShippingPoint> shippingPoints = database.getShippingPoints();
        List<Customer> customers = database.getCustomers();
        List<Truck> trucks = database.getTrucks();
        List<Placement> placements = database.getPossiblePlacementsForRoute(null, null);
        
        System.out.printf("\n#### Shipping points ####\n");
        
        for(ShippingPoint sp : shippingPoints)
        {
            System.out.printf(sp.toString() + "\n");
        }
        
        System.out.printf("\n#### Customers ####\n");
        
        for(Customer c : customers)
        {
            System.out.printf(c.toString() + "\n");
        }
        
        System.out.printf("\n#### Trucks ####\n");
        
        for(Truck t : trucks)
        {
            System.out.printf(t.toString() + "\n");
        }
        
        System.out.printf("\n#### Placements ####\n");
        
        for(Placement p : placements)
        {
            System.out.printf(p.toString() + "\n");
        }
    }
}
