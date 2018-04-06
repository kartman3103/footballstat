package footballstat.services

import model.Country
import model.football.League
import model.football.LeagueInfo
import model.football.Match
import model.football.Player

interface DataItems
{
    interface Countries
    {
        fun getCountries() : Collection<Country>
    }

    interface Teams
    {
        fun getTeamSquad(teamId : Int) : Collection<Player>
    }

    interface Leagues
    {
        fun getAvailableLeagues() : List<LeagueInfo>

        fun getLeague(leagueId: String, matchDay: Int) : League

        fun getMatches(leagueId: String, matchDay: Int) : Set<Match>

        fun getMatches(leagueId: String) : Set<Match>
    }
}