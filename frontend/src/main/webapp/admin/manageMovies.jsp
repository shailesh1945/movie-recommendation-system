<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Movies</title>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/admin.css">

</head>

<body class="bg-black">

<jsp:include page="../component/sidebar.jsp"/>

<div class="main-content">

    <jsp:include page="../component/topNavbar.jsp"/>

    <jsp:include page="../component/manageMovies.jsp"/>

    <jsp:include page="../component/footer.jsp"/>

</div>

<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script
src="${pageContext.request.contextPath}/assets/js/config.js"></script>

<script
src="${pageContext.request.contextPath}/assets/js/manageMovies.js"></script>

</body>

</html>