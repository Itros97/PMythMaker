package org.softwareanvil.data.repositories.syllable

import org.softwareanvil.db.SyllablesQueries
import org.softwareanvil.domain.models.Syllable
import org.softwareanvil.domain.models.enums.SyllableCategory
import org.softwareanvil.domain.models.enums.SyllableType

class SyllableRepositoryImpl(
    private val queries: SyllablesQueries
) : SyllableRepository {
    override fun getByTypeAndCategory(
        type: SyllableType,
        category: SyllableCategory
    ): List<Syllable> {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}