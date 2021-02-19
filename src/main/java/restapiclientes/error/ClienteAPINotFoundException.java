package restapiclientes.error;

public class ClienteAPINotFoundException extends RuntimeException{
	
	public ClienteAPINotFoundException (String message) {
		super(message);
	}

}
