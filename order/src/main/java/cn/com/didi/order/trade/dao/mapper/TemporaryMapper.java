package cn.com.didi.order.trade.dao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 临时Mapper用来存放杂论Sql更新
 * @author xlm
 *
 */
public interface TemporaryMapper {
	/**
	 * 更新VIPState
	 * @return
	 */
	public int updateVipState(@Param("accountId") Long accountId,@Param("slsId") Integer slsId,@Param("dealId") Long dealId,@Param("state") String state);
}
