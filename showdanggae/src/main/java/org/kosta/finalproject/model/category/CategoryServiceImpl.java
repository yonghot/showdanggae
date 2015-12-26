package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource
	private CategoryDAO categoryDAO;
	
	
	/**
	 * @Method 이름 : addMyCategory
	 * @Method 설명 : 회원이 쇼핑 메모를 등록할 카테고리를 추가한다. 
	 * @param category
	 * @param member_id
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public void addMyCategory(String category, String member_id) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("category", category);
		map.put("member_id", member_id);
		categoryDAO.addMyCategory(map);
	}
	
	/**
	 * @Method 이름 : deleteProductList
	 * @Method 설명 : 카테고리 삭제시 해당 카테고리 아이디를 이용하여 그에 소속된 쇼핑 메모 리스트와 하위 판매링크 및 평가항목까지 일괄적으로 삭제 
	 * @param category_id
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public void deleteProductList(int category_id) {
		if(categoryDAO.findSellerLinkByCategoryId(category_id)!=null) {
			categoryDAO.deleteSellerLink(category_id);
		}
		if(categoryDAO.findEvaluatingItemByCategoryId(category_id)!=null) {
			categoryDAO.deleteEvaluatingItem(category_id);
		}
		categoryDAO.deleteProductList(category_id);
		categoryDAO.deleteCategory(category_id);
	}

	/**
	 * @Method 이름 : getMainCategoryList
	 * @Method 설명 : 카테고리 추가, 회원 관심사 선택을 위한 대분류 카테고리 목록을 불러온다.
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public List<CategoryVO> getMainCategoryList() {
		return categoryDAO.getMainCategoryList();
	}
	
	/**
	 * @Method 이름 : getMemberCategoryList
	 * @Method 설명 : 회원이 추가해둔 카테고리 리스트를 불러온다.각 카테고리에 속해있는 쇼핑 메모의 갯수도 불러와 반복문을 이용하여 각 카테고리 VO에 setting 해준다.
	 * @param member_id
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		
		List<CategoryVO> memberCategoryList = categoryDAO.getMemberCategoryList(member_id);
		
		for(int i=0;i<memberCategoryList.size();i++) {
			int productCountNumber = categoryDAO.getProductCountNumber(memberCategoryList.get(i).getCategory_id());
			memberCategoryList.get(i).setProductCountNumber(productCountNumber);
		}
		
		return memberCategoryList;
	}
	
	
	/**
	 * @Method 이름 : getFirstMemberCategoryId
	 * @Method 설명 : 회원의 카테고리 아이디중 가장 작은 값을 불러온다.
	 * @param member_id
	 * @return
	 * @작성일 : 2015. 12. 24.
	 * @작성자 : 용호
	 */
	@Override
	public String getFirstMemberCategoryId(String member_id) {
		return categoryDAO.getFirstMemberCategoryId(member_id);
	}
	
}

