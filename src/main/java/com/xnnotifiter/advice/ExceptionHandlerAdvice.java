package com.xnnotifiter.advice;

import com.alibaba.fastjson.JSONObject;
import com.xnnotifiter.model.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public JsonResponse<JSONObject> exceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return JsonResponse.error(JsonResponse.ERROR_CODE, exception.getMessage());
    }
}
