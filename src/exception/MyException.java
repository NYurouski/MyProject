package exception;

public class MyException extends Exception
{
	private String text;
	
	public MyException(String text)
	{
		this.text=text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
