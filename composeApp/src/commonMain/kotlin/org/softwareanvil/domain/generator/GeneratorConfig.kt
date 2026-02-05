package org.softwareanvil.domain.generator

data class GenerationConfig(
    val minAge: Int = 16,
    val maxAge: Int = 80,
    val minFoundationYear: Int = 0,
    val maxFoundationYear: Int = 2000,
    val countryDescriptionTemplate: (String) -> String = { "La maravillosa nación de $it." },
    val countryMottoTemplate: (String) -> String = { "¡$it al poder!" }
)