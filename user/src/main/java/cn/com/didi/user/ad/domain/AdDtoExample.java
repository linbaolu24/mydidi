package cn.com.didi.user.ad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdDtoExample() {
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

        public Criteria andDisplayPositionIsNull() {
            addCriterion("display_position is null");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionIsNotNull() {
            addCriterion("display_position is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionEqualTo(String value) {
            addCriterion("display_position =", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionNotEqualTo(String value) {
            addCriterion("display_position <>", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionGreaterThan(String value) {
            addCriterion("display_position >", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionGreaterThanOrEqualTo(String value) {
            addCriterion("display_position >=", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionLessThan(String value) {
            addCriterion("display_position <", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionLessThanOrEqualTo(String value) {
            addCriterion("display_position <=", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionLike(String value) {
            addCriterion("display_position like", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionNotLike(String value) {
            addCriterion("display_position not like", value, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionIn(List<String> values) {
            addCriterion("display_position in", values, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionNotIn(List<String> values) {
            addCriterion("display_position not in", values, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionBetween(String value1, String value2) {
            addCriterion("display_position between", value1, value2, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andDisplayPositionNotBetween(String value1, String value2) {
            addCriterion("display_position not between", value1, value2, "displayPosition");
            return (Criteria) this;
        }

        public Criteria andLpUrlIsNull() {
            addCriterion("lp_url is null");
            return (Criteria) this;
        }

        public Criteria andLpUrlIsNotNull() {
            addCriterion("lp_url is not null");
            return (Criteria) this;
        }

        public Criteria andLpUrlEqualTo(String value) {
            addCriterion("lp_url =", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlNotEqualTo(String value) {
            addCriterion("lp_url <>", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlGreaterThan(String value) {
            addCriterion("lp_url >", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("lp_url >=", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlLessThan(String value) {
            addCriterion("lp_url <", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlLessThanOrEqualTo(String value) {
            addCriterion("lp_url <=", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlLike(String value) {
            addCriterion("lp_url like", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlNotLike(String value) {
            addCriterion("lp_url not like", value, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlIn(List<String> values) {
            addCriterion("lp_url in", values, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlNotIn(List<String> values) {
            addCriterion("lp_url not in", values, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlBetween(String value1, String value2) {
            addCriterion("lp_url between", value1, value2, "lpUrl");
            return (Criteria) this;
        }

        public Criteria andLpUrlNotBetween(String value1, String value2) {
            addCriterion("lp_url not between", value1, value2, "lpUrl");
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

        public Criteria andAdsStartIsNull() {
            addCriterion("ads_start is null");
            return (Criteria) this;
        }

        public Criteria andAdsStartIsNotNull() {
            addCriterion("ads_start is not null");
            return (Criteria) this;
        }

        public Criteria andAdsStartEqualTo(Date value) {
            addCriterion("ads_start =", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartNotEqualTo(Date value) {
            addCriterion("ads_start <>", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartGreaterThan(Date value) {
            addCriterion("ads_start >", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartGreaterThanOrEqualTo(Date value) {
            addCriterion("ads_start >=", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartLessThan(Date value) {
            addCriterion("ads_start <", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartLessThanOrEqualTo(Date value) {
            addCriterion("ads_start <=", value, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartIn(List<Date> values) {
            addCriterion("ads_start in", values, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartNotIn(List<Date> values) {
            addCriterion("ads_start not in", values, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartBetween(Date value1, Date value2) {
            addCriterion("ads_start between", value1, value2, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsStartNotBetween(Date value1, Date value2) {
            addCriterion("ads_start not between", value1, value2, "adsStart");
            return (Criteria) this;
        }

        public Criteria andAdsEndIsNull() {
            addCriterion("ads_end is null");
            return (Criteria) this;
        }

        public Criteria andAdsEndIsNotNull() {
            addCriterion("ads_end is not null");
            return (Criteria) this;
        }

        public Criteria andAdsEndEqualTo(Date value) {
            addCriterion("ads_end =", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndNotEqualTo(Date value) {
            addCriterion("ads_end <>", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndGreaterThan(Date value) {
            addCriterion("ads_end >", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndGreaterThanOrEqualTo(Date value) {
            addCriterion("ads_end >=", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndLessThan(Date value) {
            addCriterion("ads_end <", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndLessThanOrEqualTo(Date value) {
            addCriterion("ads_end <=", value, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndIn(List<Date> values) {
            addCriterion("ads_end in", values, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndNotIn(List<Date> values) {
            addCriterion("ads_end not in", values, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndBetween(Date value1, Date value2) {
            addCriterion("ads_end between", value1, value2, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsEndNotBetween(Date value1, Date value2) {
            addCriterion("ads_end not between", value1, value2, "adsEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartIsNull() {
            addCriterion("ads_time_start is null");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartIsNotNull() {
            addCriterion("ads_time_start is not null");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartEqualTo(Integer value) {
            addCriterion("ads_time_start =", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartNotEqualTo(Integer value) {
            addCriterion("ads_time_start <>", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartGreaterThan(Integer value) {
            addCriterion("ads_time_start >", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("ads_time_start >=", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartLessThan(Integer value) {
            addCriterion("ads_time_start <", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartLessThanOrEqualTo(Integer value) {
            addCriterion("ads_time_start <=", value, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartIn(List<Integer> values) {
            addCriterion("ads_time_start in", values, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartNotIn(List<Integer> values) {
            addCriterion("ads_time_start not in", values, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartBetween(Integer value1, Integer value2) {
            addCriterion("ads_time_start between", value1, value2, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeStartNotBetween(Integer value1, Integer value2) {
            addCriterion("ads_time_start not between", value1, value2, "adsTimeStart");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndIsNull() {
            addCriterion("ads_time_end is null");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndIsNotNull() {
            addCriterion("ads_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndEqualTo(Integer value) {
            addCriterion("ads_time_end =", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndNotEqualTo(Integer value) {
            addCriterion("ads_time_end <>", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndGreaterThan(Integer value) {
            addCriterion("ads_time_end >", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("ads_time_end >=", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndLessThan(Integer value) {
            addCriterion("ads_time_end <", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndLessThanOrEqualTo(Integer value) {
            addCriterion("ads_time_end <=", value, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndIn(List<Integer> values) {
            addCriterion("ads_time_end in", values, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndNotIn(List<Integer> values) {
            addCriterion("ads_time_end not in", values, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndBetween(Integer value1, Integer value2) {
            addCriterion("ads_time_end between", value1, value2, "adsTimeEnd");
            return (Criteria) this;
        }

        public Criteria andAdsTimeEndNotBetween(Integer value1, Integer value2) {
            addCriterion("ads_time_end not between", value1, value2, "adsTimeEnd");
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

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
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