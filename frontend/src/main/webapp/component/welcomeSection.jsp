<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.hero-section {
	position: relative;
	min-height: 85vh;
	background: linear-gradient(rgba(10, 10, 10, .75), rgba(10, 10, 10, .85)),
		url("https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?q=80&w=1600&auto=format&fit=crop");
	background-size: cover;
	background-position: center;
	display: flex;
	align-items: center;
}

.hero-content {
	max-width: 700px;
}

.hero-title {
	font-size: 58px;
	font-weight: 700;
	color: white;
}

.hero-title span {
	color: #dc3545;
}

.hero-text {
	color: #d8d8d8;
	font-size: 18px;
	line-height: 1.8;
}

.feature-box {
	background: rgba(255, 255, 255, .08);
	border: 1px solid rgba(255, 255, 255, .12);
	backdrop-filter: blur(15px);
	border-radius: 18px;
	padding: 25px;
	color: white;
	transition: .3s;
}

.feature-box:hover {
	transform: translateY(-8px);
	background: rgba(255, 255, 255, .13);
}

.stat {
	font-size: 35px;
	font-weight: bold;
	color: #dc3545;
}
</style>

<section class="hero-section">

	<div class="container">

		<div class="row align-items-center">

			<div class="col-lg-7">

				<div class="hero-content">

					<h1 class="hero-title">

						Find Your Next <span>Favorite Movie</span>

					</h1>

					<p class="hero-text mt-4">Our recommendation engine analyzes
						your interests, genres, ratings, and viewing preferences to
						recommend movies you'll love. Browse thousands of movies and
						discover hidden gems tailored just for you.</p>

					<div class="mt-5">

						<button class="btn btn-danger btn-lg px-5 me-3">

							<i class="bi bi-stars"></i> Get Recommendations

						</button>

						<button class="btn btn-outline-light btn-lg px-5">Browse
							Movies</button>

					</div>

				</div>

			</div>

			<div class="col-lg-5">

				<div class="row g-4">

					<div class="col-6">

						<div class="feature-box text-center">

							<div class="stat">5000+</div>

							Movies

						</div>

					</div>

					<div class="col-6">

						<div class="feature-box text-center">

							<div class="stat">25+</div>

							Genres

						</div>

					</div>

					<div class="col-6">

						<div class="feature-box text-center">

							<div class="stat">95%</div>

							Accuracy

						</div>

					</div>

					<div class="col-6">

						<div class="feature-box text-center">

							<div class="stat">AI</div>

							Recommendations

						</div>

					</div>

				</div>

			</div>

		</div>

	</div>

</section>