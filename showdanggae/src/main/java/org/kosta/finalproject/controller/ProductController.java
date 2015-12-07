package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	private ProductService productService;
	
//강민석 영역

	// 로그인 상태일때, 내가 추가할 수 있는 카테고리 리스트가 표시된다.
	@RequestMapping("getMainCategoryList.do")
	public ModelAndView getMainCategoryList(HttpServletRequest request) {
	
		HttpSession session = request.getSession(false);
		session.getAttribute("mvo");
		if (session.getAttribute("mvo") != null) {
			List<CategoryVO> maincategorylist = categoryService
					.getMainCategoryList();
			return new ModelAndView("product_productList", "maincategorylist",
					maincategorylist);
		} else {
			return new ModelAndView("login");
		}
	}

	// 로그인 상태일때, 내가 추가해 놓은 카테고리 리스트가 표시된다.
	@RequestMapping("getMyCategoryList.do")
	public ModelAndView getMyCategoryList(String member_id,
			HttpServletRequest request) {
		// HttpSession이 존재하면 현재 HttpSession을 반환하고
		// 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		// 장바구니는 세션이 존재해야 하므로 한번더 if문으로 검증한다.
		// 로그인 상태일때(나의 categoryList + 나의 productList)
		session.getAttribute("mvo");
		if (member_id != null) {
			List<CategoryVO> categorylist = categoryService
					.getMemberCategoryList(member_id);
			System.out.println(categorylist);
			return new ModelAndView("product_productList", "categorylist",
					categorylist);
		} else {
			return new ModelAndView("login");
		}
	}
	
	
	//로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	//이 때, 3개의 카테고리 까지만 추가 가능하다.
	@RequestMapping(value="addCategory.do")
	public ModelAndView addCategory(String category, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		//카테고리가 3개 초과일 경우 입력불가.(보류)
		if (mvo != null) {
			categoryService.addMyCategory(category);
			List<CategoryVO> categorylist = categoryService.getMemberCategoryList(mvo.getMember_id());
		}
		return new ModelAndView("product_productList");
	}
	
	@RequestMapping("MyProductListDelete.do")
	public void MemberProductListAndDeleteCategory(HttpServletRequest request){
		//HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		//로그인 되어있는 멤버의 세션을 가져온다.
		session.getAttribute("mvo");
		
		//List<ProductVO> pvo=request.getParameter(category_id, productVO);
		
		//카테고리를 삭제 하려면, 카테고리 아래에 저장된 product가 존재해서는 안된다. 
		//만약 존재하면 alert창으로 하위 product를 모두 삭제 하겠습니까? 물어본뒤 
		//예:하위 product삭제 및 카테고리 삭제, 아니오:취소
		
	}
	
	
	
	
	// 김용호 영역
	
	//로그인 상태일때, 내가 추가해 놓은 상품 리스트가 표시된다.
	@RequestMapping("getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		return new ModelAndView("product_productList", "pvoList", productService.getMyProductList(member_id, currentCategory));
	}
	
	
	@RequestMapping("getAllBoardList.do")
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		return new ModelAndView("product_allProductList", "pvoList", productService.getAllBoardList(sortBy));
	}


	// 카테고리를 삭제 하려면, 카테고리 아래에 저장된 product가 존재해서는 안된다.
	// 만약 존재하면 alert창으로 하위 product를 모두 삭제 하겠습니까? 물어본뒤
	// 예:하위 product삭제 및 카테고리 삭제, 아니오:취소
	// 예 선택시 해당 category_id를 가진 product들을 삭제 한 후, 카테고리를 삭제한다.

	// 상품지움 클릭~
	@RequestMapping("DeleteProduct.do")
	public ModelAndView DeleteProduct(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.DeleteProduct(category_id);
		}
		return new ModelAndView("login");
	}

	@RequestMapping("DeleteProductListAndCategory.do")
	public ModelAndView DeleteProductListAndCategory(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.DeleteProductList(category_id);
			categoryService.DeleteCategory(category_id);
		}
		return new ModelAndView("login");
	}

	@RequestMapping("DeleteCategory.do")
	public ModelAndView DeleteCategory(int category_id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			categoryService.DeleteCategory(category_id);
		}
		return new ModelAndView("login");
	}
}
