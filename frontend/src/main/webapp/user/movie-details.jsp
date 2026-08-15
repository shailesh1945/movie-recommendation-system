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

.rating {
	color: #fbbf24;
	font-size: 20px;
}

.description {
	color: #d1d5db;
	line-height: 1.7;
}
</style>

</head>

<body class="bg-dark">
	<jsp:include page="../component/userNavbar.jsp"/>
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


					<!-- Trailer -->

					<div id="trailerContainer" class="mt-4 d-none">

						<a id="movieTrailer" href="#" target="_blank"
							class="btn btn-danger"> <i class="bi bi-play-circle"></i>

							Watch Trailer

						</a>

					</div>

				</div>

			</div>

		</div>

	</div>
	<jsp:include page="../component/footer.jsp"/>

	<script>

const API = {

    BASE_URL: "http://localhost:8081",

    ADMIN_MOVIES: {
        DETAILS: "/api/movies/"
    }

};


// Get movie ID from URL

const urlParams =
    new URLSearchParams(window.location.search);

const movieId =
    urlParams.get("movieId");


// Load movie when page opens

document.addEventListener(
    "DOMContentLoaded",
    function () {

        if (!movieId) {

            showError(
                "Movie ID is missing."
            );

            return;
        }

        loadMovieDetails(movieId);

    }
);


// Load movie details

async function loadMovieDetails(movieId) {

    try {

        
        const response = await apiFetch(

        		API.BASE_URL +
                API.ADMIN_MOVIES.DETAILS +
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

		/*
        const movie =
            await response.json();


        console.log(
            "Movie Details:",
            movie
        );


        displayMovie(movie);
		*/
        const movie = await response.json();

        console.log("Movie Details:", movie);
        console.log("POSTER URL =", movie.posterUrl);

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


// Display movie

function displayMovie(movie) {

    // Movie ID

    document.getElementById(
        "movieId"
    ).textContent =
        movie.movieId ?? "-";


    // Title

    document.getElementById(
        "movieTitle"
    ).textContent =
        movie.title ?? "-";


    // Director

    document.getElementById(
        "movieDirector"
    ).textContent =
        movie.director ?? "-";


    // Release year

    document.getElementById(
        "movieReleaseYear"
    ).textContent =
        movie.releaseYear ?? "-";


    // Duration

    document.getElementById(
        "movieDuration"
    ).textContent =
        movie.duration ?? "-";


    // Language
	const language =
    movie.languageName ??
    movie.language ??
    movie.language_name ??
    "-";

	document.getElementById(
    "movieLanguage"
	).textContent = language;
	
	/*
    document.getElementById(
        "movieLanguage"
    ).textContent =
        movie.languageName ?? "-";
	*/

    // Rating

    document.getElementById(
        "movieRating"
    ).textContent =
        movie.averageRating ?? "0";


    // Description

    document.getElementById(
        "movieDescription"
    ).textContent =
        movie.description ?? "No description available.";


    // Poster

    const poster =
    document.getElementById("moviePoster");

	if (
    	movie.posterUrl &&
    	movie.posterUrl.trim() !== ""
	) {

    	poster.src =
        API.BASE_URL + movie.posterUrl;

    	console.log(
        	"Poster loaded from:",
        	poster.src
    	);

	} else {

    	poster.src =
        	"${pageContext.request.contextPath}/images/default-movie-poster.png";
	}


    // Trailer

    if (
        movie.trailerUrl &&
        movie.trailerUrl.trim() !== ""
    ) {

        const trailerContainer =
            document.getElementById(
                "trailerContainer"
            );

        const trailer =
            document.getElementById(
                "movieTrailer"
            );


        trailer.href =
            movie.trailerUrl;


        trailerContainer.classList.remove(
            "d-none"
        );

    }


    // Hide loading

    document.getElementById(
        "loadingMessage"
    ).classList.add(
        "d-none"
    );


    // Show details

    document.getElementById(
        "movieDetails"
    ).classList.remove(
        "d-none"
    );

}


// Show error

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


// Back button

function goBack() {

    window.history.back();

}

</script>

<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>

</body>
</html>