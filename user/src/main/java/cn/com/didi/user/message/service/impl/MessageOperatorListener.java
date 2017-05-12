package cn.com.didi.user.message.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.select.impl.SimplePageBound;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.FlagEnum;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.Role;
import cn.com.didi.message.push.service.IPushMessageService;
import cn.com.didi.user.message.domain.TMessageDto;
import cn.com.didi.user.message.util.MessageOperation;
@Service
public class MessageOperatorListener implements IOperationListener<MessageOperation, TMessageDto> {
	@Resource
	protected IReciverSearchService searchService;
	@Resource
	protected IPushMessageService pushMessageServiceImpl;

	@Override
	public void operate(MessageOperation operation, TMessageDto data) {
		if (MessageOperation.MESSAGE_ADDED.equals(operation)) {
			if (FlagEnum.FLAG_SET.codeEqual(data.getUserFlag())) {
				pushMessage(data, new ReciverIterator(null, true));
			}
			boolean tpmSet = FlagEnum.FLAG_SET.codeEqual(data.getTpmFlag());
			boolean selfSet = FlagEnum.FLAG_SET.codeEqual(data.getSmFlag());
			if (tpmSet || selfSet) {
				BusinessCategory cat = tpmSet && selfSet ? null
						: (selfSet ? BusinessCategory.SELF : BusinessCategory.THIRD);
				pushMessage(data, new ReciverIterator(cat, false));
			}

		}
	}

	public void pushMessage(TMessageDto data, Iterator<List<IReciverDto>> ite) {
		try {
			while (ite.hasNext()) {
				List<IReciverDto> list = ite.next();
				if (list != null) {
					pushMessageServiceImpl.push(list, null);
				}
			}
		} catch (Exception e) {

		}
	}

	protected class ReciverIterator implements Iterator<List<IReciverDto>> {
		List<IReciverDto> now;
		private int pageIndex = 1;
		private int pageSize = 10000;
		private BusinessCategory cat;
		private boolean role = false;

		public ReciverIterator(BusinessCategory cat, boolean role) {
			super();
			this.cat = cat;
			this.role = role;
		}

		@Override
		public boolean hasNext() {
			if ((now == null && pageIndex == 1) || (now != null && now.size() == pageSize)) {
				return true;
			}
			return false;
		}

		@Override
		public List<IReciverDto> next() {
			IPage<IReciverDto> page = null;
			SimplePageBound spb = new SimplePageBound();
			spb.setPageIndex(pageIndex);
			spb.setPageSize(pageSize);
			if (role) {
				page = searchService.listAllUser(Role.BUSINESS, spb);
			} else {
				page = searchService.listMerchats(cat, spb);
			}
			now = page.getList();
			pageIndex++;
			return now;
		}

	}

}
