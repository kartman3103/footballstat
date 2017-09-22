package footballstat.database


import footballstat.database.dao.DAO
import footballstat.model.football.League
import footballstat.services.json.LeagueParser
import org.codehaus.jackson.map.ObjectMapper
import org.junit.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations= arrayOf("classpath:config/testdata/league-mdao-test.properties"))
open class LeagueDAOTest
{
    @Autowired
    lateinit var leagueDAO : DAO<League>

    @Autowired
    lateinit var leagueParser : LeagueParser

    @Value("\${league}")
    private val requestGetLeague: String = ""

    @Value("\${listLeagueInfo}")
    private val requestListLeagueInfo: String = ""

    private var objectMapper : ObjectMapper = ObjectMapper()

    @Test
    fun testInsertDeleteGetbyid()
    {
        val league = leagueParser.league(requestGetLeague)
        val leagueInfo = leagueParser.availableLeagues(requestListLeagueInfo).filter { it.Name == league.Name }.first()
        with(league) {
            Year = 2016
            ShortName = leagueInfo.ShortName
            ToursPlayed = leagueInfo.ToursPlayed
        }
        leagueDAO.insert(league)

        Assert.assertNotNull(league.id)

        val leagueFromDB = league.id?.let { leagueDAO.getById(it) }

        Assert.assertEquals(objectMapper.writeValueAsString(league), objectMapper.writeValueAsString(leagueFromDB))

        league.id?.let {
            leagueDAO.delete(it)
            Assert.assertNull(leagueDAO.getById(it))
        }

    }
}