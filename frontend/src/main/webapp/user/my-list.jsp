<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My List - MovieRcsys</title>

<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Bootstrap Icons -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">


<style>

/* =========================================================
   PAGE
   ========================================================= */
body {
	background-color: #111827;
	color: white;
	min-height: 100vh;
}

/* =========================================================
   MAIN CONTAINER
   ========================================================= */
.my-list-container {
	max-width: 1200px;
	margin: 45px auto;
	padding: 0 20px;
}

/* =========================================================
   HEADER
   ========================================================= */
.my-list-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 30px;
}

.my-list-title {
	font-size: 32px;
	font-weight: 700;
	margin-bottom: 5px;
}

.my-list-subtitle {
	color: #9ca3af;
	margin: 0;
}

/* =========================================================
   MOVIE CARD
   ========================================================= */
.movie-card {
	background-color: #1f2937;
	border: 1px solid rgba(255, 255, 255, 0.08);
	border-radius: 14px;
	overflow: hidden;
	height: 100%;
	transition: all 0.25s ease;
}

.movie-card:hover {
	transform: translateY(-6px);
	box-shadow: 0 12px 30px rgba(0, 0, 0, 0.4);
}

/* =========================================================
   POSTER
   ========================================================= */
.movie-poster {
	width: 100%;
	height: 320px;
	object-fit: cover;
	background-color: #374151;
}

/* =========================================================
   MOVIE BODY
   ========================================================= */
.movie-card-body {
	padding: 14px;
}

.movie-title {
	font-size: 18px;
	font-weight: 700;
	margin-bottom: 10px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.movie-info {
	color: #9ca3af;
	font-size: 14px;
	margin-bottom: 7px;
}

.movie-info i {
	margin-right: 6px;
}

.rating {
	color: #fbbf24;
}

/* =========================================================
   BUTTONS
   ========================================================= */
.details-btn, .remove-btn {
	height: 38px;
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 5px;
}

.details-btn {
	flex: 1;
}

.remove-btn {
	flex: 1;
}

/* =========================================================
   LOADING
   ========================================================= */
#loadingContainer {
	text-align: center;
	padding: 100px 20px;
}

/* =========================================================
   EMPTY LIST
   ========================================================= */
#emptyContainer {
	text-align: center;
	padding: 100px 20px;
}

.empty-icon {
	font-size: 70px;
	color: #6b7280;
}

#emptyContainer h3 {
	margin-top: 20px;
	font-weight: 600;
}

#emptyContainer p {
	color: #9ca3af;
}

/* =========================================================
   ERROR
   ========================================================= */
#errorContainer {
	display: none;
}

/* =========================================================
   MESSAGE
   ========================================================= */
#watchlistMessage {
	position: fixed;
	bottom: 25px;
	right: 25px;
	z-index: 9999;
	padding: 13px 22px;
	border-radius: 8px;
	font-weight: 600;
	display: none;
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
}

/* =========================================================
   MOBILE
   ========================================================= */
@media ( max-width : 576px) {
	.my-list-title {
		font-size: 26px;
	}
	.my-list-header {
		display: block;
	}
	.movie-poster {
		height: 420px;
	}
}
</style>

</head>


<body class="bg-black">


	<!-- ========================================================
     USER NAVBAR
     ======================================================== -->

	<jsp:include page="../component/userNavbar.jsp" />


	<!-- ========================================================
     MAIN CONTENT
     ======================================================== -->

	<div class="container my-list-container">


		<!-- ====================================================
         HEADER
         ==================================================== -->

		<div class="my-list-header">

			<div>

				<h1 class="my-list-title text-white">

					<i class="bi bi-bookmark-heart-fill text-warning"></i> My List

				</h1>

				<p class="my-list-subtitle">Movies you have saved to watch
					later.</p>

			</div>


			<!-- Browse Movies -->

			<div class="mt-3 mt-md-0">

				<a href="${pageContext.request.contextPath}/user/allmovies.jsp"
					class="btn btn-outline-warning"> <i class="bi bi-film"></i> All
					Movies

				</a>

			</div>

		</div>


		<!-- ====================================================
         LOADING
         ==================================================== -->

		<div id="loadingContainer">

			<div class="spinner-border text-warning" role="status"></div>

			<p class="mt-3 text-secondary">Loading your My List...</p>

		</div>


		<!-- ====================================================
         ERROR
         ==================================================== -->

		<div id="errorContainer" class="alert alert-danger">

			<i class="bi bi-exclamation-triangle-fill"></i> <span id="errorText">

				Unable to load your My List. </span>

		</div>


		<!-- ====================================================
         MOVIES
         ==================================================== -->

		<div id="movieContainer" class="row g-4"></div>


		<!-- ====================================================
         EMPTY LIST
         ==================================================== -->

		<div id="emptyContainer" class="d-none">

			<i class="bi bi-bookmark-x empty-icon"> </i>


			<h3>Your My List is empty</h3>


			<p>You haven't added any movies yet.</p>


			<a href="${pageContext.request.contextPath}/user/allmovies.jsp"
				class="btn btn-warning mt-3"> <i class="bi bi-plus-lg"></i> Add
				Movies

			</a>

		</div>

	</div>


	<!-- ========================================================
     SUCCESS / ERROR MESSAGE
     ======================================================== -->

	<div id="watchlistMessage"></div>


	<jsp:include page="../component/footer.jsp" />
	<!-- ========================================================
     JAVASCRIPT
     ======================================================== -->

	<!-- API -->

	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/profile.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>


	<!-- My List -->

	<script src="${pageContext.request.contextPath}/assets/js/my-list.js">
		
	</script>


</body>

</html>