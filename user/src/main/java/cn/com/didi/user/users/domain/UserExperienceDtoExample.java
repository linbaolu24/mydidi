package cn.com.didi.user.users.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExperienceDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExperienceDtoExample() {
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

        public Criteria andAccoutidIsNull() {
            addCriterion("accoutId is null");
            return (Criteria) this;
        }

        public Criteria andAccoutidIsNotNull() {
            addCriterion("accoutId is not null");
            return (Criteria) this;
        }

        public Criteria andAccoutidEqualTo(Long value) {
            addCriterion("accoutId =", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidNotEqualTo(Long value) {
            addCriterion("accoutId <>", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidGreaterThan(Long value) {
            addCriterion("accoutId >", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidGreaterThanOrEqualTo(Long value) {
            addCriterion("accoutId >=", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidLessThan(Long value) {
            addCriterion("accoutId <", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidLessThanOrEqualTo(Long value) {
            addCriterion("accoutId <=", value, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidIn(List<Long> values) {
            addCriterion("accoutId in", values, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidNotIn(List<Long> values) {
            addCriterion("accoutId not in", values, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidBetween(Long value1, Long value2) {
            addCriterion("accoutId between", value1, value2, "accoutid");
            return (Criteria) this;
        }

        public Criteria andAccoutidNotBetween(Long value1, Long value2) {
            addCriterion("accoutId not between", value1, value2, "accoutid");
            return (Criteria) this;
        }

        public Criteria andSlsIdIsNull() {
            addCriterion("sls_id is null");
            return (Criteria) this;
        }

        public Criteria andSlsIdIsNotNull() {
            addCriterion("sls_id is not null");
            return (Criteria) this;
        }

        public Criteria andSlsIdEqualTo(Integer value) {
            addCriterion("sls_id =", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdNotEqualTo(Integer value) {
            addCriterion("sls_id <>", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdGreaterThan(Integer value) {
            addCriterion("sls_id >", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sls_id >=", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdLessThan(Integer value) {
            addCriterion("sls_id <", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdLessThanOrEqualTo(Integer value) {
            addCriterion("sls_id <=", value, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdIn(List<Integer> values) {
            addCriterion("sls_id in", values, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdNotIn(List<Integer> values) {
            addCriterion("sls_id not in", values, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdBetween(Integer value1, Integer value2) {
            addCriterion("sls_id between", value1, value2, "slsId");
            return (Criteria) this;
        }

        public Criteria andSlsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sls_id not between", value1, value2, "slsId");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNull() {
            addCriterion("last_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("last_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeIsNull() {
            addCriterion("ntl_time is null");
            return (Criteria) this;
        }

        public Criteria andNtlTimeIsNotNull() {
            addCriterion("ntl_time is not null");
            return (Criteria) this;
        }

        public Criteria andNtlTimeEqualTo(Date value) {
            addCriterion("ntl_time =", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeNotEqualTo(Date value) {
            addCriterion("ntl_time <>", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeGreaterThan(Date value) {
            addCriterion("ntl_time >", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ntl_time >=", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeLessThan(Date value) {
            addCriterion("ntl_time <", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeLessThanOrEqualTo(Date value) {
            addCriterion("ntl_time <=", value, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeIn(List<Date> values) {
            addCriterion("ntl_time in", values, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeNotIn(List<Date> values) {
            addCriterion("ntl_time not in", values, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeBetween(Date value1, Date value2) {
            addCriterion("ntl_time between", value1, value2, "ntlTime");
            return (Criteria) this;
        }

        public Criteria andNtlTimeNotBetween(Date value1, Date value2) {
            addCriterion("ntl_time not between", value1, value2, "ntlTime");
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