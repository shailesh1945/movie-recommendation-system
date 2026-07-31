<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

.trending-section{
    background:#11141b;
}

.trending-title{
    color:white;
    font-size:34px;
    font-weight:700;
}

.trending-subtitle{
    color:#a9a9a9;
}

.movie-scroll{

    display:flex;

    gap:20px;

    overflow-x:auto;

    scroll-behavior:smooth;

    padding-bottom:10px;
}

.movie-scroll::-webkit-scrollbar{

    height:8px;
}

.movie-scroll::-webkit-scrollbar-thumb{

    background:#dc3545;

    border-radius:10px;
}

.trending-card{

    min-width:220px;

    background:#1b1f27;

    border-radius:16px;

    overflow:hidden;

    transition:.35s;

    cursor:pointer;

    flex-shrink:0;
}

.trending-card:hover{

    transform:translateY(-10px) scale(1.03);

    box-shadow:0 15px 35px rgba(0,0,0,.5);
}

.trending-card img{

    width:100%;

    height:320px;

    object-fit:cover;
}

.movie-details{

    padding:15px;
}

.movie-name{

    color:white;

    font-weight:600;

    font-size:20px;
}

.movie-meta{

    color:#bbbbbb;

    font-size:14px;
}

.movie-rating{

    color:#ffc107;

    font-size:15px;
}

.rank{

    position:absolute;

    top:15px;

    left:15px;

    background:#dc3545;

    color:white;

    width:40px;

    height:40px;

    border-radius:50%;

    display:flex;

    justify-content:center;

    align-items:center;

    font-weight:bold;
}

.poster-wrapper{

    position:relative;
}

.loading{

    width:100%;

    text-align:center;

    color:#bdbdbd;

    padding:60px 0;
}

</style>

<section class="trending-section py-5">

<div class="container">

<div class="d-flex justify-content-between align-items-center mb-4">

<div>

<h2 class="trending-title">

🔥 Latest Movies

</h2>

<p class="trending-subtitle">

Newest movies added to our recommendation system.

</p>

</div>

<button class="btn btn-outline-danger">

View All

</button>

</div>

<div id="latestMoviesContainer" class="movie-scroll">

<div class="loading">

<div class="spinner-border text-danger mb-3"></div>

<p>

Loading latest movies...

</p>

</div>

</div>

</div>

</section>