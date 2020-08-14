
import java.util.Scanner;



class FactorsPls{
	
	public static int factorMe;
	
	
	
	public static void bump(){
		System.out.print(" ");
	}
	
	
	
	public static void nextFactor(int a){
		int temp = a;
		
		if(temp<0){
			System.out.print("-1");
			bump();
			factorMe*=-1;
			nextFactor(temp*(-1));
			
		}
		
		if(temp==0){
			System.out.println("infinite factors!");
			System.exit(0);
		}
		
		if(temp==1){
			System.out.print("1");
			System.exit(0);
		}
		
		
		for(long i=2;i<=factorMe-1;i+=1){
			
			if(temp%i==0){
				temp/=i;
				System.out.print(i);
				bump();
				nextFactor(temp);
				
			}
		
		
		}
		System.out.println(temp);
		System.exit(0);
		
		
		
		
		
	}
	
	
	
	public static void main(String[]args){
		System.out.println(">>: Enter a number to get its prime factors");
		Scanner scan = new Scanner(System.in);
		factorMe = scan.nextInt();
		System.out.print(">>; ");
		nextFactor(factorMe);
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}