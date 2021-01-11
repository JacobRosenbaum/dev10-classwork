# Bugs
## Add a Forage

* Lower case letter does not get any search results
  * [x] Solved by adding "toUpperCase" in ForagerService findByLastName method
  
* Names do not appear in order
  * [x] Solved by adding .sorted(Comparator.comparing(Forager::getFirstName)) in ForagerService findByLastName method

* Validation error! Program allows user to add a Forage with the same date, item, & Forager. 
  * [x] Wrote a forageDuplicateValidation method to solve issue
  
## ForageFileRepository
* Deserialize does not remove delimiter
  * [x] Wrote cleanField and restoreField methods to solve
  
## ItemService
* Does not check for null category
  * [x] Wrote a conditional inside the validate method
  
## ForagerServiceTest
* MISSING
  * [x] Created file and wrote tests
  
## View Foragers
* MISSING
  * [x] Added feature
  
## Testing
* Many missing validation tests
  * [x] Wrote validation CRUD tests to cover every method
    