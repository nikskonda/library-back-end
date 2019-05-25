package by.bntu.fitr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    private static final String INVALID_REQUEST_PARAMETERS = "exception.invalid_request_parameters";
    private static final String DURING_REQUEST_PROCESSING = "exception.during_request_processing";
    private static final String ACCESS_DENIED = "exception.access_denied";
    private static final String HTTP_MESSAGE_NOT_READABLE = "exception.http_message_not_readable";
    private static final String NOT_SUPPORTED_MEDIA_TYPE = "exception.http_not_supported_media_type";
    private static final String DUPLICATE_ENTITY = "exception.duplicate_entity";


    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(Exception ex, Locale locale) {
        LOGGER.error(messageSource.getMessage(ex.getMessage(), null, Locale.getDefault()), ex);
        return new ApiError(messageSource.getMessage(ex.getMessage(), null, locale));
    }

    @ExceptionHandler({UnsupportedOperationException.class, ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleServiceException(Exception ex, Locale locale) {
        LOGGER.error(messageSource.getMessage(ex.getMessage(), null, Locale.getDefault()), ex);
        return new ApiError(messageSource.getMessage(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBindException(BindException ex, Locale locale) {
        return new ApiError(createLogForValidation(locale, ex.getBindingResult(), ex).toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        return new ApiError(createLogForValidation(locale, ex.getBindingResult(), ex).toString());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(ConstraintViolationException e, Locale locale) {
        StringBuilder defLang = new StringBuilder();
        StringBuilder userLang = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            defLang.append(messageSource.getMessage(violation.getMessage(), null, Locale.getDefault()))
                    .append(' ');
            userLang.append(messageSource.getMessage(violation.getMessage(), null, locale))
                    .append(' ');
        }
        LOGGER.error(defLang.toString(), e);
        return new ApiError(userLang.toString());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, Locale locale) {
        LOGGER.error(messageSource.getMessage(INVALID_REQUEST_PARAMETERS, null, Locale.getDefault()), e);
        String message = messageSource.getMessage(INVALID_REQUEST_PARAMETERS, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ApiError handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, Locale locale) {
        LOGGER.error(messageSource.getMessage(NOT_SUPPORTED_MEDIA_TYPE, null, Locale.getDefault()), e);
        String message = messageSource.getMessage(NOT_SUPPORTED_MEDIA_TYPE, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleHttpMessageNotReadableException(Exception e, Locale locale) {
        LOGGER.error(messageSource.getMessage(HTTP_MESSAGE_NOT_READABLE, null, Locale.getDefault()), e);
        String message = messageSource.getMessage(HTTP_MESSAGE_NOT_READABLE, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAccessDeniedException(AccessDeniedException ex, Locale locale) {
        LOGGER.error(messageSource.getMessage(ACCESS_DENIED, null, Locale.getDefault()), ex);
        String message = messageSource.getMessage(ACCESS_DENIED, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleHibernateConstraintViolationException(Exception ex, Locale locale) {
        LOGGER.error(messageSource.getMessage(DUPLICATE_ENTITY, null, Locale.getDefault()), ex);
        String message = messageSource.getMessage(DUPLICATE_ENTITY, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex, Locale locale) {
        LOGGER.error(messageSource.getMessage(DUPLICATE_ENTITY, null, Locale.getDefault()), ex);
        String message = messageSource.getMessage(DUPLICATE_ENTITY, null, locale);
        return new ApiError(message);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInternalError(Throwable t, Locale locale) {
        LOGGER.error(t.getMessage(), t);
        String message = messageSource.getMessage(DURING_REQUEST_PROCESSING, null, locale);
        return new ApiError(message);
    }

    private StringBuilder createLogForValidation(Locale locale, BindingResult bindingResult, Exception e) {
        StringBuilder defLang = new StringBuilder();
        StringBuilder userLang = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error.getDefaultMessage().matches("^([a-zA-z_]+\\.)+([a-zA-z_]+)$")){
                defLang.append(messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()))
                        .append(' ');
                userLang.append(messageSource.getMessage(error.getDefaultMessage(), null, locale))
                        .append(' ');
            } else {

                if (error.getDefaultMessage().contains("NumberFormatException")){
                    defLang.append(messageSource.getMessage("exception.validation.number_format", null, Locale.getDefault()))
                            .append(' ');
                    userLang.append(messageSource.getMessage("exception.validation.number_format", null, locale))
                            .append(' ');
                } else {
                    defLang.append(error.getDefaultMessage())
                            .append(' ');
                    userLang.append(error.getDefaultMessage())
                            .append(' ');
                }
            }
        }
        LOGGER.error(defLang.toString(), e);
        return userLang;
    }
}
