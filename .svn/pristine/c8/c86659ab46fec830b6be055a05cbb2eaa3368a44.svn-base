/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Brandon
 */
public class Utilities {
    
    
    
    
    
    public Utilities(){
    
        
    
    
    
    
    }
    
    
    public static Tile[][] TestLevel(Engine e){
        
        Tile[][] lvl = new Tile[50][50];

        for(int x = 0; x < 50; x++)
        {
            for(int y = 0; y < 50; y++)
            {
                Tile t = e.GetLoadedTileTypes().get(12).clone();
                t.setLocation(new java.awt.Point(x * 15, y * 15));
                
                lvl[x][y] = t;
                
            }
        }
        
        
        return lvl;
        
    
    }
    
    
}
