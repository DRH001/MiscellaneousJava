
import java.util.Scanner;

class Pie{
	
	public static double findPie(long i){
		double rn = 0;
		
		for(long j=i; j>0; j--){
			rn = 2 + (Math.pow(2*j+1, 2))/rn;
		}	
			
			
			
		rn=1+1/rn;
		rn=4/rn;
		
		rn += 1/(double)i;
		
		return(rn);
			
		
		
		
		
		
		
		
		
	}
	
	
	public static void main(String[]args){
		
		Scanner scan = new Scanner(System.in);
		long inpu = scan.nextLong();
		
		
		System.out.println(findPie(inpu));
		
		
	
	}
	

}