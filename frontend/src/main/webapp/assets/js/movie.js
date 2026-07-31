document.addEventListener("DOMContentLoaded", () => {

    loadAllMovies();

});

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
    catch(error){

        console.error(error);

    }

}

function renderMovieCards(movies, containerId){

    const container =
        document.getElementById(containerId);

    container.innerHTML = "";

    if(movies.length === 0){

        container.innerHTML = `
            <div class="col-12 text-center text-white">
                No movies available.
            </div>
        `;

        return;
    }

    movies.forEach(movie => {

        container.innerHTML += `

        <div class="col-lg-3 col-md-6 col-sm-6">

            <div class="card movie-card h-100">

			<img
			    src="${API.BASE_URL}${movie.posterUrl}"
			    class="movie-poster card-img-top"
			    alt="${movie.title}">

                <div class="card-body d-flex flex-column">

                    <h5 class="movie-title">

                        ${movie.title}

                    </h5>

                    <div class="movie-info mb-2">

                        <i class="bi bi-calendar"></i>

                        ${movie.releaseYear}

                    </div>

                    <div class="movie-info mb-2">

                        <i class="bi bi-clock"></i>

                        ${movie.duration} min

                    </div>

                    <div class="movie-info mb-2">

                        <i class="bi bi-person"></i>

                        ${movie.director}

                    </div>

                    <div class="movie-info mb-3">

                        <i class="bi bi-translate"></i>

                        ${movie.language}

                    </div>

                    <div class="rating mb-3">

                        ⭐ ${movie.averageRating}

                    </div>

                    <button
                        class="btn btn-danger mt-auto"
                        onclick="viewMovie(${movie.movieId})">

                        View Details

                    </button>

                </div>

            </div>

        </div>

        `;

    });

}

function viewMovie(movieId){

    window.location.href =
        "movieDetails.jsp?id=" + movieId;

}

async function loadLatestMovies(){

    try{

        const response = await fetch(

            API.BASE_URL + API.MOVIES.LATEST,

            {
                credentials:"include"
            }

        );

        if(!response.ok){

            throw new Error("Unable to load latest movies.");

        }

        const movies = await response.json();

        renderLatestMovies(movies);

    }
    catch(error){

        console.error(error);

    }

}

function renderLatestMovies(movies){

    const container =
        document.getElementById("latestMoviesContainer");

    container.innerHTML = "";

    movies.forEach((movie,index)=>{

        container.innerHTML += `

        <div class="trending-card">

            <div class="poster-wrapper">

                <div class="rank">

                    ${index+1}

                </div>

				<img
				    src="${API.BASE_URL}${movie.posterUrl}"
				    alt="${movie.title}">

            </div>

            <div class="movie-details">

                <div class="movie-name">

                    ${movie.title}

                </div>

                <div class="movie-meta">

                    ${movie.releaseYear}

                    •

                    ${movie.duration} min

                </div>

                <div class="movie-rating">

                    ⭐ ${movie.averageRating}

                </div>

            </div>

        </div>

        `;

    });

}