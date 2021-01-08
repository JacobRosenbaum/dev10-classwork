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


* Missing Feature!
    - View Foragers is missing!
        * Is there existing code that I can use?
            * [ ] Yes
            * [ ] No
  
* Tests:
    - ForagerFileRepository
        - Failed tests?
            * [ ] Yes
            * [ ] No
        - Missing tests?
          * [ ] Yes
          * [ ] No
  - ForageFileRepository
      - Failed tests?
          * [ ] Yes
          * [ ] No
      - Missing tests?
          * [ ] Yes
          * [ ] No
    - ItemFileRepository
        - Failed tests?
            * [ ] Yes
            * [ ] No
        - Missing tests?
            * [ ] Yes
            * [ ] No
    - ForagerService
        - Failed tests?
            * [ ] Yes
            * [ ] No
        - Missing tests?
            * [ ] Yes
            * [ ] No
    - ItemService
        - Failed tests?
            * [ ] Yes
            * [ ] No
        - Missing tests?
            * [ ] Yes
            * [ ] No
    
* Missing test!
    - Forager Service test is missing!
        TODO:
            * [ ] Add positive and negative test cases for findByState
            * [ ] Add positive and negative test cases for findByLastName
      

### Code Execution
#### Main package
* Add a Forager - Time Estimate: 2 hours
    - TODO:
        * Add the following methods to ForagerRepository
            * [ ] add
        * Add the following methods to ForagerFileRepository
            * [ ] add 
            * [ ] writeAll
            * [ ] serialize
          
        * Add the following methods to ForagerService
            * [ ] add
            * [ ] validate by...
                * [ ]  First name is required.
                * [ ]  Last name is required. 
                * [ ]  State is required.
                * [ ]  The combination of first name, last name, and state cannot be duplicated.
                * [ ]  Forager ID is a system-generated GUID (globally unique identifier).
      * Add the following methods to View
            * [ ] makeForager
          
      * Add the following methods to Controller
            * [ ] addForager
        
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
    

* To implement the new features, what packages, classes, and methods will I write?
* What unit tests do I need to write to ensure that my code behaves as expected?
* Do I have to do any research?
* Are there any unknowns? What do I need to do to get clarity?
* What are my primary tasks?
* How long do I estimate each of those tasks will take?
* Are there any dependencies between tasks? What order do I need to complete the tasks in?