<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container-fluid px-4 py-4">

    <div class="card bg-dark border-secondary shadow rounded-4">

        <div
            class="card-header bg-dark border-secondary d-flex justify-content-between align-items-center">

            <div>

                <h3 class="text-white fw-bold mb-1">

                    <i class="bi bi-film me-2 text-primary"></i>

                    Manage Movies

                </h3>

                <p class="text-secondary mb-0">

                    View, edit and delete movies.

                </p>

            </div>

            <a
                href="${pageContext.request.contextPath}/frontend/admin/addMovie.jsp"
                class="btn btn-primary">

                <i class="bi bi-plus-circle me-2"></i>

                Add Movie

            </a>

        </div>

        <div class="card-body">

            <div class="row mb-4">

                <div class="col-md-4">

                    <input
                        id="searchMovie"
                        class="form-control bg-dark text-white border-secondary"
                        placeholder="Search movie...">

                </div>

            </div>

            <div class="table-responsive">

                <table
                    class="table table-dark table-hover align-middle">

                    <thead>

                    <tr>

                        <th>Poster</th>

                        <th>Title</th>

                        <th>Director</th>

                        <th>Year</th>

                        <th>Language</th>

                        <th>Rating</th>

                        <th class="text-center">

                            Actions

                        </th>

                    </tr>

                    </thead>

                    <tbody id="movieTableBody">

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>