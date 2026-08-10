<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row justify-content-center">

    <div class="col-12 col-xl-9 col-lg-10 col-md-11">

        <div class="card bg-dark border-secondary shadow-lg rounded-4">

            <!-- Header -->
            <div class="card-header bg-warning border-secondary py-3">

                <div class="d-flex justify-content-between align-items-center">

                    <div>
                        <h3 class="text-white fw-bold mb-1">
                            <i class="bi bi-pencil-square text-warning me-2"></i>
                            Edit Movie
                        </h3>

                        <p class="text-secondary mb-0">
                            Update movie information.
                        </p>
                    </div>

                    <button
                        type="button"
                        class="btn btn-secondary"
                        onclick="goBack()">

                        <i class="bi bi-arrow-left"></i>
                        Back

                    </button>

                </div>

            </div>


            <!-- Loading -->

            <div
                id="editLoading"
                class="text-center py-5">

                <div
                    class="spinner-border text-light"
                    role="status">
                </div>

                <p class="text-secondary mt-3 mb-0">
                    Loading movie...
                </p>

            </div>


            <!-- Error -->

            <div
                id="editError"
                class="alert alert-danger m-4 d-none">

                <i class="bi bi-exclamation-triangle me-2"></i>

                <span id="editErrorText">
                    Unable to load movie.
                </span>

            </div>


            <!-- Success -->

            <div
                id="editSuccess"
                class="alert alert-success m-4 d-none">

                <i class="bi bi-check-circle me-2"></i>

                <span id="editSuccessText">
                    Movie updated successfully.
                </span>

            </div>


            <!-- Form -->

            <div
                id="editFormContainer"
                class="card-body p-4 d-none">

                <form
                    id="editMovieForm"
                    enctype="multipart/form-data">

                    <div class="row g-4">


                        <!-- Movie ID -->

                        <div class="col-12">

                            <label class="form-label text-secondary">
                                Movie ID
                            </label>

                            <input
                                type="text"
                                id="movieId"
                                class="form-control bg-dark text-secondary border-secondary"
                                readonly>

                        </div>


                        <!-- Movie Title -->

                        <div class="col-lg-6 col-md-6 col-12">

                            <label
                                class="form-label text-light">
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

                            <label
                                class="form-label text-light">
                                Director
                            </label>

                            <input
                                type="text"
                                id="director"
                                class="form-control bg-dark text-white border-secondary"
                                required>

                        </div>


                        <!-- Release Year -->

                        <div class="col-lg-4 col-md-6 col-12">

                            <label
                                class="form-label text-light">
                                Release Year
                            </label>

                            <input
                                type="number"
                                id="releaseYear"
                                class="form-control bg-dark text-white border-secondary"
                                required>

                        </div>


                        <!-- Duration -->

                        <div class="col-lg-4 col-md-6 col-12">

                            <label
                                class="form-label text-light">
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

                            <label
                                class="form-label text-light">
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


                        <!-- Poster -->

                        <div class="col-lg-5 col-md-12 col-12">

                            <label
                                class="form-label text-light">
                                Movie Poster
                            </label>

                            <input
                                type="file"
                                id="poster"
                                class="form-control bg-dark text-white border-secondary"
                                accept="image/png,image/jpeg,image/webp">

                            <small class="text-secondary">
                                Select a new poster only if you want to replace
                                the existing poster.
                            </small>

                        </div>


                        <!-- Poster Preview -->

                        <div class="col-lg-7 col-md-12 col-12">

                            <label
                                class="form-label text-light">
                                Poster Preview
                            </label>

                            <div
                                class="border border-secondary rounded text-center p-3 preview-box">

                                <img
                                    id="posterPreview"
                                    src="${pageContext.request.contextPath}/assets/images/no-poster.png"
                                    class="img-fluid rounded"
                                    alt="Movie Poster Preview"
                                    style="max-height: 350px;">

                            </div>

                        </div>


                        <!-- Trailer -->

                        <div class="col-12">

                            <label
                                class="form-label text-light">
                                Trailer URL
                            </label>

                            <input
                                type="url"
                                id="trailerUrl"
                                class="form-control bg-dark text-white border-secondary"
                                placeholder="https://www.youtube.com/watch?v=...">

                        </div>


                        <!-- Description -->

                        <div class="col-12">

                            <label
                                class="form-label text-light">
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


                    <!-- Buttons -->

                    <div class="row g-3">

                        <div class="col-md-6 col-12">

                            <button
                                type="button"
                                class="btn btn-outline-light w-100"
                                onclick="goBack()">

                                <i class="bi bi-x-circle me-2"></i>
                                Cancel

                            </button>

                        </div>


                        <div class="col-md-6 col-12">

                            <button
                                type="submit"
                                id="updateMovieBtn"
                                class="btn btn-warning w-100">

                                <i class="bi bi-save me-2"></i>
                                Update Movie

                            </button>

                        </div>

                    </div>

                </form>

            </div>

        </div>

    </div>

</div>
<script src="../assets/js/editMovie.js"></script>
<script src="../assets/movie.js"></script>
<script src="../assets/config.js"></script>