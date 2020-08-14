import java.util.Scanner;

class findNextPrime{
	
	public static boolean isPrime(int a){
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
		
		
		
		
		if(a%2==0){
			return(false);
		}
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
		}
		
		
		
		for(int i=29;i<=(int)Math.sqrt(a);i++){
			
			if(a%i==0){
				return(false);
			}
			
			
		}
		return(true);
		
		
		
	}
	
	
	
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		for(int i=1;i!=-1;i++){
			if(isPrime(i+num)){
				System.out.println(i+num);
				break;
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
}