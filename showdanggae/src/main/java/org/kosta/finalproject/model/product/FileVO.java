package org.kosta.finalproject.model.product;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	
	private String member_id;
	private MultipartFile thumbnailImgFile;
	public FileVO() {
		super();
	}
	public FileVO(String member_id, MultipartFile thumbnailImgFile) {
		super();
		this.member_id = member_id;
		this.thumbnailImgFile = thumbnailImgFile;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public MultipartFile getThumbnailImgFile() {
		return thumbnailImgFile;
	}
	public void setThumbnailImgFile(MultipartFile thumbnailImgFile) {
		this.thumbnailImgFile = thumbnailImgFile;
	}
	@Override
	public String toString() {
		return "FileVO [member_id=" + member_id + ", thumbnailImgFile="
				+ thumbnailImgFile + "]";
	}
}
