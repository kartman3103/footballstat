package footballstat.database.dao.mongodb


import model.football.Match
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service

@Service
open class MatchDAO : DefaultMongoRepository<Match>() {
    @Autowired
    lateinit var matchRepo : MatchRepo

    override fun getMongoRepository(): MongoRepository<Match, String> {
        return matchRepo
    }
}