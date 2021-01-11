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
            * getDays - retrieves the amount of days per reservation
            * getTotal - calculates the total amount of money per reservation
      