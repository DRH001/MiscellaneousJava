import java.util.Scanner;
//import java.util.Random;



//random dot testing
class randomDotPi{
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		
		long dots = scan.nextLong();
		
		double in = 0;
		double total = 0;
		double x = 0;
		double y = 0;
		
		
		
		for(int i=0;i<dots;i++){
			
			x = Math.random();
			y = Math.random();
			
			if(Math.pow(x,2)+Math.pow(y,2) < 1){
				in++;
			}
			
			
			total++;
			
			System.out.print(in/total * 4);
			System.out.println(" " + in + "/" + total);
			
		}
		
		//System.out.println(in/total * 4);
			
		
		
		
	}

}
