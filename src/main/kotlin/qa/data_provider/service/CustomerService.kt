import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import qa.data_provider.repositories.CustomerRepository
import java.util.*
import java.util.logging.Logger

@Service
class CustomerService(@Autowired private val customerRepository: CustomerRepository) {

    private val logger: Logger = Logger.getLogger(CustomerService::class.java.name)

    fun findCustomerByEmail(email: String): Customer {
        logger.info("Finding customer by email: $email")
        return customerRepository.findByEmail(email)
    }

    fun findCustomerByMobile(mobile: String): Customer {
        return customerRepository.findByMobile(mobile)
    }

    fun findCustomerById(id: String): Optional<Customer> {
        return customerRepository.findById(id)
    }

    fun findCustomersByTag(tag: String): List<Customer> {
        return customerRepository.findByMetadataTagsIn(listOf(tag))
    }

    fun saveCustomer(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    fun deleteCustomer(id: String) {
        return customerRepository.deleteById(ObjectId(id))
    }

    fun updateCustomer(id: String, customer: Customer): Customer {
        val existingCustomer = customerRepository.findById(id).orElseThrow { throw Exception("Customer not found") }

        val updatedMetadata = existingCustomer.metadata?.copy(
            createdAt = existingCustomer.metadata?.createdAt // Ensure created_at is preserved
        ) ?: customer.metadata

        val updatedCustomer = existingCustomer.copy(
            name = customer.name ?: existingCustomer.name,
            preferredName = customer.preferredName ?: existingCustomer.preferredName,
            email = customer.email ?: existingCustomer.email,
            mobile = customer.mobile ?: existingCustomer.mobile,
            pincode = customer.pincode ?: existingCustomer.pincode,
            metadata = updatedMetadata
        )
        return customerRepository.save(updatedCustomer)
    }
}