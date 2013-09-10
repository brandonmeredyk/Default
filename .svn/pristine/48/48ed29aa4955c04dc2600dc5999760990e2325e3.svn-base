/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Brandon
 */
public class Vector2D {
    
    float X, Y;
    
    public Vector2D(){
        
        X = 0F;
        Y = 0F;
        
    }
    
    public Vector2D(Vector2D vect){
        X = vect.X;
        Y = vect.Y;
    }
    
    public Vector2D(float x, float y){
        X = x;
        Y = y;
    }
    
    public Vector2D Add(Vector2D operand){
        
        return new Vector2D(X + operand.X, Y + operand.Y);
        
    }
    
    public Vector2D Subtract(Vector2D operand){ 
        return new Vector2D(X - operand.X, Y - operand.Y);
    }
    
    public Vector2D Divide(Vector2D operand){
        
        Vector2D vect = new Vector2D(this);
        
        vect.X /= operand.X;
        vect.Y /= operand.Y;
        
        return vect;
        
    }
    
    public Vector2D Scale(float scaler){
        
        return new Vector2D(X * scaler, Y * scaler);
        
    }
    
    public float Distance(Vector2D operand){
        
        return this.Subtract(operand).Magnitude();
        
    }
    
    public float DotProduct(Vector2D vect){
        
        return (X * vect.X) + (Y * vect.Y);
        
    }
    
    public Vector2D Negate(){
        
        return new Vector2D(-X, -Y);
        
    }
    
    public float Magnitude(){
        return (float) Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));   
    }
    
    public Vector2D GetUnit(){
       
        return Scale(1.0F / this.Magnitude());
    }
    
    
    @Override
    public String toString(){
        return "Vector2D (" + X + ", " + Y + ")";
    }
    
}
