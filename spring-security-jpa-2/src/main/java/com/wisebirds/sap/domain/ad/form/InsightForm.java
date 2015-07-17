package com.wisebirds.sap.domain.ad.form;

public class InsightForm {
	private Long id;
	private String date_start;
	private String date_stop;
	private String date_end;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}

	public String getDate_stop() {
		return date_stop;
	}

	public void setDate_stop(String date_stop) {
		this.date_stop = date_stop;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
}
