<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Movie Preferences</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
html, body {
	width: 100%;
	min-height: 100%;
	margin: 0;
	padding: 0;
	background: #000 !important;
	color: #fff;
}

/* Main Container */
.preference-container {
	max-width: 850px;
	margin: 40px auto;
	padding: 40px 20px;
	background: #0d0d0d;
	border: 1px solid #222;
	border-radius: 22px;
}

/* Heading */
.page-title {
	font-weight: 700;
	font-size: clamp(2rem, 4vw, 2.7rem);
}

.page-subtitle {
	color: #adb5bd;
	font-size: 1rem;
}

/* Cards */
.preference-card, .slider-card {
	background: #171717 !important;
	border: 1px solid #2f2f2f;
	border-radius: 18px;
	color: #fff;
	transition: .3s ease;
}

.preference-card:hover, .slider-card:hover {
	border-color: #dc3545;
	transform: translateY(-3px);
}

/* Card Body */
.card-body {
	background: #171717;
	padding: 28px;
}

/* Chips */
.preference-chip {
	background: #252525;
	border: 1px solid #3b3b3b;
	color: #fff;
	padding: 10px 18px;
	border-radius: 30px;
	transition: .3s;
}

.preference-chip:hover {
	background: #dc3545;
	border-color: #dc3545;
}

.preference-chip.active {
	background: #dc3545;
	border-color: #dc3545;
}
/* Slider */
.slider-value {
	font-size: 22px;
	font-weight: 700;
	color: #dc3545;
}

.form-range::-webkit-slider-thumb {
	background: #dc3545;
}

.form-range::-moz-range-thumb {
	background: #dc3545;
}

/* Button */
.save-btn {
	background: #dc3545;
	border: none;
	border-radius: 35px;
	min-width: 250px;
	height: 52px;
	font-weight: 600;
}

.save-btn:hover {
	background: #bb2d3b;
}

footer {
	background: #000 !important;
	border-top: 1px solid #222;
}

/* Mobile */
@media ( max-width :768px) {
	.preference-container {
		padding: 35px 15px;
	}
	.card-body {
		padding: 20px;
	}
	.preference-chip {
		font-size: 13px;
		padding: 8px 14px;
	}
	.save-btn {
		width: 100%;
	}
}

@media ( max-width :576px) {
	.preference-chip {
		width: 100%;
		text-align: center;
	}
	h5 {
		font-size: 18px;
	}
}
</style>

</head>

<body>
	<jsp:include page="../component/userNavbar.jsp" />
	<div class="preference-container">

		<div class="text-center mb-5">

			<h2 class="page-title text-danger">Personalize Your
				Recommendations</h2>

		</div>

		<!-- Genres -->

		<div class="card preference-card mb-4">

			<div class="card-body">

				<h5 class="mb-4 text-white">

					<i class="bi bi-film text-danger me-2"></i> Favorite Genres

				</h5>

				<div class="d-flex flex-wrap gap-2">

					<button type="button" class="preference-chip" data-type="genre"
						data-id="1">Action</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="2">Adventure</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="3">Comedy</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="4">Drama</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="5">Romance</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="6">Sci-Fi</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="7">Thriller</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="8">Animation</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="9">Horror</button>

					<button type="button" class="preference-chip" data-type="genre"
						data-id="10">Fantasy</button>

				</div>

			</div>

		</div>

		<!-- Languages -->

		<div class="card preference-card mb-4">

			<div class="card-body">

				<h5 class="mb-4 text-white">

					<i class="bi bi-globe text-danger me-2"></i> Favorite Languages

				</h5>

				<div class="d-flex flex-wrap gap-2">

					<button type="button" class="preference-chip" data-type="language"
						data-id="1">English</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="2">Hindi</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="3">Marathi</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="4">Tamil</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="5">Telugu</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="6">Korean</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="7">French</button>

					<button type="button" class="preference-chip" data-type="language"
						data-id="8">Japanese</button>

				</div>
			</div>

		</div>

		<!-- Sliders -->

		<div class="row g-4">

			<div class="col-md-6">

				<div class="card slider-card">

					<div class="card-body">

						<div class="d-flex justify-content-between">

							<h6 class="text-white">

								<i class="bi bi-star-fill text-danger me-2"></i> Minimum Rating

							</h6>

							<span id="ratingValue" class="slider-value"> 3.5 </span>

						</div>

						<input type="range" class="form-range mt-4" id="ratingSlider"
							min="0" max="5" step="0.5" value="3.5">

						<div class="d-flex justify-content-between">

							<small>0</small> <small>5</small>

						</div>

					</div>

				</div>

			</div>

			<div class="col-md-6">

				<div class="card slider-card">

					<div class="card-body">

						<div class="d-flex justify-content-between">

							<h6 class="text-white">

								<i class="bi bi-calendar-event text-danger me-2"></i> Minimum
								Year

							</h6>

							<span id="yearValue" class="slider-value"> 2010 </span>

						</div>

						<input type="range" class="form-range mt-4" id="yearSlider"
							min="1950" max="2026" value="2010">

						<div class="d-flex justify-content-between">

							<small>1950</small> <small>2026</small>

						</div>

					</div>

				</div>

			</div>

		</div>

		<div class="text-center mt-5">

			<button type="button" id="savePreferencesBtn"
				class="save-btn text-white">

				Save Preferences <i class="bi bi-arrow-right ms-2"></i>

			</button>

		</div>

	</div>
	<jsp:include page="../component/footer.jsp" />

	<script>
		const CONTEXT_PATH = "${pageContext.request.contextPath}";
	</script>


	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/preferences.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/recommendation.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>