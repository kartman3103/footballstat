package footballstat.database.dao.mongodb

import footballstat.database.dao.entity.MongoLeague
import footballstat.database.dao.entity.MongoTeam
import model.football.Match
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchRepo : MongoRepository<Match, String>

@Repository
interface LeagueRepo : MongoRepository<MongoLeague, String>

@Repository
interface TeamRepo : MongoRepository<MongoTeam, String>