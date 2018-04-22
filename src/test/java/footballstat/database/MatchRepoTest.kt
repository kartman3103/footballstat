package footballstat.database


import footballstat.database.dao.mongodb.MatchRepo
import footballstat.services.json.LeagueParser
import model.football.Match
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations= arrayOf("classpath:config/testdata/match-dao-test.properties"))
open class MatchRepoTest
{
    @Autowired
    private lateinit var matchRepo : MatchRepo

    @Autowired
    private lateinit var leagueParser : LeagueParser

    @Value("\${matches}")
    private val matchesFDO: String = ""

    @Test
    fun testInsertDeleteGetbyid()
    {
        val matches : List<Match> = leagueParser.matches(matchesFDO)
        matches.forEach { it.leagueId = "test" }
        matchRepo.insert(matches)

        val exampleMatch = Match()
        exampleMatch.leagueId = "test"
        exampleMatch.matchDay = 38

        val searchResult = matchRepo.findAll(Example.of(exampleMatch))
        Assert.assertNotNull(searchResult)
        Assert.assertTrue(searchResult.size == matches.size)
        searchResult.forEach { matchRepo.delete(it.id!!) }
    }
}