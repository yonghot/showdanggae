package org.kosta.finalproject.model.product;

import java.util.List;

public class EvoListVO {
	private List<EvaluatingItemVO> evoList = null;

     public List<EvaluatingItemVO> getEvoList() {
            return evoList;
     }

     public void setEvoList(List<EvaluatingItemVO> evoList) {
            this.evoList = evoList;
     }

	@Override
	public String toString() {
		return "EvoListVO [evoList=" + evoList + "]";
	}
     
     
}
