/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.util.Arrays;
import java.util.*;
import Engine.*;
import java.awt.event.*;

/**
 *
 * @author Brandon
 */
public class Editor extends Frame{
    
    private Engine _engine;
    
    private TilesControl _tileControl = new TilesControl();
    
    public Editor()
    {
        
        super("Dragon Warrior Editor");
        
        this.setSize(800, 800);
        
        _tileControl.setLocation(new Point(25, 660));
        
        _tileControl.setSize(600, 100);
        
        
        
        this.setVisible(true);
        
        //this.setSize(600, 600);
        
       
        
        
    
        _engine = new Engine(this);
        //_engine.ClearTiles();
        
      this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we)
            {
                Shutdown();
             }
        });
        
        this.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.print(e.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    );
        
        
        
        
    }

    
        public void Shutdown()
        {
        
            System.exit(0);

        }
    
    @Override
    public void setLocation(Point p) {
        super.setLocation(p); //To change body of generated methods, choose Tools | Templates.
        
        _engine._window.setLocation(this.getLocation().x + 50, this.getLocation().y + 50);
        
        
        
    }
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        Graphics2D g2d = (Graphics2D) g;
        
        DrawTools(g2d);
 
    }
    
    public void DrawTools(Graphics2D g){
        
        
        if(_tileControl != null){
            _tileControl.draw(g);
        }
    }

    
    private class TilesControl implements Drawable
    {
        ArrayList<Tile> possibleTileSet = new ArrayList<Tile>();
        
        Point location = new Point();
        
        Dimension _size = new Dimension();
        
        public TilesControl()
        {
            
            
            
        }
        
        public void setLocation(Point p){
        
            location = p;
        
        
        }
        
        public void setSize(int x, int y){
            
            _size = new Dimension(x, y);
            
        }
        
        @Override
        public void draw(Graphics2D g) {
            
            if(_engine != null && possibleTileSet.size() < 1)
            {
                possibleTileSet = (ArrayList<Tile>)_engine.GetLoadedTileTypes().clone();
                
                int dx = location.x + 10; 
                int dy = location.y + 10;
                
                for(int x = 0; x < possibleTileSet.size(); x++)
                {

                    if(dx > 600)
                    {
                        dx = location.x + 10;
                        dy += 20;
                    }
                        
                    possibleTileSet.get(x).setLocation(new Point(dx, dy));
                    
                    dx += 20;
                }
                
            }
            
            g.setColor(Color.black);
            g.fill3DRect(location.x, location.y, _size.width, _size.height, true);
            
            for(Tile t : possibleTileSet)
            {
                t.draw(g);
                
            }
            
        }

        @Override
        public void update(float milliseconds) {
            
        }

        @Override
        public Dimension2D getSize() {
           return new Dimension2D();
        }

         @Override
        public Vector2D getLocation() {

            return new Vector2D(0, 0);

        }
        
    }
    
    
    
    
    public static void main(String[] args){
    
        new Editor();
    
    
    
    }
    
    
    
}
