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
public class Septet<A, B, C, D, E, F, G>
{
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    public final E e;
    public final F f;
    public final G g;
    
    /**
     * Constructor.
     * @param a first value in Septet.
     * @param b second value in Septet.
     * @param c third value in Septet.
     * @param d fourth value in Septet.
     * @param e fifth value in Septet.
     * @param f sixth value in Septet.
     * @param g seventh value in Septet.
     */
    public Septet(A a, B b, C c, D d, E e, F f, G g)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
}
