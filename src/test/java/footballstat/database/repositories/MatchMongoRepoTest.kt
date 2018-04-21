package footballstat.database.repositories

import footballstat.database.dao.mongodb.MatchRepo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
open class MatchMongoRepoTest {
    @Autowired
    private lateinit var matchRepo: MatchRepo

    @Test
    fun testContext() {
    }
}