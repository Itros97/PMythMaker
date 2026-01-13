import org.softwareanvil.data.repositories.country.CountryRepository
import org.softwareanvil.domain.generator.WorldGeneratorService
import org.softwareanvil.domain.models.Country

class GenerateWorldUseCase(
    private val worldGenerator: WorldGeneratorService,
    private val countryRepository: CountryRepository
) {

    fun execute(seed: Long): List<Country> {
        val countries = worldGenerator.generateCountries(seed)

        //countryRepository.deleteAll()

        countries.forEach(countryRepository::insert)
        return countries
    }
}
