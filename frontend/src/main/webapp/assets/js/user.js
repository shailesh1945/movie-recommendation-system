
document.addEventListener("DOMContentLoaded", () => {

	loadMovieCount();
	loadGenreCount();

});

async function loadMovieCount() {

    try {

        const response = await fetch(
            "http://localhost:8081/api/admin/moviescount"
        );

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const count = await response.json();

        console.log("Movies count:", count);

        const element = document.getElementById("totalMovie");

        if (element) {
            element.textContent = count;
        }

    } catch (error) {

        console.error("Error loading movies count:", error);

    }
}


async function loadGenreCount() {

    try {

        const response = await fetch(
            "http://localhost:8081/api/admin/genrecount"
        );

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const count = await response.json();

        console.log("Users count:", count);

        const element = document.getElementById("totalGenres");

        if (element) {
            element.textContent = count;
        }

    } catch (error) {

        console.error("Error loading users count:", error);

    }
}