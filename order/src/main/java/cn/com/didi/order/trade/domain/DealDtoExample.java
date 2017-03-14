package cn.com.didi.order.trade.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DealDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DealDtoExample() {
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

        public Criteria andCauseIsNull() {
            addCriterion("cause is null");
            return (Criteria) this;
        }

        public Criteria andCauseIsNotNull() {
            addCriterion("cause is not null");
            return (Criteria) this;
        }

        public Criteria andCauseEqualTo(String value) {
            addCriterion("cause =", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotEqualTo(String value) {
            addCriterion("cause <>", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThan(String value) {
            addCriterion("cause >", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThanOrEqualTo(String value) {
            addCriterion("cause >=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThan(String value) {
            addCriterion("cause <", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThanOrEqualTo(String value) {
            addCriterion("cause <=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLike(String value) {
            addCriterion("cause like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotLike(String value) {
            addCriterion("cause not like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseIn(List<String> values) {
            addCriterion("cause in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotIn(List<String> values) {
            addCriterion("cause not in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseBetween(String value1, String value2) {
            addCriterion("cause between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotBetween(String value1, String value2) {
            addCriterion("cause not between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNull() {
            addCriterion("deal_type is null");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNotNull() {
            addCriterion("deal_type is not null");
            return (Criteria) this;
        }

        public Criteria andDealTypeEqualTo(String value) {
            addCriterion("deal_type =", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotEqualTo(String value) {
            addCriterion("deal_type <>", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThan(String value) {
            addCriterion("deal_type >", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deal_type >=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThan(String value) {
            addCriterion("deal_type <", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThanOrEqualTo(String value) {
            addCriterion("deal_type <=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLike(String value) {
            addCriterion("deal_type like", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotLike(String value) {
            addCriterion("deal_type not like", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeIn(List<String> values) {
            addCriterion("deal_type in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotIn(List<String> values) {
            addCriterion("deal_type not in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeBetween(String value1, String value2) {
            addCriterion("deal_type between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotBetween(String value1, String value2) {
            addCriterion("deal_type not between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andSatIsNull() {
            addCriterion("sat is null");
            return (Criteria) this;
        }

        public Criteria andSatIsNotNull() {
            addCriterion("sat is not null");
            return (Criteria) this;
        }

        public Criteria andSatEqualTo(String value) {
            addCriterion("sat =", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotEqualTo(String value) {
            addCriterion("sat <>", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatGreaterThan(String value) {
            addCriterion("sat >", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatGreaterThanOrEqualTo(String value) {
            addCriterion("sat >=", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatLessThan(String value) {
            addCriterion("sat <", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatLessThanOrEqualTo(String value) {
            addCriterion("sat <=", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatLike(String value) {
            addCriterion("sat like", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotLike(String value) {
            addCriterion("sat not like", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatIn(List<String> values) {
            addCriterion("sat in", values, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotIn(List<String> values) {
            addCriterion("sat not in", values, "sat");
            return (Criteria) this;
        }

        public Criteria andSatBetween(String value1, String value2) {
            addCriterion("sat between", value1, value2, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotBetween(String value1, String value2) {
            addCriterion("sat not between", value1, value2, "sat");
            return (Criteria) this;
        }

        public Criteria andSaIsNull() {
            addCriterion("sa is null");
            return (Criteria) this;
        }

        public Criteria andSaIsNotNull() {
            addCriterion("sa is not null");
            return (Criteria) this;
        }

        public Criteria andSaEqualTo(String value) {
            addCriterion("sa =", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaNotEqualTo(String value) {
            addCriterion("sa <>", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaGreaterThan(String value) {
            addCriterion("sa >", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaGreaterThanOrEqualTo(String value) {
            addCriterion("sa >=", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaLessThan(String value) {
            addCriterion("sa <", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaLessThanOrEqualTo(String value) {
            addCriterion("sa <=", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaLike(String value) {
            addCriterion("sa like", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaNotLike(String value) {
            addCriterion("sa not like", value, "sa");
            return (Criteria) this;
        }

        public Criteria andSaIn(List<String> values) {
            addCriterion("sa in", values, "sa");
            return (Criteria) this;
        }

        public Criteria andSaNotIn(List<String> values) {
            addCriterion("sa not in", values, "sa");
            return (Criteria) this;
        }

        public Criteria andSaBetween(String value1, String value2) {
            addCriterion("sa between", value1, value2, "sa");
            return (Criteria) this;
        }

        public Criteria andSaNotBetween(String value1, String value2) {
            addCriterion("sa not between", value1, value2, "sa");
            return (Criteria) this;
        }

        public Criteria andDatIsNull() {
            addCriterion("dat is null");
            return (Criteria) this;
        }

        public Criteria andDatIsNotNull() {
            addCriterion("dat is not null");
            return (Criteria) this;
        }

        public Criteria andDatEqualTo(String value) {
            addCriterion("dat =", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotEqualTo(String value) {
            addCriterion("dat <>", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatGreaterThan(String value) {
            addCriterion("dat >", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatGreaterThanOrEqualTo(String value) {
            addCriterion("dat >=", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatLessThan(String value) {
            addCriterion("dat <", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatLessThanOrEqualTo(String value) {
            addCriterion("dat <=", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatLike(String value) {
            addCriterion("dat like", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotLike(String value) {
            addCriterion("dat not like", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatIn(List<String> values) {
            addCriterion("dat in", values, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotIn(List<String> values) {
            addCriterion("dat not in", values, "dat");
            return (Criteria) this;
        }

        public Criteria andDatBetween(String value1, String value2) {
            addCriterion("dat between", value1, value2, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotBetween(String value1, String value2) {
            addCriterion("dat not between", value1, value2, "dat");
            return (Criteria) this;
        }

        public Criteria andDaIsNull() {
            addCriterion("da is null");
            return (Criteria) this;
        }

        public Criteria andDaIsNotNull() {
            addCriterion("da is not null");
            return (Criteria) this;
        }

        public Criteria andDaEqualTo(String value) {
            addCriterion("da =", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaNotEqualTo(String value) {
            addCriterion("da <>", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaGreaterThan(String value) {
            addCriterion("da >", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaGreaterThanOrEqualTo(String value) {
            addCriterion("da >=", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaLessThan(String value) {
            addCriterion("da <", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaLessThanOrEqualTo(String value) {
            addCriterion("da <=", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaLike(String value) {
            addCriterion("da like", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaNotLike(String value) {
            addCriterion("da not like", value, "da");
            return (Criteria) this;
        }

        public Criteria andDaIn(List<String> values) {
            addCriterion("da in", values, "da");
            return (Criteria) this;
        }

        public Criteria andDaNotIn(List<String> values) {
            addCriterion("da not in", values, "da");
            return (Criteria) this;
        }

        public Criteria andDaBetween(String value1, String value2) {
            addCriterion("da between", value1, value2, "da");
            return (Criteria) this;
        }

        public Criteria andDaNotBetween(String value1, String value2) {
            addCriterion("da not between", value1, value2, "da");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCmentIsNull() {
            addCriterion("cment is null");
            return (Criteria) this;
        }

        public Criteria andCmentIsNotNull() {
            addCriterion("cment is not null");
            return (Criteria) this;
        }

        public Criteria andCmentEqualTo(String value) {
            addCriterion("cment =", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentNotEqualTo(String value) {
            addCriterion("cment <>", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentGreaterThan(String value) {
            addCriterion("cment >", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentGreaterThanOrEqualTo(String value) {
            addCriterion("cment >=", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentLessThan(String value) {
            addCriterion("cment <", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentLessThanOrEqualTo(String value) {
            addCriterion("cment <=", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentLike(String value) {
            addCriterion("cment like", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentNotLike(String value) {
            addCriterion("cment not like", value, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentIn(List<String> values) {
            addCriterion("cment in", values, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentNotIn(List<String> values) {
            addCriterion("cment not in", values, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentBetween(String value1, String value2) {
            addCriterion("cment between", value1, value2, "cment");
            return (Criteria) this;
        }

        public Criteria andCmentNotBetween(String value1, String value2) {
            addCriterion("cment not between", value1, value2, "cment");
            return (Criteria) this;
        }

        public Criteria andRemainIsNull() {
            addCriterion("remain is null");
            return (Criteria) this;
        }

        public Criteria andRemainIsNotNull() {
            addCriterion("remain is not null");
            return (Criteria) this;
        }

        public Criteria andRemainEqualTo(Integer value) {
            addCriterion("remain =", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainNotEqualTo(Integer value) {
            addCriterion("remain <>", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainGreaterThan(Integer value) {
            addCriterion("remain >", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain >=", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainLessThan(Integer value) {
            addCriterion("remain <", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainLessThanOrEqualTo(Integer value) {
            addCriterion("remain <=", value, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainIn(List<Integer> values) {
            addCriterion("remain in", values, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainNotIn(List<Integer> values) {
            addCriterion("remain not in", values, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainBetween(Integer value1, Integer value2) {
            addCriterion("remain between", value1, value2, "remain");
            return (Criteria) this;
        }

        public Criteria andRemainNotBetween(Integer value1, Integer value2) {
            addCriterion("remain not between", value1, value2, "remain");
            return (Criteria) this;
        }

        public Criteria andSaiIsNull() {
            addCriterion("sai is null");
            return (Criteria) this;
        }

        public Criteria andSaiIsNotNull() {
            addCriterion("sai is not null");
            return (Criteria) this;
        }

        public Criteria andSaiEqualTo(Long value) {
            addCriterion("sai =", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiNotEqualTo(Long value) {
            addCriterion("sai <>", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiGreaterThan(Long value) {
            addCriterion("sai >", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiGreaterThanOrEqualTo(Long value) {
            addCriterion("sai >=", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiLessThan(Long value) {
            addCriterion("sai <", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiLessThanOrEqualTo(Long value) {
            addCriterion("sai <=", value, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiIn(List<Long> values) {
            addCriterion("sai in", values, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiNotIn(List<Long> values) {
            addCriterion("sai not in", values, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiBetween(Long value1, Long value2) {
            addCriterion("sai between", value1, value2, "sai");
            return (Criteria) this;
        }

        public Criteria andSaiNotBetween(Long value1, Long value2) {
            addCriterion("sai not between", value1, value2, "sai");
            return (Criteria) this;
        }

        public Criteria andDaiIsNull() {
            addCriterion("dai is null");
            return (Criteria) this;
        }

        public Criteria andDaiIsNotNull() {
            addCriterion("dai is not null");
            return (Criteria) this;
        }

        public Criteria andDaiEqualTo(Long value) {
            addCriterion("dai =", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiNotEqualTo(Long value) {
            addCriterion("dai <>", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiGreaterThan(Long value) {
            addCriterion("dai >", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiGreaterThanOrEqualTo(Long value) {
            addCriterion("dai >=", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiLessThan(Long value) {
            addCriterion("dai <", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiLessThanOrEqualTo(Long value) {
            addCriterion("dai <=", value, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiIn(List<Long> values) {
            addCriterion("dai in", values, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiNotIn(List<Long> values) {
            addCriterion("dai not in", values, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiBetween(Long value1, Long value2) {
            addCriterion("dai between", value1, value2, "dai");
            return (Criteria) this;
        }

        public Criteria andDaiNotBetween(Long value1, Long value2) {
            addCriterion("dai not between", value1, value2, "dai");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNull() {
            addCriterion("commission is null");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNotNull() {
            addCriterion("commission is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionEqualTo(Integer value) {
            addCriterion("commission =", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotEqualTo(Integer value) {
            addCriterion("commission <>", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThan(Integer value) {
            addCriterion("commission >", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThanOrEqualTo(Integer value) {
            addCriterion("commission >=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThan(Integer value) {
            addCriterion("commission <", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThanOrEqualTo(Integer value) {
            addCriterion("commission <=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionIn(List<Integer> values) {
            addCriterion("commission in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotIn(List<Integer> values) {
            addCriterion("commission not in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionBetween(Integer value1, Integer value2) {
            addCriterion("commission between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotBetween(Integer value1, Integer value2) {
            addCriterion("commission not between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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