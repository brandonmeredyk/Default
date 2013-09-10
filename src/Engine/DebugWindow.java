/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.text.*;

/**
 *
 * @author Brandon
 */
public class DebugWindow implements Drawable{

    private Engine _engine;
    
    private Dimension2D _size;
    
    private double _currentFPS = 1.0;
    
    private float AveFPSUpdateTime = 1000;
    
    private DecimalFormat df = new DecimalFormat("0.00");
    
    public DebugWindow(Engine engine)
    {
        _engine = engine;
        
        Dimension d = _engine.getViewPortSize();
        
        _size = new Dimension2D(d.width, d.height);
        
        
        
    }
    
    
    private static final int MAXSAMPLES = 100;
    private int tickindex = 0;
    float ticksum = 0;
    float[] ticklist = new float[MAXSAMPLES];
    

    private double CalcAverageTick(float newtick)
    {
        ticksum -= ticklist[tickindex];  
        ticksum += newtick;             
        ticklist[tickindex] = newtick;  
        if(++tickindex==MAXSAMPLES)    
            tickindex=0;

        return((double)ticksum/MAXSAMPLES);
    }

    
    
    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.BLACK);
        
        g.fillRect(0, 0, (int)_size.Width, (int)_size.Height / 3);
                
        g.setColor(Color.red);

        //g.drawLine(0, 0, _size.width, _size.height / 3);
        g.drawString("Debug Console", _size.Width - 100, (_size.Height / 3)- 10 );
        
        g.drawString("FPS: " + df.format(_currentFPS), _size.Width - 100, (_size.Height / 3)- 50 );
        
    }

    @Override
    public void update(float milliseconds) {

          if (AveFPSUpdateTime < 0) 
            { 
                _currentFPS = 1000F / CalcAverageTick(milliseconds);
                AveFPSUpdateTime = 1000;
            }
            else
            {
                
                CalcAverageTick(milliseconds);
                AveFPSUpdateTime -= milliseconds;
            }
        
        
  
    }
    
    @Override
    public Vector2D getLocation() {
        
        return null;
        
    }
    
    
    
    public Dimension2D getSize() {
        return _size;
    
    }
    
    
}
