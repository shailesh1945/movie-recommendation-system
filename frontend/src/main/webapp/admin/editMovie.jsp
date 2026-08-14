<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Movie</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin.css">

<style>


/* =======================
   Genre Container
======================= */

.genre-container {
    background: #1f1f1f;
    min-height: 60px;
}

.genre-container .form-check {
    background: #292929;
    border: 1px solid #495057;
    border-radius: 8px;
    padding: 8px 14px 8px 36px;
    transition: 0.2s ease;
}

.genre-container .form-check:hover {
    border-color: #0d6efd;
}

.genre-container .form-check-input {
    cursor: pointer;
}

.genre-container .form-check-label {
    cursor: pointer;
    user-select: none;
}

/* ===========================
   Main Layout
=========================== */
.main-content {
	margin-left: 260px;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
	transition: margin-left .3s ease;
	background: #000;
}

/* ===========================
   Page Content
=========================== */
.page-content {
	flex: 1;
	padding: 30px;
	width: 100%;
}

/* Large Screens */
@media ( max-width :1200px) {
	.page-content {
		padding: 25px;
	}
}

/* Tablet */
@media ( max-width :992px) {
	.main-content {
		margin-left: 0;
	}
	.page-content {
		padding: 20px;
	}
}

/* Mobile */
@media ( max-width :768px) {
	.page-content {
		padding: 15px;
	}
}

/* Small Mobile */
@media ( max-width :576px) {
	.page-content {
		padding: 12px;
	}
}
</style>

</head>

<body class="bg-black">

	<!-- Sidebar -->
	<jsp:include page="../component/sidebar.jsp" />

	<!-- Main Content -->
	<div class="main-content">

		<!-- Top Navbar -->
		<%-- <jsp:include page="../component/topNavbar.jsp"/> --%>

		<!-- Form -->
		<div class="page-content">

			<div class="container-fluid px-0">

				<jsp:include page="../component/editMovieForm.jsp" />

			</div>

		</div>

		<!-- Footer -->
		<div class="mt-auto">

			<jsp:include page="../component/footer.jsp" />

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script src="../assets/js/config.js"></script>
	<script src="../assets/js/auth.js"></script>
	<script src="../assets/js/adminMovie.js"></script>
	<script src="../assets/js/editMovie.js"></script>
	<!-- <script src="../assets/movie.js"></script>  -->
</body>

</html>