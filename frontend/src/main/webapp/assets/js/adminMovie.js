document.addEventListener("DOMContentLoaded", () => {

    loadLanguages();

    setupPosterPreview();

    const form = document.getElementById("addMovieForm");

    if (form) {

        form.addEventListener("submit", addMovie);

    }

});

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

function setupPosterPreview() {

    const posterInput = document.getElementById("poster");

    const preview = document.getElementById("posterPreview");

    if (!posterInput || !preview) {

        return;

    }

    posterInput.addEventListener("change", function () {

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