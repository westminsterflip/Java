import java.io.*;
public class Morse {
	
	private String morse;
	
	public Morse(){
		morse = "";
	}
	
	public void setMorse(String morsey){
		morse = morsey;
	}
	
	public String getMorse(){
		return morse;
	}
	
	public void inputMagicSauces() throws IOException{
		InputStreamReader mjicmik = new InputStreamReader(System.in);
		BufferedReader smashewi = new BufferedReader(mjicmik);
		
		System.out.print("Input thing to be morseified: ");
		setMorse(smashewi.readLine());
	}
	
	public String toString(){
		String chars = "∑œAaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz11223344556677889900  ,,..", huehue = "";
		String[] mChars = {"fillerama",".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-","-..-","-.--","--..",".----","..---","...--","....-",".....","-....","--...","---..","----.","-----","~","","~~"};
		String blah = getMorse();
		for (int count = 0; count < blah.length();count++){
			int pos = (int) Math.round(chars.indexOf(blah.substring(count,count+1))/2);
			huehue += mChars[pos];
		}
		return huehue;
	}
	
	public static void main(String[] args) throws IOException{
		Morse mosiudfhs = new Morse();
		for(int counter = 0; counter<100;){
			mosiudfhs.inputMagicSauces();	
			System.out.println(mosiudfhs.toString());
		}
	}
}
