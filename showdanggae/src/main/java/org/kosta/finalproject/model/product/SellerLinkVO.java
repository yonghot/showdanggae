package org.kosta.finalproject.model.product;


/**
 * 링크, product_id등 다수의 정보를 포함하고 있는 판매 링크를 전달하기 위한 VO 클래스
 * @author 용호
 *
 */
public class SellerLinkVO {
	
	private String link;
	private int product_id, price, category_id;
	
	public SellerLinkVO() {
		super();
	}

	public SellerLinkVO(String link, int product_id, int price, int category_id) {
		super();
		this.link = link;
		this.product_id = product_id;
		this.price = price;
		this.category_id = category_id;
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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "SellerLinkVO [link=" + link + ", product_id=" + product_id
				+ ", price=" + price + ", category_id=" + category_id + "]";
	}

}
