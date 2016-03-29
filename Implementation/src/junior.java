public class junior extends hsstu{
	String[] key = {"Below Basic", "Basic", "Proficient", "Advanced"};
	private int keys;
	
	public junior(){}
	
	public String getKeys() {
		return key[keys];
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public String toString(){
		String output = super.toString();
		output += "Keystone: " + key[keys];
		return output;
	}

}
