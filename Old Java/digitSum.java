import java.io.*; //java package will be used
class digitSum
{
	public static void main (String [] args) throws IOException
	{
		InputStreamReader inStream = new InputStreamReader (System.in);
        BufferedReader stdin = new BufferedReader (inStream); //input will be used
		
		String input;
		int digits, firstdigit, seconddigit, thirddigit, sum; //declare variables
		
		System.out.println("Enter an integer value between 0 and 1000: "); //ask the user for a integer value between 0 to 1000
		input = stdin.readLine();
		digits = Integer.parseInt(input); //convert the input into integer and assign it to digits
		thirddigit = digits % 10;
		seconddigit = (digits / 10) % 10;
		firstdigit = digits / 100; //calculation
		
		sum = firstdigit + seconddigit + thirddigit; //calculation for the sum
		System.out.println("if the integer is " + digits + ", the sum of all its digits is " + sum); //output the the digit was entered and the sum 
	}
}