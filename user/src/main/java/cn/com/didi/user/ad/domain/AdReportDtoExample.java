package cn.com.didi.user.ad.domain;

import java.util.ArrayList;
import java.util.List;

public class AdReportDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdReportDtoExample() {
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

        public Criteria andAdIdIsNull() {
            addCriterion("ad_id is null");
            return (Criteria) this;
        }

        public Criteria andAdIdIsNotNull() {
            addCriterion("ad_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdIdEqualTo(Long value) {
            addCriterion("ad_id =", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotEqualTo(Long value) {
            addCriterion("ad_id <>", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThan(Long value) {
            addCriterion("ad_id >", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ad_id >=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThan(Long value) {
            addCriterion("ad_id <", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThanOrEqualTo(Long value) {
            addCriterion("ad_id <=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdIn(List<Long> values) {
            addCriterion("ad_id in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotIn(List<Long> values) {
            addCriterion("ad_id not in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdBetween(Long value1, Long value2) {
            addCriterion("ad_id between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotBetween(Long value1, Long value2) {
            addCriterion("ad_id not between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andTimeuintIsNull() {
            addCriterion("timeuint is null");
            return (Criteria) this;
        }

        public Criteria andTimeuintIsNotNull() {
            addCriterion("timeuint is not null");
            return (Criteria) this;
        }

        public Criteria andTimeuintEqualTo(String value) {
            addCriterion("timeuint =", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintNotEqualTo(String value) {
            addCriterion("timeuint <>", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintGreaterThan(String value) {
            addCriterion("timeuint >", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintGreaterThanOrEqualTo(String value) {
            addCriterion("timeuint >=", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintLessThan(String value) {
            addCriterion("timeuint <", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintLessThanOrEqualTo(String value) {
            addCriterion("timeuint <=", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintLike(String value) {
            addCriterion("timeuint like", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintNotLike(String value) {
            addCriterion("timeuint not like", value, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintIn(List<String> values) {
            addCriterion("timeuint in", values, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintNotIn(List<String> values) {
            addCriterion("timeuint not in", values, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintBetween(String value1, String value2) {
            addCriterion("timeuint between", value1, value2, "timeuint");
            return (Criteria) this;
        }

        public Criteria andTimeuintNotBetween(String value1, String value2) {
            addCriterion("timeuint not between", value1, value2, "timeuint");
            return (Criteria) this;
        }

        public Criteria andExposureIsNull() {
            addCriterion("exposure is null");
            return (Criteria) this;
        }

        public Criteria andExposureIsNotNull() {
            addCriterion("exposure is not null");
            return (Criteria) this;
        }

        public Criteria andExposureEqualTo(Integer value) {
            addCriterion("exposure =", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureNotEqualTo(Integer value) {
            addCriterion("exposure <>", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureGreaterThan(Integer value) {
            addCriterion("exposure >", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureGreaterThanOrEqualTo(Integer value) {
            addCriterion("exposure >=", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureLessThan(Integer value) {
            addCriterion("exposure <", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureLessThanOrEqualTo(Integer value) {
            addCriterion("exposure <=", value, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureIn(List<Integer> values) {
            addCriterion("exposure in", values, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureNotIn(List<Integer> values) {
            addCriterion("exposure not in", values, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureBetween(Integer value1, Integer value2) {
            addCriterion("exposure between", value1, value2, "exposure");
            return (Criteria) this;
        }

        public Criteria andExposureNotBetween(Integer value1, Integer value2) {
            addCriterion("exposure not between", value1, value2, "exposure");
            return (Criteria) this;
        }

        public Criteria andClickRateIsNull() {
            addCriterion("click_rate is null");
            return (Criteria) this;
        }

        public Criteria andClickRateIsNotNull() {
            addCriterion("click_rate is not null");
            return (Criteria) this;
        }

        public Criteria andClickRateEqualTo(Integer value) {
            addCriterion("click_rate =", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotEqualTo(Integer value) {
            addCriterion("click_rate <>", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThan(Integer value) {
            addCriterion("click_rate >", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_rate >=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThan(Integer value) {
            addCriterion("click_rate <", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThanOrEqualTo(Integer value) {
            addCriterion("click_rate <=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateIn(List<Integer> values) {
            addCriterion("click_rate in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotIn(List<Integer> values) {
            addCriterion("click_rate not in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateBetween(Integer value1, Integer value2) {
            addCriterion("click_rate between", value1, value2, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotBetween(Integer value1, Integer value2) {
            addCriterion("click_rate not between", value1, value2, "clickRate");
            return (Criteria) this;
        }

        public Criteria andExposureDateIsNull() {
            addCriterion("exposure_date is null");
            return (Criteria) this;
        }

        public Criteria andExposureDateIsNotNull() {
            addCriterion("exposure_date is not null");
            return (Criteria) this;
        }

        public Criteria andExposureDateEqualTo(String value) {
            addCriterion("exposure_date =", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateNotEqualTo(String value) {
            addCriterion("exposure_date <>", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateGreaterThan(String value) {
            addCriterion("exposure_date >", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateGreaterThanOrEqualTo(String value) {
            addCriterion("exposure_date >=", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateLessThan(String value) {
            addCriterion("exposure_date <", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateLessThanOrEqualTo(String value) {
            addCriterion("exposure_date <=", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateLike(String value) {
            addCriterion("exposure_date like", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateNotLike(String value) {
            addCriterion("exposure_date not like", value, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateIn(List<String> values) {
            addCriterion("exposure_date in", values, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateNotIn(List<String> values) {
            addCriterion("exposure_date not in", values, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateBetween(String value1, String value2) {
            addCriterion("exposure_date between", value1, value2, "exposureDate");
            return (Criteria) this;
        }

        public Criteria andExposureDateNotBetween(String value1, String value2) {
            addCriterion("exposure_date not between", value1, value2, "exposureDate");
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