function getToken() {

    return localStorage.getItem("token");

}


async function apiFetch(
    url,
    options = {}
) {

    const token =
        getToken();


    const headers =
        options.headers || {};


    if (token) {

        headers["Authorization"] =
            "Bearer " + token;

    }


	const response =
	        await fetch(
	            url,
	            {
	                ...options,
	                headers
	            }
	        );


	    // JWT expired / invalid
	    if (response.status === 401) {

	        localStorage.removeItem("token");

	        localStorage.removeItem("user");

	        window.location.href =
	            "/frontend/index.jsp";

	        return response;
	    }


	    return response;

}



