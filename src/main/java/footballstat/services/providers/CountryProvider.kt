package footballstat.services.providers


import footballstat.services.DataItems
import model.Country
import org.springframework.stereotype.Component

class CountryProvider
{
    @Component
    open class InternalCountryProvider : DataItems.Countries
    {
        override fun getCountries(): Collection<Country>
        {
            throw UnsupportedOperationException()
        }
    }

    @Component
    open class ExternalCountryProvider : DataItems.Countries
    {
        override fun getCountries(): Collection<Country>
        {
            throw UnsupportedOperationException()
        }
    }
}