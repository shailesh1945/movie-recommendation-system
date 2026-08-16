/*
 * ============================================================
 * WATCHLIST.JS
 * ============================================================
 *
 * Handles:
 *
 * 1. Checking whether movie is already in My List
 * 2. Adding movie to My List
 * 3. Removing movie from My List
 * 4. Updating the Watchlist button
 *
 * Authentication is handled by apiFetch().
 *
 * Backend:
 *
 * http://localhost:8081
 *
 * ============================================================
 */



/*
 * ============================================================
 * PAGE LOAD
 * ============================================================
 */

document.addEventListener(
	"DOMContentLoaded",
	function() {

		/*
		 * Get movie ID from URL
		 *
		 * Example:
		 *
		 * movie-details.jsp?movieId=8
		 *
		 * movieId = 8
		 */
		const urlParams =
			new URLSearchParams(
				window.location.search
			);


		const movieId =
			urlParams.get("movieId");


		/*
		 * Get watchlist button
		 */
		const watchlistButton =
			document.getElementById(
				"watchlistButton"
			);


		/*
		 * Check whether button exists
		 */
		if (!watchlistButton) {

			console.warn(
				"Watchlist button not found."
			);

			return;
		}


		/*
		 * Check whether movie ID exists
		 */
		if (!movieId) {

			console.warn(
				"Movie ID not found in URL."
			);

			return;
		}


		/*
		 * Store movie ID in button
		 */
		watchlistButton.dataset.movieId =
			movieId;


		/*
		 * Check current watchlist status
		 */
		checkWatchlist(movieId);


		/*
		 * Handle button click
		 */
		watchlistButton.addEventListener(
			"click",
			function() {

				toggleWatchlist(movieId);

			}
		);

	}
);


/*
 * ============================================================
 * CHECK WATCHLIST
 * ============================================================
 *
 * GET:
 *
 * http://localhost:8081/api/watchlist/check/{movieId}
 *
 * Response:
 *
 * true
 * OR
 * false
 *
 * ============================================================
 */

async function checkWatchlist(movieId) {

	const button =
		document.getElementById(
			"watchlistButton"
		);


	try {

		
		setLoading(true);

		const response =
		    await apiFetch(
		        API.BASE_URL +
		        API.WATCHLIST.CHECK +
		        movieId,
		        {
		            method: "GET"
		        }
		    );


		/*
		 * Unauthorized
		 */
		if (response.status === 401) {

			console.warn(
				"User is not authenticated."
			);

			setWatchlistButton(false);

			return;
		}


		/*
		 * Handle other errors
		 */
		if (!response.ok) {

			const errorText =
				await response.text();

			console.error(
				"Watchlist check failed:",
				response.status,
				errorText
			);

			throw new Error(
				"Unable to check watchlist status."
			);
		}


		/*
		 * Backend returns:
		 *
		 * true
		 *
		 * OR
		 *
		 * false
		 */
		const isAdded =
			await response.json();


		console.log(
			"Watchlist status:",
			isAdded
		);


		/*
		 * Update button
		 */
		setWatchlistButton(
			isAdded
		);


	} catch (error) {

		console.error(
			"Watchlist check error:",
			error
		);


		/*
		 * If check fails,
		 * show Add button.
		 */
		setWatchlistButton(false);


	} finally {

		/*
		 * Enable button
		 */
		setLoading(false);

	}

}


/*
 * ============================================================
 * TOGGLE WATCHLIST
 * ============================================================
 *
 * If movie is NOT in My List:
 *
 * POST
 * http://localhost:8081/api/watchlist/{movieId}
 *
 *
 * If movie IS in My List:
 *
 * DELETE
 * http://localhost:8081/api/watchlist/{movieId}
 *
 * ============================================================
 */

async function toggleWatchlist(movieId) {

	const button =
		document.getElementById(
			"watchlistButton"
		);


	/*
	 * Make sure button exists
	 */
	if (!button) {

		console.error(
			"Watchlist button not found."
		);

		return;
	}


	/*
	 * Determine current state
	 *
	 * data-added="true"
	 *
	 * means movie is already
	 * in My List.
	 */
	const isAdded =
		button.dataset.added === "true";


	/*
	 * Prevent duplicate clicks
	 */
	if (button.disabled) {
		return;
	}


	try {

		/*
		 * Disable button
		 */
		setLoading(true);


		/*
		 * Decide HTTP method
		 *
		 * Not added → POST
		 *
		 * Already added → DELETE
		 */
		const method =
			isAdded
				? "DELETE"
				: "POST";


		console.log(
			"Watchlist request:",
			method,
			movieId
		);

		const endpoint =
		    isAdded
		        ? API.WATCHLIST.REMOVE
		        : API.WATCHLIST.ADD;

		const response =
		    await apiFetch(
		        API.BASE_URL +
		        endpoint +
		        movieId,
		        {
		            method: method
		        }
		    );


		/*
		 * Unauthorized
		 */
		if (response.status === 401) {

			showWatchlistMessage(
				"Please login to use My List.",
				"error"
			);

			return;
		}


		/*
		 * Forbidden
		 */
		if (response.status === 403) {

			showWatchlistMessage(
				"You are not allowed to use My List.",
				"error"
			);

			return;
		}


		/*
		 * Handle API errors
		 */
		if (!response.ok) {

			let errorMessage =
				"Unable to update My List.";


			/*
			 * Try to read backend response
			 */
			try {

				const contentType =
					response.headers.get(
						"content-type"
					);


				/*
				 * JSON response
				 */
				if (
					contentType &&
					contentType.includes(
						"application/json"
					)
				) {

					const errorData =
						await response.json();


					if (
						errorData &&
						errorData.message
					) {

						errorMessage =
							errorData.message;

					}

				}

				/*
				 * Text response
				 */
				else {

					const text =
						await response.text();


					if (text) {

						errorMessage =
							text;

					}

				}

			} catch (error) {

				console.warn(
					"Could not read error response:",
					error
				);

			}


			throw new Error(
				errorMessage
			);
		}


		/*
		 * ====================================================
		 * SUCCESS
		 * ====================================================
		 */


		/*
		 * Movie was not added before
		 *
		 * POST succeeded
		 */
		if (!isAdded) {

			setWatchlistButton(true);


			showWatchlistMessage(
				"Movie added to My List",
				"success"
			);


			console.log(
				"Movie added to My List:",
				movieId
			);

		}


		/*
		 * Movie was already added
		 *
		 * DELETE succeeded
		 */
		else {

			setWatchlistButton(false);


			showWatchlistMessage(
				"Movie removed from My List",
				"success"
			);


			console.log(
				"Movie removed from My List:",
				movieId
			);

		}


	} catch (error) {

		console.error(
			"Watchlist update error:",
			error
		);


		showWatchlistMessage(
			error.message ||
			"Something went wrong. Please try again.",
			"error"
		);


	} finally {

		/*
		 * Enable button
		 */
		setLoading(false);

	}

}


/*
 * ============================================================
 * UPDATE WATCHLIST BUTTON
 * ============================================================
 */

function setWatchlistButton(isAdded) {

	const button =
		document.getElementById(
			"watchlistButton"
		);


	const icon =
		document.getElementById(
			"watchlistIcon"
		);


	const text =
		document.getElementById(
			"watchlistText"
		);


	/*
	 * Button doesn't exist
	 */
	if (!button) {
		return;
	}


	/*
	 * Save current state
	 */
	button.dataset.added =
		String(isAdded);


	/*
	 * ========================================================
	 * MOVIE IS IN MY LIST
	 * ========================================================
	 */

	if (isAdded) {

		/*
		 * Remove outline style
		 */
		button.classList.remove(
			"btn-outline-warning"
		);


		/*
		 * Add warning style
		 */
		button.classList.add(
			"btn-warning"
		);


		/*
		 * Added class
		 */
		button.classList.add(
			"added"
		);


		/*
		 * Change icon
		 */
		if (icon) {

			icon.className =
				"bi bi-bookmark-check-fill";

		}


		/*
		 * Change text
		 */
		if (text) {

			text.textContent =
				" Added to My List";

		}


		/*
		 * Accessibility
		 */
		button.setAttribute(
			"aria-label",
			"Remove from My List"
		);

	}


	/*
	 * ========================================================
	 * MOVIE IS NOT IN MY LIST
	 * ========================================================
	 */

	else {

		/*
		 * Remove warning button
		 */
		button.classList.remove(
			"btn-warning"
		);


		/*
		 * Add outline style
		 */
		button.classList.add(
			"btn-outline-warning"
		);


		/*
		 * Remove added class
		 */
		button.classList.remove(
			"added"
		);


		/*
		 * Change icon
		 */
		if (icon) {

			icon.className =
				"bi bi-bookmark-plus";

		}


		/*
		 * Change text
		 */
		if (text) {

			text.textContent =
				" Add to My List";

		}


		/*
		 * Accessibility
		 */
		button.setAttribute(
			"aria-label",
			"Add to My List"
		);

	}

}


/*
 * ============================================================
 * LOADING STATE
 * ============================================================
 */

function setLoading(isLoading) {

	const button =
		document.getElementById(
			"watchlistButton"
		);


	/*
	 * Button doesn't exist
	 */
	if (!button) {
		return;
	}


	/*
	 * Loading
	 */
	if (isLoading) {

		/*
		 * Disable button
		 */
		button.disabled = true;


		/*
		 * Add loading class
		 */
		button.classList.add(
			"loading"
		);

	}


	/*
	 * Finished loading
	 */
	else {

		/*
		 * Enable button
		 */
		button.disabled = false;


		/*
		 * Remove loading class
		 */
		button.classList.remove(
			"loading"
		);

	}

}


/*
 * ============================================================
 * SHOW WATCHLIST MESSAGE
 * ============================================================
 */

function showWatchlistMessage(
	message,
	type
) {

	/*
	 * Check if message element
	 * already exists.
	 */
	let messageElement =
		document.getElementById(
			"watchlistMessage"
		);


	/*
	 * Create message element
	 * if it doesn't exist.
	 */
	if (!messageElement) {

		messageElement =
			document.createElement(
				"div"
			);


		messageElement.id =
			"watchlistMessage";


		/*
		 * Position
		 */
		messageElement.style.position =
			"fixed";

		messageElement.style.bottom =
			"25px";

		messageElement.style.right =
			"25px";


		/*
		 * Layer
		 */
		messageElement.style.zIndex =
			"9999";


		/*
		 * Size
		 */
		messageElement.style.padding =
			"12px 20px";


		/*
		 * Border
		 */
		messageElement.style.borderRadius =
			"8px";


		/*
		 * Font
		 */
		messageElement.style.fontWeight =
			"600";


		/*
		 * Shadow
		 */
		messageElement.style.boxShadow =
			"0 5px 20px rgba(0,0,0,0.3)";


		/*
		 * Add to body
		 */
		document.body.appendChild(
			messageElement
		);

	}


	/*
	 * Set message text
	 */
	messageElement.textContent =
		message;


	/*
	 * ========================================================
	 * SUCCESS MESSAGE
	 * ========================================================
	 */

	if (type === "success") {

		messageElement.style.backgroundColor =
			"#198754";

		messageElement.style.color =
			"white";

	}


	/*
	 * ========================================================
	 * ERROR MESSAGE
	 * ========================================================
	 */

	else {

		messageElement.style.backgroundColor =
			"#dc3545";

		messageElement.style.color =
			"white";

	}


	/*
	 * Show message
	 */
	messageElement.style.display =
		"block";


	/*
	 * Automatically hide
	 * after 3 seconds.
	 */
	setTimeout(
		function() {

			messageElement.style.display =
				"none";

		},
		3000
	);

}


/*
 * ============================================================
 * GLOBAL FUNCTIONS
 * ============================================================
 *
 * Make functions available to other
 * JavaScript files if required.
 *
 * ============================================================
 */

window.toggleWatchlist =
	toggleWatchlist;


window.checkWatchlist =
	checkWatchlist;