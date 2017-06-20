package cn.com.didi.order.trade.domain;

import java.io.Serializable;
import java.util.Date;

public class DealRecordDto implements Serializable {
    /**
     * 流水id
     */
    private Long streamId;

    /**
     * 交易id
     */
    private Long dealId;

    /**
     * 交易系统id
     */
    private String tradeId;

    private String category;

    /**
     * 二级分类
     */
    private String subCategory;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 原数据
     */
    private String source;

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     **/
    public Long getStreamId() {
        return streamId;
    }

    /**
     * 流水id
     **/
    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    /**
     * 交易id
     **/
    public Long getDealId() {
        return dealId;
    }

    /**
     * 交易id
     **/
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    /**
     * 交易系统id
     **/
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 交易系统id
     **/
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * 二级分类
     **/
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * 二级分类
     **/
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }

    /**
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 原数据
     **/
    public String getSource() {
        return source;
    }

    /**
     * 原数据
     **/
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}