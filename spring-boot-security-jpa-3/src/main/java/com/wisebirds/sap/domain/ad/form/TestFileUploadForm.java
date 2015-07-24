package com.wisebirds.sap.domain.ad.form;

import org.springframework.web.multipart.MultipartFile;

public class TestFileUploadForm {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
