package org.kosta.finalproject.model.category;

public class CategoryVO {
	private String category_id;
	private String member_id;
	private String category;
	public CategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryVO(String category_id, String member_id, String category) {
		super();
		this.category_id = category_id;
		this.member_id = member_id;
		this.category = category;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "CategoryVO [category_id=" + category_id + ", member_id="
				+ member_id + ", category=" + category + "]";
	}
	
}
