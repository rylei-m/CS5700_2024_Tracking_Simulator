class TrackerViewHelper {
    private val trackedShipments = mutableMapOf<String, Shipment>()

    init {
        TrackingSimulator.addObserver {
            shipment ->
            if (trackedShipments.containsKey(shipment.id)) {
                trackedShipments[shipment.id] = shipment
                updateUI(shipment)
            }
        }
    }

    fun trackShipment(id: String) {
        trackedShipments[id] = TrackingSimulator.getShipment(id)
        updateUI(trackedShipments[id]!!) //!! is non-null assertion maybe use elvis instead?
    }

    fun stopTrackingShipment(id: String) {
        trackedShipments.remove(id)
    }

    private fun updateUI(shipment: Shipment) {
        // do this later :,(
    }
}