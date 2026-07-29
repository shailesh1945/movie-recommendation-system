document.addEventListener("DOMContentLoaded", () => {

    initializeLogin();
	initializeRegister();

	initializeLogout();
});

function initializeLogin() {

    const loginForm = document.getElementById("loginForm");

    if (loginForm) {

        loginForm.addEventListener("submit", loginUser);

    }

}

function initializeRegister(){

    const registerForm=document.getElementById("registerForm");

    if(registerForm){

        registerForm.addEventListener("submit",registerUser);

    }

}

function showAlert(message, type) {

    const alertBox = document.getElementById("alertBox");

    if (!alertBox) return;

    alertBox.innerHTML = `

        <div class="alert alert-${type} alert-dismissible fade show">

            ${message}

            <button
                class="btn-close"
                data-bs-dismiss="alert">
            </button>

        </div>

    `;

}

async function registerUser(event){

    event.preventDefault();

    const user={

        firstName:document.getElementById("firstName").value.trim(),

        lastName:document.getElementById("lastName").value.trim(),

        email:document.getElementById("email").value.trim(),

        password:document.getElementById("password").value,

        mobile:document.getElementById("mobile").value.trim(),

        gender:document.getElementById("gender").value,

        roleId:2

    };

    try{

        const response=await fetch(

            API.BASE_URL+API.AUTH.REGISTER,

            {

                method:"POST",

                headers:{

                    "Content-Type":"application/json"

                },

                body:JSON.stringify(user)

            }

        );

        const result=await response.json();

        if(response.ok && result.success){

            showAlert(result.message,"success");

            setTimeout(()=>{

                window.location.href="login.jsp";

            },1500);

        }

        else{

            showAlert(result.message,"danger");

        }

    }

    catch(error){

        console.error(error);

        showAlert("Unable to connect to server.","danger");

    }

}

function initializeLogout() {

    const logoutBtn = document.getElementById("logoutBtn");

    if (!logoutBtn) return;

    logoutBtn.addEventListener("click", logoutUser);

}

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

            window.location.href =
                "../guest/index.jsp";

        }

    }

    catch(error){

        console.error(error);

    }

}

async function loginUser(event) {

    event.preventDefault();

    const email = document.getElementById("email").value.trim();

    const password = document.getElementById("password").value;

    try {

        const response = await fetch(

            API.BASE_URL + API.AUTH.LOGIN,

            {

                method: "POST",

                credentials: "include",

                headers: {

                    "Content-Type":"application/json"

                },

                body: JSON.stringify({

                    email,

                    password

                })

            }

        );

        const result = await response.json();

        if(response.ok && result.success){

            showAlert(result.message,"success");

            setTimeout(()=>{

                if(result.data.role==="ADMIN"){

                    window.location.href="/frontend/admin/admindashboard.jsp";

                }

                else{

                    window.location.href="../user/home.jsp";

                }

            },1000);

        }

        else{

            showAlert(result.message,"danger");

        }

    }

    catch(error){

        console.error(error);

        showAlert("Unable to connect to server.","danger");

    }

}