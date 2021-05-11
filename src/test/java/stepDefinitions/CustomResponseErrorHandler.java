package stepDefinitions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();
	public boolean hasError(ClientHttpResponse response) throws IOException{
		HttpStatus statusCode = response.getStatusCode();
		if(statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
			return false;
		}else {
			return errorHandler.hasError(response);
		}
	}
	public void handleError(ClientHttpResponse response) {
		
	}
}
