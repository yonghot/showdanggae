package org.kosta.finalproject.model.product;

public class EvaluatingItemVO {
	private String item;
	private int product_id, item_point;
	public EvaluatingItemVO() {
		super();
	}
	public EvaluatingItemVO(String item, int product_id, int item_point) {
		super();
		this.item = item;
		this.product_id = product_id;
		this.item_point = item_point;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getItem_point() {
		return item_point;
	}
	public void setItem_point(int item_point) {
		this.item_point = item_point;
	}
	@Override
	public String toString() {
		return "EvaluatingItemVO [item=" + item + ", product_id=" + product_id
				+ ", item_point=" + item_point + "]";
	}
	
}
