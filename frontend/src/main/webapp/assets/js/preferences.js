document.addEventListener("DOMContentLoaded", function () {

    // ==========================================
    // Preference Chips
    // ==========================================

    const chips =
        document.querySelectorAll(".preference-chip");

    chips.forEach(function (chip) {

        chip.addEventListener("click", function () {

            this.classList.toggle("active");

        });

    });


    // ==========================================
    // Rating Slider
    // ==========================================

    const ratingSlider =
        document.getElementById("ratingSlider");

    const ratingValue =
        document.getElementById("ratingValue");

    if (ratingSlider && ratingValue) {

        ratingSlider.addEventListener("input", function () {

            ratingValue.textContent = this.value;

        });

    }


    // ==========================================
    // Release Year Slider
    // ==========================================

    const yearSlider =
        document.getElementById("yearSlider");

    const yearValue =
        document.getElementById("yearValue");

    if (yearSlider && yearValue) {

        yearSlider.addEventListener("input", function () {

            yearValue.textContent = this.value;

        });

    }


    // ==========================================
    // Save Button
    // ==========================================

    const saveButton =
        document.getElementById(
            "savePreferencesBtn"
        );

    if (saveButton) {

        saveButton.addEventListener(
            "click",
            savePreferences
        );

    }

});


// =================================================
// SAVE PREFERENCES
// =================================================

async function savePreferences() {

    const saveButton =
        document.getElementById(
            "savePreferencesBtn"
        );

    try {

        // =========================================
        // Get selected genre IDs
        // =========================================

        const genreIds = [];

        document
            .querySelectorAll(
                '.preference-chip[data-type="genre"].active'
            )
            .forEach(function (chip) {

                const id =
                    parseInt(
                        chip.dataset.id
                    );

                genreIds.push(id);

            });


        // =========================================
        // Get selected language IDs
        // =========================================

        const languageIds = [];

        document
            .querySelectorAll(
                '.preference-chip[data-type="language"].active'
            )
            .forEach(function (chip) {

                const id =
                    parseInt(
                        chip.dataset.id
                    );

                languageIds.push(id);

            });


        // =========================================
        // Get minimum rating
        // =========================================

        const minRating =
            parseFloat(
                document.getElementById(
                    "ratingSlider"
                ).value
            );


        // =========================================
        // Get minimum release year
        // =========================================

        const minReleaseYear =
            parseInt(
                document.getElementById(
                    "yearSlider"
                ).value
            );


        // =========================================
        // Validation
        // =========================================

        if (genreIds.length === 0) {

            alert(
                "Please select at least one genre."
            );

            return;

        }


        if (languageIds.length === 0) {

            alert(
                "Please select at least one language."
            );

            return;

        }


        // =========================================
        // Request Object
        // =========================================

        const requestData = {

            genreIds: genreIds,

            languageIds: languageIds,

            minRating: minRating,

            minReleaseYear: minReleaseYear

        };


        console.log(
            "Preference request:",
            requestData
        );


        // =========================================
        // Disable Save Button
        // =========================================

        saveButton.disabled = true;

        saveButton.innerHTML = `
            Saving...
            <span class="spinner-border spinner-border-sm ms-2"></span>
        `;


        // =========================================
        // POST /api/preferences
        // =========================================

        const response = await fetch(

            API.BASE_URL +
            API.PREFERENCES.ADD,

            {

                method: "POST",

                headers: {

                    "Content-Type":
                        "application/json"

                },

                credentials: "include",

                body:
                    JSON.stringify(
                        requestData
                    )

            }

        );


        // =========================================
        // Check Response
        // =========================================

        if (!response.ok) {

            let message =
                "Failed to save preferences.";

            try {

                const error =
                    await response.json();

                if (error.message) {

                    message =
                        error.message;

                }

            } catch (e) {

                console.error(
                    "Unable to parse error response."
                );

            }

            throw new Error(message);

        }


        // =========================================
        // Read API Response
        // =========================================

        const result =
            await response.json();


        console.log(
            "Preference saved:",
            result
        );


        // =========================================
        // Success
        // =========================================

        alert(
            "Preferences saved successfully!"
        );


        // =========================================
        // Redirect
        // =========================================

        window.location.href =
            "recommendation.jsp";


    } catch (error) {

        console.error(
            "Save preference error:",
            error
        );


        alert(
            error.message ||
            "Something went wrong while saving preferences."
        );


        // =========================================
        // Enable button again
        // =========================================

        if (saveButton) {

            saveButton.disabled = false;

            saveButton.innerHTML = `
                Save Preferences
                <i class="bi bi-arrow-right ms-2"></i>
            `;

        }

    }

}