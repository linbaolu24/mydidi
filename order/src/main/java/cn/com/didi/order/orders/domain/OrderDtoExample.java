package cn.com.didi.order.orders.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDtoExample() {
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

        public Criteria andFlsIdIsNull() {
            addCriterion("fls_id is null");
            return (Criteria) this;
        }

        public Criteria andFlsIdIsNotNull() {
            addCriterion("fls_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlsIdEqualTo(Integer value) {
            addCriterion("fls_id =", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdNotEqualTo(Integer value) {
            addCriterion("fls_id <>", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdGreaterThan(Integer value) {
            addCriterion("fls_id >", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fls_id >=", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdLessThan(Integer value) {
            addCriterion("fls_id <", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdLessThanOrEqualTo(Integer value) {
            addCriterion("fls_id <=", value, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdIn(List<Integer> values) {
            addCriterion("fls_id in", values, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdNotIn(List<Integer> values) {
            addCriterion("fls_id not in", values, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdBetween(Integer value1, Integer value2) {
            addCriterion("fls_id between", value1, value2, "flsId");
            return (Criteria) this;
        }

        public Criteria andFlsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fls_id not between", value1, value2, "flsId");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCnameIsNull() {
            addCriterion("cname is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("cname is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("cname =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("cname <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("cname >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("cname >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("cname <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("cname <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("cname like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("cname not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("cname in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("cname not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("cname between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("cname not between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCciIsNull() {
            addCriterion("cci is null");
            return (Criteria) this;
        }

        public Criteria andCciIsNotNull() {
            addCriterion("cci is not null");
            return (Criteria) this;
        }

        public Criteria andCciEqualTo(String value) {
            addCriterion("cci =", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciNotEqualTo(String value) {
            addCriterion("cci <>", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciGreaterThan(String value) {
            addCriterion("cci >", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciGreaterThanOrEqualTo(String value) {
            addCriterion("cci >=", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciLessThan(String value) {
            addCriterion("cci <", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciLessThanOrEqualTo(String value) {
            addCriterion("cci <=", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciLike(String value) {
            addCriterion("cci like", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciNotLike(String value) {
            addCriterion("cci not like", value, "cci");
            return (Criteria) this;
        }

        public Criteria andCciIn(List<String> values) {
            addCriterion("cci in", values, "cci");
            return (Criteria) this;
        }

        public Criteria andCciNotIn(List<String> values) {
            addCriterion("cci not in", values, "cci");
            return (Criteria) this;
        }

        public Criteria andCciBetween(String value1, String value2) {
            addCriterion("cci between", value1, value2, "cci");
            return (Criteria) this;
        }

        public Criteria andCciNotBetween(String value1, String value2) {
            addCriterion("cci not between", value1, value2, "cci");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressIsNull() {
            addCriterion("consumer_address is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressIsNotNull() {
            addCriterion("consumer_address is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressEqualTo(String value) {
            addCriterion("consumer_address =", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressNotEqualTo(String value) {
            addCriterion("consumer_address <>", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressGreaterThan(String value) {
            addCriterion("consumer_address >", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_address >=", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressLessThan(String value) {
            addCriterion("consumer_address <", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressLessThanOrEqualTo(String value) {
            addCriterion("consumer_address <=", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressLike(String value) {
            addCriterion("consumer_address like", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressNotLike(String value) {
            addCriterion("consumer_address not like", value, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressIn(List<String> values) {
            addCriterion("consumer_address in", values, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressNotIn(List<String> values) {
            addCriterion("consumer_address not in", values, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressBetween(String value1, String value2) {
            addCriterion("consumer_address between", value1, value2, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andConsumerAddressNotBetween(String value1, String value2) {
            addCriterion("consumer_address not between", value1, value2, "consumerAddress");
            return (Criteria) this;
        }

        public Criteria andCasIsNull() {
            addCriterion("cas is null");
            return (Criteria) this;
        }

        public Criteria andCasIsNotNull() {
            addCriterion("cas is not null");
            return (Criteria) this;
        }

        public Criteria andCasEqualTo(String value) {
            addCriterion("cas =", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasNotEqualTo(String value) {
            addCriterion("cas <>", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasGreaterThan(String value) {
            addCriterion("cas >", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasGreaterThanOrEqualTo(String value) {
            addCriterion("cas >=", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasLessThan(String value) {
            addCriterion("cas <", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasLessThanOrEqualTo(String value) {
            addCriterion("cas <=", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasLike(String value) {
            addCriterion("cas like", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasNotLike(String value) {
            addCriterion("cas not like", value, "cas");
            return (Criteria) this;
        }

        public Criteria andCasIn(List<String> values) {
            addCriterion("cas in", values, "cas");
            return (Criteria) this;
        }

        public Criteria andCasNotIn(List<String> values) {
            addCriterion("cas not in", values, "cas");
            return (Criteria) this;
        }

        public Criteria andCasBetween(String value1, String value2) {
            addCriterion("cas between", value1, value2, "cas");
            return (Criteria) this;
        }

        public Criteria andCasNotBetween(String value1, String value2) {
            addCriterion("cas not between", value1, value2, "cas");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNull() {
            addCriterion("consumer_name is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNotNull() {
            addCriterion("consumer_name is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameEqualTo(String value) {
            addCriterion("consumer_name =", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotEqualTo(String value) {
            addCriterion("consumer_name <>", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThan(String value) {
            addCriterion("consumer_name >", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_name >=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThan(String value) {
            addCriterion("consumer_name <", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThanOrEqualTo(String value) {
            addCriterion("consumer_name <=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLike(String value) {
            addCriterion("consumer_name like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotLike(String value) {
            addCriterion("consumer_name not like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIn(List<String> values) {
            addCriterion("consumer_name in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotIn(List<String> values) {
            addCriterion("consumer_name not in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameBetween(String value1, String value2) {
            addCriterion("consumer_name between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotBetween(String value1, String value2) {
            addCriterion("consumer_name not between", value1, value2, "consumerName");
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

        public Criteria andConsumerAccountIdIsNull() {
            addCriterion("consumer_account_id is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdIsNotNull() {
            addCriterion("consumer_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdEqualTo(Long value) {
            addCriterion("consumer_account_id =", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdNotEqualTo(Long value) {
            addCriterion("consumer_account_id <>", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdGreaterThan(Long value) {
            addCriterion("consumer_account_id >", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("consumer_account_id >=", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdLessThan(Long value) {
            addCriterion("consumer_account_id <", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("consumer_account_id <=", value, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdIn(List<Long> values) {
            addCriterion("consumer_account_id in", values, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdNotIn(List<Long> values) {
            addCriterion("consumer_account_id not in", values, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdBetween(Long value1, Long value2) {
            addCriterion("consumer_account_id between", value1, value2, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andConsumerAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("consumer_account_id not between", value1, value2, "consumerAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdIsNull() {
            addCriterion("merchant_account_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdIsNotNull() {
            addCriterion("merchant_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdEqualTo(Long value) {
            addCriterion("merchant_account_id =", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdNotEqualTo(Long value) {
            addCriterion("merchant_account_id <>", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdGreaterThan(Long value) {
            addCriterion("merchant_account_id >", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchant_account_id >=", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdLessThan(Long value) {
            addCriterion("merchant_account_id <", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("merchant_account_id <=", value, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdIn(List<Long> values) {
            addCriterion("merchant_account_id in", values, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdNotIn(List<Long> values) {
            addCriterion("merchant_account_id not in", values, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdBetween(Long value1, Long value2) {
            addCriterion("merchant_account_id between", value1, value2, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andMerchantAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("merchant_account_id not between", value1, value2, "merchantAccountId");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("Lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("Lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(BigDecimal value) {
            addCriterion("Lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(BigDecimal value) {
            addCriterion("Lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(BigDecimal value) {
            addCriterion("Lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(BigDecimal value) {
            addCriterion("Lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<BigDecimal> values) {
            addCriterion("Lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<BigDecimal> values) {
            addCriterion("Lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(BigDecimal value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(BigDecimal value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(BigDecimal value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(BigDecimal value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<BigDecimal> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<BigDecimal> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeIsNull() {
            addCriterion("business_charge is null");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeIsNotNull() {
            addCriterion("business_charge is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeEqualTo(String value) {
            addCriterion("business_charge =", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeNotEqualTo(String value) {
            addCriterion("business_charge <>", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeGreaterThan(String value) {
            addCriterion("business_charge >", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeGreaterThanOrEqualTo(String value) {
            addCriterion("business_charge >=", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeLessThan(String value) {
            addCriterion("business_charge <", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeLessThanOrEqualTo(String value) {
            addCriterion("business_charge <=", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeLike(String value) {
            addCriterion("business_charge like", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeNotLike(String value) {
            addCriterion("business_charge not like", value, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeIn(List<String> values) {
            addCriterion("business_charge in", values, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeNotIn(List<String> values) {
            addCriterion("business_charge not in", values, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeBetween(String value1, String value2) {
            addCriterion("business_charge between", value1, value2, "businessCharge");
            return (Criteria) this;
        }

        public Criteria andBusinessChargeNotBetween(String value1, String value2) {
            addCriterion("business_charge not between", value1, value2, "businessCharge");
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

        public Criteria andMasterNameIsNull() {
            addCriterion("master_name is null");
            return (Criteria) this;
        }

        public Criteria andMasterNameIsNotNull() {
            addCriterion("master_name is not null");
            return (Criteria) this;
        }

        public Criteria andMasterNameEqualTo(String value) {
            addCriterion("master_name =", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameNotEqualTo(String value) {
            addCriterion("master_name <>", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameGreaterThan(String value) {
            addCriterion("master_name >", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameGreaterThanOrEqualTo(String value) {
            addCriterion("master_name >=", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameLessThan(String value) {
            addCriterion("master_name <", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameLessThanOrEqualTo(String value) {
            addCriterion("master_name <=", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameLike(String value) {
            addCriterion("master_name like", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameNotLike(String value) {
            addCriterion("master_name not like", value, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameIn(List<String> values) {
            addCriterion("master_name in", values, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameNotIn(List<String> values) {
            addCriterion("master_name not in", values, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameBetween(String value1, String value2) {
            addCriterion("master_name between", value1, value2, "masterName");
            return (Criteria) this;
        }

        public Criteria andMasterNameNotBetween(String value1, String value2) {
            addCriterion("master_name not between", value1, value2, "masterName");
            return (Criteria) this;
        }

        public Criteria andMciIsNull() {
            addCriterion("mci is null");
            return (Criteria) this;
        }

        public Criteria andMciIsNotNull() {
            addCriterion("mci is not null");
            return (Criteria) this;
        }

        public Criteria andMciEqualTo(String value) {
            addCriterion("mci =", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciNotEqualTo(String value) {
            addCriterion("mci <>", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciGreaterThan(String value) {
            addCriterion("mci >", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciGreaterThanOrEqualTo(String value) {
            addCriterion("mci >=", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciLessThan(String value) {
            addCriterion("mci <", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciLessThanOrEqualTo(String value) {
            addCriterion("mci <=", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciLike(String value) {
            addCriterion("mci like", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciNotLike(String value) {
            addCriterion("mci not like", value, "mci");
            return (Criteria) this;
        }

        public Criteria andMciIn(List<String> values) {
            addCriterion("mci in", values, "mci");
            return (Criteria) this;
        }

        public Criteria andMciNotIn(List<String> values) {
            addCriterion("mci not in", values, "mci");
            return (Criteria) this;
        }

        public Criteria andMciBetween(String value1, String value2) {
            addCriterion("mci between", value1, value2, "mci");
            return (Criteria) this;
        }

        public Criteria andMciNotBetween(String value1, String value2) {
            addCriterion("mci not between", value1, value2, "mci");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(Integer value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(Integer value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(Integer value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(Integer value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(Integer value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<Integer> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<Integer> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(Integer value1, Integer value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(Integer value1, Integer value2) {
            addCriterion("cost not between", value1, value2, "cost");
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

        public Criteria andOrtIsNull() {
            addCriterion("ort is null");
            return (Criteria) this;
        }

        public Criteria andOrtIsNotNull() {
            addCriterion("ort is not null");
            return (Criteria) this;
        }

        public Criteria andOrtEqualTo(Date value) {
            addCriterion("ort =", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtNotEqualTo(Date value) {
            addCriterion("ort <>", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtGreaterThan(Date value) {
            addCriterion("ort >", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtGreaterThanOrEqualTo(Date value) {
            addCriterion("ort >=", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtLessThan(Date value) {
            addCriterion("ort <", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtLessThanOrEqualTo(Date value) {
            addCriterion("ort <=", value, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtIn(List<Date> values) {
            addCriterion("ort in", values, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtNotIn(List<Date> values) {
            addCriterion("ort not in", values, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtBetween(Date value1, Date value2) {
            addCriterion("ort between", value1, value2, "ort");
            return (Criteria) this;
        }

        public Criteria andOrtNotBetween(Date value1, Date value2) {
            addCriterion("ort not between", value1, value2, "ort");
            return (Criteria) this;
        }

        public Criteria andOctIsNull() {
            addCriterion("oct is null");
            return (Criteria) this;
        }

        public Criteria andOctIsNotNull() {
            addCriterion("oct is not null");
            return (Criteria) this;
        }

        public Criteria andOctEqualTo(Date value) {
            addCriterion("oct =", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctNotEqualTo(Date value) {
            addCriterion("oct <>", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctGreaterThan(Date value) {
            addCriterion("oct >", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctGreaterThanOrEqualTo(Date value) {
            addCriterion("oct >=", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctLessThan(Date value) {
            addCriterion("oct <", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctLessThanOrEqualTo(Date value) {
            addCriterion("oct <=", value, "oct");
            return (Criteria) this;
        }

        public Criteria andOctIn(List<Date> values) {
            addCriterion("oct in", values, "oct");
            return (Criteria) this;
        }

        public Criteria andOctNotIn(List<Date> values) {
            addCriterion("oct not in", values, "oct");
            return (Criteria) this;
        }

        public Criteria andOctBetween(Date value1, Date value2) {
            addCriterion("oct between", value1, value2, "oct");
            return (Criteria) this;
        }

        public Criteria andOctNotBetween(Date value1, Date value2) {
            addCriterion("oct not between", value1, value2, "oct");
            return (Criteria) this;
        }

        public Criteria andSstIsNull() {
            addCriterion("sst is null");
            return (Criteria) this;
        }

        public Criteria andSstIsNotNull() {
            addCriterion("sst is not null");
            return (Criteria) this;
        }

        public Criteria andSstEqualTo(Date value) {
            addCriterion("sst =", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstNotEqualTo(Date value) {
            addCriterion("sst <>", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstGreaterThan(Date value) {
            addCriterion("sst >", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstGreaterThanOrEqualTo(Date value) {
            addCriterion("sst >=", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstLessThan(Date value) {
            addCriterion("sst <", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstLessThanOrEqualTo(Date value) {
            addCriterion("sst <=", value, "sst");
            return (Criteria) this;
        }

        public Criteria andSstIn(List<Date> values) {
            addCriterion("sst in", values, "sst");
            return (Criteria) this;
        }

        public Criteria andSstNotIn(List<Date> values) {
            addCriterion("sst not in", values, "sst");
            return (Criteria) this;
        }

        public Criteria andSstBetween(Date value1, Date value2) {
            addCriterion("sst between", value1, value2, "sst");
            return (Criteria) this;
        }

        public Criteria andSstNotBetween(Date value1, Date value2) {
            addCriterion("sst not between", value1, value2, "sst");
            return (Criteria) this;
        }

        public Criteria andOetIsNull() {
            addCriterion("oet is null");
            return (Criteria) this;
        }

        public Criteria andOetIsNotNull() {
            addCriterion("oet is not null");
            return (Criteria) this;
        }

        public Criteria andOetEqualTo(Date value) {
            addCriterion("oet =", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetNotEqualTo(Date value) {
            addCriterion("oet <>", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetGreaterThan(Date value) {
            addCriterion("oet >", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetGreaterThanOrEqualTo(Date value) {
            addCriterion("oet >=", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetLessThan(Date value) {
            addCriterion("oet <", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetLessThanOrEqualTo(Date value) {
            addCriterion("oet <=", value, "oet");
            return (Criteria) this;
        }

        public Criteria andOetIn(List<Date> values) {
            addCriterion("oet in", values, "oet");
            return (Criteria) this;
        }

        public Criteria andOetNotIn(List<Date> values) {
            addCriterion("oet not in", values, "oet");
            return (Criteria) this;
        }

        public Criteria andOetBetween(Date value1, Date value2) {
            addCriterion("oet between", value1, value2, "oet");
            return (Criteria) this;
        }

        public Criteria andOetNotBetween(Date value1, Date value2) {
            addCriterion("oet not between", value1, value2, "oet");
            return (Criteria) this;
        }

        public Criteria andOfstIsNull() {
            addCriterion("ofst is null");
            return (Criteria) this;
        }

        public Criteria andOfstIsNotNull() {
            addCriterion("ofst is not null");
            return (Criteria) this;
        }

        public Criteria andOfstEqualTo(Date value) {
            addCriterion("ofst =", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstNotEqualTo(Date value) {
            addCriterion("ofst <>", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstGreaterThan(Date value) {
            addCriterion("ofst >", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstGreaterThanOrEqualTo(Date value) {
            addCriterion("ofst >=", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstLessThan(Date value) {
            addCriterion("ofst <", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstLessThanOrEqualTo(Date value) {
            addCriterion("ofst <=", value, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstIn(List<Date> values) {
            addCriterion("ofst in", values, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstNotIn(List<Date> values) {
            addCriterion("ofst not in", values, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstBetween(Date value1, Date value2) {
            addCriterion("ofst between", value1, value2, "ofst");
            return (Criteria) this;
        }

        public Criteria andOfstNotBetween(Date value1, Date value2) {
            addCriterion("ofst not between", value1, value2, "ofst");
            return (Criteria) this;
        }

        public Criteria andEvaluationIsNull() {
            addCriterion("evaluation is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationIsNotNull() {
            addCriterion("evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationEqualTo(Integer value) {
            addCriterion("evaluation =", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotEqualTo(Integer value) {
            addCriterion("evaluation <>", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThan(Integer value) {
            addCriterion("evaluation >", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThanOrEqualTo(Integer value) {
            addCriterion("evaluation >=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThan(Integer value) {
            addCriterion("evaluation <", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThanOrEqualTo(Integer value) {
            addCriterion("evaluation <=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationIn(List<Integer> values) {
            addCriterion("evaluation in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotIn(List<Integer> values) {
            addCriterion("evaluation not in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationBetween(Integer value1, Integer value2) {
            addCriterion("evaluation between", value1, value2, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotBetween(Integer value1, Integer value2) {
            addCriterion("evaluation not between", value1, value2, "evaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationIsNull() {
            addCriterion("text_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationIsNotNull() {
            addCriterion("text_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationEqualTo(String value) {
            addCriterion("text_evaluation =", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationNotEqualTo(String value) {
            addCriterion("text_evaluation <>", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationGreaterThan(String value) {
            addCriterion("text_evaluation >", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationGreaterThanOrEqualTo(String value) {
            addCriterion("text_evaluation >=", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationLessThan(String value) {
            addCriterion("text_evaluation <", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationLessThanOrEqualTo(String value) {
            addCriterion("text_evaluation <=", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationLike(String value) {
            addCriterion("text_evaluation like", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationNotLike(String value) {
            addCriterion("text_evaluation not like", value, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationIn(List<String> values) {
            addCriterion("text_evaluation in", values, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationNotIn(List<String> values) {
            addCriterion("text_evaluation not in", values, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationBetween(String value1, String value2) {
            addCriterion("text_evaluation between", value1, value2, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andTextEvaluationNotBetween(String value1, String value2) {
            addCriterion("text_evaluation not between", value1, value2, "textEvaluation");
            return (Criteria) this;
        }

        public Criteria andCancelFlagIsNull() {
            addCriterion("cancel_flag is null");
            return (Criteria) this;
        }

        public Criteria andCancelFlagIsNotNull() {
            addCriterion("cancel_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCancelFlagEqualTo(String value) {
            addCriterion("cancel_flag =", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagNotEqualTo(String value) {
            addCriterion("cancel_flag <>", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagGreaterThan(String value) {
            addCriterion("cancel_flag >", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_flag >=", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagLessThan(String value) {
            addCriterion("cancel_flag <", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagLessThanOrEqualTo(String value) {
            addCriterion("cancel_flag <=", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagLike(String value) {
            addCriterion("cancel_flag like", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagNotLike(String value) {
            addCriterion("cancel_flag not like", value, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagIn(List<String> values) {
            addCriterion("cancel_flag in", values, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagNotIn(List<String> values) {
            addCriterion("cancel_flag not in", values, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagBetween(String value1, String value2) {
            addCriterion("cancel_flag between", value1, value2, "cancelFlag");
            return (Criteria) this;
        }

        public Criteria andCancelFlagNotBetween(String value1, String value2) {
            addCriterion("cancel_flag not between", value1, value2, "cancelFlag");
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

        public Criteria andPoundageIsNull() {
            addCriterion("poundage is null");
            return (Criteria) this;
        }

        public Criteria andPoundageIsNotNull() {
            addCriterion("poundage is not null");
            return (Criteria) this;
        }

        public Criteria andPoundageEqualTo(Integer value) {
            addCriterion("poundage =", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotEqualTo(Integer value) {
            addCriterion("poundage <>", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThan(Integer value) {
            addCriterion("poundage >", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThanOrEqualTo(Integer value) {
            addCriterion("poundage >=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThan(Integer value) {
            addCriterion("poundage <", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThanOrEqualTo(Integer value) {
            addCriterion("poundage <=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageIn(List<Integer> values) {
            addCriterion("poundage in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotIn(List<Integer> values) {
            addCriterion("poundage not in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageBetween(Integer value1, Integer value2) {
            addCriterion("poundage between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotBetween(Integer value1, Integer value2) {
            addCriterion("poundage not between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andMlngIsNull() {
            addCriterion("mlng is null");
            return (Criteria) this;
        }

        public Criteria andMlngIsNotNull() {
            addCriterion("mlng is not null");
            return (Criteria) this;
        }

        public Criteria andMlngEqualTo(BigDecimal value) {
            addCriterion("mlng =", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngNotEqualTo(BigDecimal value) {
            addCriterion("mlng <>", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngGreaterThan(BigDecimal value) {
            addCriterion("mlng >", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mlng >=", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngLessThan(BigDecimal value) {
            addCriterion("mlng <", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mlng <=", value, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngIn(List<BigDecimal> values) {
            addCriterion("mlng in", values, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngNotIn(List<BigDecimal> values) {
            addCriterion("mlng not in", values, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mlng between", value1, value2, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mlng not between", value1, value2, "mlng");
            return (Criteria) this;
        }

        public Criteria andMlatIsNull() {
            addCriterion("mlat is null");
            return (Criteria) this;
        }

        public Criteria andMlatIsNotNull() {
            addCriterion("mlat is not null");
            return (Criteria) this;
        }

        public Criteria andMlatEqualTo(BigDecimal value) {
            addCriterion("mlat =", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatNotEqualTo(BigDecimal value) {
            addCriterion("mlat <>", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatGreaterThan(BigDecimal value) {
            addCriterion("mlat >", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mlat >=", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatLessThan(BigDecimal value) {
            addCriterion("mlat <", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mlat <=", value, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatIn(List<BigDecimal> values) {
            addCriterion("mlat in", values, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatNotIn(List<BigDecimal> values) {
            addCriterion("mlat not in", values, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mlat between", value1, value2, "mlat");
            return (Criteria) this;
        }

        public Criteria andMlatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mlat not between", value1, value2, "mlat");
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

        public Criteria andSpecialTypeIsNull() {
            addCriterion("special_type is null");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeIsNotNull() {
            addCriterion("special_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeEqualTo(String value) {
            addCriterion("special_type =", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeNotEqualTo(String value) {
            addCriterion("special_type <>", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeGreaterThan(String value) {
            addCriterion("special_type >", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeGreaterThanOrEqualTo(String value) {
            addCriterion("special_type >=", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeLessThan(String value) {
            addCriterion("special_type <", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeLessThanOrEqualTo(String value) {
            addCriterion("special_type <=", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeLike(String value) {
            addCriterion("special_type like", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeNotLike(String value) {
            addCriterion("special_type not like", value, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeIn(List<String> values) {
            addCriterion("special_type in", values, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeNotIn(List<String> values) {
            addCriterion("special_type not in", values, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeBetween(String value1, String value2) {
            addCriterion("special_type between", value1, value2, "specialType");
            return (Criteria) this;
        }

        public Criteria andSpecialTypeNotBetween(String value1, String value2) {
            addCriterion("special_type not between", value1, value2, "specialType");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andCancelCauseIsNull() {
            addCriterion("cancel_cause is null");
            return (Criteria) this;
        }

        public Criteria andCancelCauseIsNotNull() {
            addCriterion("cancel_cause is not null");
            return (Criteria) this;
        }

        public Criteria andCancelCauseEqualTo(String value) {
            addCriterion("cancel_cause =", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseNotEqualTo(String value) {
            addCriterion("cancel_cause <>", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseGreaterThan(String value) {
            addCriterion("cancel_cause >", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_cause >=", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseLessThan(String value) {
            addCriterion("cancel_cause <", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseLessThanOrEqualTo(String value) {
            addCriterion("cancel_cause <=", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseLike(String value) {
            addCriterion("cancel_cause like", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseNotLike(String value) {
            addCriterion("cancel_cause not like", value, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseIn(List<String> values) {
            addCriterion("cancel_cause in", values, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseNotIn(List<String> values) {
            addCriterion("cancel_cause not in", values, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseBetween(String value1, String value2) {
            addCriterion("cancel_cause between", value1, value2, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andCancelCauseNotBetween(String value1, String value2) {
            addCriterion("cancel_cause not between", value1, value2, "cancelCause");
            return (Criteria) this;
        }

        public Criteria andInternalFlagIsNull() {
            addCriterion("Internal_flag is null");
            return (Criteria) this;
        }

        public Criteria andInternalFlagIsNotNull() {
            addCriterion("Internal_flag is not null");
            return (Criteria) this;
        }

        public Criteria andInternalFlagEqualTo(Integer value) {
            addCriterion("Internal_flag =", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagNotEqualTo(Integer value) {
            addCriterion("Internal_flag <>", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagGreaterThan(Integer value) {
            addCriterion("Internal_flag >", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("Internal_flag >=", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagLessThan(Integer value) {
            addCriterion("Internal_flag <", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagLessThanOrEqualTo(Integer value) {
            addCriterion("Internal_flag <=", value, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagIn(List<Integer> values) {
            addCriterion("Internal_flag in", values, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagNotIn(List<Integer> values) {
            addCriterion("Internal_flag not in", values, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagBetween(Integer value1, Integer value2) {
            addCriterion("Internal_flag between", value1, value2, "internalFlag");
            return (Criteria) this;
        }

        public Criteria andInternalFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("Internal_flag not between", value1, value2, "internalFlag");
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