import java.util.Properties

const val TMDB_API_KEY = "TMDB_API_KEY"

object TMDB {

    const val BASE_URL = "https://api.themoviedb.org/"

    /* To create an API key
     * go to "https://www.themoviedb.org/settings/api" after creating an account
     *
     * Then, add the Environment Variable "TMDB_API_KEY" to /etc/environment
     * and restart your system.
     * More info: https://askubuntu.com/questions/4667/where-to-declare-environment-variables

     * If that doesn't work, add TMDB_API_KEY to local.properties
     * More info: https://blog.mindorks.com/using-local-properties-file-to-avoid-api-keys-check-in-into-version-control-system
     */
    fun getApiKeyFromEnvironmentOrProperties(properties: Properties): String {
        return System.getenv(TMDB_API_KEY) ?: properties.getProperty(TMDB_API_KEY) ?: TMDB_API_KEY
    }
}
