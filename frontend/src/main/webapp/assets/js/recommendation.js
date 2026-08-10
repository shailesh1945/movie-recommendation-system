document.addEventListener(
	"DOMContentLoaded",
	function() {

		loadRecommendations();

	}
);


// =================================================
// LOAD RECOMMENDATIONS
// =================================================

async function loadRecommendations() {

	const container =
		document.getElementById(
			"recommendationContainer"
		);


	if (!container) {

		console.error(
			"recommendationContainer not found."
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
                Finding movies for you...
            </p>

        </div>

    `;


	try {

		// =========================================
		// GET /api/recommendations
		// =========================================

		const response = await fetch(

			API.BASE_URL +
			API.RECOMMENDATIONS.LIST,

			{

				method: "GET",

				headers: {

					"Content-Type":
						"application/json"

				},

				credentials: "include"

			}

		);


		// =========================================
		// HTTP Error
		// =========================================

		if (!response.ok) {

			let message =
				"Unable to load recommendations.";

			try {

				const error =
					await response.json();

				if (error.message) {

					message =
						error.message;

				}

			} catch (e) {

				console.error(
					"Could not parse error response:",
					e
				);

			}

			throw new Error(message);

		}


		// =========================================
		// Read Response
		// =========================================

		const result =
			await response.json();


		console.log(
			"Recommendation API response:",
			result
		);


		// =========================================
		// Extract Movies
		// =========================================

		let movies;


		/*
		 * Case 1:
		 *
		 * [
		 *    {...},
		 *    {...}
		 * ]
		 */

		if (Array.isArray(result)) {

			movies = result;

		}


		/*
		 * Case 2:
		 *
		 * {
		 *     "data": [...]
		 * }
		 */

		else if (
			result &&
			Array.isArray(result.data)
		) {

			movies = result.data;

		}


		/*
		 * Case 3:
		 *
		 * {
		 *     "content": [...]
		 * }
		 */

		else if (
			result &&
			Array.isArray(result.content)
		) {

			movies = result.content;

		}


		/*
		 * Unknown response
		 */

		else {

			movies = [];

		}


		// =========================================
		// Display Movies
		// =========================================

		displayRecommendations(
			movies
		);


	} catch (error) {

		console.error(
			"Recommendation error:",
			error
		);


		showRecommendationError(
			error.message
		);

	}

}


// =================================================
// DISPLAY RECOMMENDATIONS
// =================================================

function displayRecommendations(movies) {

    const container =
        document.getElementById(
            "recommendationContainer"
        );

    if (!container) {
        return;
    }

    // =============================================
    // No Movies
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
                    No recommendations found
                </h4>

                <p class="text-secondary">
                    Try changing your preferences.
                </p>

                <a
                    href="${CONTEXT_PATH}/user/recommendationPage.jsp"
                    class="btn btn-danger mt-2">

                    Change Preferences

                </a>

            </div>

        `;

        return;
    }

    // =============================================
    // Clear Container
    // =============================================

    container.innerHTML = "";

    // =============================================
    // Create Movie Cards
    // =============================================

    movies.forEach(function(movie) {

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
	// Movie Data
	// =============================================

	const movieId =
		movie.id ??
		movie.movieId;


	const title =
		movie.title ||
		"Unknown Movie";


	const rating =
		movie.rating ??
		movie.averageRating ??
		"N/A";


	const releaseYear =
		movie.releaseYear ??
		movie.year ??
		"";


	const poster = movie.posterUrl
		? (
			movie.posterUrl.startsWith("http")
				? movie.posterUrl
				: API.BASE_URL + movie.posterUrl
		)
		: CONTEXT_PATH + "/assets/images/default-movie.jpg";


	const genre =
		movie.genre ||
		movie.genres ||
		"";


	const language =
		movie.language ||
		movie.languages ||
		"";


	// =============================================
	// Movie Card
	// =============================================

	col.innerHTML = `
	

	    <div class="card movie-card h-100 bg-dark rounded shadow">

	        <div class="position-relative">

	            <img
	                src="${escapeHtml(poster)}"
	                class="card-img-top movie-poster"
	                alt="${escapeHtml(title)}"
	                onerror="this.onerror=null; this.src='${CONTEXT_PATH}/assets/images/default-movie.png';"
	            >

	            <span
	                class="
	                    position-absolute
	                    top-0
	                    end-0
	                    badge
	                    bg-danger
	                    m-2
	                ">

	                ⭐ ${escapeHtml(rating)}

	            </span>

	        </div>

	        <div class="card-body">

	            <h5 class="card-title text-white">

	                ${escapeHtml(title)}

	            </h5>

	            ${genre
	                ? `
	                    <p class="card-text  mb-1 text-white">

	                        <i class="bi bi-film me-1"></i>

	                        ${escapeHtml(
	                            formatValue(genre)
	                        )}

	                    </p>
	                  `
	                : ""
	            }

	            ${language
	                ? `
	                    <p class="card-text  mb-1 text-white">

	                        <i class="bi bi-globe me-1"></i>

	                        ${escapeHtml(
	                            formatValue(language)
	                        )}

	                    </p>
	                  `
	                : ""
	            }

	            ${releaseYear
	                ? `
	                    <p class="card-text text-light">

	                        <i class="bi bi-calendar me-1"></i>

	                        ${escapeHtml(releaseYear)}

	                    </p>
	                  `
	                : ""
	            }

	        </div>

	    </div>

	`;


	// =============================================
	// Click Movie Card
	// =============================================

	if (movieId !== undefined && movieId !== null) {

		const movieCard =
			col.querySelector(".movie-card");

		movieCard.style.cursor =
			"pointer";


		movieCard.addEventListener(
			"click",
			function() {

				window.location.href =
					CONTEXT_PATH +
					"/movie-details?id=" +
					encodeURIComponent(
						movieId
					);

			}
		);

	}


	return col;

}


// =================================================
// FORMAT ARRAY / VALUE
// =================================================

function formatValue(value) {

	if (Array.isArray(value)) {

		return value.join(", ");

	}

	return value;

}


// =================================================
// ERROR UI
// =================================================

function showRecommendationError(message) {

	const container =
		document.getElementById(
			"recommendationContainer"
		);


	if (!container) {

		return;

	}


	container.innerHTML = `

        <div class="col-12 text-center py-5">

            <i
                class="
                    bi
                    bi-exclamation-circle
                    text-danger
                "
                style="font-size: 3rem;">
            </i>


            <h4
                class="text-white mt-3">

                Unable to load recommendations

            </h4>


            <p
                class="text-secondary">

                ${escapeHtml(
		message ||
		"Something went wrong."
	)}

            </p>


            <button
                type="button"
                class="btn btn-danger"
                onclick="loadRecommendations()">

                <i
                    class="bi bi-arrow-clockwise me-1">
                </i>

                Try Again

            </button>

        </div>

    `;

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
		.replace(
			/&/g,
			"&amp;"
		)
		.replace(
			/</g,
			"&lt;"
		)
		.replace(
			/>/g,
			"&gt;"
		)
		.replace(
			/"/g,
			"&quot;"
		)
		.replace(
			/'/g,
			"&#039;"
		);

}