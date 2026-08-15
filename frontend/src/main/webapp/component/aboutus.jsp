<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>About Us - MovieRcsys</title>

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="../assets/css/about.css">
</head>

<body>
	<div>
		<jsp:include page="../component/userNavbar.jsp" />
	</div>
	<!-- ================= HERO SECTION ================= -->
	<section class="hero-section">

		<div class="hero-overlay"></div>

		<div class="container hero-content text-center">

			<h1 class="text-white">
				The Future of Cinema<br> Discovery.
			</h1>

			<p class="text-white">
				Born from a passion for storytelling and powered by advanced AI,<br
					class="d-none d-md-block"> CineRcsys is your gateway to the
				world's most captivating stories.
			</p>

		</div>

	</section>


	<!-- ================= MISSION SECTION ================= -->
	<section class="mission-section">

		<div class="container">

			<div class="mission-content text-center">

				<h2>Our Mission</h2>

				<p>
					We exist to bridge the gap between discerning viewers and the
					masterpieces they are<br class="d-none d-md-block"> destined
					to love. We are eliminating "blind spots" by curating a
					personalized,<br class="d-none d-md-block"> immersive
					theatrical experience right in your living room. Every
					recommendation is<br class="d-none d-md-block"> premium.
				</p>

			</div>


			<!-- ================= ENGINE ================= -->

			<div class="engine-section">

				<h2 class="text-center">The Engine</h2>


				<div class="row g-4 mt-4">

					<!-- CARD 1 -->
					<div class="col-12 col-md-4">

						<div class="feature-card text-center">

							<div class="feature-icon">
								<i class="bi bi-bar-chart-fill"></i>
							</div>

							<h3>Smart Analysis</h3>

							<p>Deep-learning algorithms analyze thousands of cinematic
								data points to understand narrative structure, pacing, and
								visual style.</p>

						</div>

					</div>


					<!-- CARD 2 -->
					<div class="col-12 col-md-4">

						<div class="feature-card text-center">

							<div class="feature-icon">
								<i class="bi bi-heart-fill"></i>
							</div>

							<h3>Personalized Taste</h3>

							<p>Your unique cinematic fingerprint is constantly refined,
								ensuring recommendations resonate with your evolving mood and
								preferences.</p>

						</div>

					</div>


					<!-- CARD 3 -->
					<div class="col-12 col-md-4">

						<div class="feature-card text-center">

							<div class="feature-icon">
								<i class="bi bi-search-heart-fill"></i>
							</div>

							<h3>Real-time Discovery</h3>

							<p>Unearth hidden gems and global cinema in real-time,
								curated specifically for you without the noise of generic
								trending lists.</p>

						</div>

					</div>

				</div>

			</div>

		</div>

	</section>

	<div>
		<jsp:include page="../component/footer.jsp" />
	</div>
	
	
	
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
		
	</script>
	
	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
	
	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>

</body>
</html>