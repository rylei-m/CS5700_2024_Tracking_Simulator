class UserInterface {
    private val viewHelper = TrackerViewHelper()

    fun start() {
        //ui setup and user input
    }

    fun trackShipment(id: String) {
        viewHelper.trackShipment(id)
    }
}