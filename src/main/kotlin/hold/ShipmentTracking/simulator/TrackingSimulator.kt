import org.example.Shipment

object TrackingSimulator {
    private val shipments = mutableMapOf<String, Shipment>()
    private val observers = mutableListOf<(Shipment) -> Unit>()

    override fun addObserver(observer: (Shipment) -> Unit) {
        observers.add(observer)
    }

    override fun removeObserver(observer: (Shipment) -> Unit) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach {
            observer -> shipments.values.forEach {
                observer.update(it)
            }
        }
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

    private fun updateLocation(id: String, timestamp: Long, location: String) {
        val shipment = shipments[id]
        shipment?.let {
            it.location = location
            it.updates.add("Shipment arrived at $location on $timestamp")
            notifyObservers(it)
        }
    }

    private fun deliverShipment(id: String, timestamp: Long) {
        val shipment = shipments[id]
        shipment?.let {
            it.status = "Delivered"
            it.updates.add("Shipping arrived at $timestamp")
            notifyObservers(it)
        }
    }

    private fun delayShipment(id: String, timestamp: Long, newExpectedDelivery: Long) {
        val shipment = shipments[id]
        shipment?.let {
            it.status = "Delayed"
            it.expectedDelivery = newExpectedDelivery
            it.updates.add("Shipping delayed on $timestamp, new expected delivery on $newExpectedDelivery")
            notifyObservers(it)
        }
    }

    private fun cancelShipment(id: String, timestamp: Long) {
        val shipment = shipments[id]
        shipment?.let {
            it.status = "Cancelled"
            it.updates.add("Shipping cancelled on $timestamp")
            notifyObservers(it)
        }
    }

    private fun addNoteToShipment(id: String, timestamp: Long, note: String) {
        val shipment = shipments[id]
        shipment?.let {
            it.notes.add(note)
            it.updates.add("Note added on $timestamp: $note")
            notifyObservers(it)
        }
    }

    fun getShipment(id: String): Shipment? {
        return shipments[id]
    }

    private fun notifyObservers(shipment: Shipment) {
        observers.forEach { it(shipment) }
    }

    fun processUpdate(update: String) {
        // idk yet
    }

    fun getShipment(id: String): Shipment? {
        return shipments[id]
    }
}