//Ian Patterson
//All num integers are counters/placeholders
public class ISBN_Program {

	public static void main(String[] args) {
		String [] ISBN = {"0-7645-0417-7","0-7654-0417-6","0-912517-31-X","3-314-21145-7","91-29-65497-1","957-621-541-2"};
		System.out.println("ISBN Numbers: Given Chk#: Calculated Chk#:");
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
			int multby = 10,calcchecknum = 0;
			for(int num3:positions2){
				String character = ISBN[plchld].substring(num3,num3+1);
				int num4 = Integer.parseInt(character);
				calcchecknum = calcchecknum + multby*num4;
				multby--;
				}	
			calcchecknum = 10-((calcchecknum-1)%11);
			char chknum = ISBN[plchld].charAt(12);
			System.out.println(ISBN[plchld]+"      "+chknum+"             "+checknumbers.charAt(calcchecknum));
			
		}
	}
}
/*
ISBN Numbers: Given Chk#: Calculated Chk#:
0-7645-0417-7      7             7
0-7654-0417-6      6             6
0-912517-31-X      X             X
3-314-21145-7      7             7
91-29-65497-1      1             1
957-621-541-2      2             2
*/