package org.example.hold.ShipmentTracking.observer

import org.example.hold.ShipmentTracking.models.Shipment

interface Observer {
    fun update(shipment: Shipment)
}