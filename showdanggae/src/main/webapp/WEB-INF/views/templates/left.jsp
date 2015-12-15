<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <style type="text/css" data-isostyle-id="is5c823b6c">
.m38{display:block;margin-left:auto;margin-right:auto}.s38:empty{display:none}.u28 .v28{max-width:640px}.e38{text-transform:uppercase}.f38,.d38{width:100%}.c38,.g38{-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0;margin-left:20px}.d38,.h38{-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1}.t38{color:#4b4f54;display:inline;font-weight:600}.j38,.k38{-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.k38{border-bottom-color:#edeeee;border-bottom-width:1px;padding-bottom:30px}.w28 .x28,.w28 .v28{max-width:230px}.r38{background:0 0;border-color:#4090db transparent transparent transparent;border-style:solid;height:0;padding:0;overflow:hidden;width:0}.y28:first-child{margin-left:0}.y28:last-child{margin-right:0}.z28{text-align:center;width:33.3%}.b38:last-child{width:33.4%}.a38,.b38{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.b38{-webkit-justify-content:space-around;-ms-flex-pack:distribute;justify-content:space-around;padding:17px 0}.p38{color:#4b4f54;font-weight:300;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}.o38{-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.q38{margin-left:7px}.u38{font-weight:600}@media (min-width:736px){.i38{box-sizing:content-box;padding:40px 20px 0;width:calc(100% - 40px)}.n38{-webkit-flex-basis:30px;-ms-flex-preferred-size:30px;flex-basis:30px;-webkit-box-flex:2;-webkit-flex-grow:2;-ms-flex-positive:2;flex-grow:2}.m38{height:152px;margin-bottom:-7px;margin-top:-7px;width:152px}.l38{-webkit-flex-basis:0;-ms-flex-preferred-size:0;flex-basis:0;-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;margin-right:30px}.s38{font-size:17px;line-height:24px;margin-top:14px}.j38{margin-bottom:45px}.j38,.k38{padding:20px 0}.r38{border-width:12px 7px 0 7px;margin:11px 10px}.y28{font-size:17px;margin-right:40px}.a38{margin-top:17px}.p38{font-size:34px;line-height:40px}}@media (max-width:735px){.n38{-webkit-flex-basis:0;-ms-flex-preferred-size:0;flex-basis:0;-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1}.m38{height:77px;margin-bottom:-6px;margin-top:-6px;width:77px}.l38{margin-right:25px}.s38{font-size:14px;line-height:17px;margin-top:7px}.j38{margin:30px 20px}.k38{margin-left:20px;margin-right:20px;margin-top:30px}.r38{border-width:7px 4px 0 4px;margin:7px 6px}.y28{font-size:14px;margin-left:10px;margin-right:10px}.a38{margin-top:10px}.p38{font-size:21px}}
</style>

<style type="text/css" data-isostyle-id="is-2ab3c3b2">
.o39{background-color:#fbfbfb;border-radius:50%;border:1px solid #cccfd0;box-sizing:border-box;overflow:hidden;position:relative}.p39{border:0;cursor:pointer;height:100%;padding:0;width:100%}.q39{cursor:inherit;opacity:.5}.r39{height:100%;left:0;position:absolute;top:0;width:100%}.i38{-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;margin:0 auto 30px;max-width:935px;width:100%}.v38{-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row;margin-top:11px}.v28{font-weight:600}.v28+.x28{margin-top:13px}.x28,.v28{font-size:14px;line-height:24px}.x28,.v28,.n38{color:#4b4f54}.m38{display:block;margin-left:auto;margin-right:auto}.l38{-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0}.s38{display:block}.s38:empty{display:none}.u28 .v28{max-width:640px}.e38{text-transform:uppercase}.f38,.d38{width:100%}.c38,.g38{-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0;margin-left:20px}.d38,.h38{-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1}.t38{color:#4b4f54;display:inline;font-weight:600}.j38,.k38{-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.k38{border-bottom-color:#edeeee;border-bottom-width:1px;padding-bottom:30px}.w28 .x28,.w28 .v28{max-width:230px}.r38{background:0 0;border-color:#4090db transparent transparent transparent;border-style:solid;height:0;padding:0;overflow:hidden;width:0}.y28:first-child{margin-left:0}.y28:last-child{margin-right:0}.z28{text-align:center;width:33.3%}.b38:last-child{width:33.4%}.a38,.b38{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.b38{-webkit-justify-content:space-around;-ms-flex-pack:distribute;justify-content:space-around;padding:17px 0}.p38{color:#4b4f54;font-weight:300;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}.o38{-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row}.q38{margin-left:7px}.u38{font-weight:600}@media (min-width:736px){.i38{box-sizing:content-box;padding:40px 20px 0;width:calc(100% - 40px)}.n38{-webkit-flex-basis:30px;-ms-flex-preferred-size:30px;flex-basis:30px;-webkit-box-flex:2;-webkit-flex-grow:2;-ms-flex-positive:2;flex-grow:2}.m38{height:152px;margin-bottom:-7px;margin-top:-7px;width:152px}.l38{-webkit-flex-basis:0;-ms-flex-preferred-size:0;flex-basis:0;-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;margin-right:30px}.s38{font-size:17px;line-height:24px;margin-top:14px}.j38{margin-bottom:45px}.j38,.k38{padding:20px 0}.r38{border-width:12px 7px 0 7px;margin:11px 10px}.y28{font-size:17px;margin-right:40px}.a38{margin-top:17px}.p38{font-size:34px;line-height:40px}}@media (max-width:735px){.n38{-webkit-flex-basis:0;-ms-flex-preferred-size:0;flex-basis:0;-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1}.m38{height:77px;margin-bottom:-6px;margin-top:-6px;width:77px}.l38{margin-right:25px}.s38{font-size:14px;line-height:17px;margin-top:7px}.j38{margin:30px 20px}.k38{margin-left:20px;margin-right:20px;margin-top:30px}.r38{border-width:7px 4px 0 4px;margin:7px 6px}.y28{font-size:14px;margin-left:10px;margin-right:10px}.a38{margin-top:10px}.p38{font-size:21px}}</style>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
       
<div class="col-md-2">
	<c:choose>
		<c:when test="${sessionScope.mvo.member_id=='admingalbage'}">
			<!-- 매니져로 로그인 되었을때 -->
			<h3>manager Page</h3>
			<hr>
			<h4><a href="memberManagerForm.do">회원관리</a></h4>
			<h4><a href="notice.do">공지사항관리</a></h4>
		</c:when>
		<c:when test="${!empty sessionScope.mvo && sessionScope.mvo.member_id!='admingalbage'}">
			<!-- 일반회원으로 로그인 되었을때 -->
			<h3>My Page</h3>
				${sessionScope.mvo.member_name} 님 로그인중
								<span class="social social-linked-in"></span>
			<hr>

				<div class="row">
				<!-- 	 <div class="col-sm-6 col-md-4" > -->
					    <div class="thumbnail">
					    
					      <img class="o39 m38" src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" data-reactid=".0.1.0.0:0.0.0">
					    
					  <!--     <img src="..." alt="..."> -->
					      <div class="caption">		<!-- 프로필편집박스 -->
    					   <span class="label label-default">프로필편집</span>  
					        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
					      
					        <button type="button" class="btn btn-default btn-sm">
 							 <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Star
							</button>
					      
					      <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> 
					      </div>
					    <!-- </div> -->
					 </div>
				</div>
		</c:when>
	</c:choose>	     
</div>
