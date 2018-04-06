package footballstat.services.json

import model.football.League
import model.football.LeagueInfo
import model.football.Match

interface LeagueParser
{
    fun availableLeagues(json: String) : List<LeagueInfo>

    fun matches(json: String) : List<Match>

    fun league(json: String) : League
}