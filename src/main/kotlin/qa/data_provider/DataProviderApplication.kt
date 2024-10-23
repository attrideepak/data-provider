package qa.data_provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
@ComponentScan(basePackages = ["qa.data_provider"])
class DataProviderApplication

fun main(args: Array<String>) {
	runApplication<DataProviderApplication>(*args)
}





