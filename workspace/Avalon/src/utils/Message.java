package utils;

/**
 * @author Martin Message class for inbox messages.
 */
public class Message {
	public static int PRIVATE = 0;
	public static int GAME = 1;
	public static int BROADCAST = 2;

	private String title;
	private int targetPlayer;
	private int sourcePlayer;
	private String message;
	private int type;

	public Message(String title, String message, int targetPlayer,
			int sourcePlayer, int type) {
		super();
		this.message = message;
		this.targetPlayer = targetPlayer;
		this.sourcePlayer = sourcePlayer;
		this.message = message;
		this.type = type;
	}

	public Message() {
	}

	public String getTitle() {
		return title;
	}

	public int getTargetPlayer() {
		return targetPlayer;
	}

	public int getSourcePlayer() {
		return sourcePlayer;
	}

	public String getMessage() {
		return message;
	}

	public int getType() {
		return type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTargetPlayer(int targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public void setSourcePlayer(int sourcePlayer) {
		this.sourcePlayer = sourcePlayer;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setType(int type) {
		this.type = type;
	}

}
