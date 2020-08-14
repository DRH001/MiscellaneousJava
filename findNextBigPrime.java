import java.util.Scanner;

class findNextBigPrime{
	
	public static boolean isPrime(long a){
		if(a==1){
			return(false);
		}
		if(a==2){
			return(true);
		}
		if(a==3){
			return(true);
		}
		if(a==4){
			return(false);
		}
		if(a==5){
			return(true);
		}
		if(a%2==0){
			return(false);
		}\
		
		
		
		for(int i=3;i<=Math.sqrt(a);i+=2){
			
			if(a%i==0){
				return(false);
			}
			
			
		}
		return(true);
		
		
		
	}
	
	
	
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		long num = scan.nextLong();
		
		for(int i=0;i!=-1;i++){
			if(isPrime(i+num)){
				System.out.println(i+num);
				break;
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
}