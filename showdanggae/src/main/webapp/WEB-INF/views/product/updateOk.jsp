<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	alert("상품 정보가 수정되었습니다.");
	location.href="showProductContent.do?product_id=${requestScope.product_id}";

</script>