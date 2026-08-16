document.addEventListener("DOMContentLoaded", function () {

    loadEveryMovies();

});


// =================================================
// REFRESH WHEN RETURNING FROM MOVIE DETAILS
// =================================================

window.addEventListener("pageshow", function () {

    const updatedMovieId =
        sessionStorage.getItem("movieRatingUpdated");

    if (updatedMovieId) {

        console.log(
            "Rating updated for movie:",
            updatedMovieId
        );

        sessionStorage.removeItem(
            "movieRatingUpdated"
        );

        loadEveryMovies();

    }

});


// =================================================
// LOAD ALL MOVIES
// =================================================

async function loadEveryMovies() {

    const container =
        document.getElementById(
            "allmoviecontainer"
        );

    if (!container) {

        console.error(
            "allmoviecontainer not found."
        );

        return;
    }


    // =============================================
    // Loading UI
    // =============================================

    container.innerHTML = `
        <div class="col-12 text-center py-5">

            <div
                class="spinner-border text-danger"
                role="status">
            </div>

            <p class="text-white mt-3">
                Loading movies...
            </p>

        </div>
    `;


    try {

        // =========================================
        // GET ALL MOVIES
        // =========================================

        const response =
            await apiFetch(
                API.BASE_URL +
                API.MOVIES.USER_MOVIES,
                {
                    method: "GET"
                }
            );


        // =========================================
        // HTTP ERROR
        // =========================================

        if (!response.ok) {

            let message =
                "Unable to load movies.";

            try {

                const error =
                    await response.json();

                if (error.message) {

                    message =
                        error.message;

                }

            } catch (e) {

                console.error(
                    "Unable to parse error response:",
                    e
                );

            }

            throw new Error(message);

        }


        // =========================================
        // RESPONSE
        // =========================================

        const result =
            await response.json();


        console.log(
            "All movies response:",
            result
        );


        // =========================================
        // EXTRACT MOVIES
        // =========================================

        let movies = [];


        if (Array.isArray(result)) {

            movies = result;

        }

        else if (
            result &&
            Array.isArray(result.data)
        ) {

            movies = result.data;

        }

        else if (
            result &&
            Array.isArray(result.content)
        ) {

            movies = result.content;

        }


        console.log(
            "Movies:",
            movies
        );


        // =========================================
        // DISPLAY
        // =========================================

        displayMovies(movies);


    } catch (error) {

        console.error(
            "Load movies error:",
            error
        );


        showMoviesError(
            error.message ||
            "Unable to load movies."
        );

    }

}


// =================================================
// DISPLAY MOVIES
// =================================================

function displayMovies(movies) {

    const container =
        document.getElementById(
            "allmoviecontainer"
        );


    if (!container) {

        return;

    }


    // =============================================
    // NO MOVIES
    // =============================================

    if (
        !Array.isArray(movies) ||
        movies.length === 0
    ) {

        container.innerHTML = `

            <div class="col-12 text-center py-5">

                <i
                    class="bi bi-film text-danger"
                    style="font-size: 3rem;">
                </i>

                <h4 class="text-white mt-3">
                    No Movies Found
                </h4>

                <p class="text-secondary">
                    There are no movies available.
                </p>

            </div>

        `;

        return;

    }


    // =============================================
    // CLEAR CONTAINER
    // =============================================

    container.innerHTML = "";


    // =============================================
    // CREATE CARDS
    // =============================================

    movies.forEach(function (movie) {

        const card =
            createMovieCard(movie);

        container.appendChild(card);

    });

}


// =================================================
// CREATE MOVIE CARD
// =================================================

function createMovieCard(movie) {

    const col =
        document.createElement("div");


    col.className =
        "col-xl-3 col-lg-4 col-md-6 col-sm-6";


    // =============================================
    // MOVIE ID
    // =============================================

    const movieId =
        movie.movieId ??
        movie.id;


    // =============================================
    // TITLE
    // =============================================

    const title =
        movie.title ||
        "Unknown Movie";


    // =============================================
    // AVERAGE RATING
    // =============================================

    let averageRating =
        movie.averageRating;


    /*
     * Convert null / undefined / empty
     * values to 0.
     */

    if (
        averageRating === null ||
        averageRating === undefined ||
        averageRating === ""
    ) {

        averageRating = 0;

    }


    /*
     * Make sure rating is numeric.
     */

    averageRating =
        Number(averageRating);


    if (Number.isNaN(averageRating)) {

        averageRating = 0;

    }


    /*
     * Display one decimal place.
     *
     * Example:
     *
     * 0   -> 0.0
     * 3   -> 3.0
     * 3.5 -> 3.5
     */

    averageRating =
        averageRating.toFixed(1);


    // =============================================
    // RELEASE YEAR
    // =============================================

    const releaseYear =
        movie.releaseYear ??
        movie.year ??
        "";


    // =============================================
    // POSTER
    // =============================================

    const poster =
        movie.posterUrl
            ? (
                movie.posterUrl.startsWith("http")
                    ? movie.posterUrl
                    : API.BASE_URL +
                      movie.posterUrl
              )
            : CONTEXT_PATH +
              "/assets/images/default-movie.jpg";


    // =============================================
    // GENRE
    // =============================================

    const genre =
        movie.genreName ??
        movie.genre ??
        movie.genres ??
        "";


    // =============================================
    // LANGUAGE
    // =============================================

    const language =
        movie.languageName ??
        movie.language ??
        movie.languages ??
        "";


    // =============================================
    // CARD HTML
    // =============================================

    col.innerHTML = `

        <div
            class="card movie-card h-100 bg-dark rounded shadow"
            data-movie-id="${escapeHtml(movieId)}"
        >

            <!-- Poster -->

            <div class="position-relative">

                <img
                    src="${escapeHtml(poster)}"
                    class="card-img-top movie-poster"
                    alt="${escapeHtml(title)}"

                    onerror="
                        this.onerror=null;
                        this.src='${CONTEXT_PATH}/assets/images/default-movie.png';
                    "
                >


                <!-- Average Rating -->

                <span
                    class="
                        position-absolute
                        top-0
                        end-0
                        badge
                        bg-danger
                        m-2
                    "
                >

                    <i class="bi bi-star-fill text-warning"></i>

                    ${escapeHtml(averageRating)}

                </span>

            </div>


            <!-- Card Body -->

            <div class="card-body">

                <h5 class="card-title text-white">

                    ${escapeHtml(title)}

                </h5>


                ${
                    genre
                        ? `

                            <p class="card-text mb-1 text-white">

                                <i class="bi bi-film me-1"></i>

                                ${escapeHtml(
                                    formatValue(genre)
                                )}

                            </p>

                          `
                        : ""
                }


                ${
                    language
                        ? `

                            <p class="card-text mb-1 text-white">

                                <i class="bi bi-globe me-1"></i>

                                ${escapeHtml(
                                    formatValue(language)
                                )}

                            </p>

                          `
                        : ""
                }


                ${
                    releaseYear
                        ? `

                            <p class="card-text text-light">

                                <i class="bi bi-calendar me-1"></i>

                                ${escapeHtml(
                                    releaseYear
                                )}

                            </p>

                          `
                        : ""
                }

            </div>

        </div>

    `;


    // =============================================
    // MOVIE CARD CLICK
    // =============================================

    if (
        movieId !== undefined &&
        movieId !== null
    ) {

        const movieCard =
            col.querySelector(
                ".movie-card"
            );


        movieCard.style.cursor =
            "pointer";


        movieCard.addEventListener(
            "click",
            function () {

                window.location.href =
                    "./movie-details.jsp?movieId=" +
                    encodeURIComponent(
                        movieId
                    );

            }
        );

    }


    return col;

}


// =================================================
// FORMAT VALUE
// =================================================

function formatValue(value) {

    if (
        value === null ||
        value === undefined
    ) {

        return "";

    }


    if (Array.isArray(value)) {

        return value.join(", ");

    }


    return String(value);

}


// =================================================
// ESCAPE HTML
// =================================================

function escapeHtml(value) {

    if (
        value === null ||
        value === undefined
    ) {

        return "";

    }


    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");

}


// =================================================
// ERROR UI
// =================================================

function showMoviesError(message) {

    const container =
        document.getElementById(
            "allmoviecontainer"
        );


    if (!container) {

        return;

    }


    container.innerHTML = `

        <div class="col-12">

            <div class="alert alert-danger text-center">

                <i class="bi bi-exclamation-triangle me-2"></i>

                ${escapeHtml(
                    message ||
                    "Unable to load movies."
                )}

            </div>

        </div>

    `;

}