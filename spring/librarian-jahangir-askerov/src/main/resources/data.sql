insert into permissions (authority, admin, librarian, student) values 
('ROLE_ADD_PERMISSION', 1, 0, 0), 
('ROLE_FIND_ALL_PERMISSIONS', 1, 0, 0),
('ROLE_FIND_BY_ID_PERMISSION', 1, 0, 0), 
('ROLE_SEARCH_PERMISSIONS', 1, 0, 0),
('ROLE_PAGINATE_PERMISSIONS', 1, 0, 0), 
('ROLE_DELETE_PERMISSION', 1, 0, 0), 
('ROLE_UPDATE_PERMISSION', 1, 0, 0),
('ROLE_ADD_BOOK', 0, 1, 0), 
('ROLE_FIND_ALL_BOOKS', 0, 1, 1),
('ROLE_FIND_BY_ID_BOOK', 0, 1, 1), 
('ROLE_SEARCH_BOOKS', 0, 1, 0),
('ROLE_PAGINATE_BOOKS', 0, 1, 0), 
('ROLE_DELETE_BOOK', 0, 1, 0), 
('ROLE_UPDATE_BOOK', 0, 1, 0),
('ROLE_FILTER_BOOKS', 0, 1, 0),
('ROLE_ADD_STUDENT', 0, 1, 0),
('ROLE_STUDENT_SEARCH_BOOKS', 0, 0, 1);

insert into librarians (name, surname, birthday, email, phone, city, country, street, zip_code, user_id) values
('Ali', 'Aliyev', '2005-01-11', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 1),
('Orxan', 'Aliyev', '2005-01-01', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 2),
('Qulu', 'Aliyev', '2005-02-01', 'cavid22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 3);

insert into users (username, password, enabled, active, type) values
('a1', '{noop}1', 1, 1, 'admin'),
('l1', '{noop}1', 1, 1, 'librarian'),
('l2', '{noop}1', 1, 1, 'librarian'),
('s1', '{noop}1', 1, 1, 'student');

insert into books (name, price, author, description, color, page_count, publish_date, operator_id, book_category ) values
('1984', '25', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'red', '212', '2001-01-12', 2, 1 ),
('Moby-Dick', '70', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'black', '289', '2003-03-12', 2, 1 ),
('Pride and Prejudice', '10', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'green', '101', '2021-05-12', 2, 1 ),
('Don Quixote', '120', 'GEORGE ORWELL', 'It tells the story of Winston Smith', 'green', '567', '2011-07-12', 3, 1 );

insert into students (name, surname, birthday, email, phone, city, country, street, zip_code, user_id, operator_id ) values
('Amil', 'Orucov', '2005-01-11', 'amil22@gmail.com', '055-011-11-11', 'Baku', 'Ajarbaijan', 'not', '12345', 4, 2);


insert into authorities (username, authority) select 'a1', authority from permissions where admin=1;

insert into authorities (username, authority) select 'l1', authority from permissions where librarian=1;

insert into authorities (username, authority) select 'l2', authority from permissions where librarian=1;

insert into authorities (username, authority) select 's1', authority from permissions where student=1;