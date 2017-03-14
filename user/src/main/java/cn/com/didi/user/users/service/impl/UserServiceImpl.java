package cn.com.didi.user.users.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.ICodec;
import cn.com.didi.core.property.ICodecManager;
import cn.com.didi.core.property.impl.codec.ArrayCodec;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.users.dao.mapper.UserDtoMapper;
import cn.com.didi.user.users.dao.mapper.UserLinkIdDtoMapper;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserDtoExample;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.domain.UserLinkIdDtoExample;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.util.MessageConstans;

@Service
public class UserServiceImpl implements IUserService, InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	UserDtoMapper userDtoMapper;
	// @Value("${user.pcode:AES,BASE64}")
	@Value(value = "${user.pcode:null}")
	protected String pcode;
	protected ICodec codec;
	@Resource
	protected ICodecManager codeManager;
	@Resource
	protected UserLinkIdDtoMapper userLinkIdDtoMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (codec == null && !StringUtils.isBlank(pcode) && !"null".equals(pcode)) {
			String[] types = pcode.split(",");
			ICodec[] codecArray = codeManager.find(types);
			codec = new ArrayCodec(codecArray);
		}
	}

	@Override
	public UserDto selectUser(Long accountId) {
		return userDtoMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public void addUser(UserDto userDto) {
		if (userDto == null) {
			return;
		}
		codePassword(userDto);
		userDtoMapper.insertSelective(userDto);
	}

	protected void codePassword(UserDto userDto) {
		if (codec != null) {
			try {
				byte[] b = codec.code(userDto.getPassword().getBytes(Constans.CHARSET_UTF_8));
				String pw = new String(b, Constans.CHARSET_UTF_8);
				userDto.setPassword(pw);
				userDto.setPcode(pcode);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new MessageObjectException(MessageConstans.USER_PASSCORD_CODE_ERROR);

			}
		}
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public ICodec getCodec() {
		return codec;
	}

	public void setCodec(ICodec codec) {
		this.codec = codec;
	}

	public ICodecManager getCodeManager() {
		return codeManager;
	}

	public void setCodeManager(ICodecManager codeManager) {
		this.codeManager = codeManager;
	}

	@Override
	public String getPassword(String userName, String role) {
		UserDto dto = selectUser(userName, role);
		if (dto == null) {
			return null;
		}
		String pcode = dto.getPcode();
		return getPasswordR(dto.getPassword(), pcode);

	}

	public String getPasswordR(String password, String pcode) {
		if (StringUtils.isBlank(pcode) && !"null".equals(pcode)) {
			return password;
		}
		String[] types = pcode.split(",");
		ICodec codec = new ArrayCodec(codeManager.find(types));
		byte[] source = null;
		try {
			source = codec.decode(password.getBytes(Constans.CHARSET_UTF_8));
			return new String(source, Constans.CHARSET_UTF_8);
		} catch (Exception e) {// 解密异常
			throw new MessageObjectException(MessageConstans.USER_PASSCORD_DECODE_ERROR);
		}
	}

	@Override
	public UserDto selectUser(String userName, String role) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(role)) {
			return null;
		}
		UserDtoExample example = new UserDtoExample();
		UserDtoExample.Criteria cri = example.createCriteria();
		cri.andUserNameEqualTo(userName);
		cri.andRoleEqualTo(role);
		List<UserDto> list = userDtoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		UserDto dto = list.get(0);
		if (dto != null) {
			String real = getPasswordR(dto.getPassword(), dto.getPcode());
			dto.setPassword(real);
		}
		return dto;
	}

	@Override
	public IPage<UserDto> selectUsers(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<UserDto> list = (PageList<UserDto>) userDtoMapper.selectUsers(interval,pageBounds);
		return new MybatisPaginatorPage<>(list);
	}
	
	@Override
	public IPage<UserDto> selectPlatformUsers(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<UserDto> list = (PageList<UserDto>) userDtoMapper.selectUsers(interval,pageBounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	public void updateUserState(Long accountId, String state) {
		UserDto dto = new UserDto();
		dto.setState(state);
		dto.setAccountId(accountId);
		userDtoMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public int updatePassword(String userName, String role,String newPassword) {
		UserDtoExample example=new UserDtoExample();
		UserDtoExample.Criteria cri=example.createCriteria();
		cri.andUserNameEqualTo(userName);
		cri.andRoleEqualTo(role);
		UserDto dto=new UserDto();
		dto.setPassword(newPassword);
		codePassword(dto);
		return userDtoMapper.updateByExampleSelective(dto, example);
	}

	@Override
	public int updatePassword(String userName, String role, String newPassword, String oPassword) {
		UserDto dto=selectUser(userName, role);
		verifyPassword(dto, oPassword);
		return updatePassword(userName, role, newPassword);
	}
	protected void verifyPassword(UserDto dto,String oPassword){
		if(dto==null){
			throw new MessageObjectException(MessageConstans.USER_USER_NOT_EXISTS);// 用户不存在
		}
		String existPassword=getPasswordR(dto.getPassword(), dto.getPcode());
		if(!existPassword.equals(oPassword)){
			throw new MessageObjectException(MessageConstans.USER_PASSOWRD_NOT_EQUAL);// 密码不一致
		}
	}
	@Override
	public int updatePassword(Long accountID, String newPassword, String oPassword) {
		UserDto exdto=selectUser(accountID);
		verifyPassword(exdto, oPassword);
		UserDto dto=new UserDto();
		dto.setPassword(newPassword);
		dto.setAccountId(accountID);
		codePassword(dto);
		return userDtoMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public UserLinkIdDto selectUserLinkedId(Long accountId) {
		return userLinkIdDtoMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public int updateLinkedId(Long accountId, String gtCid, String ryToken) {
		UserLinkIdDto dto=new UserLinkIdDto();
		dto.setRyToken(ryToken);
		dto.setGtCid(gtCid);
		dto.setAccountId(accountId);
		return userLinkIdDtoMapper.updateId(dto);
		
	}

	@Override
	public void updatePlatformUser(UserDto dto) {
		dto.setCreateTime(null);
		UserDtoExample example=new UserDtoExample();
		UserDtoExample.Criteria cri=example.createCriteria();
		cri.andAccountIdEqualTo(dto.getAccountId());
		cri.andRoleEqualTo(dto.getRole());
		userDtoMapper.updateByExampleSelective(dto, example);
		
	}

	@Override
	public List<UserLinkIdDto> selectUserLinkedId(List<Long> accountId) {
		if(CollectionUtils.isEmpty(accountId)){
			return null;
		}
		UserLinkIdDtoExample example=new UserLinkIdDtoExample();
		UserLinkIdDtoExample.Criteria cri= example.createCriteria();
		cri.andAccountIdIn(accountId);
		return userLinkIdDtoMapper.selectByExample(example);
	}



}
