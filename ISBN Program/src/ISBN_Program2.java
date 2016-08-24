//Ian Patterson
//All num integers are counters/placeholders
public class ISBN_Program2 {

	public static void main(String[] args) {
		String [] ISBN = {"0-7645-0417-7","0-7654-0417-6","0-912517-31-X","3-314-21145-7","91-29-65497-1","957-621-541-2"};
		String checknumbers = "0123456789X";
		for(int plchld=0;plchld<ISBN.length;plchld++){
			int [] positions = {0,1,2,3,4,5,6,7,8,9,10,11}, positions2 = {1,2,3,4,5,6,7,8,9};
			int num1 = 0;
			for(int num:positions){
				boolean isdash = ISBN[plchld].charAt(num)=='-';
				if (!isdash){
					positions2[num1] = positions[num];
					num1++;
					}
				}
			int num2 = 10,num5 = 0;
			for(int num3:positions2){
				String character = ISBN[plchld].substring(num3,num3+1);
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