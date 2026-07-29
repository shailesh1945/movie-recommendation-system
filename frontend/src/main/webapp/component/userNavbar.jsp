<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Movie Navbar</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
body {
	background: #0f1117;
	height: 200vh;
}

.glass-navbar {
	background: rgba(15, 17, 23, .55);
	backdrop-filter: blur(18px);
	-webkit-backdrop-filter: blur(18px);
	border-bottom: 1px solid rgba(255, 255, 255, .08);
}

.search-box {
	width: 260px;
}

.profile-img {
	width: 34px;
	height: 34px;
	object-fit: cover;
}
</style>

</head>

<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark glass-navbar sticky-top py-2">

		<div class="container-fluid px-4">

			<!-- Logo -->

			<a class="navbar-brand fw-bold text-danger fs-4" href="#">
				MovieRcsys </a>

			<!-- Mobile -->

			<button class="navbar-toggler" data-bs-toggle="collapse"
				data-bs-target="#navbarMenu">

				<span class="navbar-toggler-icon"></span>

			</button>

			<div class="collapse navbar-collapse" id="navbarMenu">

				<!-- Center Menu -->

				<ul class="navbar-nav mx-auto">

					<li class="nav-item"><a
						class="nav-link fw-semibold text-light" href="#"> Movies </a></li>

					<li class="nav-item"><a
						class="nav-link fw-semibold text-light" href="#"> TV Shows </a></li>

					<li class="nav-item"><a
						class="nav-link fw-semibold text-light" href="#"> Originals </a></li>

				</ul>

				<!-- Right -->

				<div class="d-flex align-items-center gap-3">

					<div class="input-group search-box">

						<span
							class="input-group-text bg-dark border-secondary text-secondary">

							<i class="bi bi-search"></i>

						</span> <input type="text"
							class="form-control bg-dark border-secondary text-white"
							placeholder="Search titles...">

					</div>

					<a href="#" class="text-white fs-5"> <i class="bi bi-bell"></i>

					</a> <a href="#" class="text-white fs-5"> <i class="bi bi-gear"></i>

					</a> <a href="#"> <img src="https://i.pravatar.cc/150?img=12"
						class="rounded-circle profile-img border border-secondary">

					</a>

				</div>

			</div>

		</div>

	</nav>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>