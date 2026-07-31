<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

.section-title{
    color:#ffffff;
    font-size:34px;
    font-weight:700;
}

.section-subtitle{
    color:#a8a8a8;
    font-size:17px;
}

.movie-card{

    background:#181b22;

    border:none;

    border-radius:18px;

    overflow:hidden;

    transition:.35s;

    height:100%;
}

.movie-card:hover{

    transform:translateY(-10px);

    box-shadow:0 12px 30px rgba(0,0,0,.45);
}

.movie-poster{

    height:340px;

    object-fit:cover;
}

.movie-title{

    color:#ffffff;

    font-size:20px;

    font-weight:600;

    margin-bottom:12px;
}

.movie-info{

    color:#bdbdbd;

    font-size:14px;

    margin-bottom:8px;
}

.rating{

    color:#ffc107;

    font-size:15px;

    font-weight:600;
}

.details-btn{

    width:100%;

    border-radius:10px;

    margin-top:auto;
}

.loading{

    color:#bdbdbd;

    text-align:center;

    padding:60px 0;

    font-size:18px;
}

.empty-state{

    color:#bdbdbd;

    text-align:center;

    padding:60px 0;
}

.empty-state i{

    font-size:60px;

    color:#dc3545;

    margin-bottom:20px;
}

</style>

<section class="container py-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>

            <h2 class="section-title">

                <i class="bi bi-stars text-danger"></i>

                Recommended Movies

            </h2>

            <p class="section-subtitle">

                Explore movies carefully selected from our collection.

            </p>

        </div>

        <button class="btn btn-outline-danger">

            View All

        </button>

    </div>

    <!-- Movies will be loaded here dynamically -->

    <div class="row g-4" id="recommendedMoviesContainer">

        <div class="col-12">

            <div class="loading">

                <div class="spinner-border text-danger mb-3"
                     role="status">

                </div>

                <p>

                    Loading movies...

                </p>

            </div>

        </div>

    </div>

</section>