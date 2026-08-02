<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<div class="container-fluid p-3">

	<div class="card bg-dark border-secondary shadow-sm">

		<div class="card-body">

			<div class="d-flex justify-content-between align-items-center flex-wrap gap-3">

				<!-- Left Side -->
				<div class="d-flex align-items-center">

					<!-- Hamburger (Only Mobile) -->
					<button class="btn btn-outline-light d-lg-none me-3"
						data-bs-toggle="offcanvas"
						data-bs-target="#mobileSidebar">

						<i class="bi bi-list fs-3"></i>

					</button>

					<div>

						<h2 class="text-white fw-bold mb-1">
							Movie Recommendation Dashboard
						</h2>

						<p class="text-secondary mb-0">
							Manage movies, genres, languages and users.
						</p>

					</div>

				</div>

				<!-- Right Side -->
				<div class="d-flex align-items-center gap-2 flex-wrap">

					<a href="addMovie.jsp"
						class="btn btn-primary">

						<i class="bi bi-plus-circle me-2"></i>

						Add Movie

					</a>

					<button
						class="btn btn-outline-light rounded-circle"
						style="width:45px;height:45px;">

						<i class="bi bi-person-circle fs-4"></i>

					</button>

				</div>

			</div>

		</div>

	</div>

</div>