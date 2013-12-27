package gui;

public class Message {
	String title;
	String message;

	public Message() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message(String title, String message) {
		super();
		this.title = title;
		this.message = message;
	}

}
