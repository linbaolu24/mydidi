package cn.com.didi.user.register.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhoneVcDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhoneVcDtoExample() {
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

        public Criteria andVcIdIsNull() {
            addCriterion("vc_id is null");
            return (Criteria) this;
        }

        public Criteria andVcIdIsNotNull() {
            addCriterion("vc_id is not null");
            return (Criteria) this;
        }

        public Criteria andVcIdEqualTo(Long value) {
            addCriterion("vc_id =", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdNotEqualTo(Long value) {
            addCriterion("vc_id <>", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdGreaterThan(Long value) {
            addCriterion("vc_id >", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdGreaterThanOrEqualTo(Long value) {
            addCriterion("vc_id >=", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdLessThan(Long value) {
            addCriterion("vc_id <", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdLessThanOrEqualTo(Long value) {
            addCriterion("vc_id <=", value, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdIn(List<Long> values) {
            addCriterion("vc_id in", values, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdNotIn(List<Long> values) {
            addCriterion("vc_id not in", values, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdBetween(Long value1, Long value2) {
            addCriterion("vc_id between", value1, value2, "vcId");
            return (Criteria) this;
        }

        public Criteria andVcIdNotBetween(Long value1, Long value2) {
            addCriterion("vc_id not between", value1, value2, "vcId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andVcIsNull() {
            addCriterion("vc is null");
            return (Criteria) this;
        }

        public Criteria andVcIsNotNull() {
            addCriterion("vc is not null");
            return (Criteria) this;
        }

        public Criteria andVcEqualTo(Integer value) {
            addCriterion("vc =", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcNotEqualTo(Integer value) {
            addCriterion("vc <>", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcGreaterThan(Integer value) {
            addCriterion("vc >", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcGreaterThanOrEqualTo(Integer value) {
            addCriterion("vc >=", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcLessThan(Integer value) {
            addCriterion("vc <", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcLessThanOrEqualTo(Integer value) {
            addCriterion("vc <=", value, "vc");
            return (Criteria) this;
        }

        public Criteria andVcIn(List<Integer> values) {
            addCriterion("vc in", values, "vc");
            return (Criteria) this;
        }

        public Criteria andVcNotIn(List<Integer> values) {
            addCriterion("vc not in", values, "vc");
            return (Criteria) this;
        }

        public Criteria andVcBetween(Integer value1, Integer value2) {
            addCriterion("vc between", value1, value2, "vc");
            return (Criteria) this;
        }

        public Criteria andVcNotBetween(Integer value1, Integer value2) {
            addCriterion("vc not between", value1, value2, "vc");
            return (Criteria) this;
        }

        public Criteria andLttIsNull() {
            addCriterion("ltt is null");
            return (Criteria) this;
        }

        public Criteria andLttIsNotNull() {
            addCriterion("ltt is not null");
            return (Criteria) this;
        }

        public Criteria andLttEqualTo(Date value) {
            addCriterion("ltt =", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttNotEqualTo(Date value) {
            addCriterion("ltt <>", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttGreaterThan(Date value) {
            addCriterion("ltt >", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttGreaterThanOrEqualTo(Date value) {
            addCriterion("ltt >=", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttLessThan(Date value) {
            addCriterion("ltt <", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttLessThanOrEqualTo(Date value) {
            addCriterion("ltt <=", value, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttIn(List<Date> values) {
            addCriterion("ltt in", values, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttNotIn(List<Date> values) {
            addCriterion("ltt not in", values, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttBetween(Date value1, Date value2) {
            addCriterion("ltt between", value1, value2, "ltt");
            return (Criteria) this;
        }

        public Criteria andLttNotBetween(Date value1, Date value2) {
            addCriterion("ltt not between", value1, value2, "ltt");
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