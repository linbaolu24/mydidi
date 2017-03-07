package cn.com.didi.webBase.file.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.didi.core.excpetion.BaseRuntimeException;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.DomainMessageConstans;

@Component
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvisor {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public IResult<Object> handleException(Exception e) {
		LOGGER.error("" + e.getMessage(), e);
		return ResultFactory.error(DomainMessageConstans.CODE_SYSTEM_ERROR, "系统异常");

	}

	@ExceptionHandler(BaseRuntimeException.class)
	@ResponseBody
	public IResult handleException(BaseRuntimeException e) {
		LOGGER.error("" + e.getMessage(), e);
		return ResultFactory.error(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public IResult handleException(IllegalArgumentException e) {
		LOGGER.error("" + e.getMessage(), e);
		return ResultFactory.error(DomainMessageConstans.CODE_PARAM_ILLEGAL_ERROR, e.getMessage());
	}
}
