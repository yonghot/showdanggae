package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.EvoListVO;
import org.kosta.finalproject.model.product.ProductService;
import org.kosta.finalproject.model.product.ProductVO;
import org.kosta.finalproject.model.product.SlvoListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kosta
 *
 */
@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	
	// 로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	// 이 때, 3개의 카테고리 까지만 추가 가능하다.(3개까지 추가가능 옵션은 보류)
	@ResponseBody //모델앤뷰 리턴하면 json 에이잭스x
	@RequestMapping(value = "auth_addCategory.do", method = RequestMethod.POST)
	public List<CategoryVO> addCategory(String category, String member_id) {
		System.out.println("추가한다 : "+category+" "+member_id);
		categoryService.addMyCategory(category, member_id);
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		return lvo;
	}
	
	// 나의 카테고리를 지우기 위해서 하위 상품을 삭제하고 해당 카테고리를 지운다. 
	//갱신된 카테고리 정보를 반환한다.
	@ResponseBody
	@RequestMapping(value = "auth_deleteProductListAndCategory.do", method = RequestMethod.POST)
	public List<CategoryVO> deleteProductListAndCategory(int category_id, String member_id) {
		System.out.println("삭제한다 : "+member_id+" "+category_id);
		categoryService.deleteProductList(category_id);
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		System.out.println(lvo);
		return lvo;
	}

	// CategoryVO.class 에 private interest 삽입
	@RequestMapping("addInterest.do")
	public void addInterest(String interest) {
			categoryService.addInterest(interest);
	}
	
	
	// 김용호 영역
	
	
	@ResponseBody
	@RequestMapping(value = "getMemberCategoryList.do", method = RequestMethod.POST)
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return categoryService.getMemberCategoryList(member_id);
	}
	
	// getAllProductList
	@RequestMapping(value = {"getAllProductList.do", "home.do"})
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		ModelAndView mv = new ModelAndView("product_allProductList");
		
		mv.addObject("pvoList", productService.getAllProductList(sortBy));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		
		return mv;
	}
	
	// getAllProductList
	@RequestMapping("getAllProductListByCategory.do")
	public ModelAndView getAllProductListByCategoryId(String sortBy) throws Exception {
		ModelAndView mv = new ModelAndView("product_allProductList");
		
		List<ProductVO> pvoList = productService.getAllProductListByCategory(sortBy);
		
		if(pvoList==null) {
			System.out.println("pvoList가 null이넹");
		}
		
		mv.addObject("pvoList", pvoList);
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		
		System.out.println(pvoList);
		return mv;
	}
	
	@RequestMapping("auth_getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {

		ModelAndView mv = new ModelAndView("product_myProductList");

		mv.addObject("pvoList", productService.getMyProductList(member_id, currentCategory));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		mv.addObject("memberCategoryList", categoryService.getMemberCategoryList(member_id));
		mv.addObject("category_id", currentCategory);
		
		return mv;
	}

	@RequestMapping(value = {"searchProductList.do"})
	public ModelAndView searchProductList(String sortBy) throws Exception {
		return new ModelAndView("product_searchProductList", "spvoList", productService.searchProductList(sortBy));
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
	public ModelAndView registProduct(ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) throws Exception {
		// vo에 변수명이 int로 되어있어도 String 데이터가 자동으로 parseInt되면서 들어가는 듯
		
		System.out.println("registProduct: "+pvo);
		System.out.println("registProduct: "+slvoList);
		System.out.println("registProduct: "+evoList);
		
		productService.addProductWithSellerLinkAndEvaluating(pvo, slvoList, evoList);
		return new ModelAndView("product/registOk", "currentCategory", pvo.getCategory_id());
	}

	// hit
	@RequestMapping("auth_hit.do")
	public ModelAndView hit(String product_id) throws Exception {
		 productService.hit(product_id);
		return new ModelAndView("redirect:showProductContent.do?product_id="+product_id);
	}

	// showContent
	@RequestMapping("showProductContent.do")
	public ModelAndView showProductContent(int product_id) throws Exception {
		System.out.println("showProductContent: "+productService.showProductContent(product_id));
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
	public ModelAndView updateProduct(int product_id, ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) throws Exception {
		
		System.out.println(slvoList);
		System.out.println(evoList);
		
		//productService.updateProduct(product_id, pvo, slvo, evo);
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
	
	/**
	 * 	검색어 순위 
	 */
	@RequestMapping("selectReport.do")
	@ResponseBody
	public List<ProductVO> selectReport(String word) throws Exception {
		List<ProductVO> list = productService.selectReport();
		return list;
	}
	
	@RequestMapping("saveReport.do")
	@ResponseBody
	public void saveReport(String word) throws Exception {
		productService.saveReport(word);
	}
}





















