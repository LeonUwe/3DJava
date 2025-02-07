package MyVectorStuff;

public class MyPoint2D {
    private double x;
    private double y;

    public MyPoint2D(){

    }

    public MyPoint2D(double x, double y){
        this.x = x;
        this.y = y;
    }




    public double[] getCoords(){
        return new double[] {this.x, this.y}; 
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setCoords(double[] coords){
        this.x = coords[0];
        this.y = coords[1];
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int x){
        this.y = x;
    }

}
