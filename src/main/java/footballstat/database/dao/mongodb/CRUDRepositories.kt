package footballstat.database.dao.mongodb

import footballstat.database.dao.entity.MongoLeague
import footballstat.database.dao.entity.MongoTeam
import model.Country
import model.football.Match
import model.football.Player
import model.football.Team
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface CountryMongoRepository : MongoRepository<Country, String> { }

@Repository
interface MatchMongoRepository : MongoRepository<Match, String> { }

@Repository
interface TeamMongoRepository : MongoRepository<Team, String> { }

@Repository
interface PlayerMongoRepository : MongoRepository<Player, String> { }

@Repository
interface LeagueMongoRepository : MongoRepository<MongoLeague, String> { }

@Repository
interface TeamMongoMongoRepository : MongoRepository<MongoTeam, String> { }