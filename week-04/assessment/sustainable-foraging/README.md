# Assessment 4: Sustainable Foraging

## Tasks

### Code Review:

* Review provided code: time estimate - 3 hours
    - Test each of the following features in the console
        * [x] View Forages By Date
          - Initial testing:
            * Does it work?
                * [x] Yes
                * [ ] No
            * Are validations/requirements correct?
                * [x] Yes
                * [ ] No
        * [x] View Items
          - Initial testing:
            * Does it work?
                * [x] Yes
                * [ ] No
            * Are validations/requirements correct?
                * [x] Yes
                * [ ] No
        * [ ] Add a Forage
          - Initial testing:
            * Does it work?
                * [x] Yes
                * [ ] No
            * Are validations/requirements correct?
                * [ ] Yes
                * [x] No
          - Bugs:
            * Lower case letter does not get any results
                * [x] Solved by adding "toUpperCase" in ForagerService findByLastName method
                * TODO
                    * [ ] Create a positive and negative test case confirming this works
            * Names do not appear in order
                * [x] Solved by adding .sorted(Comparator.comparing(Forager::getLastName)) in ForagerService findByLastName method
            * Validation error! Program allows user to add a Forage with the same date, item, & Forager
                * [x] Wrote a forageDuplicateValidation method to solve issue
                
                * TODO:
                    * [ ] Add validation method in ForageService to ensure user cannot enter duplicate Forages  
                    * [ ] Create a positive and negative test case confirming this works

        * [x] Add an Item
          - Initial testing:
            * Does it work?
                * [x] Yes
                * [ ] No
            * Are validations/requirements correct?
                * [x] Yes
                * [ ] No
              
        * List of completed features:
            - View Forages By Date
            - View Items
            - Add an Item
        * List of unfinished features:
            - Add a Forage
            - Add a Forager
            - Report: Kilograms of Item
            - Report: Item Category Value
            - View Foragers

* Features that need to be added:
    - Add a Forager.
        * Is there existing code that I can use?
            * [x] Yes
            * [ ] No
        - ForagerService has a couple useful find methods
        - ForagerFileRepository has a couple useful find methods
    - Create a report that displays the kilograms of each Item collected on one day.
        * Is there existing code that I can use?
            * [x] Yes
            * [ ] No
        - ForageService findByDate method
    - Create a report that displays the total value of each Category collected on one day.
        * Is there existing code that I can use?
            * [x] Yes
            * [ ] No
        - ForageService findByDate method
    - BUG!
        * [ ] Deserialize does not remove delimiter


* Missing Feature!
    - View Foragers is missing!
        * Is there existing code that I can use?
            * [x] Yes
            * [ ] No
        - findAll methods in ForagerService & ForagerRepositories
  
* Tests:
    - ForagerFileRepository
        - Failed tests?
            * [ ] Yes
            * [x] No
        - Missing tests?
          * [x] Yes
          * [ ] No
            - Missing tests include:
                - findAllShouldReturnCorrectNumberOfForagers
                - findAllShouldReturnCorrectForager
                - shouldFindById
                - shouldNotFindMissingId
                - shouldFindByState
                - shouldNotFindMissingState
  - ForageFileRepository
      - Failed tests?
          * [ ] Yes
          * [x] No
      - Missing tests?
          * [x] Yes
          * [ ] No
            - Missing tests include:
                - shouldNotFindMissingDate
                - shouldNotAdd
    - ItemFileRepository
        - Failed tests?
            * [ ] Yes
            * [x] No
        - Missing tests?
            * [x] Yes
            * [ ] No
            Missing tests include:
                - shouldNotFindMissingId
    - ForageService
        - Failed tests?
            * [ ] Yes
            * [x] No
        - Missing tests?
            * [x] Yes
            * [ ] No
            Missing tests include
                - shouldFindByDate
                - shouldNotFindMissingDate
                - shouldNotAddIfForagerIsNull
                - shouldNotAddIfItemIsNull
                - shouldNotAddIfDateIsNull
                - shouldNotAddIfDateIsInFuture
                - shouldNotAddIfKGOver250
                - shouldNotAddIfKGUnder0
                - shouldNotAddDuplicateForage
        
    - ItemService
        - Failed tests?
            * [ ] Yes
            * [x] No
        - Missing tests?
            * [x] Yes
            * [ ] No
            Missing tests include
                - shouldFindByCategory
                - shouldNotFindMissingCategory
                - shouldNotAddNullCategory
                - shouldNotAddDuplicateName
    
* Missing test!
    - Forager Service test is missing!
        * TODO (at minimum):
            - Add positive and negative test cases for findByState
            - Add positive and negative test cases for findByLastName
      

### Code Execution
#### Main package
##### Add Features
* Add a Forager - Time Estimate: 2 hours
    - TODO:
        * Add the following methods to ForagerRepository
            * [x] add
        * Add the following methods to ForagerFileRepository
            * [x] add 
            * [x] writeAll
            * [x] serialize
          
        * Add the following methods to ForagerService
            * [x] add
            * [x] validate by...
                * [x]  First name is required.
                * [x]  Last name is required. 
                * [x]  State is required.
                * [x]  The combination of first name, last name, and state cannot be duplicated.
                * [x]  Forager ID is a system-generated GUID (globally unique identifier).
      * Add the following methods to View
            * [x] makeForager
          
      * Add the following methods to Controller
            * [x] addForager
        
* Kilogram report - Time Estimate: 3 hours
    * TODO:
      * [ ] Add a ReportService class to the domain with the following methods
            * [ ] findByItem (helper method)
            * [ ] getSumOfKGPerItem
        * Add the following methods to View
            * [ ] displayKilogramByForageReport
      * Add the following methods to Controller
          * [ ] reportKG

* Item Category Value report - Time Estimate: 3 hours
    * TODO:
        * Add the following methods to ReportService
          * [ ] findByCategory (helper method)
          * [ ] getSumOfCategoryValue
            
       * Add the following methods to View
         * [ ] displayItemCategoryReport
        * Add the following methods to Controller
         * [ ] reportItemCategory
    
* View Foragers - Time Estimate: 1 hour
    * TODO:
        * Add the following methods to ForagerService
          * [ ] findAll
       * Add the following methods to Controller
          * [ ] viewForagers
       * Add the following methods to View
          * [ ] displayForagers
    
#### Test package

* ForageFileRepositoryTest - Time Estimate: 15 minutes
    * TODO:
        * Add the following tests
            * [ ] shouldNotFindMissingDate
            * [ ] shouldNotAdd

* ForagerFileRepositoryTest - Time Estimate: 1 hour
    * TODO:
        * Add the following tests
          * [ ] findAllShouldReturnCorrectNumberOfForagers
          * [ ] findAllShouldReturnCorrectForager
          * [ ] shouldFindById
          * [ ] shouldNotFindMissingId
          * [ ] shouldFindByState
          * [ ] shouldNotFindMissingState
    

* ItemFileRepositoryTest - Time Estimate: 45 minutes
    * TODO:
        * Add the following tests
          * [ ] shouldFindByCategory
          * [ ] shouldNotFindMissingCategory
          * [ ] shouldNotAddNullCategory
          * [ ] shouldNotAddDuplicateName

* ForageServiceTest - Time Estimate: 2 hours
    * TODO:
        * Add the following tests
          * [ ] shouldFindByDate
          * [ ] shouldNotFindMissingDate
          * [ ] shouldNotAddIfForagerIsNull
          * [ ] shouldNotAddIfItemIsNull
          * [ ] shouldNotAddIfDateIsNull
          * [ ] shouldNotAddIfDateIsInFuture
          * [ ] shouldNotAddIfKGOver250
          * [ ] shouldNotAddIfKGUnder0
          * [ ] shouldNotAddDuplicateForage
            
        * [ ] Ensure test double represents accurate information

* ItemServiceTest - Time Estimate: 1 hour
    * TODO:
        * Add the following tests
            * [ ] shouldFindByCategory
          * [ ] shouldNotFindMissingCategory
          * [ ] shouldNotAddNullCategory
          * [ ] shouldNotAddDuplicateName

        * [ ] Ensure test double represents accurate information

* ForagerServiceTest - Time Estimate: 2 hours
    * TODO:
        * Add the following tests
            * [ ] shouldFindByState
            * [ ] shouldNotFindMissingState
            * [ ] shouldFindByLastName
            * [ ] shouldNotFindMissingLastName
            * [ ] shouldAddForager
            * [ ] shouldNotAddIfFirstNameIsNull
            * [ ] shouldNotAddIfLastNameIsNull
            * [ ] shouldNotAddDuplicateForager

        * [ ] Ensure test double represents accurate information
    

### Order of Operations

* [x] Fix bug in "Add a Forage"
* [x] Write missing tests to ensure no other bugs are present in the given code
    * [ ] Depending on outcome of missing tests, might have to fix some of the given code
* [x] Write "Add a Forager" feature
* [ ] Test new feature
* [x] Write "View Foragers" feature
* [ ] Test new feature
* [ ] Write "KG Report" feature
* [ ] Test new feature
* [ ] Write "Category Value Report" feature
* [ ] Test new feature
* [ ] Re-Factor App class, and all components, services, and repositories to include Spring DI Annotations