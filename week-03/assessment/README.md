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
                - Contains the rules and pass-through methods for repository CRUD operations _ PanelResult: Class
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
#### Actual time: 20 minutes 

* [x] Create private fields containing the info we need to know about each panel including the following
    - int Id
    - String Section
    - int Row
    - int Column
    - int yearInstalled
    - boolean isTracking
* [x] Create a constructor
* [x] Create getters and setters for the fields
* [ ] Write a method to ensure String equality
* [x] Create a stateful Enum for each type of material

### Data Layer - Estimated completion time: 8 hours
#### Actual time: 3 Hours 
##### Added 1.5 hours to dive into delimiters

* [x] Create the PanelRepository Interface to include the following methods (subject to change)
    * CRUD Methods
        - findAll
        - findById
        - findByMaterialType
        - add
        - update
        - deleteById

* [x] Create the PanelFileRepository implementing the Interface and Override the CRUD Methods to properly access data
* [x] Write helper methods including the following (subject to change)
    * serialize
    * deserialize
    * writeAll
    * clean
    * restore

* [x] Create Data Access Exception class
* [x] Write unit tests to ensure the repository works when it's supposed to and does not work when it's not supposed to
  including the following (subject to a lot of change):
    * shouldAdd
    * shouldNotAdd
    * shouldUpdate
    * shouldNotUpdate
    * shouldFindAll
    * shouldFindByType
    * shouldNotFindMissingType
    * shouldDelete
    * shouldNotDelete

### Domain Layer - Estimated Completion time: 8 hours
#### Actual time: 6 Hours 

* [x] Create PanelResult initial fields
    * ArrayList<String> messages
    * Panel panel

* [x] Create getters and setters for fields
* [x] Write isSuccess and addErrorMessages methods
* [x] In the PanelService class, write a PanelRepository repository field
* [x] Inject that field into a constructor
* [x] Write both pass-through and logic-intensive CRUD methods taking into account the following rules:
    * Section is required and cannot be blank.
    * Row is a positive number less than or equal to 250.
    * Column is a positive number less than or equal to 250.
    * Year Installed must be in the past.
    * Material is required and can only be one of the five materials listed.
    * Is Tracking is required.
    * The combined values of Section, Row, and Column may not be duplicated.

* [x] Write a validation helper method (and potentially more helper methods - subject tp change)
* [x] Create a double with the bare minimum logic to assist with testing(update double as I write unit tests)
* [x] Write unit tests to ensure the service works when it's supposed to and does not work when it's not supposed to
  including the following (subject to a lot of change):
    * shouldAdd
    * shouldNotAdd
    * shouldUpdate
    * shouldNotUpdate
    * shouldFindAll
    * shouldFindByType
    * shouldNotFindMissingType
    * shouldDelete
    * shouldNotDelete
### UI Layer - Estimated Completion time: 8 hours
#### Actual time: 8 Hours 

* [x] Create Controller class, View class, and stateful MenuOption Enum
* [x] In the Controller file, write the following fields
    * PanelService service
    * View view

* [x] Write a constructor passing in the fields
* [x] Write a runMenu while loop that switches on the Enum user input and run until they press 0 to exit
* [x] In the View File, create the following core methods (subject to change)
    * displayMenuOption
    * printAllPanels
    * createPanel
    * printResult

* [x] Also, write the following helper methods (subject to change)
    * printHeader
    * readString
    * readRequiredString
    * readInt
    * overloaded readInt
    * readType

* [x] In the controller file, write the following methods to pass the service data along to the view class and print to
  the viewer(subject to change)
    * displayAllPanels
    * displayByType
    * displayById
    * addPanel
    * updatePanel
    * deletePanel

* [x]  Write a run method (finally catching my DataAccessException)
* [x]  Create an App class
* [x]  Pass the FileRepository into the service
* [x]  Pass the service and view into the controller
* [x]  Run the controller
    

    



