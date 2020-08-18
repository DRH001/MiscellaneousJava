import java.util.Scanner;


class RandomPi{
	
	public static void main(String[]args){
		long nume = 0;
		long denomi = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("how many random points?");
		long stahp = scan.nextLong();
		System.out.println("interval for showing current value?");
		long show = scan.nextLong();
		double currX = 0.0;
		double currY = 0.0;
		
		for(long i=0; i<stahp; i++){
			currX = Math.random();
			currY = Math.random();
			if(currX*currX+currY*currY<=1.0){
				nume += 1;
				
				
				
				
			}
			
			denomi += 1;
			
			if(i%show==0){
			System.out.print(((double)nume / (double)denomi)*4);
			System.out.println("     " + nume + "/" + denomi);
			}
			
			
			
			
		}
		
		
		
		System.out.print(((double)nume / (double)denomi)*4);
		System.out.println("     " + nume + "/" + denomi);
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}