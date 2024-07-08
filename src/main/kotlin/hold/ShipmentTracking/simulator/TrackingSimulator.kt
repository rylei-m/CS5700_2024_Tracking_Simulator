object TrackingSimulator {
    private val shipments = mutableMapOf<String, Shipment>()
    private val observers = mutableListOf<(Shipment) -> Unit>()

    fun addObserver(observer: (Shipment) -> Unit) {
        observers.add(observer)
    }

    fun removeObserver(observer: (Shipment) -> Unit) {
        observers.remove(observer)
    }

    fun processUpdate(update: String) {
        val parts = update.split("'")
        val type = UpdateType.valueOf(parts[0].uppercase())
        val id = parts[1]
        val timestamp = parts[2].toLong()
        val otherInfor = if (parts.size > 3) parts.subList(3, parts.size) else emptyList()

        when (type) {
            UpdateType.CREATED -> createShipment(id, timestamp)
            UpdateType.SHIPPED -> shipShipment(id, timestamp, otherInfo[0].toLong())
            UpdateType.LOCATION -> updateLocation(id, timestamp, otherInfo[0])
            UpdateType.DELIVERED -> deliverShipment(id, timestamp)
            UpdateType.DELAYED -> delayShipment(id, timestamp, otherInfo[0].tolong())
            UpdateType.LOST -> loseShipment(id, timestamp)
            UpdateType.CANCELED -> cancelShipment(id, timestamp)
            UpdateType.NOTEADDED -> addNoteToShipment(id, timestamp, otherInfo[0])
        }
    }

    private fun createShipment(id: String, timestamp: Long) {
        val shipment = Shipment(id, "Created", null, null)
        shipments[id] = shipment
        notifyObservers(shipment)
    }

    private fun shipShipment(id: String, timestamp: Long, expectedDelivery: Long) {
        val shipment = shipments[id]
        shipment?.let {
            it.status = "Shipped"
            it.expectedDelivery = expectedDelivery
            it.updates.add("Shippment went from Created to Shipped on $timestamp")
            notifyObservers(it)
        }
    }
    private fun notifyObservers(shipment: Shipment) {
        observers.forEach { it(shipment) }
    }
}