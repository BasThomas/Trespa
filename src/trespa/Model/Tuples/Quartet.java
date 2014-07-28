/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trespa.Model.Tuples;

/**
 *
 * @author Bas
 */
public class Quartet<A, B, C, D>
{
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    
    /**
     * Constructor.
     * @param a first value in quartet.
     * @param b second value in quartet.
     * @param c third value in quartet.
     * @param d fourth value in quartet.
     */
    public Quartet(A a, B b, C c, D d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}