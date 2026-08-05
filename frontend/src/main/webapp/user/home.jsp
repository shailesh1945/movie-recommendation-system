<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
</head>
<body>
<jsp:include page="../component/userNavbar.jsp"/>

<jsp:include page="../component/welcomeSection.jsp"/>

<jsp:include page="../component/recommendedMovies.jsp"/>

<jsp:include page="../component/trendingMovies.jsp"/>
<jsp:include page="../component/footer.jsp"/>


<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/movie.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>