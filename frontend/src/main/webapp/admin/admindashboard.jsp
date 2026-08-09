<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Admin Dashboard</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

/* Leave room for fixed sidebar */
.main-content{
	margin-left:260px;
	min-height:100vh;
	display:flex;
	flex-direction:column;
}

/* Mobile */
@media (max-width:991px){

	.main-content{
		margin-left:0;
	}

}

</style>

</head>

<body class="bg-black">

	<!-- Sidebar -->
	<jsp:include page="../component/sidebar.jsp" />

	<!-- Main Content -->
	<div class="main-content">

		<%-- <jsp:include page="../component/topNavbar.jsp" /> --%>

		<div class="container-fluid py-4">

			<jsp:include page="../component/statistics.jsp" />

			<!-- Other Dashboard Components -->

		</div>

		<div class="mt-auto">
			<jsp:include page="../component/footer.jsp" />
		</div>

	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script src="../assets/js/config.js"></script>
	<script src="../assets/js/auth.js"></script>
	<script src="../assets/js/adminrole.js"></script>
	

</body>

</html>