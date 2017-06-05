package footballstat.services.providers

import footballstat.model.football.Team
import footballstat.services.DataItems
import footballstat.services.ExternalProvider
import org.codehaus.jackson.JsonNode
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.node.ArrayNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

class LeaguesProvider
{
    @Component
    open class ExternalLeaguesProvider : DataItems.Leagues
    {
        @Autowired
        lateinit var externalProvider : ExternalProvider

        override fun getLeague(leagueId: Int, year: Int): Collection<Team>
        {
            val response = externalProvider.getResponse("http://api.football-data.org/v1/competitions/426/leagueTable")

            val mapper = ObjectMapper()
            val jsonNode = mapper.readTree(response)

            val standings = jsonNode.get("standing") as ArrayNode

            val result = ArrayList<Team>()
            for (team in standings.elements)
            {
                val teamName = team.get("teamName")
                val resultTeam = Team(teamName.toString())
                result.add(resultTeam)
            }
            return result
        }
    }
}