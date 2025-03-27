insert into permissions (authority, admin, librarian) values 
('ROLE_ADD_PERMISSION', 1, 0), 
('ROLE_FIND_ALL_PERMISSION', 1, 0),
('ROLE_FIND_BY_ID_PERMISSION', 1, 0), 
('ROLE_SEARCH_PERMISSION', 1, 0),
('ROLE_PAGINATE_PERMISSION', 1, 0), 
('ROLE_DELETE_PERMISSION', 1, 0), 
('ROLE_UPDATE_PERMISSION', 1, 0),
('ROLE_ADD_BOOK', 0, 1), 
('ROLE_FIND_ALL_BOOKS', 0, 1),
('ROLE_FIND_BY_ID_BOOK', 0, 1), 
('ROLE_SEARCH_BOOK', 0, 1),
('ROLE_PAGINATE_BOOK', 0, 1), 
('ROLE_DELETE_BOOK', 0, 1), 
('ROLE_UPDATE_BOOK', 0, 1);

insert into librarians (name, surname, birthday, email, phone, city, country, street, zip_code, user_id) values
('Ali', 'Aliyev', '2005-01-11', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 1),
('Orxan', 'Aliyev', '2005-01-01', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 2),
('Qulu', 'Aliyev', '2005-02-01', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 3);

insert into users (username, password, enabled, active, type) values
('a1', '{noop}1', 1, 1, 'admin'),
('l1', '{noop}1', 1, 1, 'librarian'),
('l2', '{noop}1', 1, 1, 'librarian');

insert into books (name, price, author, description, color, page_count, publish_date, operator_id ) values
('Abb', '25', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'red', '212', '2001-01-12', 2 ),
('Add', '25', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'red', '212', '2001-01-12', 2 ),
('Acc', '25', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'red', '212', '2001-01-12', 2 ),
('ABB', '25', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'red', '212', '2001-01-12', 3 );


insert into authorities (username, authority) select 'a1', authority from permissions where admin=1;

insert into authorities (username, authority) select 'l1', authority from permissions where librarian=1;

insert into authorities (username, authority) select 'l2', authority from permissions where librarian=1;