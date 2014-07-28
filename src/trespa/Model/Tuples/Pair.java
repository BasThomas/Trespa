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
public class Pair<A, B>
{
    public final A a;
    public final B b;
    
    /**
     * Constructor.
     * @param a first value in Pair.
     * @param b second value in Pair.
     */
    public Pair(A a, B b)
    {
        this.a = a;
        this.b = b;
    }
}
