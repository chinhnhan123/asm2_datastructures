import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; 

public class Message {
	ArrayList<String> A = new ArrayList<String>();		//Sending Buffer
	Queue<String> Q = new LinkedList<String>();			//Receiving Buffer
	int Buffersize = 30;						
	int sendMessege(String s)		
	{
		String destination = "";
		int i =0;
		String c;
		int count = 0;			//Count the number of buffer usage;	
		if(s.length() <=0)
		{
			System.out.println("The messege is empty !!!!!!!!!!!");
			return 0;
		}
		else if (s.length() > 250)
		{
			System.out.println("The messege is too long. Please enter less than 250 characters!!");
			return 0;
		}
		else
		{
			System.out.println("Length of the messege: " + s.length() + " letters.");
			try
			{
				while (i < s.length())
				{
					//1. move letters from source to A
					while(A.size() < Buffersize && i < s.length()) 
					{
						c = s.substring(i, i+1);				
						A.add(c);								
						i++;									
					}
					//2. move letters from A to Q
					while(A.size()>0)
					{
						c = A.get(0);						
						Q.add(c);						
						A.remove(0);						
					}
					//3. move letters from Q to destination
					while(Q.size()>0)
					{
						c = Q.poll();						
						destination = destination + c;				
					}
					count ++;
				}
				System.out.print("\nMessage sender: \n" + s);
				System.out.print("\nMessge received: \n" + destination);
			}
			catch(Exception error)
			{
				System.out.println(error.toString());
				count = 0;
			}
			return count;
		}
		
	}
	public static void main(String[] args) {
		Message M = new Message();
		System.out.print("Enter message : ");
        Scanner  scan  = new Scanner(System.in);
		String s     = scan.nextLine();

		double start = System.currentTimeMillis();		
		int count = M.sendMessege(s);
		double finish = System.currentTimeMillis();			
		double time = (finish - start)/M.Buffersize;
		double space = M.Buffersize * 4;

		if(count >0)	
		{
			System.out.print("\n\nThe messege is sent successfully.");
			System.out.print("\nThe number of buffer usage: " + count + " times.");
			System.out.printf("\nTime : %.5f ms", time);
			System.out.printf("\nSpace: %.1f byte", space);
		}
		else
			System.out.print("\nThe messege is not sent successfully.");
	}
}