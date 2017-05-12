package cn.com.didi.user.users.domain;

import java.util.ArrayList;
import java.util.List;

public class UserLinkIdDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserLinkIdDtoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_Id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_Id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_Id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_Id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_Id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_Id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_Id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_Id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_Id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_Id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_Id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_Id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNull() {
            addCriterion("alipay_account is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNotNull() {
            addCriterion("alipay_account is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountEqualTo(String value) {
            addCriterion("alipay_account =", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotEqualTo(String value) {
            addCriterion("alipay_account <>", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThan(String value) {
            addCriterion("alipay_account >", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account >=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThan(String value) {
            addCriterion("alipay_account <", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("alipay_account <=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLike(String value) {
            addCriterion("alipay_account like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotLike(String value) {
            addCriterion("alipay_account not like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIn(List<String> values) {
            addCriterion("alipay_account in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotIn(List<String> values) {
            addCriterion("alipay_account not in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountBetween(String value1, String value2) {
            addCriterion("alipay_account between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("alipay_account not between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountIsNull() {
            addCriterion("wechat_account is null");
            return (Criteria) this;
        }

        public Criteria andWechatAccountIsNotNull() {
            addCriterion("wechat_account is not null");
            return (Criteria) this;
        }

        public Criteria andWechatAccountEqualTo(String value) {
            addCriterion("wechat_account =", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountNotEqualTo(String value) {
            addCriterion("wechat_account <>", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountGreaterThan(String value) {
            addCriterion("wechat_account >", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_account >=", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountLessThan(String value) {
            addCriterion("wechat_account <", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountLessThanOrEqualTo(String value) {
            addCriterion("wechat_account <=", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountLike(String value) {
            addCriterion("wechat_account like", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountNotLike(String value) {
            addCriterion("wechat_account not like", value, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountIn(List<String> values) {
            addCriterion("wechat_account in", values, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountNotIn(List<String> values) {
            addCriterion("wechat_account not in", values, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountBetween(String value1, String value2) {
            addCriterion("wechat_account between", value1, value2, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andWechatAccountNotBetween(String value1, String value2) {
            addCriterion("wechat_account not between", value1, value2, "wechatAccount");
            return (Criteria) this;
        }

        public Criteria andGtCidIsNull() {
            addCriterion("gt_cid is null");
            return (Criteria) this;
        }

        public Criteria andGtCidIsNotNull() {
            addCriterion("gt_cid is not null");
            return (Criteria) this;
        }

        public Criteria andGtCidEqualTo(String value) {
            addCriterion("gt_cid =", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidNotEqualTo(String value) {
            addCriterion("gt_cid <>", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidGreaterThan(String value) {
            addCriterion("gt_cid >", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidGreaterThanOrEqualTo(String value) {
            addCriterion("gt_cid >=", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidLessThan(String value) {
            addCriterion("gt_cid <", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidLessThanOrEqualTo(String value) {
            addCriterion("gt_cid <=", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidLike(String value) {
            addCriterion("gt_cid like", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidNotLike(String value) {
            addCriterion("gt_cid not like", value, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidIn(List<String> values) {
            addCriterion("gt_cid in", values, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidNotIn(List<String> values) {
            addCriterion("gt_cid not in", values, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidBetween(String value1, String value2) {
            addCriterion("gt_cid between", value1, value2, "gtCid");
            return (Criteria) this;
        }

        public Criteria andGtCidNotBetween(String value1, String value2) {
            addCriterion("gt_cid not between", value1, value2, "gtCid");
            return (Criteria) this;
        }

        public Criteria andRyTokenIsNull() {
            addCriterion("ry_token is null");
            return (Criteria) this;
        }

        public Criteria andRyTokenIsNotNull() {
            addCriterion("ry_token is not null");
            return (Criteria) this;
        }

        public Criteria andRyTokenEqualTo(String value) {
            addCriterion("ry_token =", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenNotEqualTo(String value) {
            addCriterion("ry_token <>", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenGreaterThan(String value) {
            addCriterion("ry_token >", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenGreaterThanOrEqualTo(String value) {
            addCriterion("ry_token >=", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenLessThan(String value) {
            addCriterion("ry_token <", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenLessThanOrEqualTo(String value) {
            addCriterion("ry_token <=", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenLike(String value) {
            addCriterion("ry_token like", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenNotLike(String value) {
            addCriterion("ry_token not like", value, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenIn(List<String> values) {
            addCriterion("ry_token in", values, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenNotIn(List<String> values) {
            addCriterion("ry_token not in", values, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenBetween(String value1, String value2) {
            addCriterion("ry_token between", value1, value2, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRyTokenNotBetween(String value1, String value2) {
            addCriterion("ry_token not between", value1, value2, "ryToken");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryIsNull() {
            addCriterion("business_category is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryIsNotNull() {
            addCriterion("business_category is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryEqualTo(String value) {
            addCriterion("business_category =", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryNotEqualTo(String value) {
            addCriterion("business_category <>", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryGreaterThan(String value) {
            addCriterion("business_category >", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("business_category >=", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryLessThan(String value) {
            addCriterion("business_category <", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryLessThanOrEqualTo(String value) {
            addCriterion("business_category <=", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryLike(String value) {
            addCriterion("business_category like", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryNotLike(String value) {
            addCriterion("business_category not like", value, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryIn(List<String> values) {
            addCriterion("business_category in", values, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryNotIn(List<String> values) {
            addCriterion("business_category not in", values, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryBetween(String value1, String value2) {
            addCriterion("business_category between", value1, value2, "businessCategory");
            return (Criteria) this;
        }

        public Criteria andBusinessCategoryNotBetween(String value1, String value2) {
            addCriterion("business_category not between", value1, value2, "businessCategory");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}