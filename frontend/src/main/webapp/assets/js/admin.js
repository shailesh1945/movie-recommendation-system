document.addEventListener("DOMContentLoaded", () => {

    checkAdminSession();

});


/*
|--------------------------------------------------------------------------
| CHECK ADMIN SESSION
|--------------------------------------------------------------------------
*/

async function checkAdminSession() {

    try {
		
		const response = await apiFetch(
					     API.BASE_URL + API.AUTH.ME,
					    {
					        method: "GET"
					    }
					);


        if (!response.ok) {

            window.location.href =
                "../user/login.jsp";

            return;

        }


        const result =
            await response.json();


        console.log(
            "Session response:",
            result
        );


        if (!result.success) {

            window.location.href =
                "../user/login.jsp";

            return;

        }


        const user =
            result.data;


        console.log(
            "Logged in user:",
            user
        );


        console.log(
            "Role:",
            user.role
        );


        /*
        |--------------------------------------------------------------------------
        | ADMIN ROLE CHECK
        |--------------------------------------------------------------------------
        */

        if (
            !user.role ||
            user.role.toUpperCase() !== "ADMIN"
        ) {

            alert("Access Denied");

            window.location.href =
                "../user/login.jsp";

            return;

        }


        /*
        |--------------------------------------------------------------------------
        | WELCOME MESSAGE
        |--------------------------------------------------------------------------
        */

        const welcome =
            document.getElementById(
                "welcomeMessage"
            );


        if (welcome) {

            welcome.innerHTML =
                `Welcome, ${user.firstName}`;

        }


        /*
        |--------------------------------------------------------------------------
        | LOAD DASHBOARD
        |--------------------------------------------------------------------------
        */

        await loadDashboardStats();

    }
    catch (error) {

        console.error(
            "Session check error:",
            error
        );


        window.location.href =
            "../user/login.jsp";

    }

}


/*
|--------------------------------------------------------------------------
| LOAD DASHBOARD STATISTICS
|--------------------------------------------------------------------------
*/

async function loadDashboardStats() {

    try {
		
		const response = await apiFetch(
			API.BASE_URL +
			            API.ADMIN_DASHBOARD.STATS,
					    {
					        method: "GET"
					    }
					);


        console.log(
            "Dashboard API status:",
            response.status
        );


        if (!response.ok) {

            console.error(
                "Unable to fetch dashboard statistics."
            );

            return;

        }


        const result =
            await response.json();


        console.log(
            "Dashboard API response:",
            result
        );


        if (
            !result.success ||
            !result.data
        ) {

            console.error(
                "Invalid dashboard response."
            );

            return;

        }


        const stats =
            result.data;


        /*
        |--------------------------------------------------------------------------
        | TOTAL MOVIES
        |--------------------------------------------------------------------------
        */

        const totalMovies =
            document.getElementById(
                "totalMovies"
            );


        if (totalMovies) {

            totalMovies.textContent =
                stats.totalMovies ?? 0;

        }


        /*
        |--------------------------------------------------------------------------
        | TOTAL USERS
        |--------------------------------------------------------------------------
        */

        const totalUsers =
            document.getElementById(
                "totalUsers"
            );


        if (totalUsers) {

            totalUsers.textContent =
                stats.totalUsers ?? 0;

        }


        /*
        |--------------------------------------------------------------------------
        | TOTAL RATINGS
        |--------------------------------------------------------------------------
        */

        const totalRatings =
            document.getElementById(
                "totalRatings"
            );


        if (totalRatings) {

            totalRatings.textContent =
                stats.totalRatings ?? 0;

        }


        /*
        |--------------------------------------------------------------------------
        | AVERAGE RATING
        |--------------------------------------------------------------------------
        */

        const averageRating =
            document.getElementById(
                "averageRating"
            );


        if (averageRating) {

            const rating =
                Number(
                    stats.averageRating ?? 0
                );


            averageRating.textContent =
                rating.toFixed(1);

        }

    }
    catch (error) {

        console.error(
            "Dashboard statistics error:",
            error
        );

    }

}