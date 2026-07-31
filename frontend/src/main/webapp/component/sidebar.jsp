<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<div
	class="bg-dark text-white vh-100 border-end border-secondary d-flex flex-column"
	style="width: 260px;">

	<div class="p-4">

		<div class="mb-4">

			<i class="bi bi-film text-primary display-5"></i>

			<h3 class="fw-bold mt-2 mb-0">MovieRcsys</h3>

			<small class="text-secondary">Admin Panel</small>

		</div>

		<ul class="nav nav-pills flex-column gap-2">

			<li class="nav-item">
				<a href="admindashboard.jsp" class="nav-link text-white py-3">
					<i class="bi bi-speedometer2 me-3"></i>
					Dashboard
				</a>
			</li>

			<li class="nav-item">
				<a href="manageMovies.jsp" class="nav-link text-white py-3">
					<i class="bi bi-film me-3"></i>
					Manage Movies
				</a>
			</li>

			<li class="nav-item">
				<a href="addMovie.jsp" class="nav-link text-white py-3">
					<i class="bi bi-plus-circle me-3"></i>
					Add Movie
				</a>
			</li>

			<li class="nav-item">
				<a href="manageGenres.jsp" class="nav-link text-white py-3">
					<i class="bi bi-tags me-3"></i>
					Genres
				</a>
			</li>

			<li class="nav-item">
				<a href="manageLanguages.jsp" class="nav-link text-white py-3">
					<i class="bi bi-translate me-3"></i>
					Languages
				</a>
			</li>

			<li class="nav-item">
				<a href="manageUsers.jsp" class="nav-link text-white py-3">
					<i class="bi bi-people me-3"></i>
					Users
				</a>
			</li>

		</ul>

	</div>

	<div class="mt-auto p-4 border-top border-secondary">

		<a href="#" id="logoutBtn" class="nav-link text-white">
			<i class="bi bi-box-arrow-right me-3"></i>
			Logout
		</a>

	</div>

</div>