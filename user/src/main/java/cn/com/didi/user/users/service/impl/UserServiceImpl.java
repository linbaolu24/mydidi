package cn.com.didi.user.users.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.ICodec;
import cn.com.didi.core.property.ICodecManager;
import cn.com.didi.core.property.impl.codec.ArrayCodec;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.select.IPageBound;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.Role;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.select.ListPage;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.register.service.ISendVcService;
import cn.com.didi.user.users.dao.mapper.UserDtoMapper;
import cn.com.didi.user.users.dao.mapper.UserLinkIdDtoMapper;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserDtoExample;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.domain.UserLinkIdDtoExample;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.users.service.IUserThirdAccountService;
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
	@Resource
	protected ISendVcService sendVcService;
	@Resource
	protected IAppEnv appEnv;
	@Resource
	protected IUserThirdAccountService userThirdAccountService;

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
	@Transactional
	public void addUser(UserDto userDto) {
		if (userDto == null) {
			return;
		}
		/*codePassword(userDto);
		if(StringUtils.isEmpty(userDto.getBusinessCategory())){
			userDto.setBusinessCategory(BusinessCategory.SELF.getCode());
		}
		userDtoMapper.insertSelective(userDto);
		UserLinkIdDto linked=new UserLinkIdDto();
		linked.setAccountId(userDto.getAccountId());
		linked.setRole(userDto.getRole());
		linked.setBusinessCategory(userDto.getBusinessCategory());
		linked.setExt1(userDto.getUserName());
		userLinkIdDtoMapper.insertSelective(linked);*/
		addUser(userDto, new UserLinkIdDto());
	}
	@Override
	public void addUser(UserDto userDto, boolean verify) {
		UserLinkIdDto linked=userThirdAccountService.generatorUserLinkAndThrow(userDto.getUserName(), userDto.getRole());
		addUser(userDto,linked==null?new UserLinkIdDto():linked ,verify);
		
	}
	@Override
	public void addUserWithGenThird(UserDto userDto, boolean verify, UserLinkIdDto linkDto) {
		if(verify&&exists(userDto.getUserName(), userDto.getRole())){
			throw new MessageObjectException(MessageConstans.USER_USER__EXISTS);
		}
		userThirdAccountService.generatorUserLink(userDto.getUserName(), userDto.getRole(),linkDto);
		addUser(userDto, linkDto);
	}

	public void addUser(UserDto userDto,UserLinkIdDto userLinked,boolean verify){
		if(verify&&exists(userDto.getUserName(), userDto.getRole())){
			throw new MessageObjectException(MessageConstans.USER_USER__EXISTS);
		}
		addUser(userDto,userLinked);
	}
	
	
	@Override
	public void addUser(UserDto userDto, UserLinkIdDto linked) {
		if (userDto == null) {
			return;
		}
		codePassword(userDto);
		
		if(Role.BUSINESS.codeEqual(userDto.getRole())&&StringUtils.isEmpty(userDto.getBusinessCategory())){
			userDto.setBusinessCategory(BusinessCategory.THIRD.getCode());
		}
		if(StringUtils.isEmpty(userDto.getBusinessCategory())){
			userDto.setBusinessCategory(BusinessCategory.SELF.getCode());
		}
		userDtoMapper.insertSelective(userDto);
		//UserLinkIdDto linked=new UserLinkIdDto();
		linked.setAccountId(userDto.getAccountId());
		linked.setRole(userDto.getRole());
		linked.setBusinessCategory(userDto.getBusinessCategory());
		linked.setExt1(userDto.getUserName());
		userLinkIdDtoMapper.insertSelective(linked);
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
		PageList<UserDto> list = (PageList<UserDto>) userDtoMapper.selectPlatformUsers(interval,pageBounds);
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
	public int updateWechatAndAliPayLinkedId(Long accountId, String alipay, String wechat) {
		UserLinkIdDto dto=new UserLinkIdDto();
		dto.setAlipayAccount(alipay);
		dto.setWechatAccount(wechat);
		dto.setAccountId(accountId);
		return userLinkIdDtoMapper.updateWechatAndAliPayLinkedId(dto);
		
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

	@Override
	public boolean exists(String userName, String role) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(role)) {
			return false;
		}
		UserDtoExample example = new UserDtoExample();
		UserDtoExample.Criteria cri = example.createCriteria();
		cri.andUserNameEqualTo(userName);
		cri.andRoleEqualTo(role);
		List<UserDto> list = userDtoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		return true;
	}

	@Override
	public String selectPhone(Long accountId) {
		UserDto user=selectUser(accountId);
		if(user==null){
			return null;
		}
		if(!Role.PLATFORM.getCode().equals(user.getRole())){
			return user.getUserName();
		}
		return user.getBpn();
	}



	@Override
	public void updateUserState(List<UserDto> lists) {
		if(CollectionUtils.isEmpty(lists)){
			for(UserDto one:lists){
				updateUserState(one.getAccountId(), one.getState());
			}
		}
		
	}
	
	@Override
	public IPage<IReciverDto> listAllUser(Role role, IPageBound pageBounds) {
		return list(role, null, pageBounds);
	}
	
	protected IPage<IReciverDto>  list(Role role,BusinessCategory category, IPageBound pageBounds){
		PageBounds rpageBounds = new PageBounds(pageBounds.getPageIndex(), pageBounds.getPageSize(), false);
		List<IReciverDto> dtos=userLinkIdDtoMapper.selectRecivers(role.getCode(),category==null?null:category.getCode(), rpageBounds);
		if(!CollectionUtils.isEmpty(dtos)){
			for(IReciverDto one:dtos){
				one.setAccountType(role);
			}
		}
		return new ListPage<>(dtos, -1);
	}
	
	@Override
	public IPage<IReciverDto> listAllBusiness(BusinessCategory cat, IPageBound pageBounds) {
		return list(Role.BUSINESS, cat, pageBounds);
	}

	@Override
	@Transactional
	public void updateBusinessCategory(Long accountId, String businessCategory) {
		UserDto dto=new UserDto();
		dto.setAccountId(accountId);
		dto.setBusinessCategory(businessCategory);
		UserLinkIdDto linkedDto=new UserLinkIdDto();
		linkedDto.setAccountId(accountId);
		linkedDto.setBusinessCategory(businessCategory);
		userDtoMapper.updateByPrimaryKey(dto);
		userLinkIdDtoMapper.updateByPrimaryKeySelective(linkedDto);
		
	}

	@Override
	public String sendSmToUser(String role, String message) {
		if (!appEnv.canSendSmsToAllUser()) {
			throw new IllegalArgumentException("禁止发送消息");
		}
		List<String> users = userDtoMapper.selectUserPhone(StringUtils.defaultIfBlank(role, null));
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		ArrayList<String> array=new ArrayList<>();
		for (String one : users) {
			try {
				sendVcService.send(new String[] { one }, message);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(),e);
				array.add(one);
			}
		}

		return array.toString();
	}
	
	@Override
	public String reflashUserLinkId(String role) {
		if (!appEnv.canReflashUserLinked()) {
			throw new IllegalArgumentException("不允许更新token");
		}
		List<UserDto> lists = selectUsers(role);
		if (!CollectionUtils.isEmpty(lists)) {
			ArrayList<String> array = new ArrayList<>();
			for (UserDto one : lists) {
				try {
					UserLinkIdDto dto = userThirdAccountService.generatorUserLinkAndThrow(one.getUserName(),
							one.getRole());
					if (dto != null) {
						dto.setAccountId(one.getAccountId());
						userLinkIdDtoMapper.updateByPrimaryKeySelective(dto);
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					array.add(one.getAccountId().toString());
				}
			}
			return array.toString();
		}
		return null;
	}
	public List<UserDto> selectUsers(String role){
		UserDtoExample example=new UserDtoExample();
		UserDtoExample.Criteria cri=example.createCriteria();
		cri.andRoleEqualTo(role);
		return userDtoMapper.selectByExample(example);
		
	}

	@Override
	public void updateProfilePhoto(Long accountId, String pp) {
		UserDto user=new UserDto();
		user.setAccountId(accountId);
		user.setProfilePhoto(pp);
		userDtoMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public String getProfilePhoto(Long accountId) {
		UserDto user=selectUser(accountId);
		if(user!=null){
			return user.getProfilePhoto();
		}
		return null;
	}


	@Override
	public void updateUserLinked(UserLinkIdDto userLinked) {
		if (!StringUtils.isEmpty(userLinked.getBusinessCategory())) {
			UserDto dto = new UserDto();
			dto.setAccountId(userLinked.getAccountId());
			dto.setBusinessCategory(userLinked.getBusinessCategory());
			userDtoMapper.updateByPrimaryKey(dto);
		}
		userLinkIdDtoMapper.updateByPrimaryKeySelective(userLinked);

	}

	@Override
	public void updateRemark(Long accountId, String remark) {
		UserDto dto=new UserDto();
		dto.setAccountId(accountId);
		dto.setRemark(remark);
		userDtoMapper.updateByPrimaryKeySelective(dto);
		
	}

	
	




	


}
