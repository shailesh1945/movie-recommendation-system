document.addEventListener("DOMContentLoaded", () => {

    loadProfile();

    setupProfileForm();

});


let currentProfile = null;


/* =========================
   Load Profile
========================= */

async function loadProfile() {

    try {



        const response = await apiFetch(
			API.BASE_URL + API.ADMIN_MOVIES.ADD, {
            
				method: "GET",

        });

        console.log(
            "GET profile status:",
            response.status
        );


        const result =
            await response.json();


        console.log(
            "GET profile response:",
            result
        );


        if (!response.ok ||
            !result.success) {

            throw new Error(
                result.message ||
                "Failed to load profile."
            );
        }


        currentProfile =
            result.data;


        displayProfile(
            currentProfile
        );


    } catch (error) {

        console.error(
            "Profile loading error:",
            error
        );

        showAlert(
            error.message,
            "danger"
        );
    }
}


/* =========================
   Display Profile
========================= */

function displayProfile(profile) {

    document.getElementById(
        "firstName"
    ).value =
        profile.firstName || "";


    document.getElementById(
        "lastName"
    ).value =
        profile.lastName || "";


    document.getElementById(
        "email"
    ).value =
        profile.email || "";


    document.getElementById(
        "phoneNumber"
    ).value =
        profile.phoneNumber || "";


    document.getElementById(
        "gender"
    ).value =
        profile.gender || "";


    /*
     * Never load password
     * from backend.
     */

    document.getElementById(
        "password"
    ).value = "";


    const fullName =
        `${profile.firstName || ""} ${profile.lastName || ""}`
            .trim();


    document.getElementById(
        "profileFullName"
    ).textContent =
        fullName || "User";


    document.getElementById(
        "profileEmail"
    ).textContent =
        profile.email || "";


    /*
     * Avatar initials
     */

    const firstInitial =
        profile.firstName
            ? profile.firstName
                .charAt(0)
                .toUpperCase()
            : "";


    const lastInitial =
        profile.lastName
            ? profile.lastName
                .charAt(0)
                .toUpperCase()
            : "";


    document.getElementById(
        "profileAvatar"
    ).textContent =
        firstInitial + lastInitial;
}


/* =========================
   Edit / Cancel / Save
========================= */

function setupProfileForm() {

    const form =
        document.getElementById(
            "profileForm"
        );


    const editBtn =
        document.getElementById(
            "editBtn"
        );


    const cancelBtn =
        document.getElementById(
            "cancelBtn"
        );


    editBtn.addEventListener(
        "click",
        enableEditing
    );


    cancelBtn.addEventListener(
        "click",
        cancelEditing
    );


    form.addEventListener(
        "submit",
        updateProfile
    );
}


/* =========================
   Enable Editing
========================= */

function enableEditing() {

    document.querySelectorAll(
        ".profile-input"
    ).forEach(input => {

        input.disabled = false;

    });


    document.getElementById(
        "editBtn"
    ).classList.add("d-none");


    document.getElementById(
        "cancelBtn"
    ).classList.remove("d-none");


    document.getElementById(
        "saveBtn"
    ).classList.remove("d-none");
}


/* =========================
   Cancel Editing
========================= */

function cancelEditing() {

    if (currentProfile) {

        displayProfile(
            currentProfile
        );
    }


    disableEditing();

    hideAlert();
}


/* =========================
   Disable Editing
========================= */

function disableEditing() {

    document.querySelectorAll(
        ".profile-input"
    ).forEach(input => {

        input.disabled = true;

    });


    document.getElementById(
        "editBtn"
    ).classList.remove("d-none");


    document.getElementById(
        "cancelBtn"
    ).classList.add("d-none");


    document.getElementById(
        "saveBtn"
    ).classList.add("d-none");
}


/* =========================
   Update Profile
========================= */

async function updateProfile(event) {

    event.preventDefault();


    const requestData = {

        firstName:
            document.getElementById(
                "firstName"
            ).value.trim(),

        lastName:
            document.getElementById(
                "lastName"
            ).value.trim(),

        email:
            document.getElementById(
                "email"
            ).value.trim(),

        phoneNumber:
            document.getElementById(
                "phoneNumber"
            ).value.trim(),

        gender:
            document.getElementById(
                "gender"
            ).value,

        password:
            document.getElementById(
                "password"
            ).value.trim()
    };


    /*
     * Don't send empty password.
     */

    if (!requestData.password) {

        delete requestData.password;
    }


    console.log(
        "Updating profile:",
        requestData
    );


    try {

        /*
         * IMPORTANT:
         * Use API.BASE_URL here too.
         */

        /*const response =
            await fetch(
                API.BASE_URL + "/api/profile",
                {
                    method: "PUT",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    credentials: "include",

                    body: JSON.stringify(
                        requestData
                    )
                }
            );*/

			
			const response = await apiFetch(

						      API.BASE_URL + "/api/profile",

						    {
						        method: "PUT",
						        body: formData
						    }

						);


        console.log(
            "PUT profile status:",
            response.status
        );


        const result =
            await response.json();


        console.log(
            "PUT profile response:",
            result
        );


        if (!response.ok ||
            !result.success) {

            throw new Error(
                result.message ||
                "Profile update failed."
            );
        }


        showAlert(
            "Profile updated successfully!",
            "success"
        );


        await loadProfile();


        disableEditing();


    } catch (error) {

        console.error(
            "Profile update error:",
            error
        );

        showAlert(
            error.message,
            "danger"
        );
    }
}


/* =========================
   Alert
========================= */

function showAlert(
    message,
    type
) {

    const alert =
        document.getElementById(
            "profileAlert"
        );


    alert.className =
        `alert alert-${type}`;


    alert.textContent =
        message;
}


function hideAlert() {

    const alert =
        document.getElementById(
            "profileAlert"
        );


    alert.className =
        "alert d-none";


    alert.textContent = "";
}