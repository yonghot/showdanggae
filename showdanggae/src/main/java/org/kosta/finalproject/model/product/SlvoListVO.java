package org.kosta.finalproject.model.product;

import java.util.List;


/**
 * 다수의 정보를 가지는 판매링크 VO 객체 다수를 담는 SlvolistVO 클래스
 * @author 용호
 *
 */
public class SlvoListVO {
	 private List<SellerLinkVO> slvoList = null;
	 
     public List<SellerLinkVO> getSlvoList() {
            return slvoList;
     }

     public void setSlvoList(List<SellerLinkVO> slvoList) {
            this.slvoList = slvoList;
     }

	@Override
	public String toString() {
		return "SlvoListVO [slvoList=" + slvoList + "]";
	}
     
     
}
