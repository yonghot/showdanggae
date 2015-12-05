package org.kosta.finalproject.model.product;

	
	private int product_id;
	private int likes;
	private int dislikes;
	private int hits;
	private int review_score;
	private int visiblity;
	private String member_id;
	private String product_name;
	private String review;
	private String detail;
	private String regist_date;
	
	private int currentCategory;
	
	public ProductVO() {
		super();
	}

	public ProductVO(int product_id, int likes, int dislikes, int hits,
			int review_score, int visiblity, String member_id,
			String product_name, String review, String detail,
			String regist_date, int currentCategory) {
		super();
		this.product_id = product_id;
		this.likes = likes;
		this.dislikes = dislikes;
		this.hits = hits;
		this.review_score = review_score;
		this.visiblity = visiblity;
		this.member_id = member_id;
		this.product_name = product_name;
		this.review = review;
		this.detail = detail;
		this.regist_date = regist_date;
		this.currentCategory = currentCategory;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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

	public int getReview_score() {
		return review_score;
	}

	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}

	public int getVisiblity() {
		return visiblity;
	}

	public void setVisiblity(int visiblity) {
		this.visiblity = visiblity;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", likes=" + likes
				+ ", dislikes=" + dislikes + ", hits=" + hits
				+ ", review_score=" + review_score + ", visiblity=" + visiblity
				+ ", member_id=" + member_id + ", product_name=" + product_name
				+ ", review=" + review + ", detail=" + detail
				+ ", regist_date=" + regist_date + ", currentCategory="
				+ currentCategory + "]";
	}
	
	
}


