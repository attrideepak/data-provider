import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "customers") // Optional: Specify the collection name
data class Customer(
    @Id
    var id: String? = null,

    var name: String? = null,

    var preferredName: String? = null,

    @Indexed(unique = true)
    var email: String? = null,

    @Field("mobileNumber")
    @Indexed(unique = true)
    var mobile: String? = null,

    var pincode: Int? = null,

    var metadata: Metadata? = null
)