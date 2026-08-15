document.addEventListener("DOMContentLoaded", () => {

    console.log("adminrole.js loaded");

    if (document.getElementById("userTableBody")) {
        loadUsers();
    }

    loadUserCount();
    loadMoviesCount();

});


function formatDate(dateString) {

    if (!dateString) {
        return "N/A";
    }

    const date = new Date(dateString);

    return isNaN(date.getTime())
        ? dateString
        : date.toLocaleString();
}


async function loadUsers() {

    try {

        const response = await apiFetch(
            API.BASE_URL + API.ADMIN.USERS
        );

        if (!response.ok) {
            throw new Error(
                `HTTP error! Status: ${response.status}`
            );
        }

        const users = await response.json();

        const tableBody =
            document.getElementById("userTableBody");

        if (!users || users.length === 0) {

            tableBody.innerHTML = `
                <tr>
                    <td colspan="7"
                        class="text-center text-secondary py-4">
                        No users found.
                    </td>
                </tr>
            `;

            return;
        }

        const totalUsers =
            document.getElementById("totalUsers");

        if (totalUsers) {
            totalUsers.textContent = users.length;
        }

        let count = 1;

        tableBody.innerHTML = users.map(user => {

            const fullName =
                `${user.firstName || ""} ${user.lastName || ""}`
                    .trim() || "N/A";

            const safeName =
                fullName.replace(/'/g, "\\'");

            return `
                <tr class="align-middle">

                    <td class="fw-medium text-white">
                        ${count++}
                    </td>

                    <td class="fw-semibold text-white">
                        ${fullName}
                    </td>

                    <td>
                        ${user.email || "N/A"}
                    </td>

                    <td>
                        ${user.mobile || "N/A"}
                    </td>

                    <td>
                        <span class="badge bg-secondary text-light fw-normal px-2 py-1">
                            ${user.gender || "N/A"}
                        </span>
                    </td>

                    <td class="text-secondary">
                        ${formatDate(user.createdAt)}
                    </td>

                    <td class="text-center">

                        <button
                            type="button"
                            onclick="deleteUser(${user.userId}, '${safeName}')"
                            class="btn btn-sm btn-outline-danger px-3">

                            Delete

                        </button>

                    </td>

                </tr>
            `;

        }).join("");

    } catch (error) {

        console.error(
            "Error fetching users:",
            error
        );

    }
}


async function deleteUser(userId, userName) {

    if (
        !confirm(
            `Are you sure you want to delete user ${userName} (#${userId})?`
        )
    ) {
        return;
    }

    try {

        const response = await apiFetch(
            API.BASE_URL +
            API.ADMIN.USER_DETAILS +
            userId,
            {
                method: "DELETE"
            }
        );

        if (response.ok) {

            alert(
                `User ${userName} deleted successfully.`
            );

            // Refresh table
            loadUsers();

        } else {

            alert("Failed to delete user.");

        }

    } catch (error) {

        console.error(
            "Error deleting user:",
            error
        );

        alert(
            "Server error occurred while deleting user."
        );

    }
}


async function loadMoviesCount() {

    try {

        const response = await apiFetch(
            API.BASE_URL +
            API.ADMIN.MOVIES_COUNT
        );

        if (!response.ok) {

            throw new Error(
                `HTTP error! Status: ${response.status}`
            );

        }

        const count = await response.json();

        console.log(
            "Movies count:",
            count
        );

        const element =
            document.getElementById("totalMovies");

        if (element) {
            element.textContent = count;
        }

    } catch (error) {

        console.error(
            "Error loading movies count:",
            error
        );

    }
}


async function loadUserCount() {

    try {

        const response = await apiFetch(
            API.BASE_URL +
            API.ADMIN.USER_COUNT
        );

        if (!response.ok) {

            throw new Error(
                `HTTP error! Status: ${response.status}`
            );

        }

        const count = await response.json();

        console.log(
            "Users count:",
            count
        );

        const element =
            document.getElementById("totalUsers");

        if (element) {
            element.textContent = count;
        }

    } catch (error) {

        console.error(
            "Error loading users count:",
            error
        );

    }
}