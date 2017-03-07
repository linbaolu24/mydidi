package cn.com.didi.user.users.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantDtoExample() {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_Name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_Name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_Name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_Name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_Name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_Name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_Name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_Name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_Name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_Name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_Name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_Name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_Name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_Name not between", value1, value2, "userName");
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

        public Criteria andDetailAddressIsNull() {
            addCriterion("detail_address is null");
            return (Criteria) this;
        }

        public Criteria andDetailAddressIsNotNull() {
            addCriterion("detail_address is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAddressEqualTo(String value) {
            addCriterion("detail_address =", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotEqualTo(String value) {
            addCriterion("detail_address <>", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressGreaterThan(String value) {
            addCriterion("detail_address >", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("detail_address >=", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLessThan(String value) {
            addCriterion("detail_address <", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLessThanOrEqualTo(String value) {
            addCriterion("detail_address <=", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLike(String value) {
            addCriterion("detail_address like", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotLike(String value) {
            addCriterion("detail_address not like", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressIn(List<String> values) {
            addCriterion("detail_address in", values, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotIn(List<String> values) {
            addCriterion("detail_address not in", values, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressBetween(String value1, String value2) {
            addCriterion("detail_address between", value1, value2, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotBetween(String value1, String value2) {
            addCriterion("detail_address not between", value1, value2, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andAddressCodeIsNull() {
            addCriterion("address_code is null");
            return (Criteria) this;
        }

        public Criteria andAddressCodeIsNotNull() {
            addCriterion("address_code is not null");
            return (Criteria) this;
        }

        public Criteria andAddressCodeEqualTo(String value) {
            addCriterion("address_code =", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotEqualTo(String value) {
            addCriterion("address_code <>", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeGreaterThan(String value) {
            addCriterion("address_code >", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeGreaterThanOrEqualTo(String value) {
            addCriterion("address_code >=", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLessThan(String value) {
            addCriterion("address_code <", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLessThanOrEqualTo(String value) {
            addCriterion("address_code <=", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLike(String value) {
            addCriterion("address_code like", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotLike(String value) {
            addCriterion("address_code not like", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeIn(List<String> values) {
            addCriterion("address_code in", values, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotIn(List<String> values) {
            addCriterion("address_code not in", values, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeBetween(String value1, String value2) {
            addCriterion("address_code between", value1, value2, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotBetween(String value1, String value2) {
            addCriterion("address_code not between", value1, value2, "addressCode");
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

        public Criteria andIcImg1IsNull() {
            addCriterion("ic_img1 is null");
            return (Criteria) this;
        }

        public Criteria andIcImg1IsNotNull() {
            addCriterion("ic_img1 is not null");
            return (Criteria) this;
        }

        public Criteria andIcImg1EqualTo(String value) {
            addCriterion("ic_img1 =", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1NotEqualTo(String value) {
            addCriterion("ic_img1 <>", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1GreaterThan(String value) {
            addCriterion("ic_img1 >", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1GreaterThanOrEqualTo(String value) {
            addCriterion("ic_img1 >=", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1LessThan(String value) {
            addCriterion("ic_img1 <", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1LessThanOrEqualTo(String value) {
            addCriterion("ic_img1 <=", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1Like(String value) {
            addCriterion("ic_img1 like", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1NotLike(String value) {
            addCriterion("ic_img1 not like", value, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1In(List<String> values) {
            addCriterion("ic_img1 in", values, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1NotIn(List<String> values) {
            addCriterion("ic_img1 not in", values, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1Between(String value1, String value2) {
            addCriterion("ic_img1 between", value1, value2, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg1NotBetween(String value1, String value2) {
            addCriterion("ic_img1 not between", value1, value2, "icImg1");
            return (Criteria) this;
        }

        public Criteria andIcImg2IsNull() {
            addCriterion("ic_img2 is null");
            return (Criteria) this;
        }

        public Criteria andIcImg2IsNotNull() {
            addCriterion("ic_img2 is not null");
            return (Criteria) this;
        }

        public Criteria andIcImg2EqualTo(String value) {
            addCriterion("ic_img2 =", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2NotEqualTo(String value) {
            addCriterion("ic_img2 <>", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2GreaterThan(String value) {
            addCriterion("ic_img2 >", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2GreaterThanOrEqualTo(String value) {
            addCriterion("ic_img2 >=", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2LessThan(String value) {
            addCriterion("ic_img2 <", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2LessThanOrEqualTo(String value) {
            addCriterion("ic_img2 <=", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2Like(String value) {
            addCriterion("ic_img2 like", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2NotLike(String value) {
            addCriterion("ic_img2 not like", value, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2In(List<String> values) {
            addCriterion("ic_img2 in", values, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2NotIn(List<String> values) {
            addCriterion("ic_img2 not in", values, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2Between(String value1, String value2) {
            addCriterion("ic_img2 between", value1, value2, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg2NotBetween(String value1, String value2) {
            addCriterion("ic_img2 not between", value1, value2, "icImg2");
            return (Criteria) this;
        }

        public Criteria andIcImg3IsNull() {
            addCriterion("ic_img3 is null");
            return (Criteria) this;
        }

        public Criteria andIcImg3IsNotNull() {
            addCriterion("ic_img3 is not null");
            return (Criteria) this;
        }

        public Criteria andIcImg3EqualTo(String value) {
            addCriterion("ic_img3 =", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3NotEqualTo(String value) {
            addCriterion("ic_img3 <>", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3GreaterThan(String value) {
            addCriterion("ic_img3 >", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3GreaterThanOrEqualTo(String value) {
            addCriterion("ic_img3 >=", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3LessThan(String value) {
            addCriterion("ic_img3 <", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3LessThanOrEqualTo(String value) {
            addCriterion("ic_img3 <=", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3Like(String value) {
            addCriterion("ic_img3 like", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3NotLike(String value) {
            addCriterion("ic_img3 not like", value, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3In(List<String> values) {
            addCriterion("ic_img3 in", values, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3NotIn(List<String> values) {
            addCriterion("ic_img3 not in", values, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3Between(String value1, String value2) {
            addCriterion("ic_img3 between", value1, value2, "icImg3");
            return (Criteria) this;
        }

        public Criteria andIcImg3NotBetween(String value1, String value2) {
            addCriterion("ic_img3 not between", value1, value2, "icImg3");
            return (Criteria) this;
        }

        public Criteria andLicenceImgIsNull() {
            addCriterion("licence_img is null");
            return (Criteria) this;
        }

        public Criteria andLicenceImgIsNotNull() {
            addCriterion("licence_img is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceImgEqualTo(String value) {
            addCriterion("licence_img =", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgNotEqualTo(String value) {
            addCriterion("licence_img <>", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgGreaterThan(String value) {
            addCriterion("licence_img >", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgGreaterThanOrEqualTo(String value) {
            addCriterion("licence_img >=", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgLessThan(String value) {
            addCriterion("licence_img <", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgLessThanOrEqualTo(String value) {
            addCriterion("licence_img <=", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgLike(String value) {
            addCriterion("licence_img like", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgNotLike(String value) {
            addCriterion("licence_img not like", value, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgIn(List<String> values) {
            addCriterion("licence_img in", values, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgNotIn(List<String> values) {
            addCriterion("licence_img not in", values, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgBetween(String value1, String value2) {
            addCriterion("licence_img between", value1, value2, "licenceImg");
            return (Criteria) this;
        }

        public Criteria andLicenceImgNotBetween(String value1, String value2) {
            addCriterion("licence_img not between", value1, value2, "licenceImg");
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

        public Criteria andCrIsNull() {
            addCriterion("cr is null");
            return (Criteria) this;
        }

        public Criteria andCrIsNotNull() {
            addCriterion("cr is not null");
            return (Criteria) this;
        }

        public Criteria andCrEqualTo(String value) {
            addCriterion("cr =", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrNotEqualTo(String value) {
            addCriterion("cr <>", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrGreaterThan(String value) {
            addCriterion("cr >", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrGreaterThanOrEqualTo(String value) {
            addCriterion("cr >=", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrLessThan(String value) {
            addCriterion("cr <", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrLessThanOrEqualTo(String value) {
            addCriterion("cr <=", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrLike(String value) {
            addCriterion("cr like", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrNotLike(String value) {
            addCriterion("cr not like", value, "cr");
            return (Criteria) this;
        }

        public Criteria andCrIn(List<String> values) {
            addCriterion("cr in", values, "cr");
            return (Criteria) this;
        }

        public Criteria andCrNotIn(List<String> values) {
            addCriterion("cr not in", values, "cr");
            return (Criteria) this;
        }

        public Criteria andCrBetween(String value1, String value2) {
            addCriterion("cr between", value1, value2, "cr");
            return (Criteria) this;
        }

        public Criteria andCrNotBetween(String value1, String value2) {
            addCriterion("cr not between", value1, value2, "cr");
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

        public Criteria andContactInformationIsNull() {
            addCriterion("contact_information is null");
            return (Criteria) this;
        }

        public Criteria andContactInformationIsNotNull() {
            addCriterion("contact_information is not null");
            return (Criteria) this;
        }

        public Criteria andContactInformationEqualTo(String value) {
            addCriterion("contact_information =", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationNotEqualTo(String value) {
            addCriterion("contact_information <>", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationGreaterThan(String value) {
            addCriterion("contact_information >", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationGreaterThanOrEqualTo(String value) {
            addCriterion("contact_information >=", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationLessThan(String value) {
            addCriterion("contact_information <", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationLessThanOrEqualTo(String value) {
            addCriterion("contact_information <=", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationLike(String value) {
            addCriterion("contact_information like", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationNotLike(String value) {
            addCriterion("contact_information not like", value, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationIn(List<String> values) {
            addCriterion("contact_information in", values, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationNotIn(List<String> values) {
            addCriterion("contact_information not in", values, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationBetween(String value1, String value2) {
            addCriterion("contact_information between", value1, value2, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andContactInformationNotBetween(String value1, String value2) {
            addCriterion("contact_information not between", value1, value2, "contactInformation");
            return (Criteria) this;
        }

        public Criteria andBpnIsNull() {
            addCriterion("bpn is null");
            return (Criteria) this;
        }

        public Criteria andBpnIsNotNull() {
            addCriterion("bpn is not null");
            return (Criteria) this;
        }

        public Criteria andBpnEqualTo(String value) {
            addCriterion("bpn =", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnNotEqualTo(String value) {
            addCriterion("bpn <>", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnGreaterThan(String value) {
            addCriterion("bpn >", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnGreaterThanOrEqualTo(String value) {
            addCriterion("bpn >=", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnLessThan(String value) {
            addCriterion("bpn <", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnLessThanOrEqualTo(String value) {
            addCriterion("bpn <=", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnLike(String value) {
            addCriterion("bpn like", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnNotLike(String value) {
            addCriterion("bpn not like", value, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnIn(List<String> values) {
            addCriterion("bpn in", values, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnNotIn(List<String> values) {
            addCriterion("bpn not in", values, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnBetween(String value1, String value2) {
            addCriterion("bpn between", value1, value2, "bpn");
            return (Criteria) this;
        }

        public Criteria andBpnNotBetween(String value1, String value2) {
            addCriterion("bpn not between", value1, value2, "bpn");
            return (Criteria) this;
        }

        public Criteria andMasternameIsNull() {
            addCriterion("masterName is null");
            return (Criteria) this;
        }

        public Criteria andMasternameIsNotNull() {
            addCriterion("masterName is not null");
            return (Criteria) this;
        }

        public Criteria andMasternameEqualTo(String value) {
            addCriterion("masterName =", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameNotEqualTo(String value) {
            addCriterion("masterName <>", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameGreaterThan(String value) {
            addCriterion("masterName >", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameGreaterThanOrEqualTo(String value) {
            addCriterion("masterName >=", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameLessThan(String value) {
            addCriterion("masterName <", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameLessThanOrEqualTo(String value) {
            addCriterion("masterName <=", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameLike(String value) {
            addCriterion("masterName like", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameNotLike(String value) {
            addCriterion("masterName not like", value, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameIn(List<String> values) {
            addCriterion("masterName in", values, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameNotIn(List<String> values) {
            addCriterion("masterName not in", values, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameBetween(String value1, String value2) {
            addCriterion("masterName between", value1, value2, "mastername");
            return (Criteria) this;
        }

        public Criteria andMasternameNotBetween(String value1, String value2) {
            addCriterion("masterName not between", value1, value2, "mastername");
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