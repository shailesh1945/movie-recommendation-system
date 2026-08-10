
/*const API = {

    BASE_URL: "http://localhost:8081",

    ADMIN_MOVIES: {

        DETAILS: "/api/admin/movies/",

        UPDATE: "/api/admin/movies/"

    },

    LANGUAGES: {

        ALL: "/api/languages"

    }

};
*/

// ======================================================
// GET MOVIE ID FROM URL
// ======================================================

const urlParams =
    new URLSearchParams(window.location.search);

const movieId =
    urlParams.get("movieId");


// ======================================================
// PAGE LOAD
// ======================================================

document.addEventListener(
    "DOMContentLoaded",
    async function () {

        if (!movieId) {

            showError(
                "Movie ID is missing."
            );

            return;
        }


        // Load languages and movie

        await loadLanguages();

        await loadMovie(movieId);


        // Poster preview

        document
            .getElementById("poster")
            .addEventListener(
                "change",
                previewPoster
            );


        // Form submit

        document
            .getElementById("editMovieForm")
            .addEventListener(
                "submit",
                updateMovie
            );

    }
);


// ======================================================
// LOAD LANGUAGES
// ======================================================

async function loadLanguages() {

    const languageSelect =
        document.getElementById("languageId");

    try {

        const response =
            await fetch(
                API.BASE_URL +
                API.LANGUAGES.ALL,
                {
                    method: "GET",
                    credentials: "include"
                }
            );


        if (!response.ok) {

            throw new Error(
                "Unable to load languages."
            );

        }


        const languages =
            await response.json();


        console.log(
            "Languages:",
            languages
        );


        languageSelect.innerHTML =
            `<option value="">
                Select Language
            </option>`;


        languages.forEach(
            language => {

                const option =
                    document.createElement("option");


                option.value =
                    language.languageId;


                option.textContent =
                    language.languageName;


                languageSelect.appendChild(
                    option
                );

            }
        );


    } catch (error) {

        console.error(
            "Error loading languages:",
            error
        );

        showError(
            "Unable to load languages."
        );

    }
}


// ======================================================
// LOAD MOVIE
// ======================================================

async function loadMovie(movieId) {

    try {

        const response =
            await fetch(
                API.BASE_URL +
                API.ADMIN_MOVIES.DETAILS +
                movieId,
                {
                    method: "GET",
                    credentials: "include"
                }
            );


        if (!response.ok) {

            throw new Error(
                "Unable to load movie."
            );

        }


        const movie =
            await response.json();


        console.log(
            "Movie to edit:",
            movie
        );


        populateMovieForm(movie);


    } catch (error) {

        console.error(
            "Error loading movie:",
            error
        );


        showError(
            error.message ||
            "Unable to load movie."
        );

    }
}


// ======================================================
// POPULATE FORM
// ======================================================

function populateMovieForm(movie) {

    // Movie ID

    document
        .getElementById("movieId")
        .value =
        movie.movieId ?? "";


    // Title

    document
        .getElementById("title")
        .value =
        movie.title ?? "";


    // Director

    document
        .getElementById("director")
        .value =
        movie.director ?? "";


    // Release Year

    document
        .getElementById("releaseYear")
        .value =
        movie.releaseYear ?? "";


    // Duration

    document
        .getElementById("duration")
        .value =
        movie.duration ?? "";


    // Language

    document
        .getElementById("languageId")
        .value =
        movie.languageId ?? "";


    // Trailer

    document
        .getElementById("trailerUrl")
        .value =
        movie.trailerUrl ?? "";


    // Description

    document
        .getElementById("description")
        .value =
        movie.description ?? "";


    // Existing poster

    const posterPreview =
        document.getElementById(
            "posterPreview"
        );


    if (
        movie.posterUrl &&
        movie.posterUrl.trim() !== ""
    ) {

        posterPreview.src =
            API.BASE_URL +
            movie.posterUrl;

    } else {

        posterPreview.src =
            "${pageContext.request.contextPath}/assets/images/no-poster.png";

    }


    // Poster fallback

    posterPreview.onerror =
        function () {

            this.onerror = null;

            this.src =
                "${pageContext.request.contextPath}/assets/images/no-poster.png";

        };


    // Show form

    document
        .getElementById("editLoading")
        .classList.add("d-none");


    document
        .getElementById("editFormContainer")
        .classList.remove("d-none");

}


// ======================================================
// POSTER PREVIEW
// ======================================================

function previewPoster(event) {

    const file =
        event.target.files[0];

    if (!file) {

        return;

    }


    // Check image

    if (!file.type.startsWith("image/")) {

        alert(
            "Please select an image file."
        );

        event.target.value = "";

        return;

    }


    const reader =
        new FileReader();


    reader.onload =
        function (e) {

            document
                .getElementById(
                    "posterPreview"
                )
                .src =
                e.target.result;

        };


    reader.readAsDataURL(file);

}


// ======================================================
// UPDATE MOVIE
// ======================================================

async function updateMovie(event) {

    event.preventDefault();


    const updateButton =
        document.getElementById(
            "updateMovieBtn"
        );


    try {

        updateButton.disabled = true;

        updateButton.innerHTML = `
            <span
                class="spinner-border spinner-border-sm me-2">
            </span>
            Updating...
        `;


        // Create FormData

        const formData =
            new FormData();


        // Movie fields

        formData.append(
            "title",
            document.getElementById("title").value.trim()
        );


        formData.append(
            "director",
            document.getElementById("director").value.trim()
        );


        formData.append(
            "releaseYear",
            document.getElementById("releaseYear").value
        );


        formData.append(
            "duration",
            document.getElementById("duration").value
        );


        formData.append(
            "languageId",
            document.getElementById("languageId").value
        );


        formData.append(
            "trailerUrl",
            document.getElementById("trailerUrl").value.trim()
        );


        formData.append(
            "description",
            document.getElementById("description").value.trim()
        );


        // ==============================================
        // POSTER
        // ==============================================

        const posterInput =
            document.getElementById("poster");


        if (
            posterInput.files &&
            posterInput.files.length > 0
        ) {

            formData.append(
                "poster",
                posterInput.files[0]
            );

        }


        console.log(
            "Updating movie:",
            movieId
        );


        // ==============================================
        // PUT REQUEST
        // ==============================================

        const response =
            await fetch(
                API.BASE_URL +
                API.ADMIN_MOVIES.UPDATE +
                movieId,
                {
                    method: "PUT",

                    credentials: "include",

                    body: formData
                }
            );


        if (!response.ok) {

            let errorMessage =
                "Unable to update movie.";

            try {

                const errorData =
                    await response.json();

                if (errorData.message) {

                    errorMessage =
                        errorData.message;

                }

            } catch (e) {

                // Ignore JSON parsing error

            }

            throw new Error(
                errorMessage
            );

        }


        const result =
            await response.json();


        console.log(
            "Update response:",
            result
        );


        // Success

        showSuccess(
            "Movie updated successfully."
        );


        // Wait and go back

        setTimeout(
            function () {

                window.location.href =
					"movieDetails.jsp?movieId=" + movieId;

            },
            1200
        );


    } catch (error) {

        console.error(
            "Update movie error:",
            error
        );


        showError(
            error.message ||
            "Unable to update movie."
        );


        updateButton.disabled = false;

        updateButton.innerHTML = `
            <i class="bi bi-save me-2"></i>
            Update Movie
        `;

    }

}


// ======================================================
// ERROR
// ======================================================

function showError(message) {

    document
        .getElementById("editLoading")
        .classList.add("d-none");


    document
        .getElementById("editError")
        .classList.remove("d-none");


    document
        .getElementById("editErrorText")
        .textContent =
        message;

}


// ======================================================
// SUCCESS
// ======================================================

function showSuccess(message) {

    document
        .getElementById("editSuccess")
        .classList.remove("d-none");


    document
        .getElementById("editSuccessText")
        .textContent =
        message;

}


// ======================================================
// BACK
// ======================================================

function goBack() {

    window.history.back();

}