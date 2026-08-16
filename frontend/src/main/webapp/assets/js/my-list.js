/*
 * ============================================================
 * MY-LIST.JS
 * ============================================================
 *
 * Handles:
 *
 * 1. Loading user's watchlist
 * 2. Displaying saved movies
 * 3. Removing movies from My List
 *
 * Authentication:
 *
 * apiFetch() automatically sends the JWT.
 *
 * Backend:
 *
 * http://localhost:8081
 *
 * ============================================================
 */


const WATCHLIST_BASE_URL =
	"http://localhost:8081";


/*
 * ============================================================
 * PAGE LOAD
 * ============================================================
 */

document.addEventListener(
	"DOMContentLoaded",
	function() {

		loadMyList();

	}
);


/*
 * ============================================================
 * LOAD MY LIST
 * ============================================================
 *
 * GET:
 *
 * /api/watchlist
 *
 * Backend determines the logged-in user
 * from JWT.
 *
 * ============================================================
 */

async function loadMyList() {

	const loadingContainer =
		document.getElementById(
			"loadingContainer"
		);

	const movieContainer =
		document.getElementById(
			"movieContainer"
		);

	const emptyContainer =
		document.getElementById(
			"emptyContainer"
		);

	const errorContainer =
		document.getElementById(
			"errorContainer"
		);


	try {

		/*
		 * Show loading
		 */
		loadingContainer.style.display =
			"block";


		/*
		 * Hide previous states
		 */
		movieContainer.innerHTML = "";

		emptyContainer.classList.add(
			"d-none"
		);

		errorContainer.style.display =
			"none";


		/*
		 * Call backend
		 */
		const response =
			await apiFetch(
				WATCHLIST_BASE_URL +
				"/api/watchlist",
				{
					method: "GET"
				}
			);


		/*
		 * Unauthorized
		 */
		if (response.status === 401) {

			showError(
				"Please login to view your My List."
			);

			return;
		}


		/*
		 * Other errors
		 */
		if (!response.ok) {

			const errorText =
				await response.text();

			console.error(
				"My List API error:",
				response.status,
				errorText
			);

			throw new Error(
				"Unable to load your My List."
			);
		}


		/*
		 * Convert response to JSON
		 */
		const movies =
			await response.json();


		console.log(
			"My List movies:",
			movies
		);


		/*
		 * Hide loading
		 */
		loadingContainer.style.display =
			"none";


		/*
		 * Empty list
		 */
		if (
			!movies ||
			movies.length === 0
		) {

			emptyContainer.classList.remove(
				"d-none"
			);

			return;
		}


		/*
		 * Display movies
		 */
		displayMovies(movies);


	} catch (error) {

		console.error(
			"Error loading My List:",
			error
		);


		loadingContainer.style.display =
			"none";


		showError(
			error.message ||
			"Unable to load your My List."
		);

	}

}


/*
 * ============================================================
 * DISPLAY MOVIES
 * ============================================================
 */

function displayMovies(movies) {

	const container =
		document.getElementById(
			"movieContainer"
		);


	container.innerHTML = "";


	movies.forEach(
		function(movie) {

			const movieCard =
				createMovieCard(movie);

			container.insertAdjacentHTML(
				"beforeend",
				movieCard
			);

		}
	);

}


/*
 * ============================================================
 * CREATE MOVIE CARD
 * ============================================================
 */

function createMovieCard(movie) {

	/*
	 * Movie ID
	 */
	const movieId =
		movie.movieId;


	/*
	 * Movie title
	 */
	const title =
		movie.title ||
		"Unknown Movie";


	/*
	 * Director
	 */
	const director =
		movie.director ||
		"Unknown";


	/*
	 * Release year
	 */
	const releaseYear =
		movie.releaseYear ||
		"-";


	/*
	 * Rating
	 */
	const rating =
		movie.averageRating ??
		"0";


	/*
	 * Poster
	 */
	let posterUrl =
		movie.posterUrl;


	/*
	 * Default poster
	 */
	if (
		!posterUrl ||
		posterUrl.trim() === ""
	) {

		posterUrl =
			"/images/default-movie-poster.png";

	}

	/*
	 * If poster URL is relative,
	 * add backend URL.
	 */
	else if (
		posterUrl.startsWith("/")
	) {

		posterUrl =
			WATCHLIST_BASE_URL +
			posterUrl;

	}


	/*
	 * Return Bootstrap card
	 */
	return `

        <div class="col-12
                    col-sm-6
                    col-md-4
                    col-lg-3">

            <div class="movie-card">


                <!-- Poster -->

                <img
                    src="${escapeHtml(posterUrl)}"
                    class="movie-poster"
                    alt="${escapeHtml(title)}"
                    onerror="this.src='/images/default-movie-poster.png'"
                >


                <!-- Body -->

                <div class="movie-card-body">


                    <!-- Title -->

                    <div class="movie-title text-white"
                         title="${escapeHtml(title)}">

                        ${escapeHtml(title)}

                    </div>


                    <!-- Director -->

                    <div class="movie-info">

                        <i class="bi bi-person"></i>

                        ${escapeHtml(director)}

                    </div>


                    <!-- Release year -->

                    <div class="movie-info">

                        <i class="bi bi-calendar"></i>

                        ${escapeHtml(
		String(releaseYear)
	)}

                    </div>


                    <!-- Rating -->

                    <div class="movie-info">

                        <i class="bi bi-star-fill text-warning"></i>

                        ${escapeHtml(
		String(rating)
	)}

                    </div>


                    <!-- Actions -->

                    <div class="d-flex gap-2">


                        <!-- Details -->

						<a
						    href="movie-details.jsp?movieId=${movieId}"
						    class="btn btn-outline-light details-btn">

						    <i class="bi bi-info-circle"></i>

						    Details

						</a>


                        <!-- Remove -->

						<button
						    type="button"
						    class="btn btn-danger remove-btn"
						    onclick="removeFromMyList(${movieId}, this)">

						    <i class="bi bi-trash"></i>

						    Remove

						</button>


                    </div>

                </div>

            </div>

        </div>

    `;

}


/*
 * ============================================================
 * REMOVE FROM MY LIST
 * ============================================================
 *
 * DELETE:
 *
 * /api/watchlist/{movieId}
 *
 * ============================================================
 */

async function removeFromMyList(
	movieId,
	button
) {

	/*
	 * Confirm before deleting
	 */
	const confirmed =
		confirm(
			"Remove this movie from My List?"
		);


	if (!confirmed) {
		return;
	}


	try {

		/*
		 * Disable button
		 */
		if (button) {

			button.disabled = true;

			button.innerHTML = `
                <span
                    class="spinner-border
                           spinner-border-sm"
                    role="status"
                    aria-hidden="true">
                </span>
            `;

		}


		/*
		 * Call backend
		 */
		const response =
			await apiFetch(
				WATCHLIST_BASE_URL +
				`/api/watchlist/${movieId}`,
				{
					method: "DELETE"
				}
			);


		/*
		 * Unauthorized
		 */
		if (response.status === 401) {

			showWatchlistMessage(
				"Please login to continue.",
				"error"
			);

			return;
		}


		/*
		 * Other errors
		 */
		if (!response.ok) {

			const errorText =
				await response.text();

			console.error(
				"Remove movie error:",
				errorText
			);

			throw new Error(
				"Unable to remove movie."
			);
		}


		/*
		 * Success
		 */
		showWatchlistMessage(
			"Movie removed from My List.",
			"success"
		);


		/*
		 * Reload list
		 */
		loadMyList();


	} catch (error) {

		console.error(
			"Remove from My List error:",
			error
		);


		showWatchlistMessage(
			error.message ||
			"Unable to remove movie.",
			"error"
		);


		/*
		 * Re-enable button
		 */
		if (button) {

			button.disabled = false;

			button.innerHTML = `
                <i class="bi bi-trash"></i>
                Remove
            `;

		}

	}

}


/*
 * ============================================================
 * SHOW ERROR
 * ============================================================
 */

function showError(message) {

	const errorContainer =
		document.getElementById(
			"errorContainer"
		);

	const errorText =
		document.getElementById(
			"errorText"
		);


	if (errorText) {

		errorText.textContent =
			message;

	}


	if (errorContainer) {

		errorContainer.style.display =
			"block";

	}

}


/*
 * ============================================================
 * SHOW MESSAGE
 * ============================================================
 */

function showWatchlistMessage(
	message,
	type
) {

	const element =
		document.getElementById(
			"watchlistMessage"
		);


	if (!element) {
		return;
	}


	element.textContent =
		message;


	/*
	 * Success
	 */
	if (type === "success") {

		element.style.backgroundColor =
			"#198754";

		element.style.color =
			"#ffffff";

	}


	/*
	 * Error
	 */
	else {

		element.style.backgroundColor =
			"#dc3545";

		element.style.color =
			"#ffffff";

	}


	element.style.display =
		"block";


	/*
	 * Hide after 3 seconds
	 */
	setTimeout(
		function() {

			element.style.display =
				"none";

		},
		3000
	);

}


/*
 * ============================================================
 * HTML ESCAPE
 * ============================================================
 *
 * Prevents movie data from being
 * inserted as raw HTML.
 *
 * ============================================================
 */

function escapeHtml(value) {

	if (value === null ||
		value === undefined) {

		return "";

	}


	return String(value)
		.replace(/&/g, "&amp;")
		.replace(/</g, "&lt;")
		.replace(/>/g, "&gt;")
		.replace(/"/g, "&quot;")
		.replace(/'/g, "&#039;");

}