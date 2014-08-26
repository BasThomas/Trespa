/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Model;

import java.util.Comparator;

/**
 *
 * @author Bas
 */
public class CustomerIDSorter implements Comparator<LoadedPallet>
{
    @Override
    public int compare(LoadedPallet o1, LoadedPallet o2)
    {
        if (o1.getCustomer().getCustomerID() < o2.getCustomer().getCustomerID())
        {
            return -1;
        }
        else if (o1.getCustomer().getCustomerID() > o2.getCustomer().getCustomerID())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
