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
         * getters and setters for each field - 'the getter for total will include a calc'
         * contains the following methods
            * getDays - retrieves the amount of days per reservation - private
            * getTotal - calculates the total amount of money per reservation - public
     
* #### data: package
   * HostRepository: interface
         * holds the following methods as a contract for finding host data
            * findAll
            * findById
            * findByEmail

     
   * HostFileRepository: class - implements HostRepository
         * holds the following methods
            * findAll - public
            * findByHostId - public
            * findByHostEmail - public

     
   * GuestRepository: interface
         * holds the following methods as a contract for finding guest data
           * findAll
           * findByGuestId
           * findByGuestEmail

  * GuestFileRepository: class - implements GuestRepository
         * holds the following methods
          * findAll - public
          * findByGuestId - public
          * findByGuestEmail - public

  * ReservationRepository: interface
      * holds the following methods as a contract for manipulating reservation data
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
   
* #### domain: package
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
      * holds the following methods ensuring correct, and accurate manipulation of reservation data
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
   
* #### ui: package
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
   * App: class
         * runs the app using XML annotations
     
* #### resources: package
   * data.properties: file
      * contains the file path for each repo
   
   
### data
* holds the csv files for the production and test data including the following:
         * reservations: package
            * GUID-based reservations
         * guests.csv
         * guests-seed.csv
         * guests.test.csv
         * hosts.csv
         * hosts-seed.csv
         * hosts-test.csv
  
### test
* #### data
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
        * contains the methods inside the HostRepository to re-create data testable by the service
    
  * GuestsRepositoryDouble: implements GuestsRepository interface
      * contains the methods inside the ReservationRepository to re-create data testable by the service   
      
  * ReservationRepositoryDouble: implements HostRepository interface
    * contains the methods inside the HostRepository to re-create data testable by the service     
    
* #### domain
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

    


