import java.lang.Math;

class recursivePrimeChecker{
	
	public static boolean isPrime(int maybePrime, long[] primesArray, long[] sqrtArray, int sqrtIndex){
		boolean flag = true;
		for(int i=0;i<primesArray.length;i++){
			if(maybePrime%primesArray[i]==0){
				flag = false;
				break;
				
			}
			if(sqrtIndex>sqrtArray[i]){
				flag = false;
				break;
			}
			
			
		}
		
		
		return(flag);
		
	}
	
	
	
	public static void main(String[]args){
		int numberOfPrimesToFind = 100;//must be at least like 5
		
		long[] primesArray = new long[numberOfPrimesToFind];
		primesArray[0] = 2;
		primesArray[1] = 3;
		primesArray[2] = 5;
		primesArray[3] = 7;
		
		long[] sqrtArray = new long[numberOfPrimesToFind];
		sqrtArray[0] = 2;
		sqrtArray[1] = 2;
		sqrtArray[2] = 3;
		sqrtArray[3] = 3;
		int currentIndex = 4;
		
		for(int i=4;i<numberOfPrimesToFind;i++){
			int s = (int)(Math.sqrt(i));
			if(isPrime(i,primesArray,sqrtArray,s)){
				primesArray[currentIndex] = i;
				sqrtArray[currentIndex] = (int)Math.sqrt(i)+1;
				currentIndex++;
				
				
				
			}
			
		}
		
		for(int i=0;i<numberOfPrimesToFind;i++){
			System.out.println(primesArray[i]);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}