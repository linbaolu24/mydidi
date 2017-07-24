package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import cn.com.didi.domain.util.Role;

public class MerchantDto implements Serializable {
	/**
     * 用户名
     */
    private Long accountId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 商户名称
     */
    private String cname;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 所在地址
     */
    private String addressCode;

    /**
     * 0表示有效 1表示无效
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 手持身份证图像地址
     */
    private String icImg1;

    /**
     * 身份证正面
     */
    private String icImg2;

    /**
     * 身份证背面
     */
    private String icImg3;

    /**
     * 营业执照图像
     */
    private String licenceImg;

    /**
     * 业务类型:0表示自营 1表示第三方
     */
    private String businessCategory;

    /**
     * Certification results 认证结果 0表示认证通过 1表示认证待认证 2 表示认证不通过
     */
    private String cr;

    /**
     * 认证结果原因
     */
    private String cause;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 绑定手机号
     */
    private String bpn;

    /**
     * 师傅名称
     */
    private String mastername;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 1 表示美容美发
     */
    private String specialType;

    /**
     * 描述
     */
    private String description;

    private String ext1;

    private String ext2;
    /**
     * 门店logog
     */
    private String merchantLogo;
    private static final long serialVersionUID = 1L;

	// add by my
	/**
	 * 师傅头像
	 */
	private String mpn;
	private String address;
	private String password;
	/**
	 * 支付宝账号
	 */
	private String alipayAccount;

	/**
	 * 微信账号
	 */
	private String wechatAccount;
	/**
	 * 微信名
	 */
	private String wechatName;
	/***/
	private String service;
    /**合并状态*/
	private String comState;
	
	 /**
     * 用户名
     **/
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 用户名
     **/
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 用户名
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     **/
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 商户名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 商户名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 详细地址
     **/
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 详细地址
     **/
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * 所在地址
     **/
    public String getAddressCode() {
        return addressCode;
    }

    /**
     * 所在地址
     **/
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

    /**
     * 0表示有效 1表示无效
     **/
    public String getState() {
        return state;
    }

    /**
     * 0表示有效 1表示无效
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 手持身份证图像地址
     **/
    public String getIcImg1() {
        return icImg1;
    }

    /**
     * 手持身份证图像地址
     **/
    public void setIcImg1(String icImg1) {
        this.icImg1 = icImg1 == null ? null : icImg1.trim();
    }

    /**
     * 身份证正面
     **/
    public String getIcImg2() {
        return icImg2;
    }

    /**
     * 身份证正面
     **/
    public void setIcImg2(String icImg2) {
        this.icImg2 = icImg2 == null ? null : icImg2.trim();
    }

    /**
     * 身份证背面
     **/
    public String getIcImg3() {
        return icImg3;
    }

    /**
     * 身份证背面
     **/
    public void setIcImg3(String icImg3) {
        this.icImg3 = icImg3 == null ? null : icImg3.trim();
    }

    /**
     * 营业执照图像
     **/
    public String getLicenceImg() {
        return licenceImg;
    }

    /**
     * 营业执照图像
     **/
    public void setLicenceImg(String licenceImg) {
        this.licenceImg = licenceImg == null ? null : licenceImg.trim();
    }

    /**
     * 业务类型:0表示自营 1表示第三方
     **/
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * 业务类型:0表示自营 1表示第三方
     **/
    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    /**
     * Certification results 认证结果 0表示认证通过 1表示认证待认证 2 表示认证不通过
     **/
    public String getCr() {
        return cr;
    }

    /**
     * Certification results 认证结果 0表示认证通过 1表示认证待认证 2 表示认证不通过
     **/
    public void setCr(String cr) {
        this.cr = cr == null ? null : cr.trim();
    }

    /**
     * 认证结果原因
     **/
    public String getCause() {
        return cause;
    }

    /**
     * 认证结果原因
     **/
    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    /**
     * 联系方式
     **/
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * 联系方式
     **/
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation == null ? null : contactInformation.trim();
    }

    /**
     * 绑定手机号
     **/
    public String getBpn() {
        return bpn;
    }

    /**
     * 绑定手机号
     **/
    public void setBpn(String bpn) {
        this.bpn = bpn == null ? null : bpn.trim();
    }

    /**
     * 师傅名称
     **/
    public String getMastername() {
        return mastername;
    }

    /**
     * 师傅名称
     **/
    public void setMastername(String mastername) {
        this.mastername = mastername == null ? null : mastername.trim();
    }

    /**
     * 经度
     **/
    public BigDecimal getLng() {
        return lng;
    }

    /**
     * 经度
     **/
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    /**
     * 纬度
     **/
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * 纬度
     **/
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     * 1 表示美容美发
     **/
    public String getSpecialType() {
        return specialType;
    }

    /**
     * 1 表示美容美发
     **/
    public void setSpecialType(String specialType) {
        this.specialType = specialType == null ? null : specialType.trim();
    }

    /**
     * 描述
     **/
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     **/
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
    /**
     * 门店logog
     **/
    public String getMerchantLogo() {
        return merchantLogo;
    }

    /**
     * 门店logog
     **/
    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo == null ? null : merchantLogo.trim();
    }
	public UserDto toUserDto() {
		UserDto userDto = new UserDto();
		userDto.setBpn(getBpn());
		userDto.setUserName(getUserName());
		if (StringUtils.isEmpty(userDto.getUserName())) {
			userDto.setUserName(getBpn());
		}
		userDto.setRole(Role.BUSINESS.getCode());
		userDto.setCname(getMastername());
		userDto.setProfilePhoto(getMpn());
		userDto.setState(getState());
		userDto.setCreateTime(getCreateTime());
		userDto.setBusinessCategory(getBusinessCategory());
		return userDto;
	}

	public MerchantDescriptionDto toMerchantDescriptionDto(Double distance) {
		MerchantDescriptionDto dto = new MerchantDescriptionDto();
		dto.setMerchantAccountId(getAccountId());
		dto.setAddress(getDetailAddress());
		dto.setCname(getCname());
		dto.setDescription(getContactInformation());
		if (getLat() != null) {
			dto.setLat(getLat().toString());
		}
		if (getLng() != null) {
			dto.setLng(getLng().toString());
		}
		if(distance!=null){
		dto.setDistance(distance.intValue());
		}
		return dto;
	}
	public UserLinkIdDto toUserLinkIdDto() {
		if (StringUtils.isEmpty(getWechatAccount()) && StringUtils.isEmpty(getAlipayAccount())) {
			return null;
		}
		UserLinkIdDto userDto = new UserLinkIdDto();
		userDto.setAccountId(accountId);
		userDto.setAlipayAccount(getAlipayAccount());
		userDto.setWechatAccount(wechatAccount);
		return userDto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getComState() {
		return comState;
	}

	public void setComState(String comState) {
		this.comState = comState;
	}

}