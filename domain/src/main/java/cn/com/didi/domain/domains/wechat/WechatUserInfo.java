package cn.com.didi.domain.domains.wechat;

import java.io.Serializable;

public class WechatUserInfo implements Serializable {

    /**  */
    private static final long serialVersionUID = 4252937176802841799L;
    /**
     * 用户关系表ID,系统用户ID
     */
    private Long accountId;
    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    private String openid;
    /**
     * 普通用户昵称
     */
    private String nickname;
    /**
     * 普通用户性别，男性，女性
     */
    private String sex;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headimgurl;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    private String unionid;
    
    /**
     * 用户关注状态 用于服务号.为0时表示未关注
     */
    private String focusState;
    
    
    
 

  

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

   

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFocusState() {
		return focusState;
	}

	public void setFocusState(String focusState) {
		this.focusState = focusState;
	}

	@Override
	public String toString() {
		return "WechatUserInfo [accountId=" + accountId + ", openid=" + openid + ", nickname=" + nickname + ", sex="
				+ sex + ", headimgurl=" + headimgurl + ", unionid=" + unionid + ", focusState=" + focusState + "]";
	}

}
