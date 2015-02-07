
public class Input {
	private String type = "";
	private String value = "";
	private String inputID = "";
	
	public Input (String type, String value, String inputID) {
		this.type = type;
		this.value = value;
		this.inputID = inputID;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getID() {
		return inputID;
	}
	
	public String toString() {
		return "Type: " + type +" Value: " + value + " InputID: " + inputID;
	}
}
