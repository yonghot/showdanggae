
package org.kosta.finalproject.model.member;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private String member_id;
	private MultipartFile proImgFile;
	
	public FileVO() {
		super();
	}

	public FileVO(String member_id, MultipartFile proImgFile) {
		super();
		this.member_id = member_id;
		this.proImgFile = proImgFile;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public MultipartFile getProImgFile() {
		return proImgFile;
	}

	public void setProImgFile(MultipartFile proImgFile) {
		this.proImgFile = proImgFile;
	}

	@Override
	public String toString() {
		return "FileVO [member_id=" + member_id + ", proImgFile=" + proImgFile
				+ "]";
	}
	
	
	
	
	
	
}
