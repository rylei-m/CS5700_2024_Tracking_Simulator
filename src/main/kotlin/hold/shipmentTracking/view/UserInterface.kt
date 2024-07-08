package hold.shipmentTracking.view

class UserInterface {
    private val viewHelper = TrackerViewHelper()

    fun start() {
        while (true) {
            println("Enter a command (track, stop, exit):")
            when (readLine()?.trim()) {
                "track" -> {
                    println("Enter shipment ID for tracking:")
                    val id = readLine()?.trim()
                    if (id != null) {
                        viewHelper.trackShipment(id)
                    }
                }
                "stop" -> {
                    println("Enter shipment ID to stop tracking:")
                    val id = readLine()?.trim()
                    if (id != null) {
                        viewHelper.stopTrackingShipment(id)
                    }
                }
                "exit" -> return
                else -> println("Invalid command.")
            }
        }
    }
}