package com.wisebirds.sap.domain;

public class SimplePagingImpl {
	private Integer page = 0;
	private Integer limit = 5;
	private String type = "pending";

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
