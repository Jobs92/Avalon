package eventHandling;

public class Event {
	private String text;
	private String type;
	private int value;
	
	
	public Event(String text, String type,int value){
		this.text=text;
		this.type=type;
		this.value=value;
	}


	public String getText() {
		return text;
	}


	public String getType() {
		return type;
	}





	public int getValue() {
		return value;
	}
	


	

	

}
