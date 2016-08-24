//Ian Patterson
//All num integers are counters/placeholders.  ISBN array is "input"
public class ISBN_3 {

	public static void main(String[] args) {
		String [] ISBN = {"0-7645-0417-7","0-7654-0417-6","0-912517-31-X","3-314-21145-7","91-29-65497-1","957-621-541-2"};
		String checknumbers = "0123456789X";
		for(int plchld=0;plchld<ISBN.length;plchld++){
			int firstDash = ISBN[plchld].indexOf('-'), secondDash = ISBN[plchld].indexOf('-',firstDash+1), num5 = 0, num2 = 10;
			
			String newISBN = ISBN[plchld].substring(0, firstDash)+ISBN[plchld].substring(firstDash+1, secondDash)+
					ISBN[plchld].substring(secondDash+1, 11);
			int[] places = {0,1,2,3,4,5,6,7,8};
			
			for(int num3:places){
				String character = newISBN.substring(num3, num3+1);
				int num4 = Integer.parseInt(character);
				num5 = num5 + num2*num4;
				num2--;
				}	
			
			num5 = 10-((num5-1)%11);
			char chknum = ISBN[plchld].charAt(12);
			System.out.println("ISBN Number:       "+ISBN[plchld]);
			System.out.println("Check Digit From Number:       "+chknum);
			System.out.println("Check Digit From Calculations: "+checknumbers.charAt(num5));
			System.out.println();
		}
	}
}
/*
ISBN Number:       0-7645-0417-7
Check Digit From Number:       7
Check Digit From Calculations: 7

ISBN Number:       0-7654-0417-6
Check Digit From Number:       6
Check Digit From Calculations: 6

ISBN Number:       0-912517-31-X
Check Digit From Number:       X
Check Digit From Calculations: X

ISBN Number:       3-314-21145-7
Check Digit From Number:       7
Check Digit From Calculations: 7

ISBN Number:       91-29-65497-1
Check Digit From Number:       1
Check Digit From Calculations: 1

ISBN Number:       957-621-541-2
Check Digit From Number:       2
Check Digit From Calculations: 2
*/