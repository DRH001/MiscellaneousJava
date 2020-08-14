

class TaylorSeriesOfSine{
	
	
	public static void main(String[]args){
		System.out.print("x");
		for(int i = 1;i<21;i++){//change the final value of i to get more values
			if(i%2==0){
				System.out.print(" +\\frac{x^{" + (2*i+1) + "}}{" + (2*i+1) + "!}");
				
			}else{
				System.out.print(" -\\frac{x^{" + (2*i+1) + "}}{" + (2*i+1) + "!}");
				
				
			}
			//				\frac{x^{3}}{3!}
			
			
		}	
		
		
		
		
	}
	
		
	
}