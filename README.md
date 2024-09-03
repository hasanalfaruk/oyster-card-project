# Oyster-card-project
A limited version of London’s Oyster card system demonstrating a user loading a card with £30, taking set trips, and then viewing the balance.

### Key Features

- Supports Tube journeys across different zones and Bus journeys at a flat fare.
- Calculates fares dynamically based on journey types and entry/exit zones.
- Charges the maximum fare initially, adjusting to the correct fare upon exit.

### Prerequisites

To run this project, ensure you have the following installed:

- [Java 8+]
- [Maven]
- [Git]

Run the application from your IDE or with the command:
mvn exec:java -Dexec.mainClass="com.oyster.Main"

This project follows a Test-Driven Development (TDD) approach:

- Write tests first for every new feature or functionality.
- Implement code to make the tests pass.
- Refactor and improve while ensuring all tests pass.

