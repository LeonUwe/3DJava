package MyVectorStuff;

import MyBasics.MyBasicMaths;
import jdk.jfr.Percentage;

public class MyPlane{
    private MyPoint start, direction1, direction2, normal;

    public MyPlane(MyPoint point, MyPoint dir1, MyPoint dir2){
        this.start = point;
        this.direction1 = dir1;
        this.direction2 = dir2;
        this.normal = MyPoint.cross(dir1, dir2).normalize();
    }

    public MyPlane(MyPoint point, MyPoint normal){
        this.start = point;
        this.normal = normal.normalize();
    }

    
    public MyPoint getIntersection(MyPoint startG, MyPoint directionG){
        double d = MyPoint.scalarProduct(this.normal, this.start);
        double r = 0;
        
        return null;
    }

    ///// PROJECTION //////

    



    public MyPoint parallelProjectPoint(MyPoint point, double perspective){

        /*  kp ob das funktioniert
        double nSqr = MyPoint.scalarProduct(this.normal, this.normal);
        double sc = MyPoint.scalarProduct(point, this.normal);              //komplizierter Matheshit https://wwwdid.mathematik.tu-darmstadt.de/amustud/amu_stud_website/gps_schattenwurf/schattenwurf/parallelprojektion_orthogonal_mathe.pdf

        return new MyPoint(
                            (point.getX() - this.normal.getX() * (sc/nSqr)),
                            (point.getY() - this.normal.getY() * (sc/nSqr)),
                            (point.getZ() - this.normal.getZ() * (sc/nSqr))
        );
        */
        return point.add(this.normal, -this.getDistToPoint(point));
    }



    public MyPoint centralProjectPoint(MyPoint point, double perspective){  //Dreieckshit

        point = point.recenter(this.start);

        MyPoint viewer = this.start.add(this.normal, -perspective);

        double a = this.getDistToPoint(point) + perspective;
        double c = point.add(viewer, -1).length();
        double b = Math.sqrt(c*c - a*a);
        double bb = (b*perspective)/a;
        double verschiebung = b - bb;

        MyPoint parallel = this.parallelProjectPoint(point, perspective);
        return parallel.add(parallel.add(this.start, -1).normalize(), -verschiebung);
    }

    

    public MyPolygon projectPoly(MyPolygon poly, double perspective){
        MyPoint[] points = new MyPoint[poly.getXs().length];
        for(int i = 0; i < points.length; i++){
            points[i] = this.centralProjectPoint(poly.getPoints()[i], perspective);
        }
        return new MyPolygon(points, poly.getColor());
    }

    public MyPolygon2D projectPolygonTo2D(MyPolygon poly, double perspective){
        poly = this.projectPoly(poly, perspective);
///////////////////////////////////////////////////////////// ARBEIT NÖTIG //////////////////////
        double angleXY = this.normal.angleXY();
        double angleXZ = this.normal.angleXZ();
        double angleYZ = this.normal.angleYZ();

        return null;
    }


    public double getDistToPoint(MyPoint point){
        return (MyPoint.scalarProduct(this.normal, point) - MyPoint.scalarProduct(this.start, this.normal)) / this.normal.length();
    }

    public double getViewerDistToPoint(MyPoint point, double perspective){
        return point.add(this.start.add(this.normal, -perspective), -1).length();
    }

    public double getAvrgDistToPolygon(MyPolygon poly, double perspective){
        double dist = 0;
        for(MyPoint point : poly.getPoints()){
            dist += this.getViewerDistToPoint(point, perspective);
        }
        return dist / poly.getPoints().length;
    }


    ////// SETTERS //////

    public void setStart(MyPoint start){
        this.start = start;
    }

    public void setDirection1(MyPoint dir){
        this.direction1 = dir;
    }

    public void setDirection2(MyPoint dir){
        this.direction2 = dir;
    }

    public void setNormal(MyPoint normal){
        this.normal = normal;
    }

    /////// GETTERS /////////

    public MyPoint getDirection1(){
        return this.direction1;
    }

    public MyPoint getDirection2(){
        return this.direction2;
    }

    public MyPoint getNormal(){
        return this.normal;
    }

    public MyPoint getStart(){
        return this.start;
    }

    ////////////////////

    public void move(double x, double y, double z){
        this.start.move(x, y, z);
    }
}
