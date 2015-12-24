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
		categoryService.addMyCategory(category, member_id);
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		return lvo;
	}
	
	// 나의 카테고리를 지우기 위해서 하위 상품을 삭제하고 해당 카테고리를 지운다. 
	//갱신된 카테고리 정보를 반환한다.
	@ResponseBody
	@RequestMapping(value = "auth_deleteProductListAndCategory.do", method = RequestMethod.POST)
	public List<CategoryVO> deleteProductListAndCategory(int category_id, String member_id) {
		categoryService.deleteProductList(category_id);
		List<CategoryVO> lvo = categoryService.getMemberCategoryList(member_id);
		return lvo;
	}

	

	/**
	 * @Method 이름 : getMemberCategoryList
	 * @Method 설명 : 회원이 추가한 카테고리 목록을 불러와 Ajax로 리턴한다.
	 * @param member_id
	 * @return
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@ResponseBody
	@RequestMapping(value = "getMemberCategoryList.do", method = RequestMethod.POST)
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return categoryService.getMemberCategoryList(member_id);
	}
	
	
	
	/**
	 * @Method 이름 : getAllProductList
	 * @Method 설명 : 메인화면에서 보여줄 모든 회원의 쇼핑 메모 목록과 카테고리별로 정렬 시키기 위한 카테고리 목록을 불러온다
	 * @param sortBy
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping(value = {"getAllProductList.do", "home.do"})
	public ModelAndView getAllProductList(String sortBy) throws Exception {
		ModelAndView mv = new ModelAndView("product_allProductList");
		
		mv.addObject("pvoList", productService.getAllProductList(sortBy));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		
		return mv;
	}
	
	
	/**
	 * @Method 이름 : getAllProductListByCategoryId
	 * @Method 설명 : 해당 카테고리에 속한 쇼핑 메모 목록과 정렬을 위한 카테고리 목록을 불러온다.
	 * @param sortBy
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("getAllProductListByCategory.do")
	public ModelAndView getAllProductListByCategoryId(String sortBy) throws Exception {
		ModelAndView mv = new ModelAndView("product_allProductList");
		
		List<ProductVO> pvoList = productService.getAllProductListByCategory(sortBy);
		
		mv.addObject("pvoList", pvoList);
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		
		System.out.println(pvoList);
		return mv;
	}
	
	
	/**
	 * @Method 이름 : getMyProductList
	 * @Method 설명 : 회원이 등록한 쇼핑 메모 목록과 더불어 카테고리 추가를 위한 카테고리 목록, 추가해둔 회원의 카테고리 목록, 현재 보여질 카테고리 아이디를 불러온다. 
	 * @param member_id
	 * @param currentCategory
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("auth_getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {

		ModelAndView mv = new ModelAndView("product_myProductList");
		
		String category_id = null;
		String firstCategoryId = categoryService.getFirstMemberCategoryId(member_id);
		
		//홈에서 바로 접근할 경우 DB에 접근하여 회원의 카테고리 아이디중 가장 작은 값을 불러와 이 값을 이용해 조회한다.
		if(currentCategory.equals("0")) {
			category_id = firstCategoryId;
		} else {
			category_id = currentCategory;
		}
		
		mv.addObject("pvoList", productService.getMyProductList(member_id, category_id));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		mv.addObject("memberCategoryList", categoryService.getMemberCategoryList(member_id));
		mv.addObject("category_id", category_id);
		
		return mv;
	}
	
	
	@RequestMapping(value = {"searchProductList.do"})
	public ModelAndView searchProductList(String sortBy) throws Exception {
		return new ModelAndView("product_searchProductList", "spvoList", productService.searchProductList(sortBy));
	}
	
	
	
	/**
	 * @Method 이름 : beforeGoingRegistProduct
	 * @Method 설명 : 쇼핑 메모를 작성하는 페이지로 이동할 때 작성하면서 필요한 정보인 평가 항목 리스트와 등록시 소속 카테고리를 정해줄 현재 카테고리 아이디를 가져간다
	 * @param category_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("auth_beforeGoingRegistProduct.do")
	public ModelAndView beforeGoingRegistProduct(String category_id)
			throws Exception {

		ModelAndView mv = new ModelAndView("product_registProduct");

		mv.addObject("category_id", category_id);
		mv.addObject("itemList", productService.getItemList());

		return mv;
	}

	
	/**
	 * @Method 이름 : registProduct
	 * @Method 설명 : 등록 버튼을 누르면 해당 글 정보와 함께 글안에 포함되어있는 판매 링크 목록과 평가항목+평가점수 목록이 insert 된다.
	 * 				다수의 정보를 가진 판매 링크와 평가 항목이 여러개이므로 각 vo로 이루어진 list를 담은 vo 클래스를 만들어 인자로 받는다.
	 * @param pvo
	 * @param slvoList
	 * @param evoList
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("auth_registProduct.do")
	public ModelAndView registProduct(ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) throws Exception {
		// vo에 변수명이 int로 되어있어도 String 데이터가 자동으로 parseInt되면서 들어가는 듯
		
		productService.addProductWithSellerLinkAndEvaluating(pvo, slvoList, evoList);
		return new ModelAndView("product/registOk", "currentCategory", pvo.getCategory_id());
	}

	
	/**
	 * @Method 이름 : hit
	 * @Method 설명 : 쇼핑 메모를 보는 페이지에서 새로고침을 눌러도 조회수가 올라가지 않아야 하므로 조회수만을 올려주는 컨트롤러 메소드를 만들고 클릭시에 한번만 이 메소드를 거치게 한다.
	 * @param product_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("auth_hit.do")
	public ModelAndView hit(String product_id) throws Exception {
		 productService.hit(product_id);
		return new ModelAndView("redirect:showProductContent.do?product_id="+product_id);
	}

	
	/**
	 * @Method 이름 : showProductContent
	 * @Method 설명 : 쇼핑 메모의 기본 정보와 판매링크 목록, 평가 항목을 불러온다.
	 * @param product_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("showProductContent.do")
	public ModelAndView showProductContent(int product_id) throws Exception {
		return new ModelAndView("product_contentView", "productInfo", productService.showProductContent(product_id));
	}
	
	
	/**
	 * @Method 이름 : beforeGoingUpdateProduct
	 * @Method 설명 : 쇼핑 메모 수정 페이지로 가기 전 평가항목 리스트와 쇼핑 메모의 기본 정보를 불러와 수정페이지에서 바로 적용하도록 한다.
	 * @param product_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("beforeGoingUpdateProduct.do")
	public ModelAndView beforeGoingUpdateProduct(int product_id) throws Exception {
		ModelAndView mv = new ModelAndView("product_updateProduct");
		mv.addObject("itemList", productService.getItemList());
		mv.addObject("productInfo", productService.showProductContent(product_id));
		return mv;
	}
	
	
	/**
	 * @Method 이름 : updateProduct
	 * @Method 설명 : 수정 페이지에서 입력한 정보를 바탕으로 수정한 뒤 product_id를 가지고 ... 수정하자!
	 * @param product_id
	 * @param pvo
	 * @param slvoList
	 * @param evoList
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("updateProduct.do")
	public ModelAndView updateProduct(int product_id, ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) throws Exception {
		return new ModelAndView("redirect:moveToUpdateOkWithProductId.do?product_id="+product_id);
	}
	//여기서 리턴할 때 그냥 category_id 받아서 return new ModelAndView("product_deleteOk","category_id", category_id); 해주면 안되나?? 
	
	/**
	 * @Method 이름 : deleteProduct
	 * @Method 설명 : 쇼핑 메모를 삭제한 뒤 삭제 성공 alert을 띄우기 위한 jsp 페이지로 이동 시킨다.
	 * @param product_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
	@RequestMapping("deleteProduct.do")
	public ModelAndView deleteProduct(int product_id) throws Exception {
		System.out.println("삭제하는 제품 글의 id: "+product_id);
		int category_id = productService.getCategoryIdByProductId(product_id); //product를 지우기 전에 category_id를 빼와야 한다.
		productService.deleteProduct(product_id);
		return new ModelAndView("product_deleteOk","category_id", category_id);
	}
	
	
	/**
	 * @Method 이름 : moveToUpdateOkWithProductId
	 * @Method 설명 : 수정 완료 alert을 띄우기 위한 jsp 페이지로 이동시킨다.
	 * @param product_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 용호
	 */
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





















