package footballstat.database


import footballstat.database.dao.DAO
import footballstat.database.dao.entity.MongoTeam
import footballstat.services.SportData
import model.football.League
import model.football.LeagueInfo
import model.football.Match
import model.football.Player
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Component
import java.util.*

@Component
@ManagedResource
open class DBService {
    private val logger : Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var sportDataProvider : SportData

    @Autowired
    private lateinit var leagueDAO : DAO<League>

    @Autowired
    private lateinit var matchDAO : DAO<Match>

    @Autowired
    private lateinit var teamMongoMongoRepository : DAO<MongoTeam>

    fun hasDBConnection() : Boolean {
        return true
    }

    @ManagedOperation(description = "init database from footbal-data-org")
    fun initDB() {
        try {
            sportDataProvider.getAvailableLeagues().forEach {
                loadLeague(it)
                loadMatches(it)
            }
            logger.info("YEAH! DATABASE HAS SUCCESSFUL INIT")
        }
        catch (e : Exception)
        {
            logger.error("fail to load from api.", e)
        }
    }

    private fun loadLeague(leagueInfo: LeagueInfo) {
        logger.info("_______________ START LOAD LIAGUE ${leagueInfo.shortName}")
        var league : League? = null
        var matchDay = 1
        val teamIds : MutableSet<String> = HashSet<String>()
        while (matchDay <= leagueInfo.toursPlayed) {
            league = getFilledLeague(getLeague(leagueInfo.id, matchDay), leagueInfo)
            teamIds.addAll(league.Teams!!.map() { it.id!! })
            leagueDAO.insert(league)
            logger.info("_______________ success load league ${leagueInfo.shortName} matchday: $matchDay")
            matchDay++
        }
        teamIds.forEach { saveTeamPlayers(it) }
        logger.info("_______________ FINISH LOAD LIAGUE ${leagueInfo.shortName}")
    }

    private fun loadMatches(leagueInfo: LeagueInfo) {
        logger.info("_______________ START LOAD MATCHES FOR ${leagueInfo.shortName}")
        var matchDay = 1
        while (matchDay <= leagueInfo.toursPlayed) {
            val matches : Set<Match> = getMatch(leagueInfo.id, matchDay)
            matchDAO.insertAll(matches)
            logger.info("_______________ success load matches for league ${leagueInfo.shortName} matchday: $matchDay")
            matchDay++
        }
        logger.info("_______________ FINISH MATCHES FOR ${leagueInfo.shortName}")
    }

    private fun getFilledLeague(league : League, leagueInfo: LeagueInfo) : League{
        return with(league) {
            ToursPlayed = leagueInfo.toursPlayed
            ShortName = leagueInfo.shortName
            Year = leagueInfo.year
            this
        }
    }

    private fun saveTeamPlayers(teamId: String) {
        teamMongoMongoRepository.insert(with(MongoTeam()) {
            id = teamId
            Players = getTeamSquad(teamId.toInt())
            this})
    }

    private fun getTeamSquad(teamId: Int) : Collection<Player> {
        try {
            Thread.sleep(3000)
            return sportDataProvider.getTeamSquad(teamId)
        }
        catch (e: Exception) {
            logger.warn("Fail to load TeamSquad with id=$teamId. Exception.message = ${e.message}. Exception.type = ${e.javaClass.name}")
            Thread.sleep(60000)
            return getTeamSquad(teamId)
        }
    }

    private fun getLeague(leagueId : String, matchday : Int) : League {
        try {
            Thread.sleep(3000)
            return sportDataProvider.getLeague(leagueId, matchday)
        }
        catch (e: Exception) {
            logger.warn("Fail to load League with id=$leagueId matchday=$matchday. Exception.message = ${e.message}. Exception.type = ${e.javaClass.name}")
            Thread.sleep(60000)
            return getLeague(leagueId, matchday)
        }
    }

    private fun getMatch(leagueId : String, matchday : Int) : Set<Match> {
        try {
            Thread.sleep(3000)
            val res = sportDataProvider.getMatches(leagueId, matchday)
            res.forEach { it.leagueId = leagueId }
            return res
        }
        catch (e: Exception) {
            logger.warn("Fail to load MatchSet with id=$leagueId matchday=$matchday. Exception.message = ${e.message}. Exception.type = ${e.javaClass.name}")
            Thread.sleep(60000)
            return getMatch(leagueId, matchday)
        }
    }
}


