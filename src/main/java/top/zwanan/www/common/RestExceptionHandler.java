package top.zwanan.www.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * security 异常处理
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    public RestBean<Void> handleSecurityException(AuthenticationException e) {
        return RestBean.failure(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * 放行 Security 的异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    public void handleAccessDeniedException(AccessDeniedException e) {
        throw e;
    }

    /**
     * Spring Validation 异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public RestBean<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String message = objectError.getDefaultMessage();
        log.warn("字段错误 {}", message);
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(value = RestException.class)
    @ResponseStatus(HttpStatus.OK)
    public RestBean<String> handleRestException(RestException e) {
        log.error("业务错误,{}", e.getMessage());
        return RestBean.failure(e.code, e.getMessage());
    }

    @ExceptionHandler(value = ServletException.class)
    @ResponseStatus(HttpStatus.OK)
    public RestBean<String> handleServletException(ServletException e) {
        log.error("HttpStatus.BAD_REQUEST", e);
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 其他异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public RestBean<String> handleException(Exception e) {
        log.error("服务器内部错误", e);
        return RestBean.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务器内部错误");
    }


}
