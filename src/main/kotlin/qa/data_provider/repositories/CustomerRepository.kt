package qa.data_provider.repositories

import Customer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : MongoRepository<Customer, ObjectId> {
    fun findByEmail(email: String): Customer
    fun findByMobile(mobile: String): Customer
    fun findById(id: String): Optional<Customer>
    fun findByMetadataTagsIn(tags: List<String>): List<Customer>

}