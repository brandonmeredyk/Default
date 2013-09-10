/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/**
 *
 * @author Brandon
 */
public class Rope extends Physical implements Drawable{

    public Bubble[] _bubs;
 
    public void Rope(){
        
        _bubs = new Bubble[10];
        
        _location = new Vector2D(100F, 100F); //Anchor
        
    }
    
    
    public void SolveRope(){
        
        
        
    
    }
    
    @Override
    public void draw(Graphics2D g) {
    
        
    
    }

    
    @Override
    public Vector2D getVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(float milliseconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension2D getSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    @Override
    public void setColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
