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
public class Triplet<A, B, C>
{
    public final A a;
    public final B b;
    public final C c;
    
    public Triplet(A a, B b, C c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
