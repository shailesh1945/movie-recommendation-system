<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Registered Users</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

/* ================= Main Content ================= */

.main-content{
	margin-left:260px;
	min-height:100vh;
	display:flex;
	flex-direction:column;
	background:#111418;
	transition:.3s;
}

.page-content{
	flex:1;
	padding:30px;
}

/* ================= Card ================= */

.user-card{

	background:#1b1f24;

	border:1px solid #343a40;

	border-radius:20px;

	overflow:hidden;

	box-shadow:0 10px 30px rgba(0,0,0,.35);

}

/* ================= Header ================= */

.card-header{

	background:#1b1f24;

	border-bottom:1px solid #343a40;

	padding:20px 25px;

}

.card-header h3{

	font-size:1.6rem;

	margin:0;

}

/* ================= Table ================= */

.table-responsive{

	border-radius:0 0 20px 20px;

	overflow:hidden;

}

.table{

	margin-bottom:0;

	background:#1b1f24;

}

.table thead th{

	background:#2b3035;

	color:#fff;

	padding:16px;

	font-weight:600;

	border-bottom:1px solid #495057;

	white-space:nowrap;

}

.table tbody td{

	padding:16px;

	color:#fff;

	border-color:#343a40;

	vertical-align:middle;

	white-space:nowrap;

}

.table-hover tbody tr:hover{

	background:#23272f;

}

.table tbody tr:last-child td{

	border-bottom:none;

}

/* ================= Badge ================= */

.badge{

	padding:8px 14px;

	border-radius:8px;

	font-size:.8rem;

}

/* ================= Responsive ================= */

@media(max-width:991px){

	.main-content{

		margin-left:0;

	}

	.page-content{

		padding:20px;

	}

}

@media(max-width:768px){

	.page-content{

		padding:15px;

	}

	.card-header{

		padding:18px;

	}

	.card-header .d-flex{

		flex-direction:column;

		align-items:flex-start !important;

		gap:15px;

	}

	.card-header h3{

		font-size:1.4rem;

	}

	.table thead th,
	.table tbody td{

		padding:14px;

		font-size:14px;

	}

}

@media(max-width:576px){

	.page-content{

		padding:10px;

	}

	.card-header{

		text-align:center;

	}

	.card-header .d-flex{

		align-items:center !important;

	}

	.card-header h3{

		font-size:1.2rem;

	}

	.badge{

		font-size:.75rem;

		padding:6px 12px;

	}

	.table{

		font-size:13px;

	}

}

</style>

</head>

<body class="bg-dark">

	<!-- Sidebar -->
	<jsp:include page="../component/sidebar.jsp"/>

	<!-- Main Content -->
	<div class="main-content">

		<div class="page-content">

			<div class="container-fluid">

				<div class="card user-card">

					<!-- Header -->

					<div class="card-header">

						<div class="d-flex justify-content-between align-items-center">

							<h3 class="text-black">

								<i class="bi bi-people-fill text-primary me-2"></i>

								Registered Users

							</h3>

							<span class="badge bg-primary fs-6">

								Total Users :
								<span id="totalUsers"></span>

							</span>

						</div>

					</div>

					<!-- Table -->

					<div class="card-body p-0">

						<div class="table-responsive">

							<table class="table table-dark table-hover align-middle">

							<thead class="table-light">

								<tr>

									<th>ID</th>

									<th>Full Name</th>

									<th>Email</th>

									<th>Mobile</th>

									<th>Gender</th>

									<th>Created At</th>

									<th>Actions</th>

								</tr>

							</thead>

							<tbody id="userTableBody"
								class="divide-y divide-slate-800 bg-slate-900">
								<!-- Example Row -->
								<!--  
								<tr class="align-middle">
									<td class="fw-medium text-white">#1</td>
									<td class="fw-semibold text-white">John Doe</td>
									<td>john.doe@example.com</td>
									<td>+1 234 567 890</td>
									<td><span
										class="badge bg-secondary text-light fw-normal px-2 py-1">Male</span>
									</td>
									<td class="text-secondary">2026-08-07 10:30</td>
									<td class="text-center">
										<button type="button"
											onclick="confirmDeleteUser(1, 'John Doe')"
											class="btn btn-sm btn-outline-danger px-3">Delete</button>
									</td>
								</tr>
								-->

							</tbody>
						</table>

						</div>

					</div>

				</div>

			</div>

		</div>


		<!-- Footer -->
		<div class="mt-auto">

			<jsp:include page="../component/footer.jsp" />

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/api.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/adminrole.js"></script>
	
	<script
		src="${pageContext.request.contextPath}/assets/js/auth.js"></script>
</body>
</html>