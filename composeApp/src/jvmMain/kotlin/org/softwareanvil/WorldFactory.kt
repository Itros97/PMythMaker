package org.softwareanvil

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.softwareanvil.data.repositories.character.CharacterRepositoryImpl
import org.softwareanvil.data.repositories.country.CountryRepositoryImpl
import org.softwareanvil.data.repositories.syllable.SyllableRepositoryImpl
import org.softwareanvil.data.seed.SyllableSeedInitializer
import org.softwareanvil.db.CharactersQueries
import org.softwareanvil.db.CountriesQueries
import org.softwareanvil.db.SyllablesQueries
import org.softwareanvil.domain.generator.GenerationConfig
import org.softwareanvil.domain.generator.character.CharacterGenerationService
import org.softwareanvil.domain.generator.character.CharacterNameGenerator
import org.softwareanvil.domain.generator.name.NameGenerationService
import org.softwareanvil.domain.generator.world.WorldGeneratorService
import org.softwareanvil.ui.world.WorldViewModel
import org.softwareanvil.world.GenerateWorldUseCase
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object WorldFactory {

    fun createWorldViewModel(): WorldViewModel {
        val driver = setupDatabase()
        val queries = createQueries(driver)

        seedSyllables(driver, queries.syllables)

        val repositories = createRepositories(queries)
        val services = createGenerationServices(repositories)

        val generateWorldUseCase = GenerateWorldUseCase(
            worldGenerator = services.worldGenerator,
            countryRepository = repositories.country,
            characterRepository = repositories.character
        )

        return WorldViewModel(generateWorldUseCase)
    }

    private fun setupDatabase(): JdbcSqliteDriver {
        val dbPath = "pocket_mythsmith.db"

        val dbFile = File(dbPath)
        if (dbFile.exists()) {
            dbFile.delete()
            println("🗑️ Deleted existing database")
        }

        println("🟢 Creating driver at: $dbPath")
        println("📁 Absolute path: ${dbFile.absolutePath}")

        val driver = JdbcSqliteDriver("jdbc:sqlite:$dbPath")

        println("🔨 Creating tables manually")
        createTables(driver)

        return driver
    }

    private fun createTables(driver: JdbcSqliteDriver) {
        driver.execute(
            null, """
            CREATE TABLE IF NOT EXISTS countries (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT,
                foundation_year INTEGER,
                motto TEXT,
                created_at INTEGER NOT NULL
            )
        """.trimIndent(), 0, null
        )

        driver.execute(
            null, """
            CREATE TABLE IF NOT EXISTS characters (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                first_name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                age INTEGER,
                country_id INTEGER,
                occupation TEXT,
                description TEXT,
                created_at INTEGER NOT NULL,
                FOREIGN KEY(country_id) REFERENCES countries(id)
            )
        """.trimIndent(), 0, null
        )
        
        driver.execute(
            null, """
            CREATE TABLE IF NOT EXISTS syllables (
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                type TEXT NOT NULL,
                category TEXT NOT NULL,
                syllable TEXT NOT NULL,
                weight INTEGER NOT NULL
            )
        """.trimIndent(), 0, null
        )

        println("✅ Tables created successfully")
    }

    private fun createQueries(driver: JdbcSqliteDriver): Queries {
        return Queries(
            syllables = SyllablesQueries(driver),
            countries = CountriesQueries(driver),
            characters = CharactersQueries(driver)
        )
    }

    private fun seedSyllables(driver: JdbcSqliteDriver, syllablesQueries: SyllablesQueries) {
        val syllablesSeedSql = Files.readString(
            Paths.get("src/commonMain/resources/seed/syllables_default.sql")
        )

        SyllableSeedInitializer(
            driver = driver,
            syllablesQueries = syllablesQueries
        ).seedIfNeeded(syllablesSeedSql)
    }

    private fun createRepositories(queries: Queries): Repositories {
        return Repositories(
            syllable = SyllableRepositoryImpl(queries.syllables),
            country = CountryRepositoryImpl(queries.countries),
            character = CharacterRepositoryImpl(queries.characters)
        )
    }

    private fun createGenerationServices(repositories: Repositories): GenerationServices {
        val config = GenerationConfig()

        val nameGenerationService = NameGenerationService(repositories.syllable)

        val characterNameGenerator = CharacterNameGenerator(nameGenerationService)

        val characterGenerationService = CharacterGenerationService(
            nameGenerator = characterNameGenerator,
            countryRepository = repositories.country,
            config = config
        )

        val worldGeneratorService = WorldGeneratorService(
            nameGenerationService = nameGenerationService,
            characterGenerationService = characterGenerationService
        )

        return GenerationServices(
            nameGeneration = nameGenerationService,
            characterNameGeneration = characterNameGenerator,
            characterGeneration = characterGenerationService,
            worldGenerator = worldGeneratorService
        )
    }

    private data class Queries(
        val syllables: SyllablesQueries,
        val countries: CountriesQueries,
        val characters: CharactersQueries
    )

    private data class Repositories(
        val syllable: SyllableRepositoryImpl,
        val country: CountryRepositoryImpl,
        val character: CharacterRepositoryImpl
    )

    private data class GenerationServices(
        val nameGeneration: NameGenerationService,
        val characterNameGeneration: CharacterNameGenerator,
        val characterGeneration: CharacterGenerationService,
        val worldGenerator: WorldGeneratorService
    )
}