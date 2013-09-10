/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Brandon
 */
public class PhysicsConstraint {
    
    public static enum ConstraintType{
        TRANSISITIONAL,
        ROTATIONAL
    } 
    
    
    private ConstraintType _type;
    
    private Physical _otherObject;
    
    public PhysicsConstraint(ConstraintType type, Physical otherObject){
    
        _type = type;
        
        _otherObject = otherObject;
    
    }
    
    public Vector2D getImpulse()
    {
        return new Vector2D();
    
    }
    
}
