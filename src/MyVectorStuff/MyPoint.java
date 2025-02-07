package MyVectorStuff;

public class MyPoint {
    private double x;
    private double y;
    private double z;

    public MyPoint(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public static MyPoint cross(MyPoint v1, MyPoint v2){
        return new MyPoint(
                            (v1.getY() * v2.getZ() - v1.getZ() * v2.getY()),
                            (v1.getZ() * v2.getX() - v1.getX() * v2.getZ()),
                            (v1.getX() * v2.getY() - v1.getY() * v2.getX())
                            );
    }

    public static double scalarProduct(MyPoint v1, MyPoint v2){
        return (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ());
    }

    public MyPoint add(MyPoint p, double r){
        return new MyPoint(
                            (this.x + (r*p.getX())),
                            (this.y + (r*p.getY())),
                            (this.z + (r*p.getZ()))
                            );
    }

    public MyPoint add(MyPoint p, double nX, double nY, double nZ){
        return new MyPoint(
                            (this.x + (nX*p.getX())),
                            (this.y + (nY*p.getY())),
                            (this.z + (nZ*p.getZ()))
                            );
    }

    public void moveX(double x){
        this.x += x;
    }

    public void moveY(double x){
        this.y += x;
    }

    public void moveZ(double x){
        this.z += x;
    }

    public void move(double x, double y, double z){
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public MyPoint normalize(){
        return new MyPoint(
                            this.getX() / this.length(),
                            this.getY() / this.length(),
                            this.getZ() / this.length()
        );
    }

    public double angleXY(){
        return Math.toDegrees(Math.asin(MyPoint.scalarProduct(this, new MyPoint(0,0,1)) / this.length()));
    }

    public double angleXZ(){
        return Math.toDegrees(Math.asin(MyPoint.scalarProduct(this, new MyPoint(0,1,0)) / this.length()));
    }

    public double angleYZ(){
        return Math.toDegrees(Math.asin(MyPoint.scalarProduct(this, new MyPoint(1,0,0)) / this.length()));
    }

    public double angle(MyPlane plane){
        return Math.toDegrees(Math.asin(MyPoint.scalarProduct(this, plane.getNormal().normalize()) / this.length()));
    }

    //https://de.wikipedia.org/wiki/Drehmatrix hier die Drehmatrizen
    //https://www.youtube.com/watch?v=wMnyPnQi0Ig&ab_channel=SebastianSchmidt Drehung um einen bestimmten Punkt
    public void rotateX(double alpha, MyPoint pivot){
        MyPoint vector = this.add(pivot, -1);
        double xOld = vector.x;
        double yOld = vector.y;
        double zOld = vector.z;
        this.x = (xOld * 1 + yOld * 0 + zOld* 0) + pivot.getX();
        this.y = (xOld * 0 + yOld * Math.cos(alpha) - zOld * Math.sin(alpha)) + pivot.getY();
        this.z = (xOld * 0 + yOld * Math.sin(alpha) + zOld * Math.cos(alpha)) + pivot.getZ();
    }

    public void rotateY(double alpha, MyPoint pivot){
        MyPoint vector = this.add(pivot, -1);
        double xOld = vector.x;
        double yOld = vector.y;
        double zOld = vector.z;
        this.x = (xOld * Math.cos(alpha) + yOld * 0 + zOld * Math.sin(alpha)) + pivot.getX();
        this.y = (xOld * 0 + yOld * 1 + zOld * 0) + pivot.getY();
        this.z = (-xOld * Math.sin(alpha) + yOld * 0 + zOld * Math.cos(alpha)) + pivot.getZ();
    }

    public void rotateZ(double alpha, MyPoint pivot){
        MyPoint vector = this.add(pivot, -1);
        double xOld = vector.x;
        double yOld = vector.y;
        double zOld = vector.z;
        this.x = (xOld * Math.cos(alpha) - yOld * Math.sin(alpha) + zOld * 0) + pivot.getX();
        this.y = (xOld * Math.sin(alpha) + yOld * Math.cos(alpha) - zOld * 0) + pivot.getY();
        this.z = (xOld * 0 + yOld * 0 + zOld * 1) + pivot.getZ();
    }

    public double length(){
        return (Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z));
    }

    public double[] getCoords(){
        return new double[] {this.x, this.y, this.z}; 
    }

    public String getCoordinates(){
        return this.x + " " + this.y + " " + this.z;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }

    public void setCoords(double[] coords){
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int x){
        this.y = x;
    }

    public void setZ(int x){
        this.z = x;
    }




    public MyPoint recenter(MyPoint center){         // Punkt in neues Koordinatensystem übertragen, für die Zentralprojektion
        return this.add(center, -1);
    }

}
