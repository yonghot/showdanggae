<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<link rel="stylesheet" href="${initParam.root}bootstrap.css" type="text/css">
<!--카테고리 추가하기 -->

<div class="col-md-8">
	<h2 contenteditable="true">상품 등록하기</h2>
	<div class="col-md-4">
		<div class="thumbnail">
			<img src="http://cfile24.uf.tistory.com/image/2567C64151A2AAB1352FF7"
				class="img-responsive">
		</div>
	</div>
	<div class="col-md-8">
		<div class="btn-group btn-group-xs">
			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" contenteditable="true">카테고리 선택<br>
			<span class="fa fa-caret-down"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" contenteditable="true">Action</a></li>
			</ul>
		</div>
		<div class="checkbox">
			<label><input type="checkbox">비공개</label>
		</div>
	</div>
	<div class="col-md-8">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-2">
					<label for="product_id" class="control-label">제품명</label>
				</div>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="product_id"
						placeholder="제품명을 입력해주세요">
				</div>
				<div class="col-sm-2">
					<label for="inputEmail3" class="control-label">판매 링크</label>
				</div>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="링크를 복사해주세요">
				</div>
				<div class="col-sm-2">
					<label for="inputEmail3" class="control-label">가격</label>
				</div>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="판매자의 가격을 입력해주세요">
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">링크추가</button>
				</div>
			</div>
		</form>
	</div>
	<hr>
	<div class="col-md-12">
		<div class="form-group">
			<textarea class=".form-control" rows="3" placeholder="메모를 입력해주세요"></textarea>
		</div>
		<a class="btn btn-primary">상품 등록</a>
	</div>
</div>

