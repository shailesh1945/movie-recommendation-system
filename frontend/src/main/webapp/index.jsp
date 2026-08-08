<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Movie Recommendation System</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.3.0/css/all.min.css"
	integrity="sha512-ApSLB1Pd3/bZN8fWB/RG9YhN/7bd9Hkf3AGaE2mPfebjrxagjuBtx2GcgdqIlJkUzwylBo61r9Xa9NmgBI0swA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body class="bg-dark text-white">

	<!-- ================= Navbar ================= -->
	<jsp:include page="component/navbar.jsp" />

	<!-- ================= Main Content ================= -->
	<main>

		<!-- Hero Section -->
		<jsp:include page="component/heroPage.jsp" />
		<jsp:include page="component/premiumExperience.jsp" />
		<jsp:include page="component/subscription.jsp" />
	</main>

	<!-- ================= Footer ================= -->
	<jsp:include page="component/footer.jsp" />

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>