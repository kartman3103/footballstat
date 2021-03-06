package footballstat.services.json

import model.football.Team
import model.football.TournamentStatistic
import org.codehaus.jackson.JsonNode
import java.util.*

abstract class TeamsParser
{
    fun getTeams(standings : JsonNode) : ArrayList<Team>
    {
        val teams = ArrayList<Team>()
        for (jsonNode in standings.elements) {
            val id = teamId(jsonNode)
            if (id != null)
            {
                val team = Team()
                team.id = id.toString()
                team.Name = teamName(jsonNode)

                team.AllStatistic = tournamentStatistic(jsonNode)
                team.HomeStatistic = tournamentStatistic(jsonNode.get("home"))
                team.AwayStatistic = tournamentStatistic(jsonNode.get("away"))

                teams.add(team)
            }
        }
        return teams
    }

    abstract fun teamId(jsonNode: JsonNode) : Int?

    abstract fun teamName(jsonNode: JsonNode) : String?

    abstract fun tournamentStatistic(jsonNode: JsonNode) : TournamentStatistic
}