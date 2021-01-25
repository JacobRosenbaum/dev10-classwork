use tiny_theaters;

insert into customer (first_name, last_name, customer_email_address, customer_phone_number, customer_address)
	select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
	from temp_data;
    
insert into login (customer_id, user_name, password_hash)    
select customer_id, concat(substring(first_name,1,1), last_name) user_name, concat(substring(last_name,2,5),'=%$*&', substring(first_name,1,4))
from customer;
 
insert into theater (`name`, seat_capacity, theater_phone_number, theater_email_address)
select distinct theater, 1, theater_phone, theater_email
from temp_data;

update theater set
seat_capacity = '25'
where theater_id = 1;

update theater set
seat_capacity = '12'
where theater_id = 2;

update theater set
seat_capacity = '16'
where theater_id = 3;


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

insert into ticket (seat_location, price, `date`, customer_id, theater_id, performance_id)
select seat, ticket_price, str_to_date(td.`date`,'%Y-%m-%d')`date`, c.customer_id, th.theater_id, p.performance_id
from temp_data td
inner join customer c on c.last_name = td.customer_last
inner join theater th on th.`name` = td.theater
inner join performance p on td.`show` = p.title;

insert into performance_employee (employee_first_name, employee_last_name, employee_email_address, employee_phone_number, performance_id) 
values
('Joey','Stobbart','jstobbart0@about.me','(666) 6633952', '1'),
('Darci','Frackiewicz','dfrackiewicz1@cpanel.net','(251) 3117179', '2'),
('Teddie','Climance','tclimance2@tripod.com','(976) 6122532', '1'),
('Tyrus','Fortescue','tfortescue3@engadget.com','(512) 4298065', '2'),
('Denis','Kiln','dkiln4@meetup.com','(901) 9181006','3'),
('Elly','Kalaher','ekalaher5@mapy.cz','(162) 2761845','3'),
('Zeke','Heath','zheath6@slideshare.net','(571) 9863320','4'),
('Sylvia','Bikker','sbikker7@ebay.co.uk','(693) 7031876','4'),
('Langston','Hollidge','lhollidge8@ezinearticles.com','(227) 6356653','5'),
('Johan','Grog','jgrog9@globo.com','(406) 3935678','5'),
('Kandy','Grabbam','kgrabbama@github.io','(110) 6679101','6'),
('Niccolo','Laneham','nlanehamb@tinypic.com','(654) 3022280','6'),
('Quintana','Smee','qsmeec@senate.gov','(326) 7617224','6'),
('Milicent','Irdale','mirdaled@flickr.com','(803) 9041297','7'),
('Dennis','Chupin','dchupine@sciencedirect.com','(937) 2660475','7'),
('Norah','Barford','nbarfordf@bbc.co.uk','(369) 6744849','8'),
('Griz','Bhar','gbharg@digg.com','(588) 7728618','8'),
('Mychal','Sleeny','msleenyh@bbb.org','(964) 6238349','8'),
('Roosevelt','Heale','rhealei@china.com.cn','(508) 5072509','8'),
('Valery','Laver','vlaverj@dailymotion.com','(515) 5937179','9'),
('Brittne','Guillot','bguillotk@hibu.com','(687) 6141923','9'),
('Bobbie','Trappe','btrappel@blogspot.com','(742) 1540379','6'),
('Adriana','Pullan','apullanm@yolasite.com','(935) 6533405','4'),
('Sinclair','Cratere','scrateren@linkedin.com','(255) 9421044','4'),
('Cyrille','Stilling','cstillingp@reverbnation.com','(951) 9576898','3'),
('Murvyn','Dowsey','mdowseyq@ftc.gov','(481) 3485974','8'),
('Elianore','Finby','efinbyr@reuters.com','(876) 7943907','1'),
('Maddie','Capelen','mcapelens@eepurl.com','(196) 4301280','2'),
('Claudio','Somner','csomnert@trellian.com','(394) 9355565','5'),
('Curr','Scotchmur','cscotchmuru@merriam-webster.com','(223) 6479601','2'),
('Briant','Sizland','bsizlandv@craigslist.org','(999) 6057837','3'),
('Monique','Bolesma','mbolesmaw@github.com','(910) 7494384','7'),
('Prentice','Grundle','pgrundlex@umich.edu','(789) 9805576','8'),
('Con','Antonsen','cantonseny@imageshack.us','(237) 5547856','8'),
('Lev','Forrestill','lforrestillz@istockphoto.com','(242) 3412765','1'),
('Dorthy','Ciccerale','dciccerale10@buzzfeed.com','(634) 4186242','7'),
('Coreen','Andre','candre11@cnet.com','(714) 5935146','9');

insert into performer (performer_id)
select pe.employee_number
from performance_employee pe
where pe.employee_number between '1' and '18';

insert into crew_member (crew_member_id)
select pe.employee_number
from performance_employee pe
where pe.employee_number between '19' and '37';

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
from login
where customer_id = 65; 

delete 
from customer
where customer_id = 65; 

drop table temp_data;




    
		











