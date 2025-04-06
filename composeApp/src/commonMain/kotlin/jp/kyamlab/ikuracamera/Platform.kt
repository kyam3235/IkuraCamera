package jp.kyamlab.ikuracamera

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform