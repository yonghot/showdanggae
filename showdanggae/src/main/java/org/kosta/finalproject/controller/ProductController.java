package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.EvaluatingItemVO;
import org.kosta.finalproject.model.product.ProductService;
import org.kosta.finalproject.model.product.ProductVO;
import org.kosta.finalproject.model.product.SellerLinkVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	
	@ResponseBody
	@RequestMapping(value = "auth_getMemberCategoryList.do", method = RequestMethod.POST)
	public  List<CategoryVO> getMemberCategoryList(String member_id) {
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);	
		return lvo;
	}
	
	// 로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	// 이 때, 3개의 카테고리 까지만 추가 가능하다.(3개까지 추가가능 옵션은 보류)
	@ResponseBody
	@RequestMapping(value = "auth_addCategory.do", method = RequestMethod.POST)
	public List<CategoryVO> addCategory(String category, String member_id) {
		System.out.println(category);
		categoryService.addMyCategory(category, member_id);
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		return lvo;
	}
	// 나의 카테고리를 지우기 위해서 하위 상품을 삭제하고 해당 카테고리를 지운다. 
	//갱신된 카테고리 정보를 반환한다.
	@ResponseBody
	@RequestMapping(value = "auth_deleteProductListAndCategory.do", method = RequestMethod.POST)
	public List<CategoryVO> deleteProductListAndCategory(int category_id, String member_id) {
		//카테고리 아래에 있는 상품의 아이디를 가져온다.
		System.out.println(category_id+" "+member_id); //37+java2 ok!
		List<String> listPvo = categoryService.getMemberProduct_idList(category_id); 
		System.out.println(listPvo); //25+26 ok!
		
		//categoryService.deleteProductList(product_id, category_id);
		//categoryService.deleteCategory(category_id);
		
		
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		return lvo;
	}
	
	 @RequestMapping(value="auth_ajaxMemberCategoryList.do", method = RequestMethod.POST) 
	 public ModelAndView AjaxMainCategoryList(String member_id) { 
	 return new ModelAndView("auth_ajaxMemberCategoryList"); 
	 }

	// CategoryVO.class 에 private interest 삽입
	@RequestMapping("addInterest.do")
	public void addInterest(String interest, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			session.setAttribute("interest", interest);
			System.out.println("mvo");
			MemberVO vo = (MemberVO) session.getAttribute("mvo");
			categoryService.addInterest(vo);
		}
	}
	
	// 해당 category아래의 product count를 세어온다.
	@RequestMapping(value="auth_getProductCountNumber.do", method = RequestMethod.POST)
	public ModelAndView getProdeuctCountNumber(int category_id) {
		int productCountNumber = categoryService.getProductCountNumber(category_id);
		return new ModelAndView ("auth_getMyProductList.do", "productCountNumber", productCountNumber);
	}
	
	// 나의 해당 카테고리를 지운다.
			@RequestMapping("deleteCategory.do")
			public ModelAndView deleteCategory(int category_id,
					HttpServletRequest request) {
				HttpSession session = request.getSession(false);
				if (session.getAttribute("mvo") != null) {
					categoryService.deleteCategory(category_id);
				}
				return new ModelAndView("login");
			}

	

	// 김용호 영역
	// 로그인 상태일때, 내가 추가해 놓은 상품 리스트가 표시된다.
	@RequestMapping("auth_getMyProductList.do")
	public ModelAndView getMyProductList(String member_id,
			String currentCategory) throws Exception {
		
		ModelAndView mv = new ModelAndView("product_myProductList");

		mv.addObject("pvoList",
				productService.getMyProductList(member_id, currentCategory));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		mv.addObject("memberCategoryList",
				categoryService.getMemberCategoryList(member_id));
		mv.addObject("category_id", currentCategory);

		return mv;
	}

	// getAllBoardList
	@RequestMapping(value = { "getAllBoardList.do", "home.do" })
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		return new ModelAndView("product_allProductList", "pvoList",
				productService.getAllBoardList(sortBy));
	}

	// beforeGoingRegistProduct
	@RequestMapping("auth_beforeGoingRegistProduct.do")
	public ModelAndView beforeGoingRegistProduct(String category_id)
			throws Exception {

		ModelAndView mv = new ModelAndView("product_registProduct");

		mv.addObject("category_id", category_id);
		mv.addObject("itemList", productService.getItemList());

		return mv;
	}

	// registProduct 
	@RequestMapping("auth_registProduct.do")
	public ModelAndView registProduct(ProductVO pvo, SellerLinkVO slvo,
			EvaluatingItemVO evo) throws Exception {
		// vo에 변수명이 int로 되어있어도 String 데이터가 자동으로 parseInt되면서 들어가는 듯
		productService.addProductWithSellerLinkAndEvaluating(pvo, slvo, evo);
		return new ModelAndView("product/registOk", "currentCategory",
				pvo.getCategory_id());
	}

	// hit
	@RequestMapping("auth_hit.do")
	public ModelAndView hit(String product_id) throws Exception {
		productService.hit(product_id);
		return new ModelAndView("redirect:showProductContent.do?product_id="
				+ product_id);
	}

	// showContent
	@RequestMapping("showProductContent.do")
	public ModelAndView showProductContent(int product_id) throws Exception {
		System.out.println(productService.showProductContent(product_id));
		
		return new ModelAndView("product_contentView", "productInfo", productService.showProductContent(product_id));
	}
	
	// beforeGoingUpdateProduct
	@RequestMapping("beforeGoingUpdateProduct.do")
	public ModelAndView beforeGoingUpdateProduct(int product_id) throws Exception {
		ModelAndView mv = new ModelAndView("product_updateProduct");
		mv.addObject("itemList", productService.getItemList());
		mv.addObject("productInfo", productService.showProductContent(product_id));
		return mv;
	}
	
	// updateProduct
	@RequestMapping("updateProduct.do")
	public ModelAndView updateProduct(int product_id, ProductVO pvo, SellerLinkVO slvo, EvaluatingItemVO evo) throws Exception {
		
		System.out.println("updateProduct.do: "+slvo);
		System.out.println("updateProduct.do: "+evo);
		
		productService.updateProduct(product_id, pvo, slvo, evo);
		return new ModelAndView("redirect:moveToUpdateOkWithProductId.do?product_id="+product_id);
	}
	// deleteProduct
	@RequestMapping("deleteProduct.do")
	public ModelAndView deleteProduct(int product_id) throws Exception {
		int category_id = productService.getCategoryIdByProductId(product_id); //product를 지우기 전에 category_id를 빼와야 한다.
		productService.deleteProduct(product_id);
		return new ModelAndView("redirect:moveToDeleteOkWithProductId.do?category_id="+category_id);
	}
	
	// moveToUpdateOkWithProductId
	@RequestMapping("moveToUpdateOkWithProductId.do")
	public ModelAndView moveToUpdateOkWithProductId(int product_id) throws Exception {
		return new ModelAndView("product_updateOk","product_id", product_id);
	}
	// moveToDeleteOkWithProductId
	@RequestMapping("moveToDeleteOkWithProductId.do")
	public ModelAndView moveToDeleteOkWithProductId(int category_id) throws Exception {
		return new ModelAndView("product_deleteOk","category_id", category_id);
	}

}
