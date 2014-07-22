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
public class Pallet
{
    private String packCode;
    private int panelsPer;
    
    public Pallet(String packCode, int panelsPer)
    {
        this.packCode = packCode;
        this.panelsPer = panelsPer;
    }
    
    @Override
    public String toString()
    {
        return "[" + packCode + "] holds max." + panelsPer +  "panels.";
    }

    /**
     * @return the packCode
     */
    public String getPackCode()
    {
        return packCode;
    }

    /**
     * @param packCode the packCode to set
     */
    public void setPackCode(String packCode)
    {
        this.packCode = packCode;
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
}
