<nav class="navbar navbar-dark glass-navbar sticky-top">

    <div class="container">

        <!-- Logo -->
        <a class="navbar-brand d-flex align-items-center fw-bold" href="#">

            <i class="bi bi-film text-danger fs-2 me-2"></i>

            <span class="logo-text">
                Movie<span class="text-danger">Rcsys</span>
            </span>

        </a>

        <!-- Desktop Buttons -->
        <div class="d-none d-lg-flex align-items-center gap-3">

            <a href="#"
               class="btn btn-outline-light px-4"
               data-bs-toggle="modal"
               data-bs-target="#loginModal">

                <i class="bi bi-box-arrow-in-right me-2"></i>
                Login

            </a>

            <a href="#"
               class="btn btn-danger px-4"
               data-bs-toggle="modal"
               data-bs-target="#registerModal">

                <i class="bi bi-person-plus me-2"></i>
                Register

            </a>

        </div>

        <!-- Mobile Hamburger -->

        <button
            class="btn text-white d-lg-none border-0 shadow-none"
            data-bs-toggle="offcanvas"
            data-bs-target="#mobileMenu">

            <i class="bi bi-list fs-1"></i>

        </button>

    </div>

</nav>

<!-- Mobile Offcanvas -->

<div class="offcanvas offcanvas-end bg-dark text-white"
     tabindex="-1"
     id="mobileMenu">

    <div class="offcanvas-header">

        <h4 class="fw-bold">

            <i class="bi bi-film text-danger me-2"></i>

            MovieRcsys

        </h4>

        <button
            class="btn-close btn-close-white"
            data-bs-dismiss="offcanvas">
        </button>

    </div>

    <div class="offcanvas-body">

        <div class="d-grid gap-3">

            <a href="#"
               class="btn btn-outline-light"
               data-bs-toggle="modal"
               data-bs-dismiss="offcanvas"
               data-bs-target="#loginModal">

                <i class="bi bi-box-arrow-in-right me-2"></i>

                Login

            </a>

            <a href="#"
               class="btn btn-danger"
               data-bs-toggle="modal"
               data-bs-dismiss="offcanvas"
               data-bs-target="#registerModal">

                <i class="bi bi-person-plus me-2"></i>

                Register

            </a>

        </div>

    </div>

</div>
<style>
body{
    background:#111418;
}

/* ================= NAVBAR ================= */

.glass-navbar{

    background:rgba(18,18,18,.72);

    backdrop-filter:blur(18px);
    -webkit-backdrop-filter:blur(18px);

    border-bottom:1px solid rgba(255,255,255,.08);

    padding:14px 0;

}

.logo-text{

    font-size:2rem;
    font-weight:700;

}

.navbar-brand{

    letter-spacing:.5px;

}

/* ================= BUTTONS ================= */

.btn{

    border-radius:12px;
    min-width:140px;
    transition:.3s;

}

.btn:hover{

    transform:translateY(-2px);

}

/* ================= OFFCANVAS ================= */

.offcanvas{

    width:300px;

    background:#171717;

    border-left:1px solid rgba(255,255,255,.08);

}

.offcanvas-header{

    border-bottom:1px solid rgba(255,255,255,.08);

}

.offcanvas-body{

    padding:30px;

}

/* ================= MODALS ================= */

.modal-content{

    background:#1d2127;

}

#regpage{

    background:rgba(29,33,39,.82);

    backdrop-filter:blur(18px);

}

.form-control,
.form-select,
.input-group-text{

    background:#1d2127;

    border-color:#343a40;

    color:#fff;

}

.form-control:focus,
.form-select:focus{

    background:#1d2127;

    color:#fff;

    border-color:#dc3545;

    box-shadow:none;

}

/* ================= RESPONSIVE ================= */

@media(max-width:992px){

    .logo-text{

        font-size:1.6rem;

    }

}

@media(max-width:768px){

    .logo-text{

        font-size:1.45rem;

    }

    .offcanvas{

        width:260px;

    }

}

@media(max-width:576px){

    .container{

        padding-left:18px;

        padding-right:18px;

    }

    .logo-text{

        font-size:1.25rem;

    }

    .offcanvas{

        width:240px;

    }

}
</style>