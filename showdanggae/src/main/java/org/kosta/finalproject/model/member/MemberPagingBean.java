
package org.kosta.finalproject.model.member;
/**
 * nowPage : view에서 선택한 페이지 번호
 * contentNumberPerPage :  페이지 당 보여줄 게시물 수 
 * pageNumberPerPageGroup : 페이지 그룹 당 페이지 수 
 * totalContents : 현재 db에 저장된 총 게시물 수
 * 
 * 페이징 처리를 위한 비즈니스 클래스
 * 
 * PagingBean method 구현 순서 
 * getTotalPage()
 * getTotalPageGroup()
 * getNowPageGroup()
 * getStartPageOfPageGroup()
 * getEndPageOfPageGroup()
 * isPreviousPageGroup()
 * isNextPageGroup() 
 * @author 유서정
 *
 */
public class MemberPagingBean {
	private int nowPage; 	
	private int contentNumberPerPage=20; 
	private int pageNumberPerPageGroup=5; 
	private int totalContents; 
	
	
	public MemberPagingBean(int totalContents,int nowPage){
		this.totalContents=totalContents;
		this.nowPage=nowPage;
	}
	

	public MemberPagingBean(int totalContents){
		this.totalContents=totalContents;
	}
	

		
	public MemberPagingBean() {
		super();
	}

	/**
	 * 
	 * @Method Name  : getTotalPage
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :총 페이지 그룹의 수를 return한다.
	 * @return
	 */
	public int getTotalPage() {
		int num = this.totalContents % this.contentNumberPerPage;
		int totalPage = 0;
		if (num == 0) {
			totalPage = this.totalContents / this.contentNumberPerPage;
		} else {
			totalPage = this.totalContents / this.contentNumberPerPage + 1;
		}
		return totalPage; 
	}

	/**
	 * 
	 * @Method Name  : getTotalPageGrop
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :총 페이지 그룹의 수를 return한다.
  									1. 총 페이지수 % Page Group 내 Page 수. 
 									=> 0 이면 둘을 / 값이 총 페이지 수

  									2. 총 페이지수 % Page Group 내 Page 수. 
  								=> 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수
	 * @return
	 */
	public int getTotalPageGrop(){
		int num=this.getTotalPage()%this.pageNumberPerPageGroup; 						
		int totalPageGroup=0;
		if(num==0){
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup;												
		}else{
			if(this.getTotalPage()<this.pageNumberPerPageGroup){
				totalPageGroup=1;	
			}
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup+1;
		}
		return totalPageGroup; 
	}
	
	/**
	 * 
	 * @Method Name  : getNowPageGroup
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드
	 *  1. 현재 페이지 % Page Group 내 Page 수 => 0 이면 
	 *  둘을 / 값이 현재 페이지 그룹
	 *  2. 현재 페이지 % Page Group 내 Page 수 => 0 크면
	 *      둘을 / 값에 +1을 한 값이 현재 페이지 그룹
	 * @return
	 */
	public int getNowPageGroup(){
		int num=this.nowPage%this.pageNumberPerPageGroup; 			
		int nowPageGroup=0;
		if(num==0){
			nowPageGroup=this.nowPage/this.pageNumberPerPageGroup;  
		}else{
			nowPageGroup=this.nowPage/this.pageNumberPerPageGroup+1;
		}
		return nowPageGroup;
	}
	
	/**
	 * 
	 * @Method Name  : getStartPageOfPageGroup
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.
       								Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1을 한 값이 첫 페이지이다.
	 * @return
	 */
	public int getStartPageOfPageGroup() {
		int startPageOfPageGroup = pageNumberPerPageGroup * getNowPageGroup()
				- (pageNumberPerPageGroup - 1);
		return startPageOfPageGroup;
	}

	/**
	 * 
	 * @Method Name  : getEndPageOfPageGroup
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다
       								1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다
       								2.페이지 그룹 당 페이지 수가  전체 페이지의 마지막 페이지 번호보다 
           							큰 경우는 전체 페이지의 마지막 번호를 return 한다
       								3. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 
           							큰 경우는 전체 페이지의 마지막 번호를 return 한다
	 * @return
	 */
	public int getEndPageOfPageGroup() {
		int endPageOfPageGroup = pageNumberPerPageGroup * getNowPageGroup();

		if (nowPage == getTotalPage()) {
			return getTotalPage();
		}
		if (pageNumberPerPageGroup > getTotalPage()) {
			return getTotalPage();
		}
		if (endPageOfPageGroup > getTotalPage()) {
			return getTotalPage();
		}
		return endPageOfPageGroup;
	}
	
	/**
	 * 
	 * @Method Name  : isPreviousPageGroup
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :이전 페이지 그룹이 있는지 체크하는 메서드 
	 *          				   현재 페이지가 속한 페이지 그룹이 1보다 크면 true
	 *          					현재 페이지 그룹보다 마지막페이지그룹이 더 크면 true
	 * @return
	 */
	public boolean isPreviousPageGroup(){
		boolean flag=false;
		if(getNowPageGroup()<getEndPageOfPageGroup()&&getNowPageGroup()>1){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @Method Name  : isNextPageGroup
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :다음 페이지 그룹이 있는지 체크하는 메서드 
	 * 현재 페이지 그룹이 마지막 페이지 그룹( 마지막 페이지 그룹 == 총 페이지 그룹 수) 보다 작으면 true 반환
	 * @return
	 */
	public boolean isNextPageGroup(){
		boolean flag=false;
		if(getNowPageGroup()<getTotalPageGrop()){		
			flag=true;
		}
		
		return flag;
	}
	
	


	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getContentNumberPerPage() {
		return contentNumberPerPage;
	}
	public void setContentNumberPerPage(int contentNumberPerPage) {
		this.contentNumberPerPage = contentNumberPerPage;
	}
	public int getPageNumberPerPageGroup() {
		return pageNumberPerPageGroup;
	}
	public void setPageNumberPerPageGroup(int pageNumberPerPageGroup) {
		this.pageNumberPerPageGroup = pageNumberPerPageGroup;
	}
	public int getTotalContents() {
		return totalContents;
	}
	public void setTotalContents(int totalContents) {
		this.totalContents = totalContents;
	}
	
	

}
