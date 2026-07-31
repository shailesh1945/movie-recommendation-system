<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<div class="container-fluid px-4">

	<div class="row g-4">

		<!-- Movies -->
		<div class="col-xl-3 col-lg-6">

			<div class="card bg-dark border-secondary text-white rounded-4">

				<div class="card-body">

					<div class="d-flex justify-content-between">

						<div>

							<p class="text-secondary">Total Movies</p>

							<h2 id="totalMovies">0</h2>

						</div>

						<div
							class="bg-primary rounded-circle d-flex justify-content-center align-items-center"
							style="width:60px;height:60px;">

							<i class="bi bi-film fs-3"></i>

						</div>

					</div>

				</div>

			</div>

		</div>

		<!-- Users -->
		<div class="col-xl-3 col-lg-6">

			<div class="card bg-dark border-secondary text-white rounded-4">

				<div class="card-body">

					<div class="d-flex justify-content-between">

						<div>

							<p class="text-secondary">Users</p>

							<h2 id="totalUsers">0</h2>

						</div>

						<div
							class="bg-success rounded-circle d-flex justify-content-center align-items-center"
							style="width:60px;height:60px;">

							<i class="bi bi-people-fill fs-3"></i>

						</div>

					</div>

				</div>

			</div>

		</div>

		<!-- Ratings -->
		<div class="col-xl-3 col-lg-6">

			<div class="card bg-dark border-secondary text-white rounded-4">

				<div class="card-body">

					<div class="d-flex justify-content-between">

						<div>

							<p class="text-secondary">Ratings</p>

							<h2 id="totalRatings">0</h2>

						</div>

						<div
							class="bg-warning rounded-circle d-flex justify-content-center align-items-center"
							style="width:60px;height:60px;">

							<i class="bi bi-star-fill fs-3"></i>

						</div>

					</div>

				</div>

			</div>

		</div>

		<!-- Average Rating -->
		<div class="col-xl-3 col-lg-6">

			<div class="card bg-dark border-secondary text-white rounded-4">

				<div class="card-body">

					<div class="d-flex justify-content-between">

						<div>

							<p class="text-secondary">Average Rating</p>

							<h2 id="averageRating">0.0</h2>

						</div>

						<div
							class="bg-danger rounded-circle d-flex justify-content-center align-items-center"
							style="width:60px;height:60px;">

							<i class="bi bi-graph-up-arrow fs-3"></i>

						</div>

					</div>

				</div>

			</div>

		</div>

	</div>

</div>