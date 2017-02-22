package cn.com.didi.core.message;

public class ErrorMessage {
    public static final Message SUCCESS = newErrorMessage("00000000");

    // 服务不可预知异常
    public static final Message UNKNOWN_EXCEPTION = newErrorMessage("99999999");

    // 用户不存在
    public static final Message USER_NOT_FOUND = newErrorMessage("80474001");

    // 用户名或密码错误
    public static final Message PASSWORD_NOT_PASSED = newErrorMessage("80474002");

    // 未授权
    public static final Message UNAUTHORIZED = newErrorMessage("80474003");

    // 参数不合法
    public static final Message PARAMETER_ILLEGAL = newErrorMessage("80474004");

    // 账户中心返回异常
    public static final Message UIC_EXCEPTION = newErrorMessage("80474005");

    // 用户已经存在
    public static final Message USER_EXISTS = newErrorMessage("80474006");

    // 企业不存在
    public static final Message QY_NOT_FOUND = newErrorMessage("80474007");

    // 验证码错误
    public static final Message VERIFY_CODE_NOT_PASSED = newErrorMessage("80474008");

    // 用户未绑定手机号码
    public static final Message MOBILE_NOT_BAND = newErrorMessage("80474009");

    // 用户未登陆
    public static final Message USER_NOT_LOGIN = newErrorMessage("80474010");

    // 纳税人识别号有误
    public static final Message NSRSBH_ERROR = newErrorMessage("80474011");

    // 员工姓名不能为空
    public static final Message YGXM_NOT_BLANK = newErrorMessage("80474012");

    // 员工通讯录操作方式不正确
    public static final Message YGXM_OPTYPE_ERROR = newErrorMessage("80474013");

    // 手机号码不能为空
    public static final Message MOBILE_NOT_BLANK = newErrorMessage("80474014");

    // 手机格式不正确
    public static final Message MOBILE_FROMAT_ERROR = newErrorMessage("80474015");

    // EMAIL格式不正确
    public static final Message EMAIL_FROMAT_ERROR = newErrorMessage("80474016");

    // 手机号或email已存在
    public static final Message MOBILE_EMAIL_EXISTS = newErrorMessage("80474017");

    // 数据更新失败
    public static final Message DATA_UPDATE_ERROR = newErrorMessage("80474018");

    // 数据插入失败
    public static final Message DATA_INSERT_ERROR = newErrorMessage("80474019");

    // 数据信息不完整
    public static final Message DATA_IS_BLANK = newErrorMessage("80474020");

    // 微信通信失败,无法获取微信token
    public static final Message WECHAT_TOKEN_BLANK = newErrorMessage("80474021");
    // 字段信息太长
    public static final Message COLUMN_TOO_LONG = newErrorMessage("80474022");

    // 企业ID不能为空
    public static final Message QYID_NOT_BLANK = newErrorMessage("80474023");

    // 微信通讯录交互出错
    public static final Message WECHAT_USER_ERROR = newErrorMessage("80474024");

    // 未关注微信公众号
    public static final Message QYID_WGZGZH = newErrorMessage("80474050");

    // 企业名片不存在
    public static final Message QYMP_NOT_ERROR = newErrorMessage("80474025");

    // 企业名片创建失败
    public static final Message QYMP_CREATE_ERROR = newErrorMessage("80474026");
    // 没有绑定开票机
    public static final Message KPJ_BINDING_ERROR = newErrorMessage("80474027");

    // 企业/发票抬头名片已存在
    public static final Message QYMP_EXIST_ERROR = newErrorMessage("80474028");

    // 纳税人识别号已存在
    public static final Message NSRSBH_EXISTS = newErrorMessage("80474029");

    // 活动名称已存在
    public static final Message HDACTIVITY_NAME_EXISTS = newErrorMessage("80474030");

    // 数据解压编码异常
    public static final Message DATA_ANALYSIS_ERROR = newErrorMessage("80474031");
    
    //手机号码超出11位
    public static final Message SJH_ANALYSIS_ERROR = newErrorMessage("80474032");
    
    //已存在同票据同分类同属期转出数据
    public static final Message JXZCSJ_REPEAT_ERROR = newErrorMessage("80474033");
    
    /** 调用惠税账户中心接口进行账号与企业绑定出现异常  */
    public static final Message USER_NSRXX_BIND_BY_HSACCOUNT_ERROR = newErrorMessage("80474034");
    
    /** 调用惠税账户中心接口进行账号与企业解绑出现异常  */
    public static final Message USER_NSRXX_UNBIND_BY_HSACCOUNT_ERROR = newErrorMessage("80474035");
    
    
    protected static Message newErrorMessage(String code) {
        return MessageFactory.creatMessage(code);
    }
}
