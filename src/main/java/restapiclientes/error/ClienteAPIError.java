package restapiclientes.error;

import org.springframework.http.HttpStatus;

public class ClienteAPIError {
	
	private HttpStatus status;
    private String customMessage;
    private String customError;

    public ClienteAPIError(HttpStatus status, String customMessage, String customError) {
        super();
        this.status = status;
        this.customMessage = customMessage;
        this.customError = customError;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public String getCustomError() {
		return customError;
	}

	public void setCustomError(String customError) {
		this.customError = customError;
	}
    
    
    
}
