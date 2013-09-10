/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.*;

/**
 *
 * @author Brandon
 */
public interface Drawable {

    public void draw(Graphics2D g);
    
    public void update(float milliseconds);
    
    public Dimension2D getSize();
    
    public Vector2D getLocation();
    
}
