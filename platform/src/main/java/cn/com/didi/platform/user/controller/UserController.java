package cn.com.didi.platform.user.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.query.CountObject;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.Role;

import static cn.com.didi.domain.util.NameConstans.*;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantExtDto;
import cn.com.didi.user.users.domain.MerchantHolderDto;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;

@RestController
public class UserController {
	@Resource
	protected IMerchantService service;
	@Resource
	protected IUserService userService;

	@RequestMapping(value = "/platform/b/list", method = RequestMethod.POST)
	public IResult selectMerchants(@RequestBody TimeInterval interval) {
		IPage<MerchantDto> page = service.selectMerchants(interval);
		if (page == null) {
			return new ResultExt<>(null, CountObject.ZERO);
		}
		CountObject co = new CountObject(page.getCount());
		ResultExt<List<MerchantDto>, CountObject> result = new ResultExt<>(page.getList(), co);
		return result;
	}

	@RequestMapping(value = "/platform/b/detail", method = RequestMethod.POST)
	public IResult merchantDetail(@RequestBody UserDto dto) {
		AssertUtil.assertNotNullAppend(dto.getAccountId(), ACCOUNT_ID);
		MerchantHolderDto extDto = service.getMerchant(dto.getAccountId());
		if (extDto == null) {
			return ResultFactory.success();
		}
		return ResultFactory.success(extDto.toMerchantExtDto());

	}

	@RequestMapping(value = "/platform/b/add", method = RequestMethod.POST)
	public IResult addMerchant(@RequestBody MerchantExtDto dto) {
		AssertUtil.assertNotNullAppend(dto.getAccountId(), ACCOUNT_ID);
		dto.setAccountId(null);
		service.addMerchant(dto.dto(), dto.getServiceList(), dto.getAreaList());
		return ResultFactory.success();

	}

	@RequestMapping(value = "/platform/c/list", method = RequestMethod.POST)
	public IResult selectUsers(@RequestBody TimeInterval interval) {

		String key = interval.getKey();
		if (!StringUtils.isEmpty(key) && Pattern.matches("^\\d+$", key)) {
			try {
				interval.setAccountId(Long.parseLong(key));
			} catch (Exception e) {

			}
		}
		IPage<UserDto> page = userService.selectUsers(interval);
		if (page == null) {
			return new ResultExt<>(null, CountObject.ZERO);
		}
		CountObject co = new CountObject(page.getCount());
		ResultExt<List<UserDto>, CountObject> result = new ResultExt<>(page.getList(), co);
		return result;
	}

	/***/
	@RequestMapping(value = { "/platform/c/onOff", "/platform/account/onOff" }, method = RequestMethod.POST)
	public IResult onOff(@RequestBody UserDto dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		AssertUtil.assertNotNullAppend(dto.getAccountId(), ACCOUNT_ID);
		AssertUtil.assertNotNullAppend(dto.getState(), STATE);
		userService.updateUserState(dto.getAccountId(), dto.getState());
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/account/list", method = RequestMethod.POST)
	public IResult accountList(@RequestBody TimeInterval interval) {
		IPage<UserDto> page = userService.selectPlatformUsers(interval);
		if (page == null) {
			return new ResultExt<>(null, CountObject.ZERO);
		}
		CountObject co = new CountObject(page.getCount());
		ResultExt<List<UserDto>, CountObject> result = new ResultExt<>(page.getList(), co);
		return result;
	}

	@RequestMapping(value = "/platform/account/add", method = RequestMethod.POST)
	public IResult accountAdd(@RequestBody UserDto dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		AssertUtil.assertNotNullAppend(dto.getUserName(), USER_NAME);
		AssertUtil.assertNotNullAppend(dto.getPassword(), PASSWORD);
		dto.setRole(Role.PLATFORM.getCode());
		dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));
		userService.addUser(dto);
		return ResultFactory.success();
	}
	@RequestMapping(value = "/platform/account/update", method = RequestMethod.POST)
	public IResult accountUpdate(@RequestBody UserDto dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		AssertUtil.assertNotNullAppend(dto.getAccountId(), ACCOUNT_ID);
		dto.setRole(Role.PLATFORM.getCode());
		dto.setCreateTime(null);
		if(!StringUtils.isEmpty(dto.getPassword())){
			dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));
		}
		userService.updatePlatformUser(dto);
		return ResultFactory.success();
	}
}
