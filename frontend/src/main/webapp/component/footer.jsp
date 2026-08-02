<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<footer class="footer-section">

	<div class="container">

		<div class="row gy-5">

			<!-- Brand -->

			<div class="col-lg-5 col-md-12">

				<h2 class="footer-logo">

					<i class="bi bi-film me-2"></i> Movie<span>Rcsys</span>

				</h2>

				<p class="footer-description">Discover blockbuster movies,
					timeless classics, and personalized recommendations with an
					immersive streaming experience.</p>

				<div class="social-icons">

					<a href="#"><i class="bi bi-facebook"></i></a> <a href="#"><i
						class="bi bi-instagram"></i></a> <a href="#"><i
						class="bi bi-twitter-x"></i></a> <a href="#"><i
						class="bi bi-youtube"></i></a>

				</div>

			</div>

			<!-- Explore -->

			<div class="col-lg-3 col-md-6 col-6">

				<h5 class="footer-heading">Explore</h5>

				<ul class="footer-links">

					<li><a href="#">Home</a></li>

					<li><a href="#">Movies</a></li>

					<li><a href="#">Trending</a></li>

					<li><a href="#">Subscriptions</a></li>

				</ul>

			</div>

			<!-- Support -->

			<div class="col-lg-4 col-md-6 col-6">

				<h5 class="footer-heading">Support</h5>

				<ul class="footer-links">

					<li><a href="#">Privacy Policy</a></li>

					<li><a href="#">Terms & Conditions</a></li>

					<li><a href="#">Help Center</a></li>

					<li><a href="#">Contact Us</a></li>

				</ul>

			</div>

		</div>

		<hr class="footer-divider">

		<div
			class="d-flex flex-column flex-md-row justify-content-between align-items-center text-center text-md-start gap-3">

			<p class="copyright">© 2026 MovieRcsys. All Rights Reserved.</p>

			<p class="copyright">

				Designed with <i class="bi bi-heart-fill text-danger"></i> for Movie
				Lovers

			</p>

		</div>

	</div>

</footer>

<style>

/* ================= Footer ================= */
.footer-section {
	background: #0d1117;
	padding: 70px 0 30px;
	color: #fff;
	border-top: 1px solid rgba(255, 255, 255, .08);
}

/* Logo */
.footer-logo {
	font-size: 2rem;
	font-weight: 700;
	color: #fff;
	margin-bottom: 20px;
}

.footer-logo span {
	color: #dc3545;
}

/* Description */
.footer-description {
	max-width: 420px;
	color: #adb5bd;
	line-height: 1.8;
}

/* Heading */
.footer-heading {
	font-weight: 600;
	margin-bottom: 20px;
	color: #fff;
}

/* Links */
.footer-links {
	list-style: none;
	padding: 0;
	margin: 0;
}

.footer-links li {
	margin-bottom: 12px;
}

.footer-links a {
	color: #adb5bd;
	text-decoration: none;
	transition: .3s;
}

.footer-links a:hover {
	color: #dc3545;
	padding-left: 6px;
}

/* Social Icons */
.social-icons {
	display: flex;
	gap: 15px;
	margin-top: 25px;
}

.social-icons a {
	width: 45px;
	height: 45px;
	display: flex;
	align-items: center;
	justify-content: center;
	background: #1b1f24;
	color: #fff;
	border-radius: 50%;
	text-decoration: none;
	font-size: 18px;
	transition: .3s;
}

.social-icons a:hover {
	background: #dc3545;
	transform: translateY(-5px);
}

/* Divider */
.footer-divider {
	border-color: rgba(255, 255, 255, .08);
	margin: 45px 0 25px;
}

/* Copyright */
.copyright {
	margin: 0;
	color: #9ca3af;
	font-size: .95rem;
}

/* ================= Responsive ================= */
@media ( max-width :992px) {
	.footer-section {
		text-align: center;
	}
	.footer-description {
		margin: auto;
	}
	.social-icons {
		justify-content: center;
	}
}

@media ( max-width :768px) {
	.footer-section {
		padding: 60px 0 25px;
	}
	.footer-logo {
		font-size: 1.8rem;
	}
	.footer-heading {
		margin-top: 10px;
	}
}

@media ( max-width :576px) {
	.footer-section {
		padding: 50px 0 20px;
	}
	.footer-logo {
		font-size: 1.6rem;
	}
	.footer-description {
		font-size: .95rem;
	}
	.social-icons a {
		width: 42px;
		height: 42px;
		font-size: 16px;
	}
	.footer-heading {
		font-size: 1rem;
	}
	.footer-links a {
		font-size: .95rem;
	}
	.copyright {
		font-size: .85rem;
	}
}
</style>