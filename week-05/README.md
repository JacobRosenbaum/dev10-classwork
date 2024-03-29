# Assessment 5: Don't Wreck My House

## File Structure / Class Diagram

### main
 * #### model: package 
    * Host: class
         * holds the following private fields for the host
            * hostId - String (GUID)
            * last_name - String
            * email - String
            * phone - String
            * address - String
            * city - String
            * state - String
            * postal_code - int
            * standard_rate - BigDecimal
            * weekend_rate - BigDecimal
         * getters for each field
   * Guest: class
     * holds the following private fields for the host
         * guestId - int
         * first_name - String
         * last_name - String
         * email - String
         * phone - String
         * state - String
       * getters for each field
     
   * Reservation: class
     * holds the following private fields for a reservation
       
       * reservationId - int
       * start_date - LocalDate
       * end_date - LocalDate
       * Host - class
       * Guest - class
       * total - BigDecimal
         * getters and setters for each field - 'the getter for total will include a calc
         
        * contains the following methods:
            * getDays - retrieves the amount of days per reservation - private
            * getTotal - calculates the total amount of money per reservation - public
     
* #### learn.house.data: package
   * HostRepository: interface
     
     * holds the following methods as a contract for finding host learn.house.data
       
        * findAll
        * findById
       * findByEmail

     
   * HostFileRepository: class - implements HostRepository
     
     * holds the following methods
       * findAll - public
       * findByHostId - public
       * findByHostEmail - public

     
   * GuestRepository: interface
     * holds the following methods as a contract for finding guest learn.house.data
       * findAll
       * findByGuestId
       * findByGuestEmail

  * GuestFileRepository: class - implements GuestRepository
    * holds the following methods
      * findAll - public
      * findByGuestId - public
      * findByGuestEmail - public

  * ReservationRepository: interface
      * holds the following methods as a contract for manipulating reservation learn.house.data
          * findByHostId
          * add
          * update
          * delete

  * ReservationFileRepository: class - implements ReservationRepository
      * holds the following methods
          * findByHostId - public
          * add - public
          * update - public
          * delete - public
          * writeAll - private
          * getFilePath - private
          * serialize - private
          * deserialize - private
          * cleanField - private
          * restoreField - private
   
* #### learn.house.domain: package
   * HostService: class
     * holds the following pass through methods
       * findAll - public
       * findById - public
       * findByEmail - public

  * GuestService: class
      * holds the following pass through methods
          * findAll - public
          * findById - public
          * findByEmail - public
   * ReservationService: class
      * holds the following methods ensuring correct, and accurate manipulation of reservation learn.house.data
         * findByEmail - public
         * add - public
         * update - public
         * delete - public
         * validateNulls - private: ensure the following
            * guest_email is required and cannot be empty
            * host_email is required and cannot be empty
            * start_date is required and cannot be empty
            * end_date is required and cannot be empty
            * Host cannot be null
            * Guest cannot be null
         * validateDates - private: ensures the following
           * The start date must come before the end date.
           * The reservation may never overlap existing reservation dates.
           * The start date must be in the future.
         * validate - private: takes in validateNulls & validateDates
      
   * ReservationResult: class
        * holds the following methods verifying if the reservation was successful
            * getReservation
            * setReservation
            * isSuccess
            * getErrorMessages
            * addErrorMessage
   
* #### learn.house.ui: package
   * ConsoleIO: class
      * holds the following public helper methods for interacting with the console
         * print
         * println
         * printf
         * readString
         * readRequired String
         * readInt
         * readBoolean
         * readLocalDate
         * readBigDecimal
   
   * MainMenuOption: enum
      * holds the following enum values for the main menu
         * VIEW_RESERVATIONS_FOR_HOST
         * MAKE_RESERVATION
         * UPDATE_RESERVATION
         * DELETE_RESERVATION
   * View: class
      * holds the following methods to print accurate date to console
         * selectMainMenuOption
         * displayHeader
         * displaySummary
         * getHostEmail
         * getGuestEmail
         * displayReservations
         * makeReservation
         * updateReservation
         * deleteReservation
         * displayException
         * displayStatus
   
   * Controller: class
      * holds the following methods to control flow of the app
         * run
         * runMenuLoop
         * viewReservation
         * makeReservation
         * updateReservation
         * deleteReservation
   * learn.house.App: class
         * runs the app using XML annotations
     
* #### resources: package
   * learn.house.data.properties: file 
      * contains the file path for each repo
   
   
### learn.house.data
* holds the csv files for the production and test learn.house.data including the following:
    * reservations: package
         * GUID-based reservations
         * guests.csv
         * guests-seed.csv
         * guests.test.csv
         * hosts.csv
         * hosts-seed.csv
         * hosts-test.csv
  
### test
* #### learn.house.data
  * HostFileRepositoryTest
      * contains the following tests
         * shouldFindAll
         * shouldFindById  
         * shouldNotFindMissingId
         * shouldFindByEmail
         * shouldNotFindMissingEmail
      
  * GuestFileRepositoryTest
      * contains the following tests
        * shouldFindAll
        * shouldFindById
        * shouldNotFindMissingId
        * shouldFindByEmail
        * shouldNotFindMissingEmail
      
  * ReservationRepositoryTest
     * contains the following tests
        * shouldFindByHostId
        * shouldNotFindMissingHostId
        * shouldAddReservation
        * shouldNotAddMissingReservation
        * shouldUpdateReservation
        * shouldNotUpdateMissingReservation
        * shouldNotAddMissingReservation
        * shouldDeleteReservation
        * shouldNotDeleteMissingReservation
        * userInputDelimiterShouldNotRuinData
     
  * HostRepositoryDouble: implements HostRepository interface
     * contains the methods inside the HostRepository to re-create learn.house.data testable by the service
    
  * GuestsRepositoryDouble: implements GuestsRepository interface
     * contains the methods inside the ReservationRepository to re-create learn.house.data testable by the service   
      
  * ReservationRepositoryDouble: implements HostRepository interface
    * contains the methods inside the HostRepository to re-create learn.house.data testable by the service     
    
* #### learn.house.domain
  * HostServiceTest
      * contains the following tests
          * shouldFindAll
          * shouldFindById
          * shouldNotFindMissingId
          * shouldFindByEmail
          * shouldNotFindMissingEmail

  * GuestServiceTest
      * contains the following tests
          * shouldFindAll
          * shouldFindById
          * shouldNotFindMissingId
          * shouldFindByEmail
          * shouldNotFindMissingEmail
    
  * ReservationRepositoryTest
     * contains the following tests
          * shouldFindByHostId
          * shouldNotFindMissingHostId
          * shouldAdd
          * shouldNotAddNullGuest
          * shouldNotAddMissingGuest
          * shouldNotAddNullHost
          * shouldNotAddMissingHost
          * shouldNotAddNullStartDate
          * shouldNotAddMissingStartDate
          * shouldNotAddNullEndDate
          * shouldNotAddMissingEndDate
          * shouldNotAddIfStartDateAfterEndDate
          * shouldNotAddIfReservationOverlaps
          * shouldNotAddIfStartDateInPast
          * shouldUpdateReservation
          * shouldNotUpdateMissingReservation
          * shouldDeleteReservation
          * shouldNotDeleteMissingReservation
          * shouldNotDeleteIfReservationNotInPast
    
## Ordered List of Tasks
### Total Completion Time Estimate: 26 hours, 40 mins
### Total Actual Completion Time: 34 hours, 40 mins

1. Create the models - Estimated Completion Time: 40 mins
   Actual Time: 2 hours 
    * [x] Host
    * [x] Guest
    * [x] Reservation
    
2. Create the interfaces - Estimated Completion Time: 40 mins
   Actual Time: 40 mins
     * [x] HostRepository
    * [x] GuestRepository
    * [x] ReservationRepository
    
3. Create HostFileRepository class - Estimated Completion Time: 20 mins
   Actual Time: 30 mins
     * [x] Write Override methods
    
4. Create HostFileRepositoryTest class in test package - Estimated Completion Time: 20 mins
   Actual Time: 15 mins
    * [x] Write all tests displayed above in the File Structure 


5. Create GuestFileRepository class - Estimated Completion Time: 20 mins
   Actual Time: 30 mins
     * [x] Write Override methods

6. Create GuestFileRepositoryTest class in test package - Estimated Completion Time: 20 mins
   Actual Time: 15 mins
     * [x] Write all tests displayed above in the File Structure  

7. Create ReservationFileRepository class - Estimated Completion Time: 1.5 hours
   Actual Time: 4 hours
     * [x] Write Override methods

8. Create HostService class - Estimated Completion Time: 45 minutes
   Actual Time: 25 mins
     * [x] Write pass-through methods

9. Create HostService class in test package - Estimated Completion Time: 15 minutes
   Actual Time: 15 mins
     * [x] Write all tests displayed above in the File Structure

10. Create HostRepositoryDouble class in test package - Estimated Completion Time: 15 minutes
    Actual Time: 25 mins
    * [x] Write pass through methods mimicking learn.house.data in HostFileRepository

11. Create HostServiceTest class in test package - Estimated Completion Time: 15 minutes
    Actual Time: 15 mins
    * [x] Write all tests displayed above in the File Structure
    
12. Create GuestService class - Estimated Completion Time: 15 minutes
    Actual Time: 15 mins
    * [x] Write pass through methods

13. Create GuestRepositoryDouble class in test package - Estimated Completion Time: 15 minutes
    Actual Time: 25 mins
    * [x] Write pass through methods mimicking learn.house.data in GuestFileRepository

14. Create GuestServiceTest class in test package - Estimated Completion Time: 15 minutes
    Actual Time: 15 mins
    * [x] Write all tests displayed above in the File Structure

15. Create ReservationService class - Estimated Completion Time: 3.5 hours
    Actual Time: 5 hours
    * [x] Write pass through methods
    * [x] Write CRUD methods
    * [x] Write helper methods

16. Create ReservationRepositoryDouble class in test package - Estimated Completion Time: 1 hour
    Actual Time: 25 mins
    * [x] Write methods mimicking learn.house.data in ReservationFileRepository

17. Create ReservationServiceTest class in test package - Estimated Completion Time: 1.5 hours
    Actual Time: 1.5 hours
    * [x] Write all tests displayed above in the File Structure

18. Create MainMenuOption enum - Estimated Completion Time: 15 mins
    Actual Time: 15 mins
    * [x] Write enums displayed above in File Structure
    
19. Create ConsoleIO class - Estimated Completion Time: 1 hour
    Actual Time: 20 mins
    * [x] Write methods displayed above in File Structure
    
20. Create learn.house.data.properties file - Estimated Completion Time: 15 minutes
    Actual Time: 10 mins
    * [x] Write file paths displayed above in file structure

21. Go back and annotate files - Estimated Completion Time: 15 mins
    Actual Time: 45 mins
    * [x] Components
    * [x] Services
    * [x] Repositories

22. Create learn.house.App class - Estimated Completion Time: 15 mins
    Actual Time: 15 mins
    * [x] Write main method with proper Spring DI syntax

23. Create View class - Estimated Completion Time: 1 hour
    Actual Time: 1 hour
    * [x] Write methods for View Reservations By Host displayed in File Structure

24. Create Controller class - Estimated Completion Time: 40 mins
    Actual Time: 1 hour
    * [x] Write methods for View Reservations By Host in File Structure

25. Test Method in Console until it works properly - Estimated Completion Time: 30 mins
    Actual Time: 1.5 hours

26. View class continued - Estimated Completion Time: 2 hours
    Actual Time: 2.5 hours
    * [x] Write methods for Make Reservation displayed in File Structure
    
27. Controller class continued - Estimated Completion Time: 1 hour
    Actual Time: 2 hours
    * [x] Write methods for Make Reservation displayed in File Structure

28. Test Method in Console until it works properly - Estimated Completion Time: 30 mins
    Actual Time: 1.5 hours

29. View class continued - Estimated Completion Time: 2 hours
    Actual Time: 2 hours
    * [x] Write methods for Edit Reservation displayed in File Structure

30. Controller class continued - Estimated Completion Time: 1 hour
    Actual Time: 1.5 hours
    * [x] Write methods for Edit Reservation displayed in File Structure

31. Test Method in Console until it works properly - Estimated Completion Time: 30 mins
    Actual Time: 45 mins

32. View class continued - Estimated Completion Time: 45 mins
    Actual Time: 1 hour
    * [x] Write methods for Delete Reservation displayed in File Structure

33. Controller class continued - Estimated Completion Time: 30 mins
    Actual Time: 30 mins
    * [x] Write methods for Delete Reservation displayed in File Structure

34. Test Method in Console until it works properly - Estimated Completion Time: 30 mins
    Actual Time: 20 mins

35. Celebrate completed app - Estimated Completion Time: rest of the night



    



