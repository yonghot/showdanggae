package org.kosta.finalproject.model.product;

import java.util.List;

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
