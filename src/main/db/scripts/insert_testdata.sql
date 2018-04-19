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

INSERT INTO public.roles(role_id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.roles(role_id, role) VALUES (2, 'LIBRARIAN');
INSERT INTO public.roles(role_id, role) VALUES (3, 'READER');


INSERT INTO public.users(id, login, pass, name) VALUES (nextval('users_seq'), 'admin', '{bcrypt}$2a$10$V/YgEsxpLddGdyRwNL6GDuKGn.tnLXmDbUIe9QhxEkDjErLysTUyi', 'Vladimir Vladimirovich', 'ADMIN');
INSERT INTO public.users(id, login, pass, name) VALUES (nextval('users_seq'), 'lib', '{bcrypt}$2a$10$UzzbGYtT.tu.nOlWafAyxOAl1pzTxr/WjinhqMIuDbxSdV6X8X34y', 'Vasya Nevskiy', 'LIBRARIAN');
INSERT INTO public.users(id, login, pass, name) VALUES (nextval('users_seq'), '111', '{bcrypt}$2a$10$.HNNjs3eXwcNFk8ehwnqZOakwD3xzyQFmQk6PlDsUY3xKTVPLm7ee', 'Gocha Novlyansckiy', 'READER');
INSERT INTO public.users(id, login, pass, name) VALUES (nextval('users_seq'), 'abba', '{bcrypt}$2a$10$BLhrpiue9kPhouVEtdu.IekRaAAwfCXgUG7kVQyJcKELANc0gLYLS', 'Harry Zaycev', 'READER');
INSERT INTO public.users(id, login, pass, name) VALUES (nextval('users_seq'), 'as', '{bcrypt}$2a$10$.I2dyE1W4vvUWIP5onzL2.5dqWD7YH32t1aCoCJce3DKtjv9C/jTu', 'Ivan Ivanov', 'READER');

INSERT INTO public.user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role(user_id, role_id) VALUES (2, 2);
INSERT INTO public.user_role(user_id, role_id) VALUES (3, 3);
INSERT INTO public.user_role(user_id, role_id) VALUES (4, 3);
INSERT INTO public.user_role(user_id, role_id) VALUES (5, 3);

INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 1, 'READING_ROOM', 'IN_LIBRARY');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 2, 'READING_ROOM', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 4, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 3, 'HOME', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 1, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 5, 7, 'READING_ROOM', 'REQUESTED');
