create table if not exists member (
    id int(10) not null primary key AUTO_INCREMENT,
    fname varchar(255) not null,
    lname varchar(255) not null,
    email varchar(255) not null,
    address varchar(255) not null,
    phone int(8) not null,
    pwd varchar(255) not null
);

create table if not exists guest (
    id int(10) not null primary key AUTO_INCREMENT,
    memberid int not null references member(id),
    name varchar(255) not null,
    email varchar(255) not null
);
create table if not exists guestlist (
    id int(10) not null primary key AUTO_INCREMENT,
    guestid int not null references guest(id)
);

create table if not exists template (
    id int(10) not null primary key AUTO_INCREMENT,
    file varchar(255) not null
);
create table if not exists notification (
    id int(10) not null primary key AUTO_INCREMENT,
    templateid int references template(id),
    remark varchar(500)
);

create table if not exists staff  (
    id int(10) not null primary key AUTO_INCREMENT,
    name varchar(255) not null,
    position int(1) not null default 0,
    pwd varchar(255) not null
);
Drop table if exists venue;
create table if not exists venue  (
    id int(10) not null primary key AUTO_INCREMENT,
    staffid int references staff(id),
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

insert into staff values(default, 'Chan Tai Man', 0, '123');
insert into venue values(default, 1, 'Happy Farm', 
'3 King Ling Road, Tiu King Ling, Hong Kong', 
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
    venueid int not null references venue(id),
    start date not null,
    end date not null
);
Drop table if exists opening_days;
create table if not exists opening_days  (
    venueid int not null references venue(id),
    weekdays int(1) not null,
    openinghour int(2) not null,
    closinghour int(2) not null
);
Drop table if exists opening_hours;

insert into closing_days values(1, '2023-05-11', '2023-05-12');
insert into opening_days values(1, 0, 9, 17);
insert into opening_days values(1, 1, 9, 20);
insert into opening_days values(1, 3, 9, 17);
insert into opening_days values(1, 5, 9, 20);
insert into opening_days values(1, 6, 9, 17);


create table if not exists booking (
    id int(10) not null primary key AUTO_INCREMENT,
    memberid int not null references member(id),
    status int(1) default 0 not null,
    createtime datetime default CURRENT_TIMESTAMP,
    image varchar(255) null,
    
    guestlistid int references guestlist(id),
    notificationid int references notification(id),

    checkintime datetime,
    checkouttime datetime,

    startdate date not null,
    starthour int(2) not null,
    totalhour int(2) not null,
    fee double(7,2) not null
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


