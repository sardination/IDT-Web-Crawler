
public class Input {
	private String type = "";
	private String value = "";
	private String inputID = "";
	private String tag = "";
	
	public Input (String type, String value, String inputID, String tag) {
		this.type = type.toLowerCase();
		this.value = value;
		this.inputID = inputID;
		this.tag = tag;
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
	
	public String getTag() {
		return tag;
	}
	
	public String toString() {
		return "Type: " + type +" Value: " + value + " InputID: " + inputID;
	}
}
