<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>My Profile</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/profile.css">

</head>


<body data-context-path="${pageContext.request.contextPath}">

	<!-- Navbar -->
	<jsp:include page="../component/userNavbar.jsp" />


	<main class="profile-page">

		<div class="profile-container">

			<!-- Page Heading -->

			<div class="profile-page-header">

				<div class="text-center">
					<h1>My Profile</h1>

					<p>Manage your personal information</p>
				</div>

			</div>


			<!-- Alert -->

			<div id="profileAlert" class="alert d-none" role="alert"></div>


			<!-- Profile Card -->

			<div class="profile-card">


				<!-- Profile Summary -->

				<div class="profile-summary">

					<div class="profile-avatar" id="profileAvatar">P</div>


					<div class="profile-user-info">

						<h2 id="profileFullName">Loading...</h2>

						<p id="profileEmail">Loading...</p>

					</div>

				</div>


				<!-- Divider -->

				<div class="profile-divider"></div>


				<!-- Profile Form -->

				<form id="profileForm">

					<div class="row g-3">


						<!-- First Name -->

						<div class="col-md-6">

							<label for="firstName" class="form-label"> First Name </label> <input
								type="text" class="form-control profile-input" id="firstName"
								name="firstName" disabled>

						</div>


						<!-- Last Name -->

						<div class="col-md-6">

							<label for="lastName" class="form-label"> Last Name </label> <input
								type="text" class="form-control profile-input" id="lastName"
								name="lastName" disabled>

						</div>


						<!-- Email -->

						<div class="col-md-6">

							<label for="email" class="form-label"> Email </label> <input
								type="email" class="form-control profile-input" id="email"
								name="email" disabled>

						</div>


						<!-- Phone -->

						<div class="col-md-6">

							<label for="phoneNumber" class="form-label"> Phone Number

							</label> <input type="text" class="form-control profile-input"
								id="phoneNumber" name="phoneNumber" disabled>

						</div>


						<!-- Gender -->

						<div class="col-md-6">

							<label for="gender" class="form-label"> Gender </label> <select
								class="form-select profile-input" id="gender" name="gender"
								disabled>

								<option value="">Select Gender</option>

								<option value="Male">Male</option>

								<option value="Female">Female</option>

								<option value="Other">Other</option>

							</select>

						</div>


						<!-- Password -->

						<div class="col-md-6">

							<label for="password" class="form-label"> New Password </label> <input
								type="password" class="form-control profile-input" id="password"
								name="password"
								placeholder="Leave blank to keep current password" disabled>

						</div>

					</div>


					<!-- Buttons -->

					<div class="profile-actions">

						<button type="button" id="editBtn"
							class="btn btn-primary edit-btn ">Edit Profile</button>


						<button type="button" id="cancelBtn"
							class="btn btn-danger cancel-btn d-none">Cancel</button>


						<button type="submit" id="saveBtn"
							class="btn btn-success save-btn d-none">Save Changes</button>

					</div>

				</form>

			</div>

		</div>

	</main>


	<!-- Footer -->

	<jsp:include page="../component/footer.jsp" />

	<!-- Bootstrap JavaScript -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Application JavaScript -->
	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/profile.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>


</body>

</html>