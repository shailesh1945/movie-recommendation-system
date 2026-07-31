document.addEventListener("DOMContentLoaded", () => {

    initializeLogin();
    initializeRegister();
    initializeLogout();
    initializeModalLinks();

});

// ===============================
// INITIALIZE FUNCTIONS
// ===============================

function initializeLogin() {

    const loginForm = document.getElementById("loginForm");

    if (loginForm) {

        loginForm.addEventListener("submit", loginUser);

    }

}

function initializeRegister() {

    const registerForm = document.getElementById("registerForm");

    if (registerForm) {

        registerForm.addEventListener("submit", registerUser);

    }

}

function initializeLogout() {

    const logoutBtn = document.getElementById("logoutBtn");

    if (logoutBtn) {

        logoutBtn.addEventListener("click", logoutUser);

    }

}

function initializeModalLinks() {

    const openLogin = document.getElementById("openLogin");

    if (openLogin) {

        openLogin.addEventListener("click", function (e) {

            e.preventDefault();

            bootstrap.Modal.getInstance(
                document.getElementById("registerModal")
            ).hide();

            new bootstrap.Modal(
                document.getElementById("loginModal")
            ).show();

        });

    }

    const openRegister = document.getElementById("openRegister");

    if (openRegister) {

        openRegister.addEventListener("click", function (e) {

            e.preventDefault();

            bootstrap.Modal.getInstance(
                document.getElementById("loginModal")
            ).hide();

            new bootstrap.Modal(
                document.getElementById("registerModal")
            ).show();

        });

    }

}

// ===============================
// ALERT
// ===============================

function showAlert(containerId, message, type) {

    const alertBox = document.getElementById(containerId);

    if (!alertBox) return;

    alertBox.innerHTML = `
        <div class="alert alert-${type} alert-dismissible fade show">

            ${message}

            <button type="button"
                    class="btn-close"
                    data-bs-dismiss="alert">
            </button>

        </div>
    `;

}

// ===============================
// REGISTER
// ===============================

async function registerUser(event) {

    event.preventDefault();

    const user = {

        firstName: document.getElementById("firstName").value.trim(),

        lastName: document.getElementById("lastName").value.trim(),

        email: document.getElementById("registerEmail").value.trim(),

        password: document.getElementById("registerPassword").value,

        mobile: document.getElementById("mobile").value.trim(),

        gender: document.getElementById("gender").value,

        roleId: 2

    };

    try {

        const response = await fetch(

            API.BASE_URL + API.AUTH.REGISTER,

            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(user)

            }

        );

        const result = await response.json();

        if (response.ok && result.success) {

            showAlert("registerAlert", result.message, "success");

            document.getElementById("registerForm").reset();

            setTimeout(() => {

                bootstrap.Modal.getInstance(
                    document.getElementById("registerModal")
                ).hide();

                new bootstrap.Modal(
                    document.getElementById("loginModal")
                ).show();

            }, 1200);

        }

        else {

            showAlert("registerAlert", result.message, "danger");

        }

    }

    catch (error) {

        console.error(error);

        showAlert("registerAlert", "Unable to connect to server.", "danger");

    }

}

// ===============================
// LOGIN
// ===============================

async function loginUser(event) {

    event.preventDefault();

    const email = document.getElementById("loginEmail").value.trim();

    const password = document.getElementById("loginPassword").value;

    try {

        const response = await fetch(

            API.BASE_URL + API.AUTH.LOGIN,

            {

                method: "POST",

                credentials: "include",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify({

                    email,
                    password

                })

            }

        );

        const result = await response.json();

        if (response.ok && result.success) {

            showAlert("alertBox", result.message, "success");

            setTimeout(() => {

                if (result.data.role === "ADMIN") {

                    window.location.href = "/frontend/admin/admindashboard.jsp";

                }

                else {

                    window.location.href = "/frontend/user/home.jsp";

                }

            }, 1000);

        }

        else {

            showAlert("alertBox", result.message, "danger");

        }

    }

    catch (error) {

        console.error(error);

        showAlert("alertBox", "Unable to connect to server.", "danger");

    }

}

// ===============================
// LOGOUT
// ===============================

async function logoutUser(event) {

    event.preventDefault();

    try {

        const response = await fetch(

            API.BASE_URL + API.AUTH.LOGOUT,

            {

                method: "POST",

                credentials: "include"

            }

        );

        const result = await response.json();

        if (response.ok && result.success) {

            window.location.href = "/frontend/index.jsp";

        }

    }

    catch (error) {

        console.error(error);

    }

}