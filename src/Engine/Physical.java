/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.Color;

/**
 *
 * @author Brandon
 */
public abstract class Physical {
    
    float _mass = 1.0F;

    Vector2D _location = new Vector2D();
    
    Vector2D _velocity = new Vector2D();
    
    Dimension2D _size = new Dimension2D();
    
    java.util.HashSet<PhysicsConstraint> constraints = new java.util.HashSet<>();
    
    public boolean checked = false;             //If this object has been checked against every other object
    
    public Physics.ObjectMaterial _material;    // Object Material, eg. Rubber, Glue, Sticks to you... 
 
    public void setVelocity(Vector2D vect){
        
        if(vect.X > Physics.MAXSPEED){
            
            vect.X = Physics.MAXSPEED;
            
        }
        else if (vect.X < -Physics.MAXSPEED)
        {
            vect.X = -Physics.MAXSPEED;
            
        }

        if(vect.Y > Physics.MAXSPEED){
            
            vect.Y = Physics.MAXSPEED;
            
        }
        else if(vect.Y < -Physics.MAXSPEED){
            vect.Y = -Physics.MAXSPEED;
            
        }
        
        _velocity = vect;
        
    }

    public Vector2D getVelocity(){
        
        return _velocity;
        
    }
    
    public void SolveConstraints(float milliseconds){
    
        for(PhysicsConstraint pc : constraints)
        {
            
            
            
            
        }
        
    
    }
    
    public Vector2D getLocation(){
        
        return _location;
    }
    
    public abstract void setColor(java.awt.Color color);
    
    public void ApplyImpulse(Vector2D vect)
    {
        this.setVelocity(_velocity.Add(vect));
    }
    
    public void DrawHash(java.awt.Graphics2D g)
    {
        float hashSize = 5F;
        
        g.drawLine( (int)((_location.X + (_size.Width / 2)) - hashSize), (int)(_location.Y + (_size.Height / 2)) , (int)((_location.X + (_size.Width / 2)) + hashSize), (int)(_location.Y + (_size.Height / 2)));
        g.drawLine( (int)(_location.X + (_size.Width / 2)), (int)((_location.Y + (_size.Height / 2)) - hashSize) , (int)(_location.X + (_size.Width / 2)), (int)((_location.Y + (_size.Height / 2)) + hashSize));
        
    }
    
    public void DrawVelocityVector(java.awt.Graphics2D g){
        
        g.setColor(Color.green);
        
        Vector2D unitVelocity = _velocity.GetUnit();
        
        this.DrawHash(g);
        
        if(_velocity.Magnitude() > .001F)
        {
            g.drawLine((int)(_location.X + (.5 * _size.Width)), (int)(_location.Y + (.5 * _size.Height)), (int) (_location.X + (unitVelocity.X * 25F)), (int)(_location.Y + (unitVelocity.Y * 25F)));
        }
        //g.drawString("Mag: "  + _velocity.Magnitude(), _location.X, _location.Y);
        
    }
    
}
