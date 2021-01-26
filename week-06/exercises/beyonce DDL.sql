drop database if exists musical_recording;

create database musical_recording;

use musical_recording;

create table artist(
	artist_id int primary key auto_increment,
    artist_stage_name varchar(150) not null,
    artist_real_first_name varchar(50) null,
	artist_real_last_name varchar(50) null
);

create table writer(
	writer_id int primary key auto_increment,
	writer_first_name varchar(50) null,
	writer_last_name varchar(50) null, 
    artist_id int null,
        constraint fk_writer_artist_id
		foreign key (artist_id)
        references artist(artist_id)
);

create table album(
	album_id int primary key auto_increment,
	album_title varchar(150) not null,
    release_date date not null,
    label varchar(150) null,
    artist_id int not null,
      constraint fk_album_artist_id
		foreign key (artist_id)
        references artist(artist_id)
);

create table track(
	track_id int primary key auto_increment,
	track_number int not null,
    song_title varchar(150) not null,
    length time not null,
    featured_artist varchar(150) null,
    writer varchar(150) null,
    album_id int not null,
    artist_id int not null,
    constraint fk_track_album_id
		foreign key (album_id)
        references album(album_id)
);

create table artist_track(
	artist_id int not null,
    track_id int not null,
    constraint
		primary key(artist_id, track_id),
    constraint fk_artist_track_artist_id
		foreign key (artist_id)
        references artist(artist_id),
	constraint fk_artist_track_track_id
		foreign key (track_id)
        references track(track_id)
);

create table writer_track(
	writer_id int not null,
    track_id int not null,
    constraint
		primary key(writer_id, track_number),
    constraint fk_writer_track_writer_id
		foreign key (writer_id)
        references writer(writer_id),
	constraint fk_writer_track_track_id
		foreign key (track_id)
        references track(track_id)
);


insert into artist(artist_id,
    artist_stage_name,
    artist_real_first_name,
	artist_real_last_name) values
    (1, 'Beyoncé', null, null);
    
insert into album(album_id,
	album_title,
    release_date,
    label,
    artist_id) values 
    (1, 'Beyoncé', '2020-12-13', null, 1);

-- No.	Title	Featuring	Writers	Length
-- 1.	"Pretty Hurts"		Beyoncé Knowles, Joshua Coleman, Sia Furler	4:17
-- 3.	"Drunk in Love"	Jay-Z	Knowles, Brian Soko, Jerome Harmon, Shawn Carter, Andre Eric Proctor, Rasool Díaz, Timothy Mosley, Noel Fisher	5:23
-- 12.	"Super-Power"	Frank Ocean	Knowles, Pharrell Williams, Frank Ocean

insert into track(track_id,
	track_number,
    song_title,
    length,
    featured_artist,
    album_id,
    artist_id) values
    (1, 1,'Pretty Hurts','00:04:17', null, 1, 
    
    


