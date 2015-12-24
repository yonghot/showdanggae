package org.kosta.finalproject.model.product;

/**
 * 항목명, product_id등 다수의 정보를 포함하고 있는 평가 항목을 전달하기 위한 VO 클래스
 * @author 용호
 */
public class EvaluatingItemVO {
	private String item;
	private int product_id, item_point, category_id;
	public EvaluatingItemVO() {
		super();
	}
	public EvaluatingItemVO(String item, int product_id, int item_point,
			int category_id) {
		super();
		this.item = item;
		this.product_id = product_id;
		this.item_point = item_point;
		this.category_id = category_id;
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
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "EvaluatingItemVO [item=" + item + ", product_id=" + product_id
				+ ", item_point=" + item_point + ", category_id=" + category_id
				+ "]";
	}
	
}
