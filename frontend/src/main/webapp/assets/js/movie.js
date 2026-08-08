document.addEventListener("DOMContentLoaded", () => {

	loadAllMovies();
	loadLatestMovies();

});
/*
async function loadAllMovies() {

	try {

		const response = await fetch(
			API.BASE_URL + API.MOVIES.ALL,
			{
				credentials: "include"
			}
		);

		if (!response.ok) {

			throw new Error("Unable to load movies.");

		}

		const movies = await response.json();

		renderMovieCards(
			movies,
			"recommendedMoviesContainer"
		);

	}
	catch (error) {

		console.error(error);

	}

}

function renderMovieCards(movies, containerId) {

	const container =
		document.getElementById(containerId);

	container.innerHTML = "";

	if (movies.length === 0) {

		container.innerHTML = `
            <div class="col-12 text-center text-white">
                No movies available.
            </div>
        `;

		return;
	}

	movies.forEach(movie => {

		container.innerHTML += `

		<div class="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2">

		    <div class="card movie-card">
				<!--
		        <img
		            src="${API.BASE_URL}${movie.posterUrl}"
		            class="movie-poster card-img-top"
		            alt="${movie.title}">
				-->

		        <div class="card-body">

		            <h6 class="movie-title text-truncate">

		                ${movie.title}

		            </h6>

		            <div class="movie-info">

		                <i class="bi bi-calendar"></i>

		                ${movie.releaseYear}

		            </div>

		            <div class="movie-info">

		                <i class="bi bi-clock"></i>

		                ${movie.duration} min

		            </div>

		            <div class="movie-info">

		                <i class="bi bi-person"></i>

		                ${movie.director}

		            </div>

		            <div class="movie-info">

		                <i class="bi bi-translate"></i>

		                ${movie.language}

		            </div>

		            <div class="rating">

		                ⭐ ${movie.averageRating}

		            </div>

		            <button
		                class="btn btn-danger btn-sm w-100 mt-2"
		                onclick="viewMovie(${movie.movieId})">

		                View Details

		            </button>

		        </div>

		    </div>

		</div>

		`;

	});

}
*/

async function loadAllMovies() {

    try {

        const response = await fetch(
            API.BASE_URL + API.MOVIES.ALL,
            {
                method: "GET",
                credentials: "include"
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load movies.");
        }

        const movies = await response.json();

        console.log("Movies:", movies);

        renderMovieTable(movies);

    } catch (error) {

        console.error("Error loading movies:", error);

        const tbody = document.getElementById("movieTableBody");

        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-danger py-4">
                    <i class="bi bi-exclamation-triangle me-2"></i>
                    Unable to load movies.
                </td>
            </tr>
        `;
    }
}


function renderMovieTable(movies) {

    const tbody = document.getElementById("movieTableBody");

    tbody.innerHTML = "";

    if (!movies || movies.length === 0) {

        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-secondary py-4">
                    No movies found.
                </td>
            </tr>
        `;

        return;
    }


    movies.forEach(movie => {

        const row = document.createElement("tr");

        row.innerHTML = `

            <td>
                ${movie.movieId ?? "-"}
            </td>

            <td>
                <strong class="text-white">
                    ${movie.title ?? "-"}
                </strong>
            </td>

            <td>
                ${movie.director ?? "-"}
            </td>

            <td>
                ${movie.releaseYear ?? "-"}
            </td>

            <td>
                ${movie.language ?? "-"}
            </td>

            <td class="text-center">

                <button
                    type="button"
                    class="btn btn-sm btn-info me-1"
                    onclick="viewMovie(${movie.movieId})">

                    <i class="bi bi-eye"></i>
                    View

                </button>


                <button
                    type="button"
                    class="btn btn-sm btn-warning me-1"
                    onclick="editMovie(${movie.movieId})">

                    <i class="bi bi-pencil"></i>
                    Edit

                </button>


                <button
                    type="button"
                    class="btn btn-sm btn-danger"
                    onclick="deleteMovie(${movie.movieId})">

                    <i class="bi bi-trash"></i>
                    Delete

                </button>

            </td>

        `;

        tbody.appendChild(row);

    });
}




function viewMovie(movieId) {

	window.location.href =
		"movieDetails.jsp?id=" + movieId;

}

async function loadLatestMovies() {

	try {

		const response = await fetch(

			API.BASE_URL + API.MOVIES.LATEST,

			{
				credentials: "include"
			}

		);

		if (!response.ok) {

			throw new Error("Unable to load latest movies.");

		}

		const movies = await response.json();

		renderLatestMovies(movies);

	}
	catch (error) {

		console.error(error);

	}

}

function renderLatestMovies(movies) {

    const container =
        document.getElementById("latestMoviesContainer");

    if (!container) {
        console.error("latestMoviesContainer not found");
        return;
    }

    container.innerHTML = "";

    if (!movies || movies.length === 0) {

        container.innerHTML = `
            <div class="col-12 text-center text-secondary py-5">
                <i class="bi bi-film fs-1"></i>
                <p class="mt-3">
                    No latest movies available.
                </p>
            </div>
        `;

        return;
    }

    movies.forEach((movie, index) => {

        container.innerHTML += `

            <div class="col-6 col-sm-4 col-md-3 col-lg-3 col-xl-2">

                <div class="trending-card h-100">

                    <div class="poster-wrapper">

                      <!-- <div class="rank">
                            ${index + 1}
                        </div>
					-->

                        <img
                            src="${API.BASE_URL}${movie.posterUrl}"
                            alt="${movie.title}"
                            class="movie-poster"
                        >

                    </div>

                    <div class="movie-details">

                        <div class="movie-name text-white">
                            ${movie.title ?? "-"}
                        </div>

                        <div class="movie-meta text-white">

                            ${movie.releaseYear ?? "-"}

                            <br>

                            ${movie.duration ?? "-"} min

                        </div>

                        <div class="movie-rating">

                            ⭐ ${movie.averageRating ?? "0"}

                        </div>

                    </div>

                </div>

            </div>

        `;

    });

}