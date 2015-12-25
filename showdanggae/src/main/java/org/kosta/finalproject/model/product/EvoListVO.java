package org.kosta.finalproject.model.product;

import java.util.List;


/**
 * 다수의 정보를 가지는 평가항목 VO 객체 다수를 담는 EvolistVO 클래스
 * @author 용호
 *
 */
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
