CREATE TABLE public.users
(
    id bigint NOT NULL,
    login character varying(20) NOT NULL,
    pass character varying(30) NOT NULL,
    name character varying(30) NOT NULL,
    access_level character varying(20) NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.users
    OWNER to libman;
	

CREATE TABLE public.books
(
    id bigint NOT NULL,
    title character varying(50) NOT NULL,
    year integer NOT NULL,
    available integer,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.books
    OWNER to libman;
	

	
	
CREATE TABLE public.orders
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    book_id bigint NOT NULL,
    location character varying(30) NOT NULL,
    status character varying(30) NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.orders
    OWNER to libman;
	
ALTER TABLE public.orders
    ADD CONSTRAINT book_id FOREIGN KEY (book_id)
    REFERENCES public.books (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX fki_book_id
    ON public.orders(book_id);
	
ALTER TABLE public.orders
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX fki_user_id
    ON public.orders(user_id);
	
	
	
CREATE TABLE public.authors
(
    id bigint NOT NULL,
    name character varying(30),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.authors
    OWNER to libman;
	
	
	
CREATE TABLE public.book_authors
(
    book_id bigint NOT NULL,
    author_id bigint NOT NULL,
    PRIMARY KEY (book_id, author_id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.book_authors
    OWNER to libman;
	
ALTER TABLE public.book_authors
    ADD CONSTRAINT book_id FOREIGN KEY (book_id)
    REFERENCES public.books (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX fki_book_id_book_authors
    ON public.book_authors(book_id);
	
ALTER TABLE public.book_authors
    ADD CONSTRAINT author_id FOREIGN KEY (author_id)
    REFERENCES public.authors (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX fki_author_id_book_authors
    ON public.book_authors(author_id);
	
	
	
CREATE SEQUENCE public.orders_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.orders_seq
    OWNER TO libman;
	
	
CREATE SEQUENCE public.users_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_seq
    OWNER TO libman;
	
	
CREATE SEQUENCE public.books_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.books_seq
    OWNER TO libman;
	
CREATE SEQUENCE public.authors_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.authors_seq
    OWNER TO libman;