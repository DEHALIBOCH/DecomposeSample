package kz.dehaliboch.decomposesample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform