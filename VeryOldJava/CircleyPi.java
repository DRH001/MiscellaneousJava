import java.util.Scanner;

class CircleyPi{
	
	public static double findAreaOfBiggestRectangle(double height, long a){
		double x,y;
		//x^2+y^2=1
		//x=sqrt(1-y^2)
		y = height*a;
		x = Math.sqrt(1-Math.pow(y, 2));
		return(2*x*height);
		
		
		
		
		
	}
	
	
	
	
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		long rectangleNumber = scan.nextLong()*2;
		double rectangleHeight = 1.0/(float)rectangleNumber;
		double areaSoFar = 0.0;
		
		for(long i=0;i<rectangleNumber;i++){
			areaSoFar += findAreaOfBiggestRectangle(rectangleHeight, i);
			
			
			
			
		}
		
		
		
		double x,y;
		//x^2+y^2=1
		//x=sqrt(1-y^2)
		y = rectangleHeight*(rectangleNumber-1);
		x = Math.sqrt(1-Math.pow(y, 2));
		
		areaSoFar += rectangleHeight;
		
		System.out.println(areaSoFar*2);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}