import java.util.Scanner;


public class FractionApproximator{

     public static void main(String []args){
		 
		Scanner scan = new Scanner(System.in);
        
		System.out.println("Please enter a number to be approximated as a fraction: \n");
		
		double x = scan.nextDouble();
		
		System.out.println("\nPlease enter an integer for the maximum value of the denominator: \n");
		
		long limit = scan.nextLong();
		
		long a;
		double difference = 10000000;
		
		System.out.println("\n\nFraction | Decimal value of fraction:\n");
		
		for(long b=1;b<=limit;b++){
			a = (long) (x * b);
			
			if( Math.abs((double)a / (double)b - x) < difference){//
				difference = Math.abs((double)a / (double)b - x);
				
				System.out.println(a + "/" + b + " | " + ((double)a/(double)b));
			} 
			
			a++;
			if( Math.abs((double)a / (double)b - x) < difference){//
				difference = Math.abs((double)a / (double)b - x);
				
				System.out.println(a + "/" + b + " | " + ((double)a/(double)b));
			} 
		
		}
     }
}
