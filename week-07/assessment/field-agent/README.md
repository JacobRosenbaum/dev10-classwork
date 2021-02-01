# Assessment 7: Field Agent

### Goals
* [ ] Create full HTTP CRUD for security clearance.
* [ ] Create full HTTP CRUD for agent aliases.
* [ ] Implement global error handling.
    * Estimated Time of Completion (ETC): 19 hours 50 minutes (upon further consideration, I've determined this estimate to be (hopefully) padded)
    * Actual Time of Completion (ATC): 14 hours 47 minutes

### File updates for Security Clearance
* SecurityClearanceRepository (existing Interface - needs following updates)
    * Create following methods
        * [x] List<SecurityClearance> findAll();
        * [x] SecurityClearance add(SecurityClearance securityClearance)
        * [x] boolean update(SecurityClearance securityClearance)
        * [x] @Transactional boolean deleteById(int securityClearanceId)

* SecurityClearanceJdbcTemplateRepository (existing file - needs following updates)
    * Create following methods
        * [x] List<SecurityClearance> findAll();
        * [x] SecurityClearance add(SecurityClearance securityClearance)
        * [x] boolean update(SecurityClearance securityClearance)
        * [x] @Transactional boolean deleteById(int securityClearanceId)
    
* SecurityClearanceService (new file)
    * Create following methods
        * [x] List<SecurityClearance> findAll();
        * [x] SecurityClearance findById(int securityClearanceId)  
        * [x] Result<SecurityClearance> add(SecurityClearance securityClearance)
        * [x] Result<SecurityClearance> update(SecurityClearance securityClearance)
        * [x] boolean deleteById(int securityClearanceId)
        * [x] Result<SecurityClearance> validate
            * Security clearance name is required.
            * Name cannot be duplicated.
    
* SecurityClearanceController (new file)
    * Create following methods (class has @RequestMapping of "/api/security/clearance")
        * [x] @GetMapping ("/") List<SecurityClearance> findAll()
        * [x] @GetMapping ("/{securityClearanceId}") ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId)
        * [x] @PostMapping ResponseEntity<Object> add(@RequestBody SecurityClearance securityClearance)
        * [x] @PutMapping ("/{securityClearanceId}") ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance securityClearance)
        * [x] @DeleteMapping ("/{securityClearanceId}") ResponseEntity<void> delete(@PathVariable int securityClearanceId)

### File updates for Alias

* Alias (new model)
    * Fields:
        * [x] int aliasId
        * [x] String name
        * [x] String persona
        * [x] int agentId
    * Methods:
        * [x] getters and setters for each field
    
* Agent (existing model - need following updates)
    * Fields:
        * [x] List<Alias> aliases
    * Methods
        * [x] getter and setter for aliases

* AliasRepository (new Interface)
    * Create following methods
        * [x] List <Alias> findByAgentId(int agentId);
        * [x] Alias add(Alias alias)
        * [x] boolean update(Alias alias)
        * [x] boolean deleteById(int aliasId)

* AliasJdbcTemplateRepository (new file)
    * Create following methods
        * [x] List <Alias> findByAgentId(int agentId);
        * [x] Alias add(Alias alias)
        * [x] boolean update(Alias alias)
        * [x] boolean deleteById(int aliasId)
    
* AliasService (new file)
    * Create following methods
        * [x] List<Alias> findByAgentId(int agentId);
        * [x] Result<Alias> add(SecurityClearance securityClearance)
        * [x] Result<Alias> update(SecurityClearance securityClearance)
        * [x] boolean deleteById(int securityClearanceId)
        * [x] Result<Alias> validate
            * Name is required.
            * Persona is not required unless a name is duplicated. The persona differentiates between duplicate names.

* AliasController (new file)
    * Create following methods (class has @RequestMapping of "/api/alias")
        * [x] @GetMapping ("/{agentId}") List<Alias> findByAgentId(@PathVariable int agentId)
        * [x] @PostMapping ResponseEntity<Object> add(@RequestBody Alias alias)
        * [x] @PutMapping ("/{aliasId}") ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody Alias alias)
        * [x] @DeleteMapping ("/{aliasId}") ResponseEntity<void> delete(@PathVariable int aliasId)
    
### Task List
#### Security Crud
    * Estimated Time of Completion (ETC): 8 hours 15 minutes
    * Actual Time of Completion (ATC): 4 hours 42 minutes

* [x] Read existing code to determine new files/methods
    * ETC: 45 minutes
    * ATC: 35 minutes
    
* [x] Update SecurityClearanceRepository: 
    * ETC: 10 minutes
    * ATC: 2 minutes

* [x] Update SecurityClearanceJdbcTemplateRepository:
    * ETC: 45 minutes
    * ATC: 30 minutes
    
* [x] Comb through test_schema (especially known_good_state) to ensure correct test data
    * ETC: 20 minutes
    * ATC: 15 minutes
    
* [x] Update SecurityClearanceJdbcTemplateRepositoryTest file to include positive and negative CRUD test cases using the sql known good state
    * ETC: 45 minutes
    * ATC: 30 minutes
    
* [x] Create SecurityClearanceService file
    * ETC: 1.5 hours
    * ATC: 30 minutes
    
* [ ] Create SecurityClearanceServiceTest file to include positive and negative test cases for pass through methods and validation using Mockito
    * ETC: 1 hour
    * ATC: 30 minutes

* [x] Create SecurityClearanceController file
    * ETC: 1.5 hours
    * ATC: 30 minutes

* [x] Test and refine endpoints using VS Code http requests
    * ETC: 1.5 hours
    * ATC: 40 minutes

### Task List
#### Alias Crud
    * Estimated Time of Completion (ETC): 7 hours 50 minutes 
    * Actual Time of Completion (ATC): 5 hours 35 minutes
* [x] Read existing code to determine new files/methods
    * ETC: 45 minutes
    * ATC: 1 hour
    
* [x] Create Alias model
    * ETC: 15 minutes
    * ATC: 15 minutes

* [x] Update Agent model
    * ETC: 15 minutes
    * ATC: 5 minutes

* [x] Create AliasRepository:
    * ETC: 10 minutes
    * ATC: 5 minutes

* [x] Create AliasJdbcTemplateRepository:
    * ETC: 1 hour
    * ATC: 30 minutes

* [x] Update known good state in test schema to include alias data in order to properly test
    * ETC: 30 minutes
    * ATC: 10 minutes

* [x] Create AliasJdbcTemplateRepositoryTest file to include positive and negative CRUD test cases using the sql known good state
    * ETC: 45 minutes
    * ATC: 20 minutes

* [x] Create AliasService file
    * ETC: 1.5 hours
    * ATC: 30 minutes

* [x] Create AliasServiceTest file to include positive and negative test cases for pass through methods and validation using Mockito
    * ETC: 1 hour
    * ATC: 45 minutes

* [x] Create AliasController file
    * ETC: 1 hour
    * ATC: 30 minutes

* [x] Test and refine endpoints using VS Code http requests
    * ETC: 45 minutes
    * ATC: 1 hour
    
### Task List
#### Global Exception Handling
    * Estimated Time of Completion (ETC): 2 hours 45 minutes
    * Actual Time of Completion (ATC): 4 hours 30 minutes
* [x] Read existing code to determine new files/methods
    * ETC: 30 minutes
    * ATC: 15 minutes
    
* [x] Refactor ErrorResponse Class
    * [x] Delete all code in class (yikes!)
    * [x] Add Fields
        * [x] String message
        * [x] LocalDateTime timestamp
    * [x] Add methods
        * [x] getter for each field
        * [x] constructor taking in message

  * ETC: 30 minutes
  * ATC: 45 minutes
    
* [ ] Refactor each method in every controller to return ResponseEntity instead of using old ErrorResponse code
    * ETC: 45 minutes
    * ATC: 45 minutes
    
* [ ] Trial and error using VS Code endpoints to decipher a few common data integrity exceptions
    * ETC: 15 minutes
    * ATC: 15 minutes
    
* [ ] Create GlobalExceptionHandler Class to prevent those common exceptions and handle a "catch-all" exception
    * ETC: 15 minutes
    * ATC: 45 minutes
    
* [ ] Trial and error using VS Code endpoints to ensure exception handling works properly
  * ETC: 30 minutes
  * ATC: 30 minutes
    
* [ ] Add repo and service to log errors
    * ETC: 1 hour
    * ATC: 2 hours
    

    
