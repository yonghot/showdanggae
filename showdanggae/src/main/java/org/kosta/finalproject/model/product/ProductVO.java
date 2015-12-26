package org.kosta.finalproject.model.product;


/**
 * 다수의 정보를 갖는 product를 전달하기 위한 VO 클래스 
 * @author 용호
 *
 */
public class ProductVO {
	
	private int product_id;
	private int category_id;
	private String member_id;
	private String product_name;
	private int likes;
	private int dislikes;
	private int hits;
	private String review;
	private int review_score;
	private String detail;
	private int visiblity;
	private String regist_date;
	private int currentCategory;
	private String thumbnail_link;
	private int lowestPrice;
	private int rownum;
	
	public ProductVO() {
		super();
	}

	public ProductVO(int product_id, int category_id, String member_id,
			String product_name, int likes, int dislikes, int hits,
			String review, int review_score, String detail, int visiblity,
			String regist_date, int currentCategory, String thumbnail_link,
			int lowestPrice, int rownum) {
		super();
		this.product_id = product_id;
		this.category_id = category_id;
		this.member_id = member_id;
		this.product_name = product_name;
		this.likes = likes;
		this.dislikes = dislikes;
		this.hits = hits;
		this.review = review;
		this.review_score = review_score;
		this.detail = detail;
		this.visiblity = visiblity;
		this.regist_date = regist_date;
		this.currentCategory = currentCategory;
		this.thumbnail_link = thumbnail_link;
		this.lowestPrice = lowestPrice;
		this.rownum = rownum;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getReview_score() {
		return review_score;
	}

	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getVisiblity() {
		return visiblity;
	}

	public void setVisiblity(int visiblity) {
		this.visiblity = visiblity;
	}

	public String getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(String regist_date) {
		this.regist_date = regist_date;
	}

	public int getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(int currentCategory) {
		this.currentCategory = currentCategory;
	}

	public String getThumbnail_link() {
		return thumbnail_link;
	}

	public void setThumbnail_link(String thumbnail_link) {
		this.thumbnail_link = thumbnail_link;
	}

	public int getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(int lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", category_id="
				+ category_id + ", member_id=" + member_id + ", product_name="
				+ product_name + ", likes=" + likes + ", dislikes=" + dislikes
				+ ", hits=" + hits + ", review=" + review + ", review_score="
				+ review_score + ", detail=" + detail + ", visiblity="
				+ visiblity + ", regist_date=" + regist_date
				+ ", currentCategory=" + currentCategory + ", thumbnail_link="
				+ thumbnail_link + ", lowestPrice=" + lowestPrice + ", rownum="
				+ rownum + "]";
	}

	
}


