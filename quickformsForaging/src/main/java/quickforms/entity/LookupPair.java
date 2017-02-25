/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quickforms.entity;

/**
 *
 * @author achamney
 */
public class LookupPair {
    public String left;
    public String right;
    public String extra;
    public LookupPair(String left, String right, String extra)
    {
        this.left = left;
        this.right = right;
        this.extra = extra;
    }
    public LookupPair(String left, String right)
    {
        this.left = left;
        this.right = right;
    }
    public LookupPair()
    {
        
    }
}
