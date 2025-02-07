package MyVectorStuff.SolidFigures;
import MyVectorStuff.MyPoint;
import MyVectorStuff.MyPolygon;
import java.awt.*;

public class MyCuboid {
    private MyPolygon[] polygons = new MyPolygon[6];
    private MyPoint[] points = new MyPoint[8];
    private MyPoint center;

    public MyCuboid(MyPoint start, double width, double length, double height){
        this.center = start;
        this.points[0] = start.add(new MyPoint(.5,-.5,-.5), length, width, height);
        this.points[1] = this.points[0].add(new MyPoint(0,1,0), length, width, height);
        this.points[2] = this.points[1].add(new MyPoint(0,0,1), length, width, height);
        this.points[3] = this.points[2].add(new MyPoint(0,-1,0), length, width, height);
        this.points[4] = this.points[0].add(new MyPoint(-1,0,0), length, width, height);
        this.points[5] = this.points[4].add(new MyPoint(0,1,0), length, width, height);
        this.points[6] = this.points[5].add(new MyPoint(0,0,1), length, width, height);
        this.points[7] = this.points[4].add(new MyPoint(0,0,1), length, width, height);

        MyPoint[] face = new MyPoint[4];
        face[0] = this.points[0];
        face[1] = this.points[1];
        face[2] = this.points[2];
        face[3] = this.points[3]; 
        polygons[0] = new MyPolygon(face, new Color(100,150,150));

        face[0] = this.points[1];
        face[1] = this.points[5];
        face[2] = this.points[6];
        face[3] = this.points[2]; 
        polygons[1] = new MyPolygon(face, new Color(0,255,0));
        
        face[0] = this.points[5];
        face[1] = this.points[4];
        face[2] = this.points[7];
        face[3] = this.points[6]; 
        polygons[2] = new MyPolygon(face, new Color(255,0,0));
        
        face[0] = this.points[0];
        face[1] = this.points[4];
        face[2] = this.points[7];
        face[3] = this.points[3]; 
        polygons[3] = new MyPolygon(face, new Color(0,255,0));

        face[0] = this.points[0];
        face[1] = this.points[1];
        face[2] = this.points[5];
        face[3] = this.points[4]; 
        polygons[4] = new MyPolygon(face, new Color(0,0,255));

        face[0] = this.points[2];
        face[1] = this.points[3];
        face[2] = this.points[7];
        face[3] = this.points[6]; 
        polygons[5] = new MyPolygon(face, new Color(0,0,255));
    }

    public MyPolygon[] getPolygons(){
        return this.polygons;
    }

    public MyPoint[] getPoints(){
        return this.points;
    }

    public void rotateX(double alpha, MyPoint pivot){
        for(MyPolygon poly : this.polygons){
            poly.rotateX(alpha, pivot);
        }
    }

    public void rotateY(double alpha, MyPoint pivot){
        for(MyPolygon poly : this.polygons){
            poly.rotateY(alpha, pivot);
        }
    }

    public void rotateZ(double alpha, MyPoint pivot){
        for(MyPolygon poly : this.polygons){
            poly.rotateZ(alpha, pivot);
        }
    }

    public MyPoint getCenter(){
        return this.center;
    }
}