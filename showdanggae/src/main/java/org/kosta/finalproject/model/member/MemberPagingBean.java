package org.kosta.finalproject.model.member;

//비즈?��?��로직 ?��?���? �?�? ?��?��


/**
 * 	getTotalPage() -> getTotalPageGroup() ->getNowPageGroup() ->getStartPageOfPageGroup()
		  	->getEndPageOfPageGroup()-> isPreviousPageGroup() -> isNextPageGroup()
 * @author kosta
 *
 */




public class MemberPagingBean {
	private int nowPage; 	
	private int contentNumberPerPage=20; //페이지당 보여줄 게시물 수
	private int pageNumberPerPageGroup=5; //페이지 그룹당 페이지 수 
	private int totalContents; //지금 20개
	
	
	public MemberPagingBean(int totalContents,int nowPage){
		this.totalContents=totalContents;
		this.nowPage=nowPage;
	}
	

	public MemberPagingBean(int totalContents){
		this.totalContents=totalContents;
	}
	
	
	
	
	public static void main(String[] args) {
		
		MemberPagingBean pb = new MemberPagingBean();
	
		System.out.println("result: "+pb.getTotalPage());
	}
	
	
	
	public MemberPagingBean() {
		super();
		
	}
	
	public int getTotalPage(){

		int num=this.totalContents%this.contentNumberPerPage;
			// 20 페이지당 보여줄 게시물 수10
		int totalPage=0;
		if(num==0){
			totalPage=this.totalContents/this.contentNumberPerPage;
		}else{
			totalPage=this.totalContents/this.contentNumberPerPage+1;
		}
		System.out.println(totalPage);
		return totalPage;  //2개 왜? 총 콘ㅌㄴ츠가 20개니까
	}
	
	public int getTotalPageGrop(){
		int num=this.getTotalPage()%this.pageNumberPerPageGroup; //페이지그룹당 페이지수
										//2%5
		int totalPageGroup=0;
		if(num==0){
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup;
		}else{
			totalPageGroup=this.getTotalPage()/this.pageNumberPerPageGroup+1;
		}
		return totalPageGroup; 
	}
	
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
	
	
	public int getStartPageOfPageGroup(){

		int startPageOfPageGroup=pageNumberPerPageGroup*getNowPageGroup()-(pageNumberPerPageGroup-1);
                           	
		return startPageOfPageGroup;
	}
	
	public int getEndPageOfPageGroup(){ 
		
		int endPageOfPageGroup=pageNumberPerPageGroup*getNowPageGroup();
									
		int num=this.totalContents/this.contentNumberPerPage;
		
		if(pageNumberPerPageGroup> num){		                                     
			return num;
		}
		
		if(nowPage==getTotalPage()){
			
			return getTotalPage();		
		}
		return endPageOfPageGroup;
	}
	
	public boolean isPreviousPageGroup(){
		boolean flag=false;
		if(getNowPageGroup()<getEndPageOfPageGroup()&&getNowPageGroup()>1){
			flag=true;
		}
		return flag;
	}
	
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
