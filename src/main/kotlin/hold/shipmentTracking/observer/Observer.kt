package hold.shipmentTracking.observer

import hold.shipmentTracking.models.Shipment

interface Observer {
    fun update(shipment: Shipment)
}
