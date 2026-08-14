document.addEventListener(
	"DOMContentLoaded",
	function() {

		loadEveryMovies();

	}
);


// =================================================
// LOAD ALL MOVIES
// =================================================

async function loadEveryMovies() {

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
        document.getElementById("allmoviecontainer");

    if (!container) {
        console.error("Movies Container not found");
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

            <div class="col-6 col-sm-6 col-md-4 col-lg-3 ">

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
