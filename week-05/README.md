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
         * getters and setters for each field
         * constructor that takes in each field
   * Guest: class
         * holds the following private fields for the host
            * guestId - int
            * first_name - String
            * last_name - String
            * email - String
            * phone - String
            * state - String
         * getters and setters for each field
         * constructor that takes in each field
     
   * Reservation: class
         * holds the following private fields for a reservation
            * reservationId - int
            * start_date - LocalDate
            * end_date - LocalDate
            * Host - class
            * Guest - class
            * total - BigDecimal
         * getters and setters for each field - 'the getter for total will include a calc'
         * constructor that takes in each field
         * contains the following methods
            * getDays - retrieves the amount of days per reservation - private
            * getTotal - calculates the total amount of money per reservation - public
     
* #### data: package
   * HostRepository: interface
         * holds the following methods as a contract for manipulating host data
            * findAll
            * findById
            * findByEmail
            * add
            * update
            * delete
     
   * HostFileRepository: class - implements HostRepository
         * holds the following methods
            * findAll
            * findById
            * findByEmail - public
            * add - public
            * update - public
            * delete - public
            * writeAll - private
            * serialize - private 
            * deserialize - private
            * cleanField - private
            * restoreField - private
     
   * GuestRepository: interface
         * holds the following methods as a contract for manipulating host data
           * findAll
           * findById
           * findByEmail
           * add
           * update
           * delete

  * GuestFileRepository: class - implements GuestRepository
         * holds the following methods
          * findAll
          * findById
          * findByEmail - public
          * add - public
          * update - public
          * delete - public
          * writeAll - private
          * serialize - private
          * deserialize - private
          * cleanField - private
          * restoreField - private

  * ReservationRepository: interface
      * holds the following methods as a contract for manipulating reservation data
          * findByHostId
          * add
          * update
          * delete

  * ReservationFileRepository: class - implements GuestRepository
      * holds the following methods
          * findByHostId - public
          * add - public
          * update - public
          * delete - public
          * writeAll - private
          * getFilePath
          * serialize - private
          * deserialize - private
          * cleanField - private
          * restoreField - private
                  
      