package footballstat


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableCaching
@SpringBootApplication
@EnableMongoRepositories("footballstat.database.dao.mongodb")
open class Application : SpringBootServletInitializer()
{
    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder
    {
        return application.sources(Application::class.java)
    }
}

@Throws(Exception::class)
fun main(args: Array<String>)
{
    SpringApplication.run(arrayOf(Application::class.java), args)
}

