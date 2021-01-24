use tiny_theaters;

select * from temp_data;

-- 194 total rows in denormalized db

insert into customer (first_name, last_name, customer_email_address, customer_phone_number, customer_address)
	select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
	from temp_data;
 
insert into theater (`name`, theater_phone_number, theater_email_address)
select distinct theater, theater_phone, theater_email
from temp_data;

insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 24, 5) city,
    SUBSTRING(theater_address, 31,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    1
from temp_data td
limit 1;

insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 19, 7) city,
    SUBSTRING(theater_address, 28,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    2
from temp_data
limit 1,1;

insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 27, 10) city,
    SUBSTRING(theater_address, 39,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    3
from temp_data
limit 2,1;

insert into performance (title, theater_id)
select distinct `show`, theater_id
from temp_data td
inner join theater t on t.`name` = td.theater;


-- insert into performance_theater (performance_id, theater_id)
select distinct performance_id, t.theater_id
from performance p
inner join temp_data td on td.`show` = p.title
inner join theater t on t.`name` = td.theater;

-- insert into ticket (seat_location, price, `date`, customer_id, theater_id, performance_id)
select seat, ticket_price, str_to_date(td.`date`,'%Y-%m-%d')`date`, c.customer_id, th.theater_id, p.performance_id
from temp_data td
inner join customer c on c.last_name = td.customer_last
inner join theater th on th.`name` = td.theater
inner join performance p on td.`show` = p.title;


-- The Little Fitz's 2021-03-01 performance of The Sky Lit Up is listed with a $20 ticket price. 
-- The actual price is $22.25 because of a visiting celebrity actor. (Customers were notified.) 
-- Update the ticket price for that performance only.

set sql_safe_updates = 0;
update ticket set
price = '22.25'
where price = '20.00' and date = '2021-03-01';
set sql_safe_updates = 1;

-- In the Little Fitz's 2021-03-01 performance of The Sky Lit Up, Pooh Bedburrow and Cullen Guirau seat reservations aren't in the same row.
-- Adjust seating so all groups are seated together in a row. This may require updates to all reservations for that performance.
-- Confirm that no seat is double-booked and that everyone who has a ticket is as close to their original seat as possible.
 
update ticket set
seat_location = 'B4'
where ticket_id = '95';

update ticket set
seat_location = 'C2'
where ticket_id = '99';

update ticket set
seat_location = 'A4'
where ticket_id = '101';
 
-- Update Jammie Swindles's phone number from "801-514-8648" to "1-801-EAT-CAKE".

select c.customer_id
from customer c
where c.first_name = 'Jammie' and c.last_name = 'Swindles';

update customer set
customer_phone_number = "1-801-EAT-CAKE"
where customer_id = 48;

-- Delete all single-ticket reservations at the 10 Pin. (You don't have to do it with one query.)

select customer_id
from (
    select customer_id
    from ticket t
    inner join theater th on th.theater_id = t.theater_id
    where th.`name` = '10 Pin'
    group by customer_id
    having COUNT(*) = 1
) as ONLY_ONCE;

-- customer_id = 7, 8, 10, 15, 18, 19, 22, 25, 26

delete 
from ticket 
where customer_id = '7';

delete 
from ticket 
where customer_id = '8';

delete 
from ticket 
where customer_id = '10';

delete 
from ticket 
where customer_id = '15';

delete 
from ticket 
where customer_id = '18';

delete 
from ticket 
where customer_id = '19';

delete 
from ticket 
where customer_id = '22';

delete 
from ticket 
where customer_id = '25';

delete 
from ticket 
where customer_id = '26';

-- Delete the customer Liv Egle of Germany. It appears their reservations were an elaborate joke.

select concat(first_name, ' ', last_name) customer_name, customer_id
from customer
where concat(first_name, ' ', last_name) = 'Liv Egle of Germany';

-- customer_id = 65

delete 
from ticket
where customer_id = 65; 

delete 
from customer
where customer_id = 65; 




    
		











