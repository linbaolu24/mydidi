package cn.com.didi.order.trade.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DealRecordDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DealRecordDtoExample() {
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

        public Criteria andStreamIdIsNull() {
            addCriterion("stream_id is null");
            return (Criteria) this;
        }

        public Criteria andStreamIdIsNotNull() {
            addCriterion("stream_id is not null");
            return (Criteria) this;
        }

        public Criteria andStreamIdEqualTo(Long value) {
            addCriterion("stream_id =", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdNotEqualTo(Long value) {
            addCriterion("stream_id <>", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdGreaterThan(Long value) {
            addCriterion("stream_id >", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("stream_id >=", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdLessThan(Long value) {
            addCriterion("stream_id <", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdLessThanOrEqualTo(Long value) {
            addCriterion("stream_id <=", value, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdIn(List<Long> values) {
            addCriterion("stream_id in", values, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdNotIn(List<Long> values) {
            addCriterion("stream_id not in", values, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdBetween(Long value1, Long value2) {
            addCriterion("stream_id between", value1, value2, "streamId");
            return (Criteria) this;
        }

        public Criteria andStreamIdNotBetween(Long value1, Long value2) {
            addCriterion("stream_id not between", value1, value2, "streamId");
            return (Criteria) this;
        }

        public Criteria andDealIdIsNull() {
            addCriterion("deal_id is null");
            return (Criteria) this;
        }

        public Criteria andDealIdIsNotNull() {
            addCriterion("deal_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealIdEqualTo(Long value) {
            addCriterion("deal_id =", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotEqualTo(Long value) {
            addCriterion("deal_id <>", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdGreaterThan(Long value) {
            addCriterion("deal_id >", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdGreaterThanOrEqualTo(Long value) {
            addCriterion("deal_id >=", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdLessThan(Long value) {
            addCriterion("deal_id <", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdLessThanOrEqualTo(Long value) {
            addCriterion("deal_id <=", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdIn(List<Long> values) {
            addCriterion("deal_id in", values, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotIn(List<Long> values) {
            addCriterion("deal_id not in", values, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdBetween(Long value1, Long value2) {
            addCriterion("deal_id between", value1, value2, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotBetween(Long value1, Long value2) {
            addCriterion("deal_id not between", value1, value2, "dealId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNull() {
            addCriterion("trade_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(String value) {
            addCriterion("trade_id =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(String value) {
            addCriterion("trade_id <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(String value) {
            addCriterion("trade_id >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("trade_id >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(String value) {
            addCriterion("trade_id <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(String value) {
            addCriterion("trade_id <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLike(String value) {
            addCriterion("trade_id like", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotLike(String value) {
            addCriterion("trade_id not like", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<String> values) {
            addCriterion("trade_id in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<String> values) {
            addCriterion("trade_id not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(String value1, String value2) {
            addCriterion("trade_id between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(String value1, String value2) {
            addCriterion("trade_id not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNull() {
            addCriterion("sub_category is null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNotNull() {
            addCriterion("sub_category is not null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryEqualTo(String value) {
            addCriterion("sub_category =", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotEqualTo(String value) {
            addCriterion("sub_category <>", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThan(String value) {
            addCriterion("sub_category >", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("sub_category >=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThan(String value) {
            addCriterion("sub_category <", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThanOrEqualTo(String value) {
            addCriterion("sub_category <=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLike(String value) {
            addCriterion("sub_category like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotLike(String value) {
            addCriterion("sub_category not like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIn(List<String> values) {
            addCriterion("sub_category in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotIn(List<String> values) {
            addCriterion("sub_category not in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryBetween(String value1, String value2) {
            addCriterion("sub_category between", value1, value2, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotBetween(String value1, String value2) {
            addCriterion("sub_category not between", value1, value2, "subCategory");
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