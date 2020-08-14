import java.util.Scanner;

class ccc2{
	//this is a VERY old program from my high-school days. Evidently I didn't know what a switch statement was back then, but I've left this file as-is
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
		int T = scan.nextInt();
		String[] list = new String[T];
		//System.out.println(isPrime(T));
		for(int j=0; j<T;j++){
			int num = scan.nextInt();
			for(int i=num-1;i>=2;i--){
				if(isPrime(i)){
					if(isPrime(2*num-i)){
						//int num2 = 2*num-i;
						//System.out.println(i + " " + (num-i));
						list[j] = (i + " " + (2*num-i));
						break;
					}
				}
				
				
			}
			
			
			
		}
		
		for(int i=0;i<T;i++){
			System.out.println(list[i]);
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
