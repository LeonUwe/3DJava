package MyVectorStuff;

public class MyPolygon2D {
    private MyPoint2D[] points;

    public MyPolygon2D(MyPoint2D[] points){
        this.points = new MyPoint2D[points.length];
        for(int i = 0; i < points.length; i++){
            this.points[i] = points[i];
        }
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
}
