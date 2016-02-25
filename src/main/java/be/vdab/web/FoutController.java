package be.vdab.web;

import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class FoutController {
	public static final String VIEW = "fout";
	public static final String NOT_FOUND = "404";
	public static final String ERROR = "500";

	@ExceptionHandler(Exception.class)
	public String foutPagina() {
		return VIEW;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String notFound(){
		return NOT_FOUND;
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public String internalServerError(){
		return ERROR;
	}
}