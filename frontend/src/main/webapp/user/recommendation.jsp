<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Recommended Movies</title>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<style>
/* Movie Card */
.movie-card {
	overflow: hidden;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.movie-card:hover {
	transform: translateY(-5px);
}

/* Movie Poster */
.movie-poster {
	width: 100%;
	height: 380px;
	object-fit: cover;
	object-position: center;
	display: block;
}
</style>
<body class="bg-dark">

	<jsp:include page="../component/userNavbar.jsp" />
	<div class="container py-5">


		<!-- Header -->

		<div class="text-center mb-5">

			<h2 class="page-title text-danger">

				<i class="bi bi-stars me-2"></i> Recommended For You

			</h2>


			<p class="text-secondary">Movies selected based on your
				preferences</p>

		</div>


		<!-- Movies -->

		<div id="recommendationContainer" class="row g-4"></div>


	</div>
	<jsp:include page="../component/footer.jsp" />

	<!-- Context Path -->

	<script>
		const CONTEXT_PATH = "${pageContext.request.contextPath}";
	</script>


	<!-- API -->

	<script src="${pageContext.request.contextPath}/assets/js/config.js">
		
	</script>

	<script src="${pageContext.request.contextPath}/assets/js/auth.js">
		
	</script>
	<!-- Recommendation JS -->

	<script
		src="${pageContext.request.contextPath}/assets/js/recommendation.js">
		
	</script>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
		
	</script>


</body>

</html>