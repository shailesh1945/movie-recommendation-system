<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>User Movie Details</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
body {
	background-color: #111827;
	color: white;
	min-height: 100vh;
}

.movie-container {
	max-width: 1100px;
	margin: 50px auto;
}

.movie-card {
	background-color: #1f2937;
	border-radius: 15px;
	padding: 30px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
}

.poster {
	width: 100%;
	max-width: 350px;
	height: 500px;
	object-fit: cover;
	border-radius: 12px;
	background-color: #374151;
}

.movie-card:hover {
	transform: translateY(-5px);
}

.movie-title {
	font-size: 32px;
	font-weight: 700;
	margin-bottom: 20px;
}

.movie-info {
	margin-bottom: 12px;
}

.movie-label {
	color: #9ca3af;
	font-weight: 600;
}

.rating-star {
	font-size: 32px;
	color: #6b7280;
	cursor: pointer;
	transition: 0.2s;
}

.rating-star:hover {
	color: #fbbf24;
	transform: scale(1.1);
}

.rating-star.active {
	color: #fbbf24;
}

#ratingStars i {
	color: #6c757d;
	transition: 0.2s ease;
}

#ratingStars i:hover {
	transform: scale(1.15);
}

#ratingStars i.active {
	color: #ffc107;
}

.rating {
	color: #fbbf24;
	font-size: 20px;
}

.description {
	color: #d1d5db;
	line-height: 1.7;
}

#watchlistButton {
	min-width: 190px;
	transition: all 0.2s ease;
}

#watchlistButton:hover {
	transform: translateY(-2px);
}

#watchlistButton.added {
	background-color: #f59e0b;
	border-color: #f59e0b;
	color: #111827;
}

#watchlistButton.loading {
	pointer-events: none;
	opacity: 0.7;
}
</style>

</head>

<body class="bg-dark">
	<jsp:include page="../component/userNavbar.jsp" />
	<div class="container movie-container">

		<div class="mb-4">

			<button type="button" class="btn btn-secondary" onclick="goBack()">

				<i class="bi bi-arrow-left"></i> Back

			</button>

		</div>


		<!-- Loading -->

		<div id="loadingMessage" class="text-center py-5">

			<div class="spinner-border text-light" role="status"></div>

			<p class="mt-3">Loading movie details...</p>

		</div>


		<!-- Error -->

		<div id="errorMessage" class="alert alert-danger d-none">

			<i class="bi bi-exclamation-triangle"></i> <span id="errorText">
				Unable to load movie details. </span>

		</div>


		<!-- Movie Details -->

		<div id="movieDetails" class="movie-card d-none text-white">

			<div class="row g-4">

				<!-- Poster -->

				<div class="col-md-4 text-center">

					<img id="moviePoster" class="poster"
						src="/images/default-movie-poster.png" alt="Movie Poster">

				</div>


				<!-- Information -->

				<div class="col-md-8">

					<h1 id="movieTitle" class="movie-title">-</h1>


					<div class="movie-info d-none">

						<span class="movie-label"> Movie ID: </span> <span id="movieId">
							- </span>

					</div>


					<div class="movie-info">

						<span class="movie-label"> Director: </span> <span
							id="movieDirector"> - </span>

					</div>


					<div class="movie-info">

						<span class="movie-label"> Release Year: </span> <span
							id="movieReleaseYear"> - </span>

					</div>


					<div class="movie-info">

						<span class="movie-label"> Duration: </span> <span
							id="movieDuration"> - </span> minutes

					</div>


					<div class="movie-info">

						<span class="movie-label"> Language: </span> <span
							id="movieLanguage"> - </span>

					</div>


					<div class="movie-info">

						<span class="movie-label"> Rating: </span> <span class="rating">

							<i class="bi bi-star-fill"></i> <span id="movieRating"> -
						</span>

						</span>

					</div>


					<hr>


					<h5>Description</h5>

					<p id="movieDescription" class="description">-</p>


					<!-- User Rating -->

					<!-- User Rating -->

					<div class="mt-4 p-4 rounded"
						style="background: #111827; border: 1px solid #374151;">

						<h5 class="mb-3">
							<i class="bi bi-star-fill text-warning me-2"></i> Rate This Movie
						</h5>

						<!-- 1 to 5 Stars -->

						<div id="ratingStars" class="d-flex gap-2 mb-2">

							<i class="bi bi-star rating-star" data-rating="1"></i> <i
								class="bi bi-star rating-star" data-rating="2"></i> <i
								class="bi bi-star rating-star" data-rating="3"></i> <i
								class="bi bi-star rating-star" data-rating="4"></i> <i
								class="bi bi-star rating-star" data-rating="5"></i>

						</div>


						<!-- Selected Rating -->

						<div class="text-secondary mb-3">

							Your rating: <span id="selectedRating" class="text-warning">
								Not rated </span>

						</div>


						<!-- Submit -->

						<button type="button" id="submitRatingBtn" class="btn btn-danger"
							onclick="submitRating()">

							<i class="bi bi-star-fill me-1"></i> Submit Rating

						</button>


						<!-- Success/Error Message -->

						<div id="ratingMessage" class="mt-3"></div>

					</div>

					<!-- Trailer -->

					<div id="trailerContainer" class="mt-4 d-none">

						<a id="movieTrailer" href="#" target="_blank"
							class="btn btn-danger"> <i class="bi bi-play-circle"></i>

							Watch Trailer

						</a>

					</div>

					<!-- Watchlist -->
					<div class="mt-3">

						<button id="watchlistButton" type="button"
							class="btn btn-outline-warning">

							<i id="watchlistIcon" class="bi bi-bookmark-plus"></i> <span
								id="watchlistText">Add to My List</span>

						</button>

					</div>

					<hr class="my-4">

				</div>

			</div>

		</div>

	</div>
	<jsp:include page="../component/footer.jsp" />

	<script>

let selectedRating = 0;

const API = {

    BASE_URL: "http://localhost:8081",

    MOVIES: {
        DETAILS: "/api/movies/"
    },

    RATINGS: {
        MY_RATING: "/api/ratings/movie/",
        SAVE: "/api/ratings/movie/"
    }

};


// =================================================
// GET MOVIE ID
// =================================================

const urlParams =
    new URLSearchParams(
        window.location.search
    );

const movieId =
    urlParams.get("movieId");


// =================================================
// PAGE LOAD
// =================================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        if (!movieId) {

            showError(
                "Movie ID is missing."
            );

            return;
        }


        // Setup rating stars

        setupRatingStars();


        // Load movie details

        loadMovieDetails(movieId);


        // Load user's existing rating

        loadMyRating(movieId);

    }
);


// =================================================
// LOAD MOVIE DETAILS
// =================================================

async function loadMovieDetails(movieId) {

    try {

        const response =
            await apiFetch(

                API.BASE_URL +
                API.MOVIES.DETAILS +
                movieId,

                {
                    method: "GET"
                }

            );


        if (!response.ok) {

            throw new Error(
                "Unable to load movie details."
            );

        }


        const movie =
            await response.json();


        console.log(
            "Movie Details:",
            movie
        );


        console.log(
            "POSTER URL =",
            movie.posterUrl
        );


        displayMovie(movie);


    } catch (error) {

        console.error(
            "Error loading movie:",
            error
        );


        showError(
            error.message ||
            "Unable to load movie details."
        );

    }

}


// =================================================
// RATING
// =================================================


// -------------------------------------------------
// Load Current User Rating
// -------------------------------------------------

async function loadMyRating(movieId) {

    try {

        const response =
            await apiFetch(

                API.BASE_URL +
                API.RATINGS.MY_RATING +
                movieId +
                "/my-rating",

                {
                    method: "GET"
                }

            );


        if (!response.ok) {

            console.error(
                "Unable to load user rating."
            );

            return;
        }


        const result =
            await response.json();


        console.log(
            "My rating:",
            result
        );


        if (
            result.success &&
            result.data
        ) {

            selectedRating =
                Number(
                    result.data.rating
                );


            updateRatingStars();

        }


    } catch (error) {

        console.error(
            "Rating loading error:",
            error
        );

    }

}


// -------------------------------------------------
// Setup Rating Stars
// -------------------------------------------------

function setupRatingStars() {

    const stars =
        document.querySelectorAll(
            "#ratingStars i"
        );


    if (!stars.length) {

        console.warn(
            "Rating stars not found."
        );

        return;
    }


    stars.forEach(
        function (star) {

            star.addEventListener(
                "click",
                function () {

                    selectedRating =
                        Number(
                            this.dataset.rating
                        );


                    updateRatingStars();

                }
            );


            // Optional hover effect

            star.addEventListener(
                "mouseenter",
                function () {

                    const hoverRating =
                        Number(
                            this.dataset.rating
                        );


                    highlightRating(
                        hoverRating
                    );

                }
            );

        }
    );


    const ratingStars =
        document.getElementById(
            "ratingStars"
        );


    if (ratingStars) {

        ratingStars.addEventListener(
            "mouseleave",
            function () {

                updateRatingStars();

            }
        );

    }

}


// -------------------------------------------------
// Highlight Stars
// -------------------------------------------------

function highlightRating(rating) {

    const stars =
        document.querySelectorAll(
            "#ratingStars i"
        );


    stars.forEach(
        function (star) {

            const value =
                Number(
                    star.dataset.rating
                );


            if (value <= rating) {

                star.classList.remove(
                    "bi-star"
                );


                star.classList.add(
                    "bi-star-fill"
                );

            } else {

                star.classList.remove(
                    "bi-star-fill"
                );


                star.classList.add(
                    "bi-star"
                );

            }

        }
    );

}


// -------------------------------------------------
// Update Selected Rating UI
// -------------------------------------------------

function updateRatingStars() {

    const stars =
        document.querySelectorAll(
            "#ratingStars i"
        );


    stars.forEach(
        function (star) {

            const value =
                Number(
                    star.dataset.rating
                );


            if (value <= selectedRating) {

                star.classList.remove(
                    "bi-star"
                );


                star.classList.add(
                    "bi-star-fill",
                    "active"
                );

            } else {

                star.classList.remove(
                    "bi-star-fill",
                    "active"
                );


                star.classList.add(
                    "bi-star"
                );

            }

        }
    );


    const selectedRatingElement =
        document.getElementById(
            "selectedRating"
        );


    if (selectedRatingElement) {

        if (selectedRating > 0) {

            selectedRatingElement.textContent =
                selectedRating + " / 5";

        } else {

            selectedRatingElement.textContent =
                "Not rated";

        }

    }

}


// -------------------------------------------------
// Submit Rating
// -------------------------------------------------

async function submitRating() {

    if (selectedRating === 0) {

        alert(
            "Please select a rating first."
        );

        return;
    }


    const button =
        document.getElementById(
            "submitRatingBtn"
        );


    try {

        if (button) {

            button.disabled = true;

            button.innerHTML = `
                Saving...
                <span class="spinner-border spinner-border-sm ms-2"></span>
            `;

        }


        const response =
            await apiFetch(

                API.BASE_URL +
                API.RATINGS.SAVE +
                movieId +
                "?rating=" +
                selectedRating,

                {
                    method: "POST"
                }

            );


        const result =
            await response.json();


        console.log(
            "Rating response:",
            result
        );


        if (
            !response.ok ||
            !result.success
        ) {

            throw new Error(
                result.message ||
                "Failed to save rating."
            );

        }


        // Success message

        const ratingMessage =
            document.getElementById(
                "ratingMessage"
            );


        if (ratingMessage) {

            ratingMessage.innerHTML = `
                <div class="alert alert-success">
                    <i class="bi bi-check-circle me-2"></i>
                    Rating saved successfully!
                </div>
            `;

        }


        // Reload movie details
        // so average rating is updated

        await loadMovieDetails(
            movieId
        );


        // Reload user's rating

        await loadMyRating(
            movieId
        );


    } catch (error) {

        console.error(
            "Rating error:",
            error
        );


        const ratingMessage =
            document.getElementById(
                "ratingMessage"
            );


        if (ratingMessage) {

            ratingMessage.innerHTML = `
                <div class="alert alert-danger">
                    <i class="bi bi-exclamation-triangle me-2"></i>
                    ${error.message}
                </div>
            `;

        }

    } finally {

        if (button) {

            button.disabled = false;

            button.innerHTML = `
                <i class="bi bi-star-fill"></i>
                Submit Rating
            `;

        }

    }

}


// =================================================
// DISPLAY MOVIE
// =================================================

function displayMovie(movie) {

    // -------------------------------------------------
    // Movie ID
    // -------------------------------------------------

    document.getElementById(
        "movieId"
    ).textContent =
        movie.movieId ?? "-";


    // -------------------------------------------------
    // Title
    // -------------------------------------------------

    document.getElementById(
        "movieTitle"
    ).textContent =
        movie.title ?? "-";


    // -------------------------------------------------
    // Director
    // -------------------------------------------------

    document.getElementById(
        "movieDirector"
    ).textContent =
        movie.director ?? "-";


    // -------------------------------------------------
    // Release Year
    // -------------------------------------------------

    document.getElementById(
        "movieReleaseYear"
    ).textContent =
        movie.releaseYear ?? "-";


    // -------------------------------------------------
    // Duration
    // -------------------------------------------------

    document.getElementById(
        "movieDuration"
    ).textContent =
        movie.duration ?? "-";


    // -------------------------------------------------
    // Language
    // -------------------------------------------------

    const language =
        movie.languageName ??
        movie.language ??
        movie.language_name ??
        "-";


    document.getElementById(
        "movieLanguage"
    ).textContent =
        language;


    // -------------------------------------------------
    // Average Rating
    // -------------------------------------------------

    document.getElementById(
        "movieRating"
    ).textContent =
        movie.averageRating ?? "0";


    // -------------------------------------------------
    // Description
    // -------------------------------------------------

    document.getElementById(
        "movieDescription"
    ).textContent =
        movie.description ??
        "No description available.";


    // -------------------------------------------------
    // Poster
    // -------------------------------------------------

    const poster =
        document.getElementById(
            "moviePoster"
        );


    if (
        movie.posterUrl &&
        movie.posterUrl.trim() !== ""
    ) {

        poster.src =
            API.BASE_URL +
            movie.posterUrl;


        console.log(
            "Poster loaded from:",
            poster.src
        );

    } else {

        poster.src =
            "${pageContext.request.contextPath}/images/default-movie-poster.png";

    }


    // -------------------------------------------------
    // Trailer
    // -------------------------------------------------

    const trailerContainer =
        document.getElementById(
            "trailerContainer"
        );


    const trailer =
        document.getElementById(
            "movieTrailer"
        );


    if (
        movie.trailerUrl &&
        movie.trailerUrl.trim() !== ""
    ) {

        trailer.href =
            movie.trailerUrl;


        trailerContainer.classList.remove(
            "d-none"
        );

    } else {

        trailerContainer.classList.add(
            "d-none"
        );

    }


    // -------------------------------------------------
    // Hide Loading
    // -------------------------------------------------

    document.getElementById(
        "loadingMessage"
    ).classList.add(
        "d-none"
    );


    // -------------------------------------------------
    // Show Movie Details
    // -------------------------------------------------

    document.getElementById(
        "movieDetails"
    ).classList.remove(
        "d-none"
    );

}


// =================================================
// SHOW ERROR
// =================================================

function showError(message) {

    document.getElementById(
        "loadingMessage"
    ).classList.add(
        "d-none"
    );


    document.getElementById(
        "errorMessage"
    ).classList.remove(
        "d-none"
    );


    document.getElementById(
        "errorText"
    ).textContent =
        message;

}


// =================================================
// BACK BUTTON
// =================================================

function goBack() {

    window.history.back();

}

</script>

	<<<<<<< HEAD

	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/movie.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/user.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/watchlist.js"></script>
	=======
	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>
	>>>>>>> e3af109 (solve issue of rating module)

</body>
</html>