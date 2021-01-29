# Assessment 7: Field Agent

### Goals
* [ ] Create full HTTP CRUD for security clearance.
* [ ] Create full HTTP CRUD for agent aliases.
* [ ] Implement global error handling.

### File updates for Security Clearance
* SecurityClearanceRepository (existing Interface - needs following updates)
    * Create following methods
        * [ ] List<SecurityClearance> findAll();
        * [ ] SecurityClearance add(SecurityClearance securityClearance)
        * [ ] boolean update(SecurityClearance securityClearance)
        * [ ] @Transactional boolean deleteById(int securityClearanceId)

* SecurityClearanceJdbcTemplateRepository (existing file - needs following updates)
    * Create following methods
        * [ ] List<SecurityClearance> findAll();
        * [ ] SecurityClearance add(SecurityClearance securityClearance)
        * [ ] boolean update(SecurityClearance securityClearance)
        * [ ] @Transactional boolean deleteById(int securityClearanceId)
    
* SecurityClearanceService (new file)
    * Create following methods
        * [ ] List<SecurityClearance> findAll();
        * [ ] SecurityClearance findById(int securityClearanceId)  
        * [ ] Result<SecurityClearance> add(SecurityClearance securityClearance)
        * [ ] Result<SecurityClearance> update(SecurityClearance securityClearance)
        * [ ] boolean deleteById(int securityClearanceId)
        * [ ] Result<SecurityClearance> validate
            * Security clearance name is required.
            * Name cannot be duplicated.
    
* SecurityClearanceController (new file)
    * Create following methods (class has @RequestMapping of "/api/security/clearance")
        * [ ] @GetMapping ("/") List<SecurityClearance> findAll()
        * [ ] @GetMapping ("/{securityClearanceId}") ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId)
        * [ ] @PostMapping ResponseEntity<Object> add(@RequestBody SecurityClearance securityClearance)
        * [ ] @PutMapping ("/{securityClearanceId}") ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance securityClearance)
        * [ ] @DeleteMapping ("/{securityClearanceId}") ResponseEntity<void> delete(@PathVariable int securityClearanceId)      


### File updates for Alias
* Alias (new model)
    * Fields:
        * [ ] int aliasId
        * [ ] String name
        * [ ] String persona
        * [ ] int agentId
    * Methods:
        * [ ] getters and setters for each field
    
* Agent (existing model - need following updates)
    * Fields:
        * [ ] List<Alias> aliases
    * Methods
        * [ ] getter and setter for aliases

* AliasRepository (new Interface)
    * Create following methods
        * [ ] List <Alias> findByAgentId(int agentId);
        * [ ] Alias add(Alias alias)
        * [ ] boolean update(Alias alias)
        * [ ] boolean deleteById(int aliasId)

* AliasJdbcTemplateRepository (new file)
    * Create following methods
        * [ ] List <Alias> findByAgentId(int agentId);
        * [ ] Alias add(Alias alias)
        * [ ] boolean update(Alias alias)
        * [ ] boolean deleteById(int aliasId)
    
* AliasService (new file)
    * Create following methods
        * [ ] List<Alias> findByAgentId(int agentId);
        * [ ] Result<Alias> add(SecurityClearance securityClearance)
        * [ ] Result<Alias> update(SecurityClearance securityClearance)
        * [ ] boolean deleteById(int securityClearanceId)
        * [ ] Result<Alias> validate
            * Name is required.
            * Persona is not required unless a name is duplicated. The persona differentiates between duplicate names.

* AliasController (new file)
    * Create following methods (class has @RequestMapping of "/api/alias")
        * [ ] @GetMapping ("/{agentId}") List<Alias> findByAgentId(@PathVariable int agentId)
        * [ ] @PostMapping ResponseEntity<Object> add(@RequestBody Alias alias)
        * [ ] @PutMapping ("/{aliasId}") ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody Alias alias)
        * [ ] @DeleteMapping ("/{aliasId}") ResponseEntity<void> delete(@PathVariable int aliasId)
    
### Task List
#### Security Crud
    * Estimated Time of Completion (ETC): 8 hours 15 minutes
    * Actual Time of Completion (ATC): 

* [x] Read existing code to determine new files/methods
    * ETC: 45 minutes
    * ATC: 35 minutes
    
* [ ] Update SecurityClearanceRepository: 
    * ETC: 10 minutes
    * ATC:

* [ ] Update SecurityClearanceJdbcTemplateRepository:
    * ETC: 45 minutes
    * ATC:
    
* [ ] Comb through test_schema (especially known_good_state) to ensure correct test data
    * ETC: 20 minutes
    * ATC:
    
* [ ] Update SecurityClearanceJdbcTemplateRepositoryTest file to include positive and negative CRUD test cases using the sql known good state
    * ETC: 45 minutes
    * ATC:
    
* [ ] Create SecurityClearanceService file
    * ETC: 1.5 hours
    * ATC:
    
* [ ] Create SecurityClearanceServiceTest file to include positive and negative test cases for pass through methods and validation using Mockito
    * ETC: 1 hour
    * ATC:

* [ ] Create SecurityClearanceController file
    * ETC: 1.5 hours
    * ATC:

* [ ] Test and refine endpoints using VS Code http requests
    * ETC: 1.5 hours
    * ATC:


#### Alias Crud
    * Estimated Time of Completion (ETC): 7 hours 50 minutes 
    * Actual Time of Completion (ATC): 
* [x] Read existing code to determine new files/methods
    * ETC: 45 minutes
    * ATC: 1 hour
    
* [ ] Create Alias model
    * ETC: 15 minutes
    * ATC: 

* [ ] Update Agent model
    * ETC: 15 minutes
    * ATC:

* [ ] Create AliasRepository:
    * ETC: 10 minutes
    * ATC:

* [ ] Create AliasJdbcTemplateRepository:
    * ETC: 1 hour
    * ATC:

* [ ] Update known good state in test schema to include alias data in order to properly test
    * ETC: 30 minutes
    * ATC:

* [ ] Create AliasJdbcTemplateRepositoryTest file to include positive and negative CRUD test cases using the sql known good state
    * ETC: 45 minutes
    * ATC:

* [ ] Create AliasService file
    * ETC: 1.5 hours
    * ATC:

* [ ] Create AliasServiceTest file to include positive and negative test cases for pass through methods and validation using Mockito
    * ETC: 1 hour
    * ATC:

* [ ] Create AliasController file
    * ETC: 1 hour
    * ATC:

* [ ] Test and refine endpoints using VS Code http requests
    * ETC: 45 minutes
    * ATC:
    

    
