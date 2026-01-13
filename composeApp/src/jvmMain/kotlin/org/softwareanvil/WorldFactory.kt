package org.softwareanvil

import GenerateWorldUseCase
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
import java.nio.file.Files
import java.nio.file.Paths

object WorldFactory {

    fun createWorldViewModel(): WorldViewModel {

        // 1️⃣ Crear driver SQLite (JVM, en memoria)
        val driver: SqlDriver =
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

        // 2️⃣ Crear esquema (tablas SQLDelight)
        Schema.create(driver)

        // 3️⃣ Crear Queries tipadas
        val syllablesQueries = SyllablesQueries(driver)
        val countriesQueries = CountriesQueries(driver)

        // 4️⃣ Leer el SQL de seed de sílabas
        val syllablesSeedSql = Files.readString(
            Paths.get("src/commonMain/resources/seed/syllables_default.sql")
        )

        // 5️⃣ Ejecutar seed SOLO si la tabla está vacía
        SyllableSeedInitializer(
            driver = driver,
            syllablesQueries = syllablesQueries
        ).seedIfNeeded(syllablesSeedSql)

        // 6️⃣ Crear repositorios
        val syllableRepository =
            SyllableRepositoryImpl(syllablesQueries)

        val countryRepository =
            CountryRepositoryImpl(countriesQueries)

        // 7️⃣ Crear el generador de mundos (dominio)
        val worldGenerator =
            WorldGeneratorService(syllableRepository)

        // 8️⃣ Crear el Use Case (aplicación)
        val generateWorldUseCase =
            GenerateWorldUseCase(worldGenerator, countryRepository)

        // 9️⃣ Crear y devolver el ViewModel (UI)
        return WorldViewModel(generateWorldUseCase)
    }
}
