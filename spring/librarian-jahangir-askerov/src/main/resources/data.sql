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
('ROLE_DELETE_STUDENT', 0, 1, 0),
('ROLE_UPDATE_STUDENT', 0, 1, 0),
('ROLE_FILTER_STUDENTS', 0, 1, 0),
('ROLE_STUDENT_SEARCH_BOOKS', 0, 0, 1),
('ROLE_LENDING_BOOK', 0, 1, 0),
('ROLE_FIND_ALL_LIBRARIANS_WITH_BOOK_COUNT', 1, 0, 0);

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

insert into translations (language, word, translate) values
('en', 'EDIT', 'edit'),
('en', 'COPY', 'copy'),
('az', 'EDIT', 'Redaktə et'),
('az', 'COPY', 'Kopyalayın');

insert into book_lendings (book_id, student_id, librarian_id, borrow_date, return_date, fine_amount, actual_return_date, book_condition, status, note ) values
(1, 1, 2, '2025-10-13', '2025-10-20', 20, null, 'NEW', 'ACTIVE', 'Borrowed for exam');
 
insert into authorities (username, authority) select 'a1', authority from permissions where admin=1;

insert into authorities (username, authority) select 'l1', authority from permissions where librarian=1;

insert into authorities (username, authority) select 'l2', authority from permissions where librarian=1;

insert into authorities (username, authority) select 's1', authority from permissions where student=1;

drop table librarians_book_count;

create view librarians_book_count as (
select l.id, l.name, l.surname, u.username, count(b.name) as book_count from librarians l left join books b on l.id = b.operator_id inner join users u on l.user_id = u.id group by l.id, l.name, l.surname, u.username order by count(b.name) desc
);
  
drop table borrowed_books_details;

create view borrowed_books_details as (
SELECT bl.id, bl.student_id, bl.book_id, bl.borrow_date, bl.return_date, bl.actual_return_date, bl.status, bl.book_condition, bl.return_condition, bl.fine_amount, bl.note, b.name as book_name, l.id as librarian_identity, l.name as librarian_name, s.name as student_name, s.surname as student_surname, s.email as student_email, s.phone as student_phone, s.country, s.city from book_lendings bl left join books b on bl.book_id = b.id left join students s on bl.student_id = s.id left join librarians l on bl.librarian_id = l.id
);
