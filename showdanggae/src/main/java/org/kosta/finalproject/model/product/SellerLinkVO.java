package org.kosta.finalproject.model.product;

public class SellerLinkVO {
	
	private String link, member_id;
	private int category_id, product_id, price;
	
	public SellerLinkVO() {
		super();
	}
	
	public SellerLinkVO(String link, String member_id, int category_id,
			int product_id, int price) {
		super();
		this.link = link;
		this.member_id = member_id;
		this.category_id = category_id;
		this.product_id = product_id;
		this.price = price;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "SellerLinkVO [link=" + link + ", member_id=" + member_id
				+ ", category_id=" + category_id + ", product_id=" + product_id
				+ ", price=" + price + "]";
	}
	
}
