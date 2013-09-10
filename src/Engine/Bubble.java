/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Brandon
 */
public class Bubble extends Physical implements Drawable {

    private java.util.Random _rand;
    
    private java.awt.Color _color = java.awt.Color.blue;
    
    //private float _velocity.Y = 0.0001F;
    //private float _velocity.X = 0.0001F;

    
    
    private Rectangle2D.Float _bounds;
    
    private Engine _e;
    
    public Bubble(Engine e)
    {
        _e = e;
        
        _size = new Dimension2D(20F, 20F);
        
        _location.X = 25F;
        _location.Y = 25F;
        
        _rand = new java.util.Random();
        _rand.setSeed(this.hashCode());
        
        //_velocity.Y *= _rand.nextFloat(); 
        //_velocity.X *= _rand.nextFloat();
        
        _velocity = new Vector2D(50F * _rand.nextFloat(), 50F * _rand.nextFloat());
        
        _color = new java.awt.Color(_rand.nextFloat(), _rand.nextFloat(), _rand.nextFloat());
        
    }
    
    public Bubble(Engine e, Vector2D location){
        
         _e = e;
        
        _size = new Dimension2D(20F, 20F);
      
        _rand = new java.util.Random();
        _rand.setSeed(this.hashCode());
        
        //_velocity.Y *= _rand.nextFloat(); 
        //_velocity.X *= _rand.nextFloat();

        _velocity = new Vector2D(25F * _rand.nextFloat(), 25F * _rand.nextFloat());
        
        _material = Physics.ObjectMaterial.Rubber;
        
        _location = location;
        _color = new java.awt.Color(_rand.nextFloat(), _rand.nextFloat(), _rand.nextFloat());
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(_color);
        g.drawOval((int)_location.X, (int)_location.Y, (int)_size.Width, (int)_size.Height);
        
    }

     @Override
    public void setColor(java.awt.Color g) {
         
         
        _color = g;
        
    }
     
     
    @Override
    public void update(float milliseconds) {
        
        
        
        if(this._location.Y > _e._window.getHeight() - _size.Height)
        {
            _velocity.Y = -Math.abs(_velocity.Y * .60F);
        }
        else if(this._location.Y < 0 )
        {
            _velocity.Y = Math.abs(_velocity.Y);
        }
        
        if(this._location.X > _e._window.getWidth() - _size.Width)
        {
            _velocity.X = -Math.abs(_velocity.X);
        }
        else if(this._location.X < 0)
        {
            _velocity.X = Math.abs(_velocity.X);
        }
        
        this._location.Y += (_velocity.Y * (milliseconds / 1000F));
        this._location.X += (_velocity.X * (milliseconds / 1000F));
        //System.out.println("Updated!:" + milliseconds);
        
    }

    @Override
    public Dimension2D getSize() {
        return _size;
        
    }
    
    @Override
    public Vector2D getLocation() {
        
        return _location;
        
    }

    
}
