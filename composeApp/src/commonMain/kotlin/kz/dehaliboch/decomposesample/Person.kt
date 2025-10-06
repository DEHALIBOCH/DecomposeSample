package kz.dehaliboch.decomposesample

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val age: Int,
    val name: String,
)
