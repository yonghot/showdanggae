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
	private int contentNumberPerPage=5; //페이지당 보여줄 게시물 수
	private int pageNumberPerPageGroup=3; //페이지 그룹당 페이지 수 
	private int totalContents; 
	
	
	public MemberPagingBean(int totalContents,int nowPage){
		this.totalContents=totalContents;
		this.nowPage=nowPage;
	}
	

	public MemberPagingBean(int totalContents){
		this.totalContents=totalContents;
	}
	
	
	
	
	public static void main(String[] args) {
		
		MemberPagingBean pb = new MemberPagingBean();
		pb.setTotalContents(30);
		System.out.println("result: "+pb.getTotalPage());
	}
	
	
	
	public MemberPagingBean() {
		super();
		
	}
	
	public int getTotalPage(){

		int num=this.totalContents%this.contentNumberPerPage;
		int totalPage=0;
		if(num==0){
			totalPage=this.totalContents/this.contentNumberPerPage;
		}else{
			totalPage=this.totalContents/this.contentNumberPerPage+1;
		}
		return totalPage; 
	}
	
	public int getTotalPageGrop(){
		int num=this.getTotalPage()%this.pageNumberPerPageGroup; //
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
