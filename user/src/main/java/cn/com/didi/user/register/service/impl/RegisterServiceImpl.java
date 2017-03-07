package cn.com.didi.user.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.IEnvironment;
import cn.com.didi.domain.util.State;
import cn.com.didi.thirdExt.util.EnvConstants;
import cn.com.didi.user.register.dao.mapper.PhoneVcDtoMapper;
import cn.com.didi.user.register.domain.PhoneVcDto;
import cn.com.didi.user.register.domain.PhoneVcDtoExample;
import cn.com.didi.user.register.domain.RegisterDto;
import cn.com.didi.user.register.service.IRegisterService;
import cn.com.didi.user.register.service.ISendVcService;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.util.MessageConstans;

@Service
public class RegisterServiceImpl implements IRegisterService {
	private static final Logger LOGGER=LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Resource
	protected PhoneVcDtoMapper mapper;
	@Resource
	protected ISendVcService sendVcService;
	@Resource
	protected IUserService userService;
	@Resource
	protected IEnvironment didEnvironment;

	@Override
	public Long sendVc(String phone) {
		PhoneVcDtoExample example = new PhoneVcDtoExample();
		PhoneVcDtoExample.Criteria cri = example.createCriteria();
		cri.andPhoneEqualTo(phone);
		cri.andStateEqualTo(State.VALID.getState());
		long now = System.currentTimeMillis();
		int mmdelay = getDelay();
		Date time = new Date(now - getDelay(mmdelay));
		cri.andCreateTimeGreaterThanOrEqualTo(time);
		List<PhoneVcDto> list = mapper.selectByExample(example);
		PhoneVcDto dto;
		if (CollectionUtils.isEmpty(list)) {
			dto = new PhoneVcDto();
			dto.setVc(RandomUtils.nextInt(9000) + 1000);
			dto.setPhone(phone);
			dto.setState(State.VALID.getState());
			dto.setCreateTime(new Date(now));
			dto.setLtt(dto.getCreateTime());
			mapper.insertSelective(dto);
		} else {
			dto = list.get(0);
			if (now - dto.getLtt().getTime() <= getInterval()) {
				throw new MessageObjectException(MessageConstans.USER_VC_TOO_OFEN);// 发送验证码太频繁
			}
			updateLLT(new Date(now), dto.getVcId());

		}
		sendVcService.send(phone, dto.getVc(), mmdelay);
		return dto.getVcId();
	}

	protected void updateLLT(Date LLT, Long vcId) {
		PhoneVcDto dto = new PhoneVcDto();
		dto.setLtt(LLT);
		dto.setVcId(vcId);
		mapper.updateByPrimaryKeySelective(dto);
	}
	
	protected void updateState(String state, Long vcId) {
		PhoneVcDto dto = new PhoneVcDto();
		dto.setState(state);
		dto.setVcId(vcId);
		mapper.updateByPrimaryKeySelective(dto);
	}


	/**
	 * 返回验证码有效时间单位分
	 * 
	 * @return
	 */
	protected int getDelay() {
		String env = didEnvironment.getProperty(EnvConstants.VC_VALID_TIME_MM);
		if (!StringUtils.isEmpty(env)) {
			return Integer.parseInt(env);
		}
		return 30;
	}

	protected long getDelay(int mm) {
		return mm * 60 * 1000;
	}

	protected long getInterval() {
		String env = didEnvironment.getProperty(EnvConstants.VC_VALID_TIME_INTERVAL);
		if (!StringUtils.isEmpty(env)) {
			return Integer.parseInt(env);
		}
		return 59 * 1000;
	}

	@Override
	public void resetPassword(RegisterDto dto) {
		verifyVc(dto);
		userService.updatePassword(dto.getPhone(), dto.getRole(), dto.getPassword());
		try{
			updateState(State.UNVALID.getState(),dto.getVcId());
		}catch(Exception e){
			LOGGER.error(""+e.getMessage(),e);
		}
	}

	protected void verifyVc(RegisterDto dto) {
		PhoneVcDto pDto = mapper.selectByPrimaryKey(dto.getVcId());
		if (pDto==null||!State.VALID.getState().equals(pDto.getState())
				|| (System.currentTimeMillis() - pDto.getCreateTime().getTime()) > getDelay(getDelay())) {
			throw new MessageObjectException(MessageConstans.USER_VC_UNVALID);// 注册码失效
		}
		if(pDto.getVc()!=Integer.parseInt(dto.getVc())){
			throw new MessageObjectException(MessageConstans.USER_VC_NOT_EQUAL);
		}
	}

	@Override
	public Long register(RegisterDto dto) {
		verifyVc(dto);
		UserDto userDto = userService.selectUser(dto.getPhone(), dto.getRole());
		if (userDto != null) {
			throw new MessageObjectException(MessageConstans.USER_USER__EXISTS);// 用户已存在
		}
		UserDto users = dto.toUserDto(State.VALID.getState(), new Date());
		try{
			updateState(State.UNVALID.getState(),dto.getVcId());
		}catch(Exception e){
			LOGGER.error(""+e.getMessage(),e);
		}
		userService.addUser(users);
		return users.getAccountId();
	}

	@Override
	public void changePassword(Long accoutId, String newPassword, String oldPassword) {
		userService.updatePassword(accoutId, newPassword, oldPassword);
	}

}
