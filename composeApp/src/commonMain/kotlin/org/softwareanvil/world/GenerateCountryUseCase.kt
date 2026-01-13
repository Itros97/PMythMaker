import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.WorldGeneratorService

class GenerateWorldUseCase(
    private val worldGenerator: WorldGeneratorService,
    private val countryRepository: CountryRepository
) {

    fun execute(seed: Long) {
        val countries = worldGenerator.generateCountries(seed)
       // countries.forEach(countryRepository::insert)
    }
}
