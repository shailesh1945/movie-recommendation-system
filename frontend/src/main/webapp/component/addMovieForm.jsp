<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid px-4 py-4">

	<div class="card bg-dark border-secondary shadow rounded-4">

		<div class="card-header bg-dark border-secondary">

			<h3 class="text-white fw-bold mb-1">
				<i class="bi bi-film me-2 text-primary"></i>
				Add New Movie
			</h3>

			<p class="text-secondary mb-0">
				Add a movie to the recommendation system.
			</p>

		</div>

		<div class="card-body">

			<form id="addMovieForm" enctype="multipart/form-data">

				<div class="row g-4">

					<div class="col-md-6">

						<label class="form-label text-light">
							Movie Title
						</label>

						<input
							type="text"
							class="form-control bg-dark text-white border-secondary"
							id="title"
							required>

					</div>

					<div class="col-md-6">

						<label class="form-label text-light">
							Director
						</label>

						<input
							type="text"
							class="form-control bg-dark text-white border-secondary"
							id="director"
							required>

					</div>

					<div class="col-md-4">

						<label class="form-label text-light">
							Release Year
						</label>

						<input
							type="number"
							class="form-control bg-dark text-white border-secondary"
							id="releaseYear"
							required>

					</div>

					<div class="col-md-4">

						<label class="form-label text-light">
							Duration (Minutes)
						</label>

						<input
							type="number"
							class="form-control bg-dark text-white border-secondary"
							id="duration"
							required>

					</div>

					<div class="col-md-4">

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

					<div class="col-md-5">

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

					<div class="col-md-7">

						<label class="form-label text-light">
							Poster Preview
						</label>

						<div
							class="border border-secondary rounded text-center p-2">

							<img
								id="posterPreview"
								src="${pageContext.request.contextPath}/assets/images/no-poster.png"
								class="img-fluid rounded"
								style="max-height:300px; object-fit:contain;">

						</div>

					</div>

					<div class="col-12">

						<label class="form-label text-light">
							Trailer URL
						</label>

						<input
							type="url"
							id="trailerUrl"
							class="form-control bg-dark text-white border-secondary">

					</div>

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

				<div class="text-end">

					<button
						type="reset"
						class="btn btn-outline-light me-2">

						Clear

					</button>

					<button
						type="submit"
						class="btn btn-primary">

						<i class="bi bi-plus-circle me-2"></i>

						Add Movie

					</button>

				</div>

			</form>

		</div>

	</div>

</div>