package MyVectorStuff;
import java.awt.*;
import java.util.ArrayList;

public class MyPolygon {
    private MyPoint[] points;
    private int nPoints;
    private double distToPlane;
    private Color color;

    public MyPolygon(MyPoint[] points, Color c){
        this.points = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++){
            this.points[i] = points[i];
        }
        this.nPoints = this.points.length;
        this.color = c;
    }

    ///// ROTATION /////

    public void rotateX(double alpha, MyPoint pivot){
        for(MyPoint point : this.points){
            point.rotateX(alpha, pivot);
        }
    }

    public void rotateY(double alpha, MyPoint pivot){
        for(MyPoint point : this.points){
            point.rotateY(alpha, pivot);
        }
    }

    public void rotateZ(double alpha, MyPoint pivot){
        for(MyPoint point : this.points){
            point.rotateZ(alpha, pivot);
        }
    }
    
    ///// MOVEMENT /////

    public void moveX(double x){
        for(int i = 0; i < this.points.length; i++){
            this.points[i].moveX(x);
        }
    }

    public void moveY(double x){
        for(int i = 0; i < this.points.length; i++){
            this.points[i].moveY(x);
        }
    }

    public void moveZ(double x){
        for(int i = 0; i < this.points.length; i++){
            this.points[i].moveZ(x);
        }
    }

    public void move(double x, double y, double z){
        for (MyPoint point : this.points) {
            point.move(x,y,z);
        }
    }

    ///// GETTERS /////

    public MyPolygon translate(double x, double y, double z){
        MyPoint[] r = new MyPoint[this.points.length];
        for(int i = 0; i < this.points.length; i++){
            r[i] = this.points[i].add(new MyPoint(x, y, z), 1);
        }
        return new MyPolygon(r, this.color);
    }

    public MyPolygon inverse(double width, double height){      //Inversion der y-Koordinate im zweidimensionalen, da java von oben links nach unten rechts malt/zählz
        MyPoint[] r = new MyPoint[this.points.length];
        for(int i = 0; i < this.points.length; i++){
            r[i] = new MyPoint(this.points[i].getX(), this.points[i].getY(), height - this.points[i].getZ());
        }
        return new MyPolygon(r, this.color);
    }


    public Color getColor(){
        return this.color;
    }

    public double getDistanceToPlane(){
        return this.distToPlane;
    }

    public MyPoint[] getPoints(){
        return this.points;
    }

    public int nPoints(){
        return nPoints;
    }

    public int[] getXs(){
        int[] r = new int[points.length];
        for(int i = 0; i < points.length; i++){
            r[i] = (int) points[i].getX();
        }
        return r;
    }

    public int[] getYs(){
        int[] r = new int[points.length];
        for(int i = 0; i < points.length; i++){
            r[i] = (int) points[i].getY();
        }
        return r;
    }

    public int[] getZs(){
        int[] r = new int[points.length];
        for(int i = 0; i < points.length; i++){
            r[i] = (int) points[i].getZ();
        }
        return r;
    }

    public void setDistToPlane(double dist){
        this.distToPlane = dist;
    }

    public void setColor(Color c){
        this.color = c;
    }
}
