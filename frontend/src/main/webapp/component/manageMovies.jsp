<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">

	<div class="card bg-dark border-secondary shadow-lg rounded-4">

		<div class="card-header bg-dark border-secondary rounded-top-4 py-3">

			<div
				class="d-flex justify-content-between align-items-center flex-wrap gap-3">

				<div>

					<h3 class="text-white fw-bold mb-1">

						<i class="bi bi-film text-primary me-2"></i> Manage Movies

					</h3>

					<p class="text-secondary mb-0">View, edit and delete movies.</p>

				</div>

				<a href="${pageContext.request.contextPath}/admin/addMovie.jsp"
					class="btn btn-primary"> <i class="bi bi-plus-circle me-2"></i>

					Add Movie

				</a>

			</div>

		</div>

		<div class="card-body">

			<div class="row g-3 mb-4">

				<div class="col-lg-6 col-md-8 col-12">

					<div class="input-group">

						<input type="text" id="searchMovie"
							class="form-control bg-dark text-white border-secondary"
							placeholder="Search movie by title...">

						<button type="button" class="btn btn-primary"
							onclick="searchMovies()">

							<i class="bi bi-search"></i> Search

						</button>
	
						<button type="button" class="btn btn-secondary"
							onclick="clearMovieSearch()">

							<i class="bi bi-x-circle"></i> Clear

						</button> 

					</div>

				</div>

			</div>

			<div class="table-responsive">

				<table
					class="table table-dark table-hover table-bordered align-middle mb-0">

					<thead class="table-secondary">

						<tr>

							<!-- <th style="min-width: 100px;">Poster</th>-->

							<th style="min-width: 100px;">Id</th>

							<th style="min-width: 180px;">Title</th>

							<th style="min-width: 170px;">Director</th>

							<th style="min-width: 90px;">Year</th>

							<th style="min-width: 120px;">Language</th>

							<!-- <th style="min-width: 90px;">Rating</th> -->

							<th class="text-center" style="min-width: 180px;">Actions</th>

						</tr>

					</thead>

					<tbody id="movieTableBody">

					</tbody>

				</table>

			</div>

		</div>

	</div>

</div>

<script src="${pageContext.request.contextPath}/assets/js/movie.js"></script>