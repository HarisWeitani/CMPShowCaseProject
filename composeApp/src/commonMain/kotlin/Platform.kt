interface Platform {
    val name: String
    val baseUrl: String
    val imageBaseUrl: String
}

expect fun getPlatform(): Platform