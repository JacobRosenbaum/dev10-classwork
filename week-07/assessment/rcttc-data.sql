use tiny_theaters;

-- select * from temp_data;

-- 194 total rows in denormalized db

-- insert into customer (first_name, last_name, customer_email_address, customer_phone_number, customer_address)
	select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
	from temp_data;
 
-- insert into theater (`name`, theater_phone_number, theater_email_address)
select distinct theater, theater_phone, theater_email
from temp_data;

-- insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 24, 5) city,
    SUBSTRING(theater_address, 31,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    1
from temp_data td
limit 1;

-- insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 19, 7) city,
    SUBSTRING(theater_address, 28,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    2
from temp_data
limit 1,1;

-- insert into location (address, city, state, postal_code, theater_id)
select distinct 
	SUBSTRING_INDEX(theater_address, ",", 1) address,
    SUBSTRING(theater_address, 27, 10) city,
    SUBSTRING(theater_address, 39,2) state,
    SUBSTRING_INDEX(theater_address, " ", -1) postal_code,
    3
from temp_data
limit 2,1;

-- insert into performance (title)
select distinct `show`
from temp_data;


-- insert into performance_theater (performance_id, theater_id)
select distinct performance_id, t.theater_id
from performance p
inner join temp_data td on td.`show` = p.title
inner join theater t on t.`name` = td.theater;

-- insert into ticket (seat_location, price, `date`, customer_id, theater_id)
select seat, ticket_price, str_to_date(`date`,'%Y-%m-%d'), c.customer_id, t.theater_id
from temp_data td
inner join customer c on c.last_name = td.customer_last
inner join theater t on t.`name` = td.theater











