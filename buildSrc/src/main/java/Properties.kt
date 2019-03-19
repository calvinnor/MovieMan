object TMDB {

    const val BASE_URL = "https://api.themoviedb.org/"

    /* To create an API key
     * go to "https://www.themoviedb.org/settings/api" after creating an account
     *
     * Then, add the Environment Variable "TMDB_API_KEY" to /etc/environment
     * and restart your system.
     *
     * More info: https://askubuntu.com/questions/4667/where-to-declare-environment-variables
     */
    val API_KEY: String = System.getenv("TMDB_API_KEY")
}
