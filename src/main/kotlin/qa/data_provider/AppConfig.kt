package qa.data_provider

import CustomerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import qa.data_provider.repositories.CustomerRepository

@Configuration
class AppConfig {
    @Bean
    fun customerService(customerRepository: CustomerRepository): CustomerService {
        return CustomerService(customerRepository)
    }
}