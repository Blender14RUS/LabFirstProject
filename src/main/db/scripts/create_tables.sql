CREATE TABLE IF NOT EXISTS public.users
(
  id    BIGINT                 NOT NULL,
  login CHARACTER VARYING(20)  NOT NULL,
  pass  CHARACTER VARYING(100) NOT NULL,
  name  CHARACTER VARYING(30)  NOT NULL,
  PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

ALTER TABLE  public.users
  OWNER TO libman;

CREATE TABLE IF NOT EXISTS public.books
(
  id        BIGINT                NOT NULL,
  title     CHARACTER VARYING(50) NOT NULL,
  year      INTEGER               NOT NULL,
  available INTEGER,
  PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

ALTER TABLE public.books
  OWNER TO libman;


CREATE TABLE IF NOT EXISTS public.orders
(
  id       BIGINT                NOT NULL,
  user_id  BIGINT                NOT NULL,
  book_id  BIGINT                NOT NULL,
  location CHARACTER VARYING(30) NOT NULL,
  status   CHARACTER VARYING(30) NOT NULL,
  PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);

ALTER TABLE public.orders
  OWNER TO libman;

ALTER TABLE public.orders
  ADD CONSTRAINT book_id FOREIGN KEY (book_id)
REFERENCES public.books (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_book_id
  ON public.orders (book_id);

ALTER TABLE public.orders
  ADD CONSTRAINT user_id FOREIGN KEY (user_id)
REFERENCES public.users (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_user_id
  ON public.orders (user_id);


CREATE TABLE IF NOT EXISTS public.authors
(
  id   BIGINT NOT NULL,
  name CHARACTER VARYING(30),
  PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);

ALTER TABLE public.authors
  OWNER TO libman;


CREATE TABLE IF NOT EXISTS public.book_authors
(
  book_id   BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  PRIMARY KEY (book_id, author_id)
)
WITH (
OIDS = FALSE
);

ALTER TABLE public.book_authors
  OWNER TO libman;

ALTER TABLE public.book_authors
  ADD CONSTRAINT book_id FOREIGN KEY (book_id)
REFERENCES public.books (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_book_id_book_authors
  ON public.book_authors (book_id);

ALTER TABLE public.book_authors
  ADD CONSTRAINT author_id FOREIGN KEY (author_id)
REFERENCES public.authors (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_author_id_book_authors
  ON public.book_authors (author_id);


CREATE TABLE IF NOT EXISTS public.roles
(
  role_id BIGINT,
  role    CHARACTER VARYING(20),
  PRIMARY KEY (role_id)
)
WITH (
OIDS = FALSE
);

ALTER TABLE public.roles
  OWNER TO libman;


CREATE TABLE IF NOT EXISTS public.user_role
(
  user_id BIGINT,
  role_id BIGINT,
  PRIMARY KEY (user_id, role_id)
)
WITH (
OIDS = FALSE
);

ALTER TABLE public.user_role
  OWNER TO libman;

ALTER TABLE public.user_role
  ADD CONSTRAINT login FOREIGN KEY (user_id)
REFERENCES public.users (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_login
  ON public.user_role (user_id);

ALTER TABLE public.user_role
  ADD CONSTRAINT role_user FOREIGN KEY (role_id)
REFERENCES public.roles (role_id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;
CREATE INDEX fki_role_user
  ON public.user_role (role_id);


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

