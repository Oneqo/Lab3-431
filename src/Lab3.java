import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class Lab3 {
    private static final double EPSILON = 0.00001;
    private static final int ITERATION_COUNT = 2000;

    public static void main(String[] args){

        double x = Math.random()*12 - 6;
        double y = Math.random()*10 - 5;
        double result = findMinMax(x,y);
        System.out.println("The value of solution: " + result);

        System.out.println("Z1: "+ z(1, 0.571632));
        System.out.println("Z2: "+ z(0.5, 0.571632));
        System.out.println("Z3: "+ z(0.732426, 1));
        System.out.println("Z4: "+ z(0.732426, 0));
    }
    private static double findMinMax(double x, double y){
        int count = 1;
        do{
            double nextX = x - fprime_x(x,y)/fdprime_x(x,y);
            double nextY = y - fprime_y(nextX,y)/fdprime_y(nextX,y);

            if(Math.abs(z(x,y)-z(nextX,nextY)) <= EPSILON){
                //Found a solution
                System.out.println("Found a solution : " + nextX +", " + nextY + "z = " + z(nextX,nextY));
                if(Math.abs(fprime_x(nextX,nextY)) <= EPSILON && Math.abs(fprime_y(nextX,nextY)) <= EPSILON){
                    //If it's either a local min/max, we return the value of z
                    return z(nextX,nextY);
                }else{
                    //Get new values for x/y and restart the method
                     x = Math.random()*12 - 6;
                     y = Math.random()*10 - 5;
                     count = 1;
                     continue;
                }
            }
            x = nextX;
            y = nextY;
            count++;
        }while(count <= ITERATION_COUNT);
        return Double.POSITIVE_INFINITY;
    }

    private static double z(double x, double y){
        return Math.pow(x,4)+2*Math.pow(y,6)-x*y-x+2;
    }
    private static double fdprime_xy(double x, double y){
        return -1;
    }
    private static double fprime_x(double x, double y){
        return 4*Math.pow(x,3) - y - 1;
    }
    private static double fprime_y(double x, double y){
        return 12*Math.pow(y,5) - x;
    }
    private static double fdprime_x(double x, double y){
        return 12*Math.pow(x,2);
    }
    private static double fdprime_y(double x, double y){
        return 60*Math.pow(y,4);
    }
}
