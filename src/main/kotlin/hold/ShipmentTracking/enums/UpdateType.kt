enum class UpdateType {
    CREATED,
    SHIPPED,
    LOCATION,
    DELIVERED,
    DELAYED,
    LOST,
    CANCELED,
    NOTEADDED
}

data class Shipment(
    val id: String,
    var status: String,
    var location: String?,
    var expectedDelivery: Long?,
    val notes: MutableList<String> = mutableListOf(),
    val updates: MutableList<String> = mutableListOf(),
)