# Assignment #2 - Shipment Tracking Simulator

Assignment: build a shipment tracking simulator accompanied by a GUI where a user can type in the id of their shipment and get updates in real time about their shipment. This is just a simulation and shipment information will be read from a file rather than generated as a result of a user actually ordering something.

User interface creates and uses tracker view helper which has the responsibility of preparing data for the using interface to display. The Tracking simulator is a static class the can be accessed from anywhere.


## Objectives
Continue to practice by implement a system that follows the principles of abstraction, encapsulation, and modularity.
Become more familiar with conceptual modelling using UML.
Become more familiar with the basics of unit testing.
Properly apply the strategy and observer patterns.
Requirements
Your shipping simulator should meet the following requirements:

## UML (20 pts)
1.1 Conceptual model using UML first before writing any code.
1.2 UML diagram should include all classes and should correctly model their relationships, attributes, and methods. Indicate which methods are public or private (all attributes should be private).
1.3 UML should correctly model the design patterns you plan on implementing.
Implementation - System should meet the following requirements (65 pts)
2.1: User should be able to type in one or more tracking numbers (IDs) and get real-time updates as the data around each shipment changes. 
This doesn't mean that you need to allow users to type multiple numbers in the same field, rather, they can type in a number and push a "track" button then type in another number and push "track" again. The user should see both shipment information on the screen being updated in real-time.
You are not required to do anything to the UI when a new shipment is created only when an existing shipment is updated, though if you implement that it might make debugging easier.
2.2: User should be able to stop tracking a shipment at any time.
2.3: When a user starts tracking a shipment they should immediately see the following information about the shipment
- The status
- The location
- The date and time that delivery is expected
- The notes added to the shipment
- The updates
Should be text read like "Shipment went from {previousStatus} to {newStatus} on {dateOfUpdate}"
Define what the statuses are but you will need at least the following statuses: created, shipped, lost, canceled, delivered
2.4: If a user tries to track a shipment that doesn't exist then the UI should notify the user in some way (just some text explaining the issue is fine)
2.5: System should read the shipment information from one of the files provided below.
Each line in the file represents an update to shipment and have the following form
updateType,shipmentId,timestampOfUpdate,otherInfo. (otherInfo is optional)
There are 8 types of updates, here is an explanation of each of them.
- "created" - a new shipment has been created
  - Example: "created,1243234,1999283774"
- "shipped" - the shipment has shipped
  - Example: "shipped,1243234,1999437478,19999488398"
  - The final value is the timestamp of the expected delivery date.
- "location" - the shipment has arrived at a new location
  - Example: "location,1243234,1983893498,Los Angeles CA
- "delivered" - the shipment has been delivered to the consumer
  - Example: "delivered,1243234,1999345988"
- "delayed" - the shipment has been delayed
  - Example: "delayed,1243234,1999948389,19999387834"
  - The otherInfo is the new expected delivery date timestamp.
- "lost" - the shipment has been lost
  - Example "lost,1243234,1999934873"
- "canceled" - the shipment was canceled by the consumer
  - Example: "canceled,1243234,199993487"
- "noteadded" - a note was added by the shipper to the shipment
  - Example: "noteadded,1243234,199999495,packaging was damaged slightly during shipping"
The simulation, should only process one update per second. Kotlin coroutines make this easy https://kotlinlang.org/docs/coroutines-guide.htmlLinks to an external site.
2.6: Look for opportunities as a developer to use the strategy pattern (I hope it should be pretty obvious where you could use it)
2.7: Look for opportunities as a developer to use the observer pattern (again, hopefully pretty obvious...)
Unit Tests (35 points)
Executable unit test cases should give reasonable assurances that the system is working correctly.
Executable unit test cases should have high coverage in terms of code path.
You do not need to write unit tests for your user interface but you should test the user interface thoroughly by using it yourself (we call this ad hoc testing).
