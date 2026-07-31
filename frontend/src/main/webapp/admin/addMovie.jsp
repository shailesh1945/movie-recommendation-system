<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Movie</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body class="bg-black">

	<div class="d-flex">

		<!-- Sidebar -->
		<jsp:include page="../component/sidebar.jsp" />

		<!-- Main Content -->
		<div class="flex-grow-1 d-flex flex-column min-vh-100">

			<!-- Top Navbar -->
			<jsp:include page="../component/topNavbar.jsp" />

			<!-- Add Movie Form -->
			<jsp:include page="../component/addMovieForm.jsp" />

			<!-- Footer -->
			<div class="mt-auto">
				<jsp:include page="../component/footer.jsp" />
			</div>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/config.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/adminMovie.js"></script>

</body>

</html>