document.addEventListener("DOMContentLoaded", () => {

    const form =
        document.getElementById("addMovieForm");

    if (!form) {
        return;
    }

    loadLanguages();

    loadGenres();

    setupPosterPreview();

    setupAddMovieForm();


});


// INITIALIZE FORM

function setupAddMovieForm() {

    const form =
        document.getElementById("addMovieForm");

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        addMovie
    );
}


/***  LOAD LANGUAGES ***/

async function loadLanguages() {

    try {

        const response = await fetch(
            API.BASE_URL + API.LANGUAGES.ALL
        );

        if (!response.ok) {

            throw new Error("Failed to load languages.");

        }

        const languages = await response.json();

        const select = document.getElementById("languageId");

        select.innerHTML = `
            <option value="">
                Select Language
            </option>
        `;

        languages.forEach(language => {

            select.innerHTML += `
                <option value="${language.languageId}">
                    ${language.languageName}
                </option>
            `;

        });

    }

    catch (error) {

        console.error(error);

        alert("Unable to load languages.");

    }

}


// ==========================================================
// LOAD GENRES
// ==========================================================

async function loadGenres() {

    const container =
        document.getElementById("genreContainer");

    if (!container) {
        return;
    }

    try {

        const response = await fetch(
            API.BASE_URL + API.GENRES.ALL,
            {
                method: "GET",
                credentials: "include"
            }
        );

        if (!response.ok) {

            throw new Error(
                "Failed to load genres."
            );
        }

        const result =
            await response.json();

        console.log(
            "Genres:",
            result
        );

        /*
         * Supports:
         *
         * [
         *   {
         *      genreId: 1,
         *      genreName: "Action"
         *   }
         * ]
         *
         * OR:
         *
         * {
         *   success: true,
         *   data: [...]
         * }
         */

        const genres =
            Array.isArray(result)
                ? result
                : result.data;

        container.innerHTML = "";

        if (!genres || genres.length === 0) {

            container.innerHTML = `
                <div class="text-secondary">
                    No genres available.
                </div>
            `;

            return;
        }

        genres.forEach(genre => {

            const wrapper =
                document.createElement("div");

            wrapper.className =
                "form-check form-check-inline mb-2 me-3";

            wrapper.innerHTML = `
                <input
                    class="form-check-input genre-checkbox"
                    type="checkbox"
                    value="${genre.genreId}"
                    id="genre-${genre.genreId}"
                >

                <label
                    class="form-check-label text-light"
                    for="genre-${genre.genreId}"
                >
                    ${escapeHtml(genre.genreName)}
                </label>
            `;

            container.appendChild(wrapper);

        });

    }
    catch (error) {

        console.error(
            "Error loading genres:",
            error
        );

        container.innerHTML = `
            <div class="text-danger">
                <i class="bi bi-exclamation-triangle me-2"></i>
                Unable to load genres.
            </div>
        `;
    }
}


/*** Poster Preview ***/

function setupPosterPreview() {

    const posterInput = document.getElementById("poster");

    const preview = document.getElementById("posterPreview");

    if (!posterInput || !preview) {

        return;

    }

    posterInput.addEventListener("change", function() {

        const file = this.files[0];

        if (!file) {

            preview.src = "";

            return;

        }

        preview.src = URL.createObjectURL(file);

    });

}

async function addMovie(event) {

    event.preventDefault();

    const formData = new FormData();

    formData.append(
        "title",
        document.getElementById("title").value.trim()
    );

    formData.append(
        "description",
        document.getElementById("description").value.trim()
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
        "director",
        document.getElementById("director").value.trim()
    );

    formData.append(
        "languageId",
        document.getElementById("languageId").value
    );

    formData.append(
        "trailerUrl",
        document.getElementById("trailerUrl").value.trim()
    );

    const selectedGenres =
        document.querySelectorAll(
            ".genre-checkbox:checked"
        );

    if (selectedGenres.length === 0) {

        alert(
            "Please select at least one genre."
        );

        return;
    }

    selectedGenres.forEach(
        checkbox => {

            formData.append(
                "genreIds",
                checkbox.value
            );

        }
    );

    const poster = document.getElementById("poster").files[0];

    if (poster) {

        formData.append("poster", poster);

    }

    try {

        const response = await fetch(

            API.BASE_URL + API.ADMIN_MOVIES.ADD,

            {

                method: "POST",

                body: formData

            }

        );

        const result = await response.json();

        if (response.ok) {

            alert(result.message);

            document.getElementById("addMovieForm").reset();

            const preview = document.getElementById("posterPreview");

            if (preview) {

                preview.src = "";

            }

        }

        else {

            alert(result.message);

        }

    }

    catch (error) {

        console.error(error);

        alert("Failed to save movie.");

    }

}

