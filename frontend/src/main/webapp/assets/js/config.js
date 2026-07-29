const API = {

    BASE_URL: "http://localhost:8081",

    AUTH: {

        LOGIN: "/api/auth/login",

        REGISTER: "/api/auth/register",

        LOGOUT: "/api/auth/logout",

        ME: "/api/auth/me"

    },

	MOVIES: {

	        ALL: "/api/movies",

	        DETAILS: "/api/movies/",

	        SEARCH: "/api//movies/search",

	        LATEST: "/api/movies/latest",

	        TOP_RATED: "/api/movies/top-rated"

	    },

    RECOMMENDATIONS: {

        LIST: "/recommendations"

    },
	LANGUAGES: {

	    ALL: "/api/languages"

	}

};