/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.*;
import javax.swing.JFrame;


/**
 *
 * @author Brandon
 */
public class ViewPort extends java.awt.Window{

    
    private Engine _engine;
    
    
    
    public ViewPort(Frame frame, Engine engine){
        super(frame);
        
        _engine = engine;
        
        //this.setSize(frame.getSize());

        this.setLocation(frame.getLocation().x + 50, frame.getLocation().y + 50);
        
    }
    
    /*
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        //_engine.paint(g);
    }
    
    */
    
    
    
    
    
}
