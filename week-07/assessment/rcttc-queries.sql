use tiny_theaters;

-- Recreate the spreadsheet data with a single query.
select 
	c.first_name, c.last_name, c.customer_email_address, c.customer_phone_number, c.customer_address,
    t.seat_location, 
    p.title,
    t.price,
    t.`date`,
    th.`name`,
    th.theater_phone_number,
    th.theater_email_address,
    l.address,
    l.city,
    l.state,
    l.postal_code
from customer c
inner join ticket t on c.customer_id = t.customer_id
inner join theater th on t.theater_id = th.theater_id
inner join location l on l.theater_id = th.theater_id
inner join performance p on t.performance_id = p.performance_id
order by th.`name`, p.title;

-- Find all performances in the last quarter of 2021 (Oct. 1, 2021 - Dec. 31 2021).
select p.title, t.`date`
from ticket t
inner join performance p on p.performance_id = t.performance_id
where t.`date` between '2021-10-1' and '2021-12-31'
group by p.title, t.`date`;

-- List customers without duplication.
select * from customer;

-- Find all customers without a .com email address.
select *
from customer
where customer_email_address not like ('%.com');

-- Find the three cheapest shows.
select distinct p.title, t.price
from performance p
inner join ticket t on p.performance_id = t.performance_id
order by t.price asc
limit 3;

-- List customers and the show they're attending with no duplication.
select c.first_name, c.last_name, p.title
from customer c
inner join ticket t on t.customer_id = c.customer_id
inner join performance p on p.performance_id = t.performance_id
group by c.first_name, c.last_name, p.title;

-- List customer, show, theater, and seat number in one query.
select distinct
	c.first_name, c.last_name,
    t.seat_location, 
    p.title,
    th.`name`
from customer c
inner join ticket t on c.customer_id = t.customer_id
inner join theater th on t.theater_id = th.theater_id
inner join performance p on t.performance_id = p.performance_id;

-- Find customers without an address.
select first_name, last_name
from customer
where customer_address = '';

-- Count total tickets purchased per customer.
select concat(c.first_name, ' ', c.last_name) customer_name, count(t.customer_id) total_tickets
from customer c
inner join ticket t on c.customer_id = t.customer_id
group by customer_name
order by total_tickets desc;

-- Calculate the total revenue per show based on tickets sold.
select p.title, sum(t.price) as revenue
from ticket t
inner join performance p on t.performance_id = p.performance_id
group by p.performance_id
order by revenue desc;

-- Calculate the total revenue per theater based on tickets sold.
select th.`name`, sum(t.price) as revenue
from ticket t
inner join performance p on t.performance_id = p.performance_id
inner join theater th on th.theater_id = t.theater_id
group by th.`name`
order by revenue desc;

-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?
select concat(c.first_name, ' ', c.last_name) customer_name, sum(t.price) money_spent
from customer c
inner join ticket t on t.customer_id = c.customer_id
group by concat(c.first_name, ' ', c.last_name) 
order by money_spent desc
limit 1;