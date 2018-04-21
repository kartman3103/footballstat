package footballstat.database.dao.mongodb


import footballstat.database.dao.entity.MongoTeam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service

@Service
open class MongoTeamDAO : DefaultMongoRepository<MongoTeam>() {
    @Autowired
    lateinit var teamRepo : TeamRepo

    override fun getMongoRepository(): MongoRepository<MongoTeam, String> {
        return teamRepo
    }
}