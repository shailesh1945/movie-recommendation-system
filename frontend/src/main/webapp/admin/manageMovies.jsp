<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Movies</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin.css">

<style>

/* ===============================
   Main Layout
================================== */

.main-content{
	margin-left:260px;
	min-height:100vh;
	display:flex;
	flex-direction:column;
	transition:margin-left .3s ease;
	background:#000;
}

/* ===============================
   Page Content
================================== */

.page-content{
	flex:1;
	padding:30px;
	width:100%;
}

/* ===============================
   Laptop
================================== */

@media (max-width:1200px){

	.page-content{
		padding:25px;
	}

}

/* ===============================
   Tablet
================================== */

@media (max-width:992px){

	.main-content{
		margin-left:0;
		width:100%;
	}

	.page-content{
		padding:20px;
	}

}

/* ===============================
   Mobile
================================== */

@media (max-width:768px){

	.page-content{
		padding:15px;
	}

}

/* ===============================
   Small Mobile
================================== */

@media (max-width:576px){

	.page-content{
		padding:12px;
	}

}

</style>

</head>

<body class="bg-black">

	<!-- Sidebar -->
	<jsp:include page="../component/sidebar.jsp"/>

	<!-- Main Content -->
	<div class="main-content">

		<!-- Navbar -->
		<%-- <jsp:include page="../component/topNavbar.jsp"/> --%>

		<!-- Page -->
		<div class="page-content">

			<div class="container-fluid px-0">

				<jsp:include page="../component/manageMovies.jsp"/>

			</div>

		</div>

		<!-- Footer -->
		<div class="mt-auto">

			<jsp:include page="../component/footer.jsp"/>

		</div>

	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/config.js"></script>
	<script src="../assets/js/auth.js"></script>

</body>

</html>