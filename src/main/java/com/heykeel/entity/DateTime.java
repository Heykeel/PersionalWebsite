package com.heykeel.entity;

public class DateTime {
	private String releaseDateStr;
	private String releaseDateStrCN;
	private Integer dateCount;
	
	public DateTime(String releaseDateStr, String releaseDateStrCN ,Integer dateCount){
		this.releaseDateStr = releaseDateStr;
		this.releaseDateStrCN = releaseDateStrCN;
		this.dateCount = dateCount;
	}
	
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
	public Integer getDateCount() {
		return dateCount;
	}
	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public String getReleaseDateStrCN() {
		return releaseDateStrCN;
	}

	public void setReleaseDateStrCN(String releaseDateStrCN) {
		this.releaseDateStrCN = releaseDateStrCN;
	}
}
