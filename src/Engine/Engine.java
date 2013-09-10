/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;


import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import org.w3c.dom.Document;
import org.w3c.dom.*;
import java.awt.geom.*;
/**
 *
 * @author Brandon
 */
public class Engine implements Runnable{
    
    private Frame _frame;
    
    public ViewPort _window;

    private static long _previousStamp = System.nanoTime(); //Stamp beginning
    
    private BufferStrategy _graphicsStrategy;
    
    HashSet<Drawable> _things = new HashSet<>();
    
   
    
    
    private Tile[][] _tiles = new Tile[50][50];
    
    public boolean _debug = true;
    
    private DebugWindow _debugWindow;

    private HashMap<String, String> _tileMapping;
    
    public HashMap<String, BufferedImage> _images = new HashMap<>();
    
    public java.awt.Point _externalFrameLocation = new Point();
    
    private ArrayList<Tile> _loadedTiles = new ArrayList<Tile>();
    
    private Audio _audio = new Audio();
    
    private Physics _physicsEngine = new Physics();
    
    
    
    public Engine(){
        
        _frame = new Frame();
        
        _frame.setSize(600, 600);
        
        _frame.setLocation(300, 300);
        
        _frame.setUndecorated(true);
        
        _window = new ViewPort(_frame, this);
        
        _window.setBackground(Color.black);
        
        _window.setSize(600, 600);
        
        _frame.setVisible(true);
        
        _window.setVisible(true);
        
         // Create a general double-buffering strategy
        _window.createBufferStrategy(2);
        
        _graphicsStrategy = _window.getBufferStrategy();
        
        _debugWindow = new DebugWindow(this);
        
        //this._things.add(new Tile());
        
        this.loadImages();

        //this.LoadTiles();
        
        //this.Save("test.txt");
        
        //this.buildTileMap("TileMap.xml", _images);
        
        //_tileMapping = GetBufferedMapping("TileMap.xml");
        
         //System.out.println(_tileMapping);
        
         //Save("TileMap.xml", _tileMapping);
         
         TestCode();
         
         startGameLoop();
    }
    
    public Engine(Frame frame){
        
        _frame = frame;

        _frame.setBackground(Color.DARK_GRAY);
        
        _window = new ViewPort(_frame, this);

        _window.setBackground(Color.black);
        
        _window.setSize(600, 600);
              
        _frame.setVisible(true);
        
        _window.setVisible(true);
        
         // Create a general double-buffering strategy
        _window.createBufferStrategy(2);
        
        _graphicsStrategy = _window.getBufferStrategy();
        
        _debugWindow = new DebugWindow(this);
        
        //this.loadImages();
        
        //this.LoadTiles();
  
        
        //this._tiles = Utilities.TestLevel(this);
        
        
        
        
        
        this.buildTileMap("TileMap.xml", _images);
        
        frame.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                
                _externalFrameLocation = e.getComponent().getLocation();
              
                _window.setLocation(_externalFrameLocation.x + 175, _externalFrameLocation.y + 50);

            }

            @Override
            public void componentShown(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        
        
       this.Save("test.txt");
        
        startGameLoop();
    }
    
    public void TestCode(){
        /*
        for(int x = 0; x < 10; x++){
            
            Bubble b = new Bubble(this);
            
            _physicsThings.add(b);
            _things.add(b);
            
        }
        */
        
        java.util.Random rand = new java.util.Random();
        rand.setSeed(this.hashCode());
         
        for(int x = 0; x < 100; x++){
            
            Bubble b = new Bubble(this, new Vector2D(Math.abs(rand.nextFloat()) * 600, Math.abs(rand.nextFloat() * 500)));
            
            _physicsEngine.Add(b);
            _things.add(b);
            
        }
        
        
        
       
        //System.out.println("Unit:  " + new Vector2D(-1, 1).GetUnit());
        //System.out.println("Normalized:  " + new Vector2D(1, -1).DotProduct(new Vector2D(-1, 1).GetUnit()));
        
    }
    
    public void loadImages(){

        File folder = new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\Default\\src\\Engine\\Sprites\\Tiles\\");
        
        
        FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".png")) {
					return true;
				} else {
					return false;
				}
			}
		};
        
        File[] listOfFiles = folder.listFiles(textFilter);
        
        Arrays.sort(listOfFiles);
        
        for(File f : listOfFiles)
        {
            try{
            
                BufferedImage in = ImageIO.read(f);
        
                _images.put(f.getName(), in);
            }
            catch(Exception e){}
        }
        
        System.out.println(_images.size() + " Images Loaded.");
  
    }
    
    public String ToElement(String name, String data){
        
        String result = "";
        
        result += "<" +name + ">";
        result += "\r" + data + "\r";
        result += "</" +name + ">\r";
        
        return result;
        
    }
    
    public void NewLoadTiles(){
        
        
         Object[] keyset = _images.keySet().toArray();
         
         for(Object t : keyset){
            Tile newTile = new Tile(0,0, _images.get(t));
            _loadedTiles.add(newTile);
         }
         
        
    }
    
    public void buildTileMap(String fileLocation,  HashMap<String, BufferedImage> tiles){
         try{
             
            FileWriter fstream = new FileWriter(fileLocation);
            BufferedWriter out = new BufferedWriter(fstream);
            
            Set<String> keys = tiles.keySet();
            
            int x = 0;
            
            String fileData = "" + ToElement("Data", "");
            String tileData = "";
            
             
            for(String key : keys)
            {
                tileData +=  ToElement("TILE", 
                                    ToElement("CharSak", "" + (char) (x + 97))
                                        + ToElement("ImageIdentifier", key));
                
                x++;

            }
            
            out.write(ToElement("root", fileData + ToElement("Tiles", tileData)));
            
            //Close the output stream
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Save Failed");
        }
    }
    
    public void ClearTiles(){
        _tiles = new Tile[50][50];
    }
    
    public void LoadTiles(){
        
        int t;
        
        Object[] keyset = _images.keySet().toArray();
        
        NewLoadTiles();
        
        for(int y = 0; y < 50; y++)
        {
            t = y % _images.size();
            for(int x = 0; x < 50; x++)
            {
                if(t >= _images.size()){t = 0;}
                
                Tile newTile = new Tile(x,y, _images.get(keyset[t]));
                
                newTile._type = ((char)(97 + t));
                
                _tiles[x][y] = newTile;

                t++;
                
            }
        }
    }
    
    
    public HashMap<String, String> swapMapping(HashMap<String, String> oldMapping){
        HashMap<String, String> swappedMapping = new HashMap<>();

        for(String key : oldMapping.keySet())
        {
            swappedMapping.put(oldMapping.get(key), key);
        }

        return swappedMapping;
        
        
    }
    
    private void Save(String mapLocation, HashMap<String, String> bufferedMapping){

         try{
            FileWriter fstream = new FileWriter(mapLocation);
            BufferedWriter out = new BufferedWriter(fstream);
            
            out.write("<PROPERTIES>\n");
            out.write("<SIZE>\n");
            
            out.write("</SIZE>\n");
            
            out.write("</PROPERTIES>\n");
            
            out.write("<TILES>\n");
            
            for( Tile[] t : _tiles)
            {
                for (Tile s : t)
                {
                    out.write(s._type);
                }
                
            }
            
            out.write("</TILES>\n");
            
            //Close the output stream
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Save Failed");
        }
        
        
    }
    
    public HashMap<String, String> GetBufferedMapping(String tileMappingFileLocation)
    {
        HashMap<String, String> bufferedMapping = new HashMap<>();
    
         try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(tileMappingFileLocation));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
           // System.out.println ("Root element of the doc is " + 
              //   doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("TILE");
            int totalPersons = listOfPersons.getLength();
            //System.out.println("Total no of images : " + totalPersons);

            for(int s=0; s<listOfPersons.getLength() ; s++){

                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){

                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("CharSak");
                    Element firstNameElement = (Element)firstNameList.item(0);
                      
                    NodeList textFNList = firstNameElement.getChildNodes();
                   // System.out.println("CharSak : " + 
                     //      ((Node)textFNList.item(0)).getNodeValue().trim());
                           
                    
                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("ImageIdentifier");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                    //System.out.println("Image Identifier : " + 
                      //     ((Node)textLNList.item(0)).getNodeValue().trim());

                    
                    bufferedMapping.put(textFNList.item(0).getNodeValue().trim(), textLNList.item(0).getNodeValue().trim());
                    
                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        
        return bufferedMapping;
    
    
    }
    
    
    private void Save(String fileLocation){
    
        try{
            FileWriter fstream = new FileWriter(fileLocation);
            BufferedWriter out = new BufferedWriter(fstream);
            
            out.write("<PROPERTIES>\n");
            out.write("<SIZE>\n");
            
            out.write("</SIZE>\n");
            
            out.write("</PROPERTIES>\n");
            
            out.write("<TILES>\n");
            
            for( Tile[] t : _tiles)
            {
                for (Tile s : t)
                {
                    out.write(s._type);
                }
                
            }
            
            out.write("</TILES>\n");
            
            //Close the output stream
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Save Failed");
        }
    
    }
    
    private void startGameLoop(){
     
        new Thread(this).start();

    }
    
    
    private void RenderTiles(Graphics2D g){
    
        for(Tile[] t : _tiles)
        {
           for(Tile s : t)
            {
                if(s != null){
                    
                    s.draw(g);
                }
            } 
        }
    }
    
    
    public ArrayList<Tile> GetLoadedTileTypes(){

        return _loadedTiles;
    }
    
    public void draw(Graphics2D g){
        
        //Transform and Scale draw here
        //graphics.getTransform();
        

        RenderTiles(g);
        
        for(Drawable d : _things)
        {
            d.draw(g);

        }
        
        if(_debug)
        {
            _physicsEngine.DrawVelocityVectors(g);
            _debugWindow.draw(g);

        }

        
    }
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("Engine Starting...");
        new Engine();

    }

    public void update(float milliseconds){
        
        _physicsEngine.update(milliseconds);

        for(Drawable d : _things)
        {
            d.update(milliseconds);
        }
        
        if(_debug){
            _debugWindow.update(milliseconds);
        }
    }
    
    
    
    
    public Dimension getViewPortSize()
    {
        return this._window.getSize();
    
    }
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
       // Main loop
        while (true) {
            
            
            long currentTime = System.nanoTime();
            
            long deltaT = currentTime - _previousStamp; 
            
            _previousStamp = currentTime;
            
            // Prepare for rendering the next frame passed as milliseconds
            this.update((deltaT / 1000000F));
            
            
            // Render single frame
            do {
                // The following loop ensures that the contents of the drawing buffer
                // are consistent in case the underlying surface was recreated
                do {
                    // Get a new graphics context every time through the loop
                    // to make sure the strategy is validated
                    Graphics2D graphics = (Graphics2D)_graphicsStrategy.getDrawGraphics();
                    
                     graphics.clearRect(0,  0, _window.getWidth(), _window.getHeight());
                    //graphics.clearRect(_window.getX(), _window.getY(), _window.getWidth(), _window.getHeight());
                    
                    // Render to graphics
                    // ...
                    
                    this.draw(graphics);

                    // Dispose the graphics
                    graphics.dispose();

                // Repeat the rendering if the drawing buffer contents
                // were restored
                } while (_graphicsStrategy.contentsRestored());

                // Display the buffer
                _graphicsStrategy.show();

            // Repeat the rendering if the drawing buffer was lost
            } while (_graphicsStrategy.contentsLost());
        
        }

    }
 
}
