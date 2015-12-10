package org.kosta.finalproject.model.product;

public class SellerLinkVO {
	
	private String link;
	private int product_id, price;
	
	public SellerLinkVO() {
		super();
	}

	public SellerLinkVO(String link, int product_id, int price) {
		super();
		this.link = link;
		this.product_id = product_id;
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
		return "SellerLinkVO [link=" + link + ", product_id=" + product_id
				+ ", price=" + price + "]";
	}
	
}
