<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MovieRcsys</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

/* ================= Hero Section ================= */

.hero-section{

	position:relative;

	min-height:100vh;

	overflow:hidden;

}

/* Background */

.hero-bg{

	position:absolute;

	top:0;

	left:0;

	width:100%;

	height:100%;

	object-fit:cover;

}

/* Dark Overlay */

.hero-overlay{

	position:absolute;

	inset:0;

	background:rgba(0,0,0,.72);

}

/* Content */

.hero-content{

	position:relative;

	z-index:2;

	min-height:100vh;

	display:flex;

	align-items:center;

	padding:80px 0;

}

/* Heading */

.hero-title{

	font-size:clamp(2.8rem,6vw,5.8rem);

	font-weight:800;

	line-height:1.1;

}

.hero-text{

	font-size:1.15rem;

	color:#d4d4d4;

	max-width:620px;

}

/* Buttons */

.hero-buttons .btn{

	padding:14px 32px;

	font-weight:600;

	border-radius:10px;

	transition:.3s;

}

.hero-buttons .btn:hover{

	transform:translateY(-3px);

}

/* ================= Responsive ================= */

@media (max-width:992px){

	.hero-content{

		text-align:center;

		justify-content:center;

	}

	.hero-text{

		margin:auto;

	}

}

@media (max-width:768px){

	.hero-content{

		padding:120px 0 60px;

	}

	.hero-buttons{

		display:flex;

		flex-direction:column;

		gap:15px;

	}

	.hero-buttons .btn{

		width:100%;

	}

}

@media (max-width:576px){

	.hero-title{

		font-size:2.4rem;

	}

	.hero-text{

		font-size:1rem;

	}

	.badge{

		font-size:.75rem;

	}

}

</style>

</head>

<body class="bg-dark">

<section class="hero-section">

	<!-- Background -->

	<img
		src="assets/images/heropage.png"
		alt="Hero"
		class="hero-bg">

	<!-- Overlay -->

	<div class="hero-overlay"></div>

	<!-- Content -->

	<div class="container hero-content">

		<div class="row w-100">

			<div class="col-xl-7 col-lg-8 col-md-10 col-12 text-white">

				<span class="badge border border-danger text-danger px-3 py-2 mb-4">

					EXCLUSIVE PREMIERE

				</span>

				<h1 class="hero-title mb-4">

					THE WORLD OF

					<span class="text-danger">

						CINEMA

					</span>

					<br>

					<span class="text-danger">

						REIMAGINED.

					</span>

				</h1>

				<p class="hero-text mb-5">

					Stream the latest blockbusters, timeless classics and exclusive originals in stunning 4K HDR. Experience premium entertainment anytime, anywhere.

				</p>

				<!-- <div class="hero-buttons d-flex gap-3 flex-wrap">

					<a href="#"
						class="btn btn-danger">

						START YOUR FREE TRIAL

					</a>

					<a href="#"
						class="btn btn-outline-light">

						VIEW PRICING

					</a>

				</div> -->

			</div>

		</div>

	</div>

</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>