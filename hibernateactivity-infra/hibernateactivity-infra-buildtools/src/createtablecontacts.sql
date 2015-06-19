create table Contacts(
    id int not null, 
    contact text default null, 
    person_id int default null, 
    type text, PRIMARY KEY (id)
);

