<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	alert("상품 정보가 삭제되었습니다.");
	location.href="auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=${requestScope.category_id}";

</script>