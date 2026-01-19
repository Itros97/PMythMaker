package org.softwareanvil

import org.softwareanvil.world.GenerateWorldUseCase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.softwareanvil.data.repositories.country.CountryRepositoryImpl
import org.softwareanvil.data.repositories.syllable.SyllableRepositoryImpl
import org.softwareanvil.data.seed.SyllableSeedInitializer
import org.softwareanvil.db.CountriesQueries
import org.softwareanvil.db.PocketMythDatabase.Companion.Schema
import org.softwareanvil.db.SyllablesQueries
import org.softwareanvil.domain.generator.WorldGeneratorService
import org.softwareanvil.ui.world.WorldViewModel
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object WorldFactory {

    fun createWorldViewModel(): WorldViewModel {

        val dbFile = File("pocket_mythsmith.db")

        if (dbFile.exists()) {
            dbFile.delete()
        }

        val driver = JdbcSqliteDriver("jdbc:sqlite:pocket_mythsmith.db")

        Schema.create(driver)

        val syllablesQueries = SyllablesQueries(driver)
        val countriesQueries = CountriesQueries(driver)

        val syllablesSeedSql = Files.readString(
            Paths.get("src/commonMain/resources/seed/syllables_default.sql")
        )

        SyllableSeedInitializer(
            driver = driver,
            syllablesQueries = syllablesQueries
        ).seedIfNeeded(syllablesSeedSql)

        val syllableRepository =
            SyllableRepositoryImpl(syllablesQueries)

        val countryRepository =
            CountryRepositoryImpl(countriesQueries)

        val worldGenerator =
            WorldGeneratorService(syllableRepository)

        val generateWorldUseCase =
            GenerateWorldUseCase(worldGenerator, countryRepository)

        return WorldViewModel(generateWorldUseCase)
    }
}
