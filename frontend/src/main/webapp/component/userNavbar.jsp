<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
.glass-navbar {
	background: #0f1117;
	border-bottom: 1px solid rgba(255, 255, 255, .08);
}
.navbar-nav .nav-link{
    color:#ffffff;
    transition:.3s;
}

.navbar-nav .nav-link:hover{
    color:#dc3545;
}

.navbar-nav .active-nav{
    color:#dc3545 !important;
    font-weight:700;
}
.navbar-brand {
	font-size: 32px;
	font-weight: 700;
}

.nav-link {
	color: #c9c9c9 !important;
	margin: 0 10px;
	transition: .3s;
}

.nav-link:hover {
	color: #ffffff !important;
}

.search-box {
	width: 280px;
}

.profile-img {
	width: 40px;
	height: 40px;
	object-fit: cover;
}

.dropdown-menu {
	background: #1b1b1b;
	border: 1px solid #333;
}

.dropdown-item {
	color: white;
}

.dropdown-item:hover {
	background: #dc3545;
	color: white;
}

.notification {
	cursor: pointer;
}
</style>

<nav
	class="navbar navbar-expand-lg navbar-dark glass-navbar sticky-top py-3">

	<div class="container-fluid px-4">

		<!-- Logo -->

		<a class="navbar-brand text-danger"
			href="${pageContext.request.contextPath}/user/home.jsp">

			MovieRcsys </a>

		<!-- Mobile -->

		<button class="navbar-toggler" data-bs-toggle="collapse"
			data-bs-target="#navbarMenu">

			<span class="navbar-toggler-icon"></span>

		</button>

		<div class="collapse navbar-collapse" id="navbarMenu">

			<!-- Center -->

			<ul class="navbar-nav mx-auto">

				<li class="nav-item"><a class="nav-link active" href="home.jsp">

						Home </a></li>

				<li class="nav-item"><a class="nav-link" href="#"> Movies </a>

				</li>

				<li class="nav-item"><a class="nav-link active-nav"
					href="recommendationPage.jsp"> Recommendation </a></li>

				<li class="nav-item"><a class="nav-link" href="#"> My List

				</a></li>

			</ul>

			<!-- Right -->

			<div class="d-flex align-items-center gap-3">

				<!-- Search -->

				<div class="input-group search-box">

					<span
						class="input-group-text bg-dark border-secondary text-secondary">

						<i class="bi bi-search"></i>

					</span> <input type="text"
						class="form-control bg-dark border-secondary text-white"
						placeholder="Search movies...">

				</div>

				<!-- Profile -->

				<div class="dropdown">

					<button class="btn btn-link p-0 border-0" type="button"
						id="profileDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">

						<img src="https://i.pravatar.cc/150?img=12"
							class="rounded-circle profile-img border border-secondary"
							alt="Profile">

					</button>

					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="profileDropdown">

						<li><a class="dropdown-item" href="#">My Profile</a></li>

						<li><a class="dropdown-item text-danger" href="#"
							id="logoutBtn">Logout</a></li>

					</ul>

				</div>

			</div>

		</div>

	</div>

</nav>