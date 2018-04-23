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
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'A Long Way Gone: Memoirs of a Boy Soldier', 2008, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Catch-22: 50th Anniversary Edition', 2011, 13);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Gone Girl', 2014, 0);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'In Cold Blood', 2004, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Lolita', 1993, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Me Talk Pretty One Day', 2001, 9);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Out of Africa', 1992, 6);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Silent Spring', 2002, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Diary of a Young Girl', 2010, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Hunger Games (Book 1)', 2010, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Lord of the Rings', 2012, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Right Stuff', 2008, 3);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Shining', 2012, 2);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Sun Also Rises', 2006, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Wind in the Willows', 2003, 5);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Things Fall Apart', 1994, 10);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), '2001: a Space Odyssey', 2000, 2);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Fahrenheit 451', 2012, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'Frankenstein ', 1994, 1);
INSERT INTO public.books(id, title, year, available) VALUES (nextval('books_seq'), 'The Martian', 2014, 10);


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
INSERT INTO public.book_authors(book_id, author_id) VALUES (11, 1);
INSERT INTO public.book_authors(book_id, author_id) VALUES (12, 2);
INSERT INTO public.book_authors(book_id, author_id) VALUES (13, 5);
INSERT INTO public.book_authors(book_id, author_id) VALUES (14, 3);
INSERT INTO public.book_authors(book_id, author_id) VALUES (15, 4);
INSERT INTO public.book_authors(book_id, author_id) VALUES (16, 6);
INSERT INTO public.book_authors(book_id, author_id) VALUES (17, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (18, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (19, 8);
INSERT INTO public.book_authors(book_id, author_id) VALUES (20, 2);
INSERT INTO public.book_authors(book_id, author_id) VALUES (21, 3);
INSERT INTO public.book_authors(book_id, author_id) VALUES (22, 1);
INSERT INTO public.book_authors(book_id, author_id) VALUES (23, 2);
INSERT INTO public.book_authors(book_id, author_id) VALUES (23, 6);
INSERT INTO public.book_authors(book_id, author_id) VALUES (23, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (24, 5);
INSERT INTO public.book_authors(book_id, author_id) VALUES (25, 3);
INSERT INTO public.book_authors(book_id, author_id) VALUES (26, 4);
INSERT INTO public.book_authors(book_id, author_id) VALUES (27, 6);
INSERT INTO public.book_authors(book_id, author_id) VALUES (28, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (29, 7);
INSERT INTO public.book_authors(book_id, author_id) VALUES (30, 8);
INSERT INTO public.book_authors(book_id, author_id) VALUES (30, 1);

INSERT INTO public.users(id, login, pass, name, access_level, language) VALUES (nextval('users_seq'), 'admin', '{bcrypt}$2a$10$V/YgEsxpLddGdyRwNL6GDuKGn.tnLXmDbUIe9QhxEkDjErLysTUyi', 'Vladimir Vladimirovich', 'ADMIN','en_US');
INSERT INTO public.users(id, login, pass, name, access_level, language) VALUES (nextval('users_seq'), 'lib', '{bcrypt}$2a$10$UzzbGYtT.tu.nOlWafAyxOAl1pzTxr/WjinhqMIuDbxSdV6X8X34y', 'Vasya Nevskiy', 'LIBRARIAN','en_US');
INSERT INTO public.users(id, login, pass, name, access_level, language) VALUES (nextval('users_seq'), '111', '{bcrypt}$2a$10$.HNNjs3eXwcNFk8ehwnqZOakwD3xzyQFmQk6PlDsUY3xKTVPLm7ee', 'Gocha Novlyansckiy', 'READER','en_US');
INSERT INTO public.users(id, login, pass, name, access_level, language) VALUES (nextval('users_seq'), 'abba', '{bcrypt}$2a$10$BLhrpiue9kPhouVEtdu.IekRaAAwfCXgUG7kVQyJcKELANc0gLYLS', 'Harry Zaycev', 'READER','ru_RU');
INSERT INTO public.users (id, login, pass, name, access_level, language) VALUES
  (nextval('users_seq'), 'as', '{bcrypt}$2a$10$.I2dyE1W4vvUWIP5onzL2.5dqWD7YH32t1aCoCJce3DKtjv9C/jTu', 'Ivan Ivanov',
   'READER', 'en_US');



INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 1, 'READING_ROOM', 'IN_LIBRARY');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 2, 'READING_ROOM', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 3, 4, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 3, 'HOME', 'GIVEN');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 4, 1, 'HOME', 'REQUESTED');
INSERT INTO public.orders(id, user_id, book_id, location, status) VALUES (nextval('orders_seq'), 5, 7, 'READING_ROOM', 'REQUESTED');

