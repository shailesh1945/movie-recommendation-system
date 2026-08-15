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

		SEARCH: "/api/movies/search",

		LATEST: "/api/movies/latest",

		TOP_RATED: "/api/movies/top-rated"

	},

	RECOMMENDATIONS: {
		LIST: "/api/recommendations"
	},

	PREFERENCES: {
		ADD: "/api/preferences"
	},

	ADMIN_MOVIES: {
		ALL: "/api/admin/movies",
		ADD: "/api/admin/movies",
		DETAILS: "/api/admin/movies/",
		UPDATE: "/api/admin/movies/",
		DELETE: "/api/admin/movies/"
	},

	LANGUAGES: {

		ALL: "/api/languages"

	},
	GENRES: {

	    ALL: "/api/genres"

	},
	
	ADMIN: {
	      USERS: "/api/admin/users",
	      USER_DETAILS: "/api/admin/users/",
	      USER_COUNT: "/api/admin/usercount",
	      MOVIES_COUNT: "/api/admin/moviescount",
		  GENRE_COUNT: "/api/admin/genrecount"
	  },

};