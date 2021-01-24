drop database if exists tiny_theaters;

create database tiny_theaters;

use tiny_theaters;

create table customer (
	customer_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
	customer_email_address varchar(100) not null,
	customer_phone_number varchar(15) null,
    customer_address varchar(200) null
);

create table theater (
	theater_id int primary key auto_increment,
    `name` varchar(150) not null,
    theater_phone_number varchar(15) not null,
    theater_email_address varchar(100) not null
);

create table location (
	location_id int primary key auto_increment,
    address varchar(300) not null,
    city varchar(50) not null,
    state varchar(30) not null,
    postal_code varchar(5) not null,
    theater_id int not null,
        constraint fk_location_theater_id
		foreign key (theater_id)
        references theater(theater_id)
);

create table performance ( 
	performance_id int primary key auto_increment,
    title varchar(300) not null,
    start_date date null,
    end_date date null,
    theater_id int not null,
    	constraint fk_performance_theater_id
		foreign key (theater_id)
        references theater(theater_id)
);

create table ticket ( 
	ticket_id int primary key auto_increment,
    seat_location varchar(5) not null,
    price decimal(4,2) not null,
	`date` date not null,
    customer_id int null,
    theater_id int not null,
    performance_id int not null,
	constraint fk_ticket_customer_id
		foreign key (customer_id)
        references customer(customer_id),
	constraint fk_ticket_theater_id
		foreign key (theater_id)
        references theater(theater_id),
	constraint fk_ticket_performance_id
		foreign key (performance_id)
        references performance(performance_id)
);
