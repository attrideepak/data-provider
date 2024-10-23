package qa.data_provider.controller

import Customer
import CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import qa.data_provider.model.ErrorResponse

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/v1/customer/email")
    fun getCustomerByEmail(@RequestParam email: String): Customer {
        return customerService.findCustomerByEmail(email)
    }

    @GetMapping("/v1/customer/mobile")
    fun getCustomerByMobile(@RequestParam mobile: String): Customer {
        return customerService.findCustomerByMobile(mobile)
    }

    @GetMapping("/v1/customer/{id}")
    fun getCustomerById(@PathVariable id: String): ResponseEntity<Any> {
        val customer = customerService.findCustomerById(id)
        return if (customer.isPresent) {
            ResponseEntity(customer.get(), HttpStatus.OK)
        } else {
            val errorResponse = ErrorResponse(
                message = "Customer not found with id: $id",
                status = HttpStatus.NOT_FOUND.value()
            )
            ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
        }
    }


    @GetMapping("/v1/customer/tag")
    fun getCustomersByTag(@RequestParam tag: String): ResponseEntity<List<Customer>> {
        val customers = customerService.findCustomersByTag(tag)
        return ResponseEntity(customers, HttpStatus.OK)
    }

    @PostMapping("/v1/customer")
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val savedCustomer = customerService.saveCustomer(customer)
        return ResponseEntity(savedCustomer, HttpStatus.CREATED)
    }

    @PutMapping("/v1/customer/{id}")
    fun updateCustomer(@PathVariable id: String, @RequestBody customer: Customer): ResponseEntity<Customer> {
        val updatedCustomer = customerService.updateCustomer(id, customer)
        return ResponseEntity(updatedCustomer, HttpStatus.OK)
    }

    @DeleteMapping("/v1/customer/{id}")
    fun deleteCustomer(@PathVariable id: String): ResponseEntity<Unit> {
        val updatedCustomer = customerService.deleteCustomer(id)
        return ResponseEntity(updatedCustomer, HttpStatus.NO_CONTENT)
    }

}