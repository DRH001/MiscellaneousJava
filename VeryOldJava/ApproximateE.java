import java.util.Scanner;


class ApproximateE{
	
	
	public static int factorial(int a){
		int b = 1;
		for(int i=1;i<=a;i++){
			b*=i;
		}
		
		return(b);
		
	}
	
	
	
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		double e = 0;
		
		for(int i = 1; i < n; i++){
			e+=1.0/(double)(factorial(i));//sum of 1/n!
			System.out.println(e+1);
			
		}
		
		
		
		
		
		
	}
	
	
	
	
	
}
