<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
/* Desktop Sidebar */
.sidebar {
	width: 260px;
	height: 100vh;
	background: #1a1a1a;
	border-right: 1px solid #343a40;
	position: fixed;
	top: 0;
	left: 0;
	overflow-y: auto;
	z-index: 1030;
}

.sidebar .nav-link {
	color: #bfc3c9;
	padding: 14px 18px;
	border-radius: 10px;
	transition: .3s;
	font-size: 16px;
}

.sidebar .nav-link:hover, .sidebar .nav-link.active {
	background: #0d6efd;
	color: white;
}

.sidebar .logo {
	font-size: 32px;
	color: #0d6efd;
}

.sidebar-title {
	font-size: 24px;
	font-weight: bold;
}

.sidebar small {
	color: #9ca3af;
}

/* Mobile */
@media ( max-width :991px) {
	.sidebar {
		display: none;
	}
}

/* Hamburger */
.mobile-header {
	display: none;
}

@media ( max-width :991px) {
	.mobile-header {
		display: flex;
		align-items: center;
		justify-content: space-between;
		background: #1a1a1a;
		padding: 15px 20px;
		color: white;
		position: sticky;
		top: 0;
		z-index: 1050;
	}
	.mobile-header h5 {
		margin: 0;
		font-weight: bold;
	}
}

/* Offcanvas */
.offcanvas {
	background: #1a1a1a;
	color: white;
	width: 260px;
}

.offcanvas .nav-link {
	color: #bfc3c9;
	padding: 14px 18px;
	border-radius: 10px;
	transition: .3s;
}

.offcanvas .nav-link:hover, .offcanvas .nav-link.active {
	background: #0d6efd;
	color: white;
}
</style>

<!-- ================= MOBILE HEADER ================= -->

<div class="mobile-header">

	<h5 class="text-white">
		<i class="bi bi-film text-primary"></i> MovieRcsys
	</h5>

	<button class="btn btn-outline-light" data-bs-toggle="offcanvas"
		data-bs-target="#mobileSidebar">

		<i class="bi bi-list fs-3"></i>

	</button>

</div>

<!-- ================= DESKTOP SIDEBAR ================= -->

<div class="sidebar d-none d-lg-flex flex-column">

	<div class="p-4">

		<div class="mb-4">

			<i class="bi bi-film logo"></i>

			<div class="sidebar-title mt-2 text-white">MovieRcsys</div>

		</div>

		<ul class="nav flex-column gap-2">

			<li class="nav-item"><a href="admindashboard.jsp"
				class="nav-link active"> <i class="bi bi-speedometer2 me-2"></i>
					Dashboard
			</a></li>

			<li><a href="manageMovies.jsp" class="nav-link"> <i
					class="bi bi-film me-2"></i> Manage Movies
			</a></li>

			<li><a href="addMovie.jsp" class="nav-link"> <i
					class="bi bi-plus-circle me-2"></i> Add Movie
			</a></li>

			<!-- <li><a href="manageGenres.jsp" class="nav-link"> <i
					class="bi bi-tags me-2"></i> Genres
			</a></li>

			<li><a href="manageLanguages.jsp" class="nav-link"> <i
					class="bi bi-translate me-2"></i> Languages
			</a></li>
 -->
			<li><a href="viewUser.jsp" class="nav-link"> <i
					class="bi bi-people me-2"></i> Users
			</a></li>

		</ul>

	</div>

	<div class="mt-auto p-4 border-top border-secondary">

		<a href="#" id="logoutBtn" class="nav-link"> <i
			class="bi bi-box-arrow-right me-2"></i> Logout
		</a>

	</div>

</div>

<!-- ================= MOBILE OFFCANVAS ================= -->

<div class="offcanvas offcanvas-start" tabindex="-1" id="mobileSidebar">

	<div class="offcanvas-header">

		<h4 class="fw-bold text-white">

			<i class="bi bi-film text-primary"></i> MovieRcsys

		</h4>

		<button type="button" class="btn-close btn-close-white"
			data-bs-dismiss="offcanvas"></button>

	</div>

	<div class="offcanvas-body d-flex flex-column">

		<ul class="nav flex-column gap-2">

			<li><a href="admindashboard.jsp" class="nav-link active"> <i
					class="bi bi-speedometer2 me-2"></i> Dashboard
			</a></li>

			<li><a href="manageMovies.jsp" class="nav-link"> <i
					class="bi bi-film me-2"></i> Manage Movies
			</a></li>

			<li><a href="addMovie.jsp" class="nav-link"> <i
					class="bi bi-plus-circle me-2"></i> Add Movie
			</a></li>

			<!-- <li><a href="manageGenres.jsp" class="nav-link"> <i
					class="bi bi-tags me-2"></i> Genres
			</a></li> -->

			<!-- <li><a href="manageLanguages.jsp" class="nav-link"> <i
					class="bi bi-translate me-2"></i> Languages
			</a></li> -->

			<li><a href="viewUser.jsp" class="nav-link"> <i
					class="bi bi-people me-2"></i> Users
			</a></li>

		</ul>

		<div class="mt-auto pt-3 border-top border-secondary">

			<a href="#" id="logoutBtn" class="nav-link"> <i
				class="bi bi-box-arrow-right me-2"></i> Logout

			</a>

		</div>

	</div>

</div>

<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>