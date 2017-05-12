package cn.com.didi.order.orders.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderNotifyDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderNotifyDtoExample() {
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

        public Criteria andValidFlagIsNull() {
            addCriterion("valid_flag is null");
            return (Criteria) this;
        }

        public Criteria andValidFlagIsNotNull() {
            addCriterion("valid_flag is not null");
            return (Criteria) this;
        }

        public Criteria andValidFlagEqualTo(String value) {
            addCriterion("valid_flag =", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotEqualTo(String value) {
            addCriterion("valid_flag <>", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagGreaterThan(String value) {
            addCriterion("valid_flag >", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagGreaterThanOrEqualTo(String value) {
            addCriterion("valid_flag >=", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLessThan(String value) {
            addCriterion("valid_flag <", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLessThanOrEqualTo(String value) {
            addCriterion("valid_flag <=", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagLike(String value) {
            addCriterion("valid_flag like", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotLike(String value) {
            addCriterion("valid_flag not like", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagIn(List<String> values) {
            addCriterion("valid_flag in", values, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotIn(List<String> values) {
            addCriterion("valid_flag not in", values, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagBetween(String value1, String value2) {
            addCriterion("valid_flag between", value1, value2, "validFlag");
            return (Criteria) this;
        }

        public Criteria andValidFlagNotBetween(String value1, String value2) {
            addCriterion("valid_flag not between", value1, value2, "validFlag");
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