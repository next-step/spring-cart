package cart.exception;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ServiceExceptionHandler {
	@ExceptionHandler(value = NoSuchElementException.class)
	public String handleNoSuchElementException() {
		return "error/404";
	}

	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleExceptionHandler(ServiceException e) {
		ModelAndView modelAndView = new ModelAndView();
		ErrorType errorType = e.getErrorType();
		modelAndView.setViewName("error/error_page");
		modelAndView.addObject("error",
			new ErrorResponse(e.getErrorType().getStatusValue(), e.getMessage()));
		modelAndView.setStatus(errorType.getErrorStatus());
		return modelAndView;
	}
}
