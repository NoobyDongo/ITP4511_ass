
Drop table if exists staff_remark_remark_type;
Drop table if exists staff_remark;
Drop table if exists remark_type;
Drop table if exists member_comment;
Drop table if exists opening_hours;
Drop table if exists opening_days;
Drop table if exists closing_days;
drop table if exists notification;
drop table if exists template;
drop table if exists guestlist;
drop table if exists guestlist_guest;
drop table if exists guest;
drop table if exists member;
drop table if exists user;
Drop table if exists venue;
drop table if exists booking;
drop table if exists booking_venue;
drop table if exists staff;

create table if not exists user (
    id int(10) not null primary key AUTO_INCREMENT,
    fname varchar(255) not null,
    lname varchar(255) not null,
    email varchar(255) not null,
    phone int(8) not null,
    pwd varchar(255) not null,
    role int(1) not null default 0
);
create table if not exists guest (
    id int(10) not null primary key AUTO_INCREMENT,
    memberid int not null references user(id),
    name varchar(255) not null,
    email varchar(255) not null
);
create table if not exists guestlist_guest(
    guestlistid int not null references guestlist(id),
    guestid int not null references guest(id)
);
create table if not exists guestlist (
    id int(10) not null primary key AUTO_INCREMENT
);

Drop table if exists venue;
create table if not exists venue  (
    id int(10) not null primary key AUTO_INCREMENT,
    staffid int references user(id),
    name varchar(255) not null,
    address varchar(255) not null,
    `desc` varchar(255) not null,
    img varchar(255) not null,
    type varchar(255) not null,
    capacity int(10) not null,
    fee double(7,2) not null,
    hidden bit(1) not null default 0,
    lastmodifiedfee datetime not null default CURRENT_TIMESTAMP
);

insert into user values(default, 'Tai Man', 'Chan', 'staff@outlook.com', 69285085, 'staff@outlook.com', 1);
insert into user values(default, 'Ming', 'Siu', 'a@a.a', 69285085, 'a', 0);

insert into guest values(default, 2, 'name', 'haha@123.com');
insert into guest values(default, 2, 'name2', 'haha@1223.com');
insert into guest values(default, 2, 'name3', 'haha@12123.com');

insert into venue values(default, 1, 'Happy Farm', 
'King Ling Road 3, Tiu King Ling, Hong Kong', 
'A farm for you to explore.', 
'01',
'exhibition',
'100',
12345.12,
default,
default);

insert into venue values(default, 1, 'Apple Tree Farm', 
'18 Tsing Wun Rd, Tuen Mun, Hong Kong', 
'A farm that has a lot of apples, which you are not allowed to pick up.', 
'02',
'playground',
'231',
2455.92,
default,
default);

Drop table if exists closing_days;
create table if not exists closing_days  (
    id int(10) not null primary key AUTO_INCREMENT,
    venueid int not null references venue(id),
    start date not null,
    end date not null
);
Drop table if exists opening_days;
create table if not exists opening_days  (
    id int(10) not null primary key AUTO_INCREMENT,
    venueid int not null references venue(id),
    weekdays int(1) not null,
    openinghour int(2) not null,
    closinghour int(2) not null
);

insert into closing_days values(default, 1, '2023-05-11', '2023-05-12');
insert into opening_days values(default, 1, 1, 9, 18);
insert into opening_days values(default, 1, 2, 9, 18);
insert into opening_days values(default, 1, 3, 9, 18);
insert into opening_days values(default, 1, 4, 9, 13);
insert into opening_days values(default, 1, 5, 13, 18);

insert into closing_days values(default, 2, '2023-05-15', '2023-05-18');
insert into opening_days values(default, 2, 1, 9, 18);
insert into opening_days values(default, 2, 2, 9, 18);
insert into opening_days values(default, 2, 3, 9, 18);
insert into opening_days values(default, 2, 4, 9, 13);
insert into opening_days values(default, 2, 5, 13, 18);


create table if not exists booking (
    id int(10) not null primary key AUTO_INCREMENT,
    memberid int not null references user(id),
    status int(1) default 0 not null,
    createtime datetime default CURRENT_TIMESTAMP,
    image varchar(255) null,
    
    guestlistid int references guestlist(id),
    remark varchar(500),

    checkintime datetime null,
    checkouttime datetime null,

    startdate date not null,
    starthour int(2) not null,
    totalhour int(2) not null,
    fee double(7,2) not null,

    template varchar(10) not null
);

create table if not exists member_comment  (
    id int(10) not null primary key AUTO_INCREMENT,
    bookingid int not null references booking(id),
    memberid int not null references member(id),
    remark varchar(500) not null
);

create table if not exists staff_remark  (
    id int(10) not null primary key AUTO_INCREMENT,
    bookingid int not null references booking(id)
);
create table if not exists remark_type (
    id int(10) not null primary key AUTO_INCREMENT,
    name varchar(255) not null
);
create table if not exists staff_remark_remark_type  (
    staff_remarkid int not null references staff_remark(id),
    remark_typeid int not null references remark_type(id)
);


