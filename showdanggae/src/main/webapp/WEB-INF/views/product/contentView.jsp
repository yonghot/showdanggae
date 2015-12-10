<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="col-md-8">
	<div class="col-sm-9">
		<h3 contenteditable="true">상품 등록하기</h3>
		<hr>
	</div>
	<div class="col-sm-3" align="right">
		<div class="btn-group btn-group-xs">
			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" contenteditable="true">카테고리 선택<br>
			<span class="fa fa-caret-down"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" contenteditable="true">Action</a></li>
			</ul>
		</div>
	</div>
	<div class="col-md-5">
		<a href="fileupload.do" class="thumbnail">
			<c:choose>
				<c:when test="">
					<img src="img/no_image.png" width="285">
				</c:when>
				<c:otherwise>
					<img src="img/no_image.png" width="285">
				</c:otherwise>
			</c:choose>
		</a>
	    <form id="uploadForm" action="${initParam.root}fileupload.do" enctype="multipart/form-data" method="post" class="thumbnail">
			<input type="file" name="file[0]"><br>
			<input type="button" value="파일 업로드" id="uploadBtn"><br>
		</form>
	</div>
	<form class="form-horizontal" role="form" action="auth_registProduct.do" id="registForm">
		<div class="col-md-7">
			<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
			<div class="form-group">
				<div class="col-sm-3">
					<label for="product_name" class="control-label">제품명</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="product_name" placeholder="제품명을 입력해주세요">
					<hr>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">판매 링크</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="link" placeholder="링크를 복사해주세요">
					<br>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">가격</label>
				</div>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="price" placeholder="판매자의 가격을 입력해주세요">
					<br>
				</div>
				<button type="button" class="btn btn-default" id="linkPlusBtn"><img src="img/plus_icon.png" width="20"
				data-toggle="popover" title="판매자 링크를 추가합니다" data-placement="right"/></button>
				<div class="checkbox col-sm-4">
					<label><input type="checkbox" name="visiblity" value="1">비공개</label>
				</div>
			</div>
		</div>
		<table class="table table-hover table-condensed">
			<thead>
				<tr></tr>
			</thead>
			<tbody id="linkView" valign="middle"></tbody>
		</table>
		<table class="table">
			<tbody>
				<tr>
					<td>
						<div class="col-sm-5">
							<table class="table table-hover table-condensed table-bordered">
								<thead align="center">
									<tr>
										<td>평가 항목</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 수&nbsp;&nbsp;&nbsp;</td>
									</tr>
								</thead>
								<tbody id="itemView" align="center" valign="middle">
										<tr>
											<td>평가 항목</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 수&nbsp;&nbsp;&nbsp;</td>
										</tr>
								</tbody>
							</table>
						</div>
						<div class="col-sm-7">
							<pre>${requestScope.detail}</pre>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="상품 등록" id="registProductBtn" class="btn btn-info btn-md">
						<input type="button" value="취소" id="cancelRegist" class="btn btn-info btn-md">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>