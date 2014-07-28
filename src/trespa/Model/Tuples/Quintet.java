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
public class Quintet<A, B, C, D, E>
{
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    public final E e;
    
    /**
     * Constructor.
     * @param a first value in Quintet.
     * @param b second value in Quintet.
     * @param c third value in Quintet.
     * @param d fourth value in Quintet.
     * @param e fifth value in Quintet.
     */
    public Quintet(A a, B b, C c, D d, E e)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
}
