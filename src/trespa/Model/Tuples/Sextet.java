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
public class Sextet<A, B, C, D, E, F>
{
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    public final E e;
    public final F f;
    
    /**
     * Constructor.
     * @param a first value in Sextet.
     * @param b second value in Sextet.
     * @param c third value in Sextet.
     * @param d fourth value in Sextet.
     * @param e fifth value in Sextet.
     * @param f sixth value in Sextet.
     */
    public Sextet(A a, B b, C c, D d, E e, F f)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
}
