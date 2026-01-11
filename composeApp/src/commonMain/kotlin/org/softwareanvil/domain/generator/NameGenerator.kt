package org.softwareanvil.domain.generator

import org.softwareanvil.domain.models.Syllable
import kotlin.random.Random

class NameGenerator(
    private val syllables: List<Syllable>,
    private val random: Random
)
