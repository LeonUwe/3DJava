package renderer;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

import javax.swing.JFrame;

import MyVectorStuff.*;
import MyVectorStuff.SolidFigures.*;

public class Display extends Canvas implements Runnable{
    
    private Thread thread;
    private JFrame frame;
    private static String title = "3D Java";
    private static int WIDTH = 800;
    private static int HEIGHT = 500;
    private static boolean running = false;

    private ArrayList<MyPolygon> polys = new ArrayList<>();
    private MyCube cube, cube2;
    private MyCuboid cuboid;

    private double perspective = 2000;
    private double th = 0;
    

    public Display(){
        this.frame = new JFrame(title);
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.frame.setPreferredSize(size);
        this.frame.setMinimumSize(size);
    }

    public static void main(String[] args){
        Display display = new Display();
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);
        display.setBackground(Color.black);
        display.start();

        
    }

    public synchronized void start(){
        this.running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start();



        cube = new MyCube(new MyPoint(1000,0,0), 600);
        cube2 = new MyCube(new MyPoint(500,500,300), 300);
        cuboid = new MyCuboid(new MyPoint(500, -200, 0), 50, 1000, 200);
        //cube2.rotateY(.01, cube2.getCenter());
        

        //for(MyPolygon pol : cube.getPolygons()){
        //    polys.add(pol);
        //}
        for(MyPolygon pol : cube2.getPolygons()){
            polys.add(pol);
        }
        for(MyPolygon pol : cuboid.getPolygons()){
            polys.add(pol);
        }
    }

    public synchronized void stop(){
        this.running = false;
        try{
            this.thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;


        while (this.running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;

            

            while (delta >= 1){
                update();
                delta--;
            }

            this.WIDTH = this.getWidth();
            this.HEIGHT = this.getHeight();

            render();
            
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }
        this.stop();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        do{
            try{
                draw(bs);

            } finally{
                g.dispose();
            }
            bs.show();
        } while (bs.contentsLost());      
    }

    public void draw(BufferStrategy bs){
        do {
            do{
                Graphics g = bs.getDrawGraphics();
                clear(g);
                DrawPolygons.drawPolygons(g, this.WIDTH, this.HEIGHT, this.polys, this.perspective, th);
                g.dispose();
            } while(bs.contentsRestored());
            bs.show();
        } while(bs.contentsLost());
        
        
                         
                
        //https://de.wikipedia.org/wiki/Drehmatrix damit die Ebene/MyPlane drehen bzw. alle darauf projizierte Punkte drehen, sodass ich das auf den canvas übertragen kann
        // Den Winkel, um den ich drehen muss, kann ich bestimmt herausfinden, indem ich den Winkel zwischen Ortsvektor und Koordinatenachsen berechne
        // Viel Spaß dabei Zukunfts-Leon!

        // Vielleicht wird der Würfel, also auch alles andere, irgendwie falsch herum angezeigt. KP, musst du herausfinden Zukunfts-Leon! Genau so wie das mit der Betrachtungsebene. HAHA

    }

    

    public void clear(Graphics g){
        g.clearRect(0, 0, this.WIDTH, this.HEIGHT);
    }

    public void update(){
        //cube.rotateX(0.001, cube.getCenter());
        //cube.rotateY(0.001, cube.getCenter());
        //cube.rotateZ(0.001, cube.getCenter());
        //cube.move(0,-1,0);

        //cube3.rotateX(0.001, cube.getCenter());
        //cube3.rotateY(0.01, cube3.getCenter());
        //cube3.rotateZ(0.01, cube3.getCenter());

        //cube2.rotateX(-0.001, cube2.getCenter());
        cube2.rotateY(0.001, cube2.getCenter());
        cube2.rotateZ(-0.001, cube2.getCenter()); 
        
        //cube2.rotateX(-0.001, cube2.getCenter());
        //cuboid.rotateY(-0.001, cuboid.getCenter());
        //cuboid.rotateZ(-0.001, cuboid.getCenter()); 

        //this.th+=10;
        for(MyPolygon poly : this.polys){
            poly.rotateZ(0.01, new MyPoint(500,0,0));
        }
    }
}