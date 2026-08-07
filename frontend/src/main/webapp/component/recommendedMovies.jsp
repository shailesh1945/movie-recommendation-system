<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>

/* ================= Section ================= */
.recommendation-section {
	background: #000;
	padding: 30px 0;
}

/* Header */
.section-title {
	font-size: clamp(1.6rem, 3vw, 2.2rem);
	font-weight: 700;
	color: #fff;
}

.section-subtitle {
	color: #9ca3af;
	font-size: 15px;
	margin-bottom: 0;
}

/* View Button */
.btn-outline-danger {
	border-radius: 10px;
	padding: 8px 20px;
}

/* ================= Movie Card ================= */
.movie-card {
	background: #171717;
	border: 1px solid #2d2d2d;
	border-radius: 14px;
	overflow: hidden;
	transition: .3s;
	height: 340px;
}

.movie-card:hover {
	transform: translateY(-4px);
	border-color: #dc3545;
}

.movie-card img {
	width: 100%;
	height: 190px;
	object-fit: cover;
}

.movie-card .card-body {
	padding: 12px;
}

.movie-card .card-title {
	font-size: 15px;
	font-weight: 600;
	color: #fff;
	margin-bottom: 6px;
}

.movie-card p {
	font-size: 13px;
	color: #adb5bd;
	margin-bottom: 5px;
}

/* Loading */
.loading {
	min-height: 120px;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}

/* ================= Responsive ================= */
@media ( max-width :992px) {
	.movie-card {
		height: 320px;
	}
	.movie-card img {
		height: 175px;
	}
}

@media ( max-width :768px) {
	.section-title {
		font-size: 1.5rem;
	}
	.btn-outline-danger {
		width: 100%;
	}
	.movie-card {
		height: 300px;
	}
	.movie-card img {
		height: 165px;
	}
}

@media ( max-width :576px) {
	.section-title {
		font-size: 1.3rem;
	}
	.movie-card {
		height: 285px;
	}
	.movie-card img {
		height: 150px;
	}
}
/* Movie Card */

.movie-card{

    background:#111;

    border:1px solid #2d2d2d;

    border-radius:14px;

    overflow:hidden;

    transition:.3s;

    height:100%;

}

.movie-card:hover{

    transform:translateY(-5px);

    border-color:#dc3545;

}

/* Poster */

.movie-poster{

    width:100%;

    height:220px;

    object-fit:cover;

}

/* Card Body */

.movie-card .card-body{

    padding:12px;

}

/* Movie Title */

.movie-title{

    color:#fff;

    font-size:15px;

    font-weight:600;

    margin-bottom:8px;

}

/* Movie Info */

.movie-info{

    color:#bdbdbd;

    font-size:13px;

    margin-bottom:6px;

}

/* Rating */

.rating{

    color:#ffc107;

    font-size:14px;

    font-weight:600;

    margin-top:8px;

}

/* Button */

.movie-card .btn{

    border-radius:8px;

}

/* Mobile */

@media(max-width:768px){

    .movie-poster{

        height:180px;

    }

}

@media(max-width:576px){

    .movie-poster{

        height:200px;

    }

}
</style>

<div class="recommendation-section">

	<div class="container-fluid">

		<div
			class="d-flex justify-content-between align-items-center flex-wrap gap-3 mb-4">

			<div>

				<h2 class="section-title">

					<i class="bi bi-stars text-danger me-2"></i>Latest Movies

				</h2>

			</div>

			<button class="btn btn-outline-danger">View All</button>

		</div>

		<!-- Dynamic Movies -->

		<div class="row g-3" id="recommendedMoviesContainer">

			<div class="col-12">

				<div class="loading">

					<div class="spinner-border text-danger" role="status">

						<span class="visually-hidden"> Loading... </span>

					</div>

					<p class="text-secondary mt-3 mb-0">Loading movies...</p>

				</div>

			</div>

		</div>

	</div>

</div>