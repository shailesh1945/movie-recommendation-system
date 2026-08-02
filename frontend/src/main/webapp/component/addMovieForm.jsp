<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container py-4">

	<div class="row justify-content-center">

		<div class="col-12 col-xl-9 col-lg-10 col-md-11">

			<div class="card bg-dark border-secondary shadow-lg rounded-4">

				<div class="card-header bg-dark border-secondary py-3">

					<h3 class="text-white fw-bold mb-1">
						<i class="bi bi-film text-primary me-2"></i>
						Add New Movie
					</h3>

					<p class="text-secondary mb-0">
						Add a movie to the recommendation system.
					</p>

				</div>

				<div class="card-body p-4">

					<form id="addMovieForm" enctype="multipart/form-data">

						<div class="row g-4">

							<!-- Movie Title -->
							<div class="col-lg-6 col-md-6 col-12">

								<label class="form-label text-light">
									Movie Title
								</label>

								<input
									type="text"
									id="title"
									class="form-control bg-dark text-white border-secondary"
									required>

							</div>

							<!-- Director -->
							<div class="col-lg-6 col-md-6 col-12">

								<label class="form-label text-light">
									Director
								</label>

								<input
									type="text"
									id="director"
									class="form-control bg-dark text-white border-secondary"
									required>

							</div>

							<!-- Release Year -->
							<div class="col-lg-4 col-md-6 col-sm-6 col-12">

								<label class="form-label text-light">
									Release Year
								</label>

								<input
									type="number"
									id="releaseYear"
									class="form-control bg-dark text-white border-secondary"
									required>

							</div>

							<!-- Duration -->
							<div class="col-lg-4 col-md-6 col-sm-6 col-12">

								<label class="form-label text-light">
									Duration (Minutes)
								</label>

								<input
									type="number"
									id="duration"
									class="form-control bg-dark text-white border-secondary"
									required>

							</div>

							<!-- Language -->
							<div class="col-lg-4 col-md-12 col-12">

								<label class="form-label text-light">
									Language
								</label>

								<select
									id="languageId"
									class="form-select bg-dark text-white border-secondary"
									required>

									<option value="">
										Select Language
									</option>

								</select>

							</div>

							<!-- Poster Upload -->
							<div class="col-lg-5 col-md-12 col-12">

								<label class="form-label text-light">
									Movie Poster
								</label>

								<input
									type="file"
									id="poster"
									class="form-control bg-dark text-white border-secondary"
									accept="image/png,image/jpeg,image/webp">

								<small class="text-secondary">
									JPG, PNG or WEBP
								</small>

							</div>

							<!-- Poster Preview -->
							<div class="col-lg-7 col-md-12 col-12">

								<label class="form-label text-light">
									Poster Preview
								</label>

								<div
									class="border border-secondary rounded text-center p-3 preview-box">

									<img
										id="posterPreview"
										src="${pageContext.request.contextPath}/assets/images/no-poster.png"
										class="img-fluid rounded">

								</div>

							</div>

							<!-- Trailer -->
							<div class="col-12">

								<label class="form-label text-light">
									Trailer URL
								</label>

								<input
									type="url"
									id="trailerUrl"
									class="form-control bg-dark text-white border-secondary">

							</div>

							<!-- Description -->
							<div class="col-12">

								<label class="form-label text-light">
									Description
								</label>

								<textarea
									id="description"
									rows="5"
									class="form-control bg-dark text-white border-secondary"
									required></textarea>

							</div>

						</div>

						<hr class="border-secondary my-4">

						<div class="row g-3">

							<div class="col-md-6 col-12">

								<button
									type="reset"
									class="btn btn-outline-light w-100">

									Clear

								</button>

							</div>

							<div class="col-md-6 col-12">

								<button
									type="submit"
									class="btn btn-primary w-100">

									<i class="bi bi-plus-circle me-2"></i>

									Add Movie

								</button>

							</div>

						</div>

					</form>

				</div>

			</div>

		</div>

	</div>

</div>

<style>

/* =======================
   Layout
======================= */

.container{
	max-width:1200px;
}

/* =======================
   Card
======================= */

.card{
	width:100%;
	margin:auto;
	border-radius:18px;
	border:1px solid rgba(255,255,255,.12)!important;
	overflow:hidden;
}

/* =======================
   Header
======================= */

.card-header{
	padding:1.5rem 2rem;
}

.card-body{
	padding:2rem;
}

/* =======================
   Labels
======================= */

.form-label{
	font-weight:500;
	margin-bottom:.45rem;
}

/* =======================
   Inputs
======================= */

.form-control,
.form-select{
	background:#1f1f1f;
	color:#fff;
	border:1px solid #495057;
	min-height:48px;
	border-radius:10px;
}

.form-control:focus,
.form-select:focus{
	background:#1f1f1f;
	color:#fff;
	border-color:#0d6efd;
	box-shadow:0 0 0 .15rem rgba(13,110,253,.25);
}

textarea.form-control{
	min-height:150px;
	resize:vertical;
}

/* =======================
   Preview
======================= */

.preview-box{
	height:300px;
	display:flex;
	align-items:center;
	justify-content:center;
	border:1px solid #495057;
	border-radius:12px;
}

#posterPreview{
	max-height:250px;
	max-width:100%;
	object-fit:contain;
}

/* =======================
   Buttons
======================= */

.btn{
	height:46px;
	border-radius:10px;
	font-weight:500;
}

/* =======================
   Responsive
======================= */

@media (max-width:992px){

	.container{
		max-width:100%;
	}

	.card-header{
		padding:1.25rem;
	}

	.card-body{
		padding:1.5rem;
	}

	.preview-box{
		height:240px;
	}

}

@media (max-width:768px){

	.container{
		padding-left:18px;
		padding-right:18px;
	}

	.card{
		border-radius:16px;
	}

	h3{
		font-size:1.4rem;
	}

	.card-body{
		padding:1.25rem;
	}

	.preview-box{
		height:210px;
	}

	#posterPreview{
		max-height:180px;
	}

}

@media (max-width:576px){

	.container{
		padding-left:12px;
		padding-right:12px;
	}

	.card-header,
	.card-body{
		padding:1rem;
	}

	h3{
		font-size:1.2rem;
	}

	.form-control,
	.form-select{
		font-size:14px;
	}

	.preview-box{
		height:180px;
	}

	#posterPreview{
		max-height:150px;
	}

}
</style>