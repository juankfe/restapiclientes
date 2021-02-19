package restapiclientes.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClienteAPIExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Dispara, por exemplo, se passar um parâmetro string onde deveria ser um Long.
	@ExceptionHandler({MethodArgumentTypeMismatchException.class}) 
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	MethodArgumentTypeMismatchException ex, WebRequest request) 
	{ 
		ClienteAPIError apiError = new ClienteAPIError(
				HttpStatus.BAD_REQUEST, "Parâmetros inválidos.",
				"MethodArgumentTypeMismatchException. Erro no request.");
		
		return new ResponseEntity<Object>(
	    		apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);	 
	}
	
	//Utilizado no controller quando parâmetros não atendem validação.
	@ExceptionHandler(value = {ClienteAPIBadRequestException.class})
	public ResponseEntity<Object> handleClienteAPIBadRequestException(
			ClienteAPIBadRequestException ex, WebRequest request) {	
		
		ClienteAPIError apiError = new ClienteAPIError(
				HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				"ClienteAPIBadRequestException. Erro no request.");
	    
	    return new ResponseEntity<Object>(
	    		apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);		 
		
	}
	
	
	//Utilizado no controller quando não encontra resultados para os parâmetros informados.
	@ExceptionHandler(value = {ClienteAPINotFoundException.class})
	public ResponseEntity<Object> handleClienteAPINotFoundException(
			ClienteAPINotFoundException ex, WebRequest request) {	
		
		ClienteAPIError apiError = new ClienteAPIError(
				HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
				"ClienteAPINotFoundException. Não encontrado.");
	    
	    return new ResponseEntity<Object>(
	    		apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);		 
		
	}
	
	//Utilizado no controller para erros genéricos.
	@ExceptionHandler(value = {ClienteAPIException.class})
	public ResponseEntity<Object> handleClienteAPIException(
			ClienteAPIException ex, WebRequest request) {	
		
		ClienteAPIError apiError = new ClienteAPIError(
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
				"ClienteAPIException. Request não pode ser executado.");
	    
	    return new ResponseEntity<Object>(
	    		apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		 
		
	}

	//Dispara quando ocorre um erro que não foi pego pelos anteriores.
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {	
		
		ClienteAPIError apiError = new ClienteAPIError(
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Ocorreu um erro.");
	    
	    return new ResponseEntity<Object>(
	    		apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		 
		
	}
	
	
	
	
	/*
	
	
	
	
	@ExceptionHandler({ClienteAPICustomException.class})
	public ResponseEntity<Object> testException(ClienteAPICustomException e, ServletWebRequest request) {

	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {	
		
	    return new ResponseEntity<>(
	    		ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		 
		
	}
	
	*/
	
	
	/*
	
	
	
	*/

}
