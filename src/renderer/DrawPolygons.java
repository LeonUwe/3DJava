package renderer;
import java.awt.*;
import MyVectorStuff.*;
import MyBasics.*;
import java.util.*;


public class DrawPolygons {
    public static void drawPolygons(Graphics g, int WIDTH, int HEIGHT, ArrayList<MyPolygon> polys, double perspective, double th){
        MyPlane plane = new MyPlane(new MyPoint(0,th,0), new MyPoint(0,1,0), new MyPoint(0,0,1));
        ArrayList<MyPolygon> polysSorted = DrawPolygons.sortPolygons(polys, plane, perspective);

        for(int i = 0; i < polysSorted.size(); i++){
            MyPolygon poly = polysSorted.get(i);
            double dist = Math.abs(poly.getDistanceToPlane());
            Color c = poly.getColor();
            g.setColor(new Color(
                                (MyBasicMaths.clampInt(c.getRed()- (int)(dist/50), 255, 0)),
                                (MyBasicMaths.clampInt(c.getGreen()- (int)(dist/50), 255, 0)),
                                (MyBasicMaths.clampInt(c.getBlue()- (int)(dist/50), 255, 0))
            ));
            poly = plane.projectPoly(poly, perspective);
            MyPolygon translated = poly.translate(0, WIDTH/2, HEIGHT/2).inverse(WIDTH, HEIGHT);
            g.fillPolygon(translated.getYs(), translated.getZs(), translated.nPoints());
        }   
    }

    public static ArrayList<MyPolygon> sortPolygons(ArrayList<MyPolygon> polys, MyPlane plane, double perspective){
        ArrayList<MyPolygon> polysSorted = new ArrayList<>();
        for(int i = 0; i < polys.size(); i++){
            double dist = plane.getAvrgDistToPolygon(polys.get(i), perspective);
            polys.get(i).setDistToPlane(Math.abs(dist));
            if(dist < 0) continue;
            polysSorted.add(polys.get(i));
            
        }
        
        for(int i = 0; i < polysSorted.size(); i++){
            for(int k = 0; k < polysSorted.size()-1; k++){
                if(polysSorted.get(k+1).getDistanceToPlane() > polysSorted.get(k).getDistanceToPlane()){        // Bubblesort hässlich
                    MyPolygon curPoly = polysSorted.get(k);
                    polysSorted.set(k, polysSorted.get(k+1));
                    polysSorted.set(k+1, curPoly);
                }
            }
        }
        return polysSorted;
    }


    public static ArrayList<MyPolygon> mergeSort(ArrayList<MyPolygon> arr, int l, int r){
        if(l < r){
            int m = 1 + (r-1)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
        return arr;
    }

    private static void merge(ArrayList<MyPolygon> arr, int l, int m, int r){
        int n1 = m-1+1;
        int n2 = r-m;
        ArrayList<MyPolygon> L = new ArrayList<MyPolygon>(n1);
        ArrayList<MyPolygon> R = new ArrayList<MyPolygon>(n2);
        for(int i = 0; i < n1; i++){
            L.set(i, arr.get(l+i));
        }
        for(int i = 0; i < n2; i++){
            R.set(i, arr.get(m+1+i));
        }
        int i = 0, j = 0, k = 1;
        while(i < n1 && j < n2){
            if(L.get(i).getDistanceToPlane() <= R.get(j).getDistanceToPlane()){
                arr.set(k++, L.get(i++));
            }else{
                arr.set(k++, R.get(j++));
            }
        }
        while(i < n1){
            arr.set(k++, L.get(i++));
        }
        while(j < n2){
            arr.set(k++, R.get(j++));
        }
    }

    
}
