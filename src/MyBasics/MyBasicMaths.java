package MyBasics;

public class MyBasicMaths {
    public static int clampInt(int n, int max, int min){
        if(n > max) return max;
        if(n < min) return min;
        return n;
    }

    public static double clampDouble(double n, double max, double min){
        if(n > max) return max;
        if(n < min) return min;
        return n;
    }
}
