create sequence hibernate_sequence start 1 increment 1
create table tb_notes (id int8 not null, created_date_time timestamp not null, note_description varchar(255) not null, note_recipient varchar(255) not null, note_sender varchar(255) not null, primary key (id))
