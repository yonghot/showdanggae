<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<script type="text/javascript">

	$(document).ready(function(){
		$("#linkPlusBtn").hover(function(){
			
		}, function(){
			
		});
		
		$('#myPopover')
	});

</script>
<div class="col-md-8">
	<h3 contenteditable="true">상품 등록하기</h3>
	<hr>
	<div class="col-md-5">
		<div class="thumbnail">
			<img src="http://cfile24.uf.tistory.com/image/2567C64151A2AAB1352FF7"/>
		</div>
	</div>
	<div class="col-md-7">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-3">
					<label for="product_id" class="control-label">제품명</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="product_id" placeholder="제품명을 입력해주세요">
					<hr>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">판매 링크</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="inputEmail3" placeholder="링크를 복사해주세요">
					<br>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">가격</label>
				</div>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputEmail3" placeholder="판매자의 가격을 입력해주세요">
					<br>
				</div>
				<button type="button" class="btn btn-default" id="linkPlusBtn"><img src="img/plusSign.png" width="20"/></button>
			</div>
		</form>
	</div>
	<table class="table">
		<tbody>
			<tr>
				<td colspan="1">이름</td>
				<td colspan="2"><input type="text" name="writer" size="8" value="${sessionScope.mvo.member_name}" readonly="readonly"></td> 
				<td>
					<div class="btn-group btn-group-xs">
						<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" contenteditable="true">카테고리 선택<br>
						<span class="fa fa-caret-down"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" contenteditable="true">Action</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" 
					data-trigger="hover" id="myPopover" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">Popover on right</button>
				</td>
				<td>
					<div class="checkbox">
						<label><input type="checkbox">비공개</label>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="left">
					<textarea cols="105" rows="5" name="content" placeholder="메모를 입력해주세요"></textarea>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<input type="submit" value="상품 등록" id="subBtn" class="btn btn-info btn-md">
					<input type="button" value="취소" id="noticeWrBtn" class="btn btn-info btn-md">
				</td>
			</tr>
		</tbody>
	</table>
</div>

