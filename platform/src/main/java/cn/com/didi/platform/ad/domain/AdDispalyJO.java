package cn.com.didi.platform.ad.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.user.ad.domain.DpDto;

public class AdDispalyJO {
	private String cname;
	private String displayPosition;
	private List<DpDto> imgList;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}

	public List<DpDto> getImgList() {
		return imgList;
	}

	public void setImgList(List<DpDto> imgList) {
		this.imgList = imgList;
	}
	
	public void addImg(DpDto img) {
		if(this.imgList==null){
			this.imgList=new ArrayList<>(4);
		}
		this.imgList.add(img);
	}

	public static List<AdDispalyJO> wrapper(List<DpDto> dpdto) {
		if (CollectionUtils.isEmpty(dpdto)) {
			
			return null;
		}
		Map<String,AdDispalyJO> map = new HashMap<>();
		for(DpDto one:dpdto){
			AdDispalyJO jo=map.get(one.getDisplayPosition());
			if(jo==null){
				jo=new AdDispalyJO();
				map.put(one.getDisplayPosition(), jo);
			}
			jo.addImg(one);
		}
		return new ArrayList<>(map.values());

	}
}
