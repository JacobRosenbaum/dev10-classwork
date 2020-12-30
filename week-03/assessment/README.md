# Assessment 3: Solar Farm
## Plan of Attack
I tend to take a bit longer to figure out my code, so I'm allotting an entire day to complete each phase.
* First Day(12/30)
    - Complete the Models

* Second Day(12/31)
    - Complete Data layer
    - Complete testing for Data layer
    
* Third Day(1/1)
    - Complete Domain layer
    - Complete testing for Domain layer


* Fourth Day(1/2)
    - Complete UI Layer
    - Ensure app is functioning properly

## File Structure
* data
    * panels.csv
        - Contains the true data for the project
    * panels-test.csv
        - Contains the test data to assist with testing the repository and service
    * panels-seed.csv
        - Contains the seed data to populate the test repository and assist with testing
        
* src
    * Main
        * Data Layer
            - PanelFileRepository: Class
                - Contains CRUD operations for my txt file data
            - PanelRepository: Interface
                - Contract for CRUD Operations
            - DataAccessException: Class
                - Custom exception containing a message and throwable root cause
        
        * Domain Layer
            - PanelService: Class
                - Contains the rules and pass-through methods for repository CRUD operations
            _ PanelResult: Class
                  - Contains the results: error messages and the "payload" of the CRUD operation
        * UI Layer
            - View: Class
                - Prints the UI onto the Console
            - Controller: Class
                - Acts as a go-between for the service and view, controlls the flow of the program
            - MenuOption: ENUM
                - Holds the menu option items
        * Models
            - Panel: Class
                - Contains the fields, constructors, and getters and setters to be distributed throughout the project
            - MaterialType: ENUM
                - Contains the the different types of materials that a panel can have
        * App: class
            - Runs the application
        
    * Test
        * Data
            - PanelFileRepositoryTest
                - Contains tests to ensure the PanelFileRepository class is working properly
            - PanelRepositoryDouble
                - Contains logic that links to test data to ensure the PanelService is working properly
        * Domain
            - PanelServiceTest
                - Contains tests to ensure the PanelService class is working properly
        
## Tasks

### Models - Estimated completion time: 1 hour
* [ ] Create private fields containing the info we need to know about each panel including the following
    - int Id
    - String Section
    - int Row
    - int Column
    - int yearInstalled
    - boolean isTracking
* [ ] Create a constructor
* [ ] Create getters and setters for the fields
* [ ] Write a method to ensure String equality 
* [ ] Create a stateful Enum for each type of material 
### Data Layer - Estimated completion time: 8 hours
* [ ] Create the PanelRepository Interface to include the following methods (subject to change)
  * CRUD Methods
    - findAll
    - findById
    - findByMaterialType
    - add
    - update
    - deleteById

* [ ] Create the PanelFileRepository implementing the Interface and Override the CRUD Methods to properly access data
* [ ] Write helper methods including the following (subject to change)
    * serialize
    * deserialize
    * writeAll
    * clean
    * restore

* [ ] Create Data Access Exception class
* [ ] Write unit tests to ensure the repository works when it's supposed to and does not work when it's not supposed to
    
    
### Domain Layer - Estimated Completion time: 8 hours
* [ ] Create PanelResult initial fields
    * ArrayList<String> messages
    * Panel panel

* [ ] Create getters and setters for fields
* [ ] Write isSuccess and addErrorMessages methods
* [ ] In the PanelService class, write a PanelRepository repository field
* [ ] Inject that field into a constructor
* [ ] Write both pass-through and logic-intensive CRUD methods taking into account the following rules:
  * Section is required and cannot be blank.
  * Row is a positive number less than or equal to 250.
  * Column is a positive number less than or equal to 250.
  * Year Installed must be in the past.
  * Material is required and can only be one of the five materials listed.
  * Is Tracking is required.
  * The combined values of Section, Row, and Column may not be duplicated.

* [ ] Write a validation helper method (and potentially more helper methods - subject tp change)
* [ ] Create a double with the bare minimum logic to assist with testing(update double as I write unit tests)
* [ ] Write unit tests to ensure the service works when it's supposed to and does not work when it's not supposed to

### UI Layer - Estimated Completion time: 8 hours
* [ ] Create Controller class, View class, and stateful MenuOption Enum
* [ ] In the Controller file, write the following fields
    * PanelService service
    * View view

* [ ] Write a constructor passing in the fields
* [ ] Write a runMenu while loop that switches on the Enum user input and run until they press 0 to exit
* [ ] In the View File, create the following core methods (subject to change)
    * displayMenuOption
    * printAllPanels
    * createPanel
    * printResult

* [ ] Also, write the following helper methods (subject to change)
    * printHeader
    * readString
    * readRequiredString
    * readInt
    * overloaded readInt
    * readType

* [ ] In the controller file, write the following methods to pass the service data along to the view class and print to the viewer(subject to change)
    * displayAllPanels
    * displayByType
    * displayById
    * addPanel
    * updatePanel
    * deletePanel

* [ ]  Write a run method (finally catching my DataAccessException)
* [ ]  Create an App class
* [ ]  Pass the FileRepository into the service
* [ ]  Pass the service and view into the controller
* [ ]  Run the controller
    

    



