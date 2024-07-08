package hold.shipmentTracking.view

import hold.shipmentTracking.models.Shipment
import hold.shipmentTracking.observer.Observer
import hold.shipmentTracking.simulator.TrackingSimulator

class TrackerViewHelper : Observer {
    private val trackedShipments = mutableMapOf<String, Shipment>()

    init {
        TrackingSimulator.addObserver(this)
    }

    fun trackShipment(id: String) {
        val shipment = TrackingSimulator.getShipment(id)
        if (shipment != null) {
            trackedShipments[id] = shipment
            update(shipment)
        }
        else {
            println("Shipment not found")
        }
    }

    fun stopTrackingShipment(id: String) {
        trackedShipments.remove(id)
    }

    override fun update(shipment:Shipment) {
        if (trackedShipments.containsKey(shipment.id)) {
            trackedShipments[shipment.id] = shipment
            println("Shipment ID: ${shipment.id}")
            println("Status: ${shipment.status}")
            println("Location: ${shipment.location}")
            println("Expected Delivery: ${shipment.expectedDelivery}")
            println("Notes: ${shipment.notes.joinToString()}")
            println("Updates: ${shipment.updates.joinToString()}")
        }
    }

    private fun updateUI(shipment: Shipment) {
        // do this later :,(
    }
}