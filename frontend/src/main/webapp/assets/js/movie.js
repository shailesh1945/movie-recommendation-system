document.addEventListener("DOMContentLoaded", () => {

    const movieTableBody =
        document.getElementById("movieTableBody");

    const latestMoviesContainer =
        document.getElementById("latestMoviesContainer");


    // ==========================================
    // MANAGE MOVIES PAGE
    // ==========================================

    if (movieTableBody) {

        console.log("Manage Movies page detected.");

        loadAllMovies();

    }


    // ==========================================
    // HOME PAGE - LATEST MOVIES
    // ==========================================

    if (latestMoviesContainer) {

        console.log("Home page detected.");

        loadLatestMovies();

    }

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

        const response = await apiFetch(
            API.BASE_URL + API.MOVIES.ALL,
            {
                method: "GET"
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

///////   Delete Movies //////////////////////

async function deleteMovie(movieId) {

    const confirmDelete = confirm(
        "Are you sure you want to delete this movie?"
    );

    if (!confirmDelete) {
        return;
    }

    try {

        const response = await apiFetch(
            API.BASE_URL + API.ADMIN_MOVIES.DELETE + movieId,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {

            let message = "Unable to delete movie.";

            try {
                const errorData = await response.json();

                if (errorData.message) {
                    message = errorData.message;
                }
            } catch (e) {
                // Ignore JSON parsing error
            }

            throw new Error(message);
        }

        const result = await response.json();

        console.log("Delete response:", result);

        alert(
            result.message || "Movie deleted successfully."
        );

        // Reload movie table
        loadAllMovies();

    } catch (error) {

        console.error("Error deleting movie:", error);

        alert(
            error.message || "Unable to delete movie."
        );
    }
}

////////////////// View Movie JS //////////////////////////
async function viewMovie(movieId) {

    try {

        // Open movie details page
        window.location.href =
            "movieDetails.jsp?movieId=" + movieId;

    } catch (error) {

        console.error("Error opening movie details:", error);

        alert("Unable to open movie details.");
    }
}
/*
async function viewMovie(movieId) {

    try {

        const response = await fetch(
            API.BASE_URL + API.ADMIN_MOVIES.DETAILS + movieId,
            {
                method: "GET",
                credentials: "include"
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load movie details.");
        }

        const movie = await response.json();

        console.log("Movie Details:", movie);

        // Fill movie details in modal
        document.getElementById("viewMovieId").textContent =
            movie.movieId ?? "-";

        document.getElementById("viewMovieTitle").textContent =
            movie.title ?? "-";

        document.getElementById("viewMovieDirector").textContent =
            movie.director ?? "-";

        document.getElementById("viewMovieReleaseYear").textContent =
            movie.releaseYear ?? "-";

        document.getElementById("viewMovieLanguage").textContent =
            movie.language ?? "-";

        document.getElementById("viewMovieRating").textContent =
            movie.averageRating ?? "-";

        document.getElementById("viewMovieDescription").textContent =
            movie.description ?? "-";

        // Poster
        const poster = document.getElementById("viewMoviePoster");

        if (movie.posterUrl) {
            poster.src = movie.posterUrl;
        } else {
            poster.src = "/images/default-movie-poster.png";
        }

        // Show Bootstrap modal
        const modalElement =
            document.getElementById("viewMovieModal");

        const modal =
            bootstrap.Modal.getOrCreateInstance(modalElement);

        modal.show();

    } catch (error) {

        console.error("Error loading movie:", error);

        alert("Unable to load movie details.");
    }
}
*/


///     edit movie call /////////////

function editMovie(movieId) {

    window.location.href =
        "editMovie.jsp?movieId=" + movieId;

}

////////////////////////   LATEST MOVIES  ///////////////////////////////////

async function loadLatestMovies() {

    const container =
        document.getElementById("latestMoviesContainer");

    if (!container) {
        console.log("latestMoviesContainer not found.");
        return;
    }

    try {

        console.log(
            "Fetching latest movies:",
            API.BASE_URL + API.MOVIES.LATEST
        );


        const response = await apiFetch(
            API.BASE_URL + API.MOVIES.LATEST,
            {
                method: "GET"
            }
        );

        console.log(
            "Latest movies response status:",
            response.status
        );

        if (!response.ok) {

            throw new Error(
                "Unable to load latest movies. HTTP " +
                response.status
            );

        }

        const result =
            await response.json();

        console.log(
            "Latest movies API response:",
            result
        );


        // Supports either:
        //
        // [
        //   {...},
        //   {...}
        // ]
        //
        // OR:
        //
        // {
        //   data: [...]
        // }

        const movies =
            Array.isArray(result)
                ? result
                : result.data;


        if (!Array.isArray(movies)) {

            throw new Error(
                "Invalid latest movies response."
            );

        }


        renderLatestMovies(movies);

    }
    catch (error) {

        console.error(
            "Error loading latest movies:",
            error
        );

        container.innerHTML = `
            <div class="col-12 text-center text-danger py-5">

                <i class="bi bi-exclamation-triangle fs-1"></i>

                <p class="mt-3">
                    Unable to load latest movies.
                </p>

            </div>
        `;

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


//================ Search Movie========================

async function searchMovies() {

    try {

        const title =
            document.getElementById("searchMovie")
                .value
                .trim();


        const request = {

            title: title

        };


        console.log("Search request:", request);


        const response = await apiFetch(
			
			API.BASE_URL +
			            API.MOVIES.SEARCH, {
            
				method: "POST",
            
				body: formData
        });

        if (!response.ok) {

            throw new Error(
                "Unable to search movies."
            );

        }


        const movies =
            await response.json();


        console.log(
            "Search results:",
            movies
        );


        renderMovieTable(movies);


    } catch (error) {

        console.error(
            "Error searching movies:",
            error
        );


        const tbody =
            document.getElementById(
                "movieTableBody"
            );


        tbody.innerHTML = `
            <tr>
                <td
                    colspan="6"
                    class="text-center text-danger py-4">

                    <i class="bi bi-exclamation-triangle me-2"></i>

                    Unable to search movies.

                </td>
            </tr>
        `;

    }

}

function clearMovieSearch() {

    document.getElementById("searchMovie").value = "";

    loadAllMovies();

}