INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Wife Between Us', 2018, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Before We Were Yours', 2017, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Ready Player One', 2011, 2);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Harry Potter and the Sorcerer''s Stone', 2015, 3);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'An American Princess: The Many Lives of Allene Tew', 2018, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Great Alone: A Novel', 2018, 10);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Winter Garden', 2010, 2);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Milk and Honey', 2015, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Best of Us: A Novel', 2013, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Importance of Being Ernest', 2013, 50);


INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Greer Hendricks');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Sarah Pekkanen');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Ernest Cline');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'J.K. Rowling');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Lisa Wingate');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Annejet van der Zijl');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Kristin Hannah');
INSERT INTO public.authors(id, name) VALUES (nextval('authors_seq'), 'Rupi Kaur');


INSERT INTO public.book_authors(book_id, author_id) VALUES (1, 1);
INSERT INTO public.book_authors(book_id, author_id) VALUES (1, 2);
INSERT INTO public.book_authors(book_id, author_id) VALUES (2, 5);
INSERT INTO public.book_authors(book_id, author_id) VALUES (3, 3);
INSERT INTO public.book_authors(book_id, author_id) VALUES (4, 4);
INSERT INTO public.book_authors(book_id, author_id) VALUES (5, 6);
INSERT INTO public.book_authors(book_id, author_id) VALUES (6, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (7, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (8, 8);
INSERT INTO public.book_authors(book_id, author_id) VALUES (9, 2);
INSERT INTO public.book_authors(book_id, author_id) VALUES (10, 3);


INSERT INTO public.users(id, login, pass, name, access_level) VALUES (nextval('users_seq'), 'admin', 'root', 'Vladimir Vladimirovich', 'ADMIN');
INSERT INTO public.users(id, login, pass, name, access_level) VALUES (nextval('users_seq'), 'dog12', '666a66', 'Vasya Nevskiy', 'LIBRARIAN');
INSERT INTO public.users(id, login, pass, name, access_level) VALUES (nextval('users_seq'), 'killer', 'qwerty1', 'Gocha Novlyansckiy', 'READER');
INSERT INTO public.users(id, login, pass, name, access_level) VALUES (nextval('users_seq'), 'dog12', '666a66', 'Harry Zaycev', 'READER');
INSERT INTO public.users(id, login, pass, name, access_level) VALUES (nextval('users_seq'), 'ahmat', '9841asf', 'Ivan Ivanov', 'READER');


INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 1, 'READING_ROOM', 'IN_LIBRARY');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 2, 'READING_ROOM', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 4, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 3, 'HOME', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 1, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 5, 7, 'READING_ROOM', 'REQUESTED');