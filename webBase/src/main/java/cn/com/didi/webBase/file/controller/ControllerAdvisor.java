package cn.com.didi.webBase.file.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.didi.core.property.impl.result.Result;

@Component
@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvisor {
	private static final Logger LOGGER=LoggerFactory.getLogger(ControllerAdvisor.class);
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<Object> handleException(Exception e){
		LOGGER.error(""+e.getMessage(), e);
		return null;
		
	}
}
