import java.util.Scanner;

class findNextNBigPrimes{
	
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
		}/*
		if(a==7){
			return(true);
		}
		if(a==11){
			return(true);
		}
		if(a==13){
			return(true);
		}
		if(a==17){
			return(true);
		}
		if(a==19){
			return(true);
		}
		if(a==23){
			return(true);
		}
		if(a==29){
			return(true);
		}
		
		
		
		*/
		if(a%2==0){
			return(false);
		}/*
		if(a%3==0){
			return(false);
		}
		if(a%5==0){
			return(false);
		}
		if(a%7==0){
			return(false);
		}
		if(a%11==0){
			return(false);
		}
		if(a%13==0){
			return(false);
		}
		if(a%17==0){
			return(false);
		}
		if(a%19==0){
			return(false);
		}
		if(a%23==0){
			return(false);
		}
		if(a%29==0){
			return(false);
		}*/
		
		
		
		for(int i=3;i<=Math.sqrt(a);i+=2){
			
			if(a%i==0){
				return(false);
			}
			
			
		}
		return(true);
		
		
		
	}
	
	
	
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		System.out.println("enter the number of primes you wish to find");
		int N = scan.nextInt();
		long num = scan.nextLong();
		
		
		for(int i=0;i!=-1;i++){
			if(isPrime(i+num)){
				System.out.println(i+num);
				N--;
				if(N==0){
					break;
				}
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
}