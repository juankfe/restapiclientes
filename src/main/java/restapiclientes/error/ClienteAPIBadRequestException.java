package restapiclientes.error;

public class ClienteAPIBadRequestException extends RuntimeException{
	
	public ClienteAPIBadRequestException (String message) {
		super(message);
	}

}
