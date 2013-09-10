/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.util.HashSet;

/**
 *
 * @author Brandon
 */
public class Physics {
    
    private Vector2D _gravityVect = new Vector2D(0F, .1F);
    
    private HashSet<Physical> _physicsThings = new HashSet<>();

    public static final float MAXSPEED = 800F;
    
    public boolean SLEEPIFSTATIC = true;
    
    public static final float RESTMAXVELOCITY = 1F;
    
    public enum ObjectMaterial{
        Rubber, Metal, Glue
    }
    
    public float Elasticity(Physical obj1, Physical obj2){
        
        ObjectMaterial mat1 = obj1._material;
        ObjectMaterial mat2 = obj2._material;
        
        if(mat1 == ObjectMaterial.Metal && mat2 == ObjectMaterial.Metal )
        {
            return 1.4F;
        }
        else if (mat1 == ObjectMaterial.Rubber && mat2 == ObjectMaterial.Rubber)
        {
            return 1.0F;
            
        }
        else if (mat1 == ObjectMaterial.Glue && mat2 == ObjectMaterial.Glue)
        {
            return 2F;
            
        }
        else
        {
            return 1F;
        }
    }
    
    
    public Physics(){
    
    
    
    
    
    }

    //Move this out of here some time soon
    public void update(float milliseconds)
    {
        ApplyGravity();
        
        float distance;
        
        for(Physical d: _physicsThings)
        {
            d.checked = false;
        }
        
        for(Physical d : _physicsThings)
        {
            if(d._velocity.Magnitude() < RESTMAXVELOCITY && SLEEPIFSTATIC)
            {
                continue;
            }
            
            for(Physical p : _physicsThings)
            {
                if(d == p || p.checked || d.checked){continue;}
                
                 distance = d.getLocation().Distance(p.getLocation());
                
                if(distance < 20F /*&& distance > 1F*/ && Physics.AreClosing(d, p))
                {
                    Vector2D vectNormal = p.getLocation().Subtract(d.getLocation()).GetUnit();
                    
                    //Closing Velocity
                    Vector2D p_hat_normal = p.getVelocity().Subtract(d.getVelocity());
                    
                    float p_hat = p_hat_normal.DotProduct(vectNormal);
                    
                    float eachP = p_hat / Elasticity(p, d); /// 2.0F;
                    
                    Vector2D result = vectNormal.Scale(eachP);
                    
                    d.ApplyImpulse(result);
                    
                    p.ApplyImpulse(result.Negate());

                }
            }
            d.checked = true;
            
            d.SolveConstraints(milliseconds);
            
        }
    }
    
    public void Add(Physical thing){
        
        _physicsThings.add(thing);
        
    }
    
    public void DrawVelocityVectors(java.awt.Graphics2D g){
        
        for(Physical p : _physicsThings)
            {
                p.DrawVelocityVector(g);
                
            }
        
    }
    
    public void ApplyGravity(){
        
        
        for(Physical p : _physicsThings)
        {
            
            p.ApplyImpulse(_gravityVect);
            
        }
        
    }
    
    public static boolean AreClosing(Physical d1, Physical d2){
        
        Vector2D vectNormal = d1.getLocation().Subtract(d2.getLocation()).GetUnit();

        float d1VelocityNormalizedVelocity = d1.getVelocity().DotProduct(vectNormal);
        
        float d2VelocityNormalizedVelocity = d2.getVelocity().DotProduct(vectNormal);
        
        if(d1VelocityNormalizedVelocity - d2VelocityNormalizedVelocity <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    
}
