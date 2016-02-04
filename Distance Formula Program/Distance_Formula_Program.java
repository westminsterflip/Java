//Ian Patterson
//This program calculates the distance between any two given points on a 2 dimensional cartesian plane.  x1,x2,y1,y2 are input, the distance between the x's and y's are independently calculated
public class Distance_Formula_Program {

	public static void main(String[] args) {
		double x1 = -2.15,y2 = -5.93; //Input
		int y1 = 8, x2 = 3; //Input
		double xdist = (double)(x2-x1);
		double ydist = (double)(y2-y1);
		double distance = Math.sqrt(Math.pow(xdist, 2)+Math.pow(ydist, 2));
		System.out.println("The distance between ("+x1+","+y1+") and ("+x2+","+y2+") is "+(double)Math.round(distance*100)/100+".");
	}
}
//The distance between (-2.15,8) and (3,-5.93) is 14.85.
