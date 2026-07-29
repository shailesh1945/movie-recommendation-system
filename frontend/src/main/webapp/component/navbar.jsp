<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>MovieRcsys</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
body {
	background: #111418;
}

.glass-navbar {
	background: rgba(20, 20, 20, .45);
	backdrop-filter: blur(12px);
	-webkit-backdrop-filter: blur(12px);
	border-bottom: 1px solid rgba(255, 255, 255, .08);
}

.modal-content {
	background: #1d2127;
}

.form-control, .input-group-text {
	background: #1d2127;
	border-color: #343a40;
	color: white;
}

.form-control:focus {
	background: #1d2127;
	color: white;
	border-color: #dc3545;
	box-shadow: none;
}

#regpage {
	background: rgba(29, 33, 39, .75);
	backdrop-filter: blur(15px);
	-webkit-backdrop-filter: blur(15px);
}

#registerModal .modal-dialog {
	max-width: 550px;
}

</style>

</head>

<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark glass-navbar sticky-top py-3">

		<div class="container">

			<a class="navbar-brand fw-bold text-danger fs-3" href="#">
				MovieRcsys </a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarMenu">

				<span class="navbar-toggler-icon"></span>

			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navbarMenu">

				<div class="d-flex align-items-center gap-3">

					<a href="#"
						class="btn text-white btn-danger text-decoration-none fw-semibold"
						data-bs-toggle="modal" data-bs-target="#loginModal"> LOG IN </a><a
						href="#" class="btn btn-danger px-4 fw-semibold"
						data-bs-toggle="modal" data-bs-target="#registerModal">
						Register </a>

				</div>

			</div>

		</div>

	</nav>
	<div class="modal fade" id="registerModal" tabindex="-1">

		<div class="modal-dialog modal-dialog-centered modal-md">

			<div
				class="modal-content register-modal border border-secondary rounded-4 text-white" id="regpage">

				<div class="modal-body p-4">

					<div id="registerAlert" class="mb-3"></div>

					<form id="registerForm">

						<div class="row">

							<div class="col-md-6 mb-3">

								<label class="form-label text-secondary small fw-bold">
									FIRST NAME </label> <input type="text"
									class="form-control bg-dark text-white border-secondary"
									id="firstName" placeholder="Enter first name" required>

							</div>

							<div class="col-md-6 mb-3">

								<label class="form-label text-secondary small fw-bold">
									LAST NAME </label> <input type="text"
									class="form-control bg-dark text-white border-secondary"
									id="lastName" placeholder="Enter last name" required>

							</div>

						</div>

						<div class="mb-3">

							<label class="form-label text-secondary small fw-bold">
								EMAIL ADDRESS </label> <input type="email"
								class="form-control bg-dark text-white border-secondary"
								id="registerEmail" placeholder="name@example.com" required>

						</div>

						<div class="row">

							<div class="col-md-6 mb-3">

								<label class="form-label text-secondary small fw-bold">
									MOBILE NUMBER </label> <input type="tel"
									class="form-control bg-dark text-white border-secondary"
									id="mobile" placeholder="+91 9876543210" required>

							</div>

							<div class="col-md-6 mb-3">

								<label class="form-label text-secondary small fw-bold">
									GENDER </label> <select
									class="form-select bg-dark text-white border-secondary"
									id="gender" required>

									<option selected disabled>Select Gender</option>
									<option>Male</option>
									<option>Female</option>
									<option>Other</option>

								</select>

							</div>

						</div>

						<div class="mb-4">

							<label class="form-label text-secondary small fw-bold">
								PASSWORD </label>

							<div class="input-group">

								<input type="password"
									class="form-control bg-dark text-white border-secondary"
									id="registerPassword" placeholder="Create Password" required>

								<span
									class="input-group-text bg-dark border-secondary text-white">

									<i class="bi bi-eye"></i>

								</span>

							</div>

						</div>

						<button type="submit" class="btn btn-danger w-100 py-2 fw-bold">

							Register</button>

					</form>

					<hr class="my-4 text-secondary">

					<div class="text-center">

						<span class="text-secondary"> Already have an account? </span> <a
							href="#" id="openLogin"
							class="text-danger fw-bold text-decoration-none"> Log In </a>

					</div>

				</div>

			</div>

		</div>

	</div>
	<div class="modal fade" id="loginModal" tabindex="-1">

		<div class="modal-dialog modal-dialog-centered modal-md">

			<div
				class="modal-content bg-dark border border-secondary rounded-4 text-white">

				<div class="modal-body p-5">

					<div class="text-center mb-5">

						<h2 class="fw-bold">Sign In</h2>

						<p class="text-secondary mb-0">Access your premium cinematic
							library.</p>

					</div>

					<div id="alertBox" class="mb-3"></div>
					<form id="loginForm">

						<div class="mb-4">

							<label class="form-label text-secondary fw-semibold small">
								EMAIL ADDRESS </label> <input type="email" id="email"
								class="form-control form-control-lg bg-dark text-white border-secondary"
								placeholder="name@example.com" required>

						</div>

						<div class="mb-4">

							<div class="d-flex justify-content-between mb-2">

								<label class="form-label text-secondary fw-semibold small">
									PASSWORD </label> <a href="#"
									class="text-danger text-decoration-none small fw-semibold">
									FORGOT PASSWORD? </a>

							</div>

							<div class="input-group input-group-lg">

								<input type="password" id="password"
									class="form-control bg-dark text-white border-secondary"
									placeholder="Password" required> <span
									class="input-group-text bg-dark border-secondary text-white">

									<i class="bi bi-eye"></i>

								</span>

							</div>

						</div>

						<div class="form-check mb-4">

							<input class="form-check-input" type="checkbox" id="remember">

							<label class="form-check-label text-secondary" for="remember">
								Remember me </label>

						</div>

						<button type="submit" class="btn btn-danger btn-lg w-100 fw-bold">
							Sign In</button>

					</form>

					<hr class="my-4 text-secondary">

					<div class="text-center">

						<span class="text-secondary"> Don't have an account? </span> <a
							href="#" class="text-danger fw-bold text-decoration-none ms-1">
							Register </a>

					</div>

				</div>

			</div>

		</div>

	</div>

	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>