package cn.com.didi.app.deal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.DrawInfoDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

public class DrawInfoWrapperJAO {
	private DrawInfoDto drawInfo;
	private List<ReamainingWrapperJAO> remainingList;

	public List<ReamainingWrapperJAO> getRemainingList() {
		return remainingList;
	}

	public void setRemainingList(List<ReamainingWrapperJAO> remainingList) {
		this.remainingList = remainingList;
	}

	public DrawInfoWrapperJAO(DrawInfoDto drawInfo, UserLinkIdDto userLinkedId) {
		super();
		this.drawInfo = drawInfo;
		List<MerchantDayRemainingDto> remain = drawInfo.getRemainDto();
		if (remain == null) {
			remain = new ArrayList<>(2);
			for (PayAccountEnum one : PayAccountEnum.values()) {
				remain.add(createDefault(one));
			}
		}
		if (remain != null) {
			remain.stream().map(one -> new ReamainingWrapperJAO(one, userLinkedId)).collect(Collectors.toList());
		}
	}

	protected MerchantDayRemainingDto createDefault(PayAccountEnum enums) {
		MerchantDayRemainingDto dto = new MerchantDayRemainingDto();
		dto.setPat(enums.getCode());
		dto.setRemaining(0L);
		return dto;
	}

	public void setTotal(Long total) {
		drawInfo.setTotal(total);
	}

	public Long getPending() {
		return drawInfo.getPending();
	}

	public void setPending(Long pending) {
		drawInfo.setPending(pending);
	}

	public List<MerchantDayRemainingDto> getRemainDto() {
		return drawInfo.getRemainDto();
	}

}
