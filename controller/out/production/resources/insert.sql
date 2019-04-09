--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-04-08 18:02:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 209 (class 1259 OID 16633)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    author_id bigint NOT NULL,
    author_description character varying(255),
    author_first_name character varying(30) NOT NULL,
    author_last_name character varying(30) NOT NULL,
    author_wiki_link character varying(255)
);


ALTER TABLE public.author OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16514)
-- Name: author_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.author_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.author_sequence OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16881)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_id bigint NOT NULL,
    book_isbn character varying(255),
    book_age_restriction character varying(255),
    book_description character varying(255) NOT NULL,
    book_pages integer,
    book_pdf_url character varying(255),
    book_picture_url character varying(255),
    book_price numeric(10,2) NOT NULL,
    book_rating integer,
    book_size character varying(255),
    book_status integer,
    book_thumbnail_url character varying(255),
    book_title character varying(255) NOT NULL,
    book_type integer,
    book_weight integer,
    book_year integer,
    importer_id bigint NOT NULL,
    language_id bigint NOT NULL,
    producer_id bigint NOT NULL,
    publishing_house_id bigint NOT NULL
);


ALTER TABLE public.book OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16891)
-- Name: book_has_author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_has_author (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE public.book_has_author OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16896)
-- Name: book_has_genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_has_genre (
    book_id bigint NOT NULL,
    genre_id bigint NOT NULL
);


ALTER TABLE public.book_has_genre OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16901)
-- Name: book_has_translator; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_has_translator (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE public.book_has_translator OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16516)
-- Name: book_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.book_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_sequence OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16649)
-- Name: bookmark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookmark (
    bookmark_id bigint NOT NULL,
    book_id bigint,
    language_id bigint NOT NULL,
    user_id bigint
);


ALTER TABLE public.bookmark OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16518)
-- Name: bookmark_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bookmark_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bookmark_sequence OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16654)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    city_id bigint NOT NULL,
    city_name character varying(255) NOT NULL,
    state_id bigint
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16520)
-- Name: city_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_sequence OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16659)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    country_id bigint NOT NULL,
    country_name character varying(255) NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16522)
-- Name: country_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_sequence OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16664)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    genre_id bigint NOT NULL,
    genre_name character varying(255) NOT NULL,
    language_id bigint NOT NULL
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16763)
-- Name: genre_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genre_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genre_sequence OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16669)
-- Name: language; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.language (
    language_id bigint NOT NULL,
    language_name character varying(20) NOT NULL,
    language_tag character varying(5) NOT NULL
);


ALTER TABLE public.language OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16524)
-- Name: language_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.language_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.language_sequence OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16674)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    news_id bigint NOT NULL,
    news_creation_date timestamp without time zone,
    news_modification_date timestamp without time zone,
    news_picture_url character varying(255),
    news_thumbnail_url character varying(255),
    news_title character varying(255),
    user_id bigint NOT NULL,
    news_text character varying
);


ALTER TABLE public.news OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16526)
-- Name: news_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_sequence OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16682)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    order_id bigint NOT NULL,
    order_comment character varying(500),
    order_date_time timestamp without time zone NOT NULL,
    order_modification timestamp without time zone,
    order_status integer NOT NULL,
    book_id bigint,
    user_id bigint
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16528)
-- Name: order_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_sequence OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16690)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    organization_id bigint NOT NULL,
    organization_title character varying(255) NOT NULL
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16530)
-- Name: organization_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organization_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_sequence OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16695)
-- Name: publishing_house; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishing_house (
    publishing_house_id bigint NOT NULL,
    publishing_house_description character varying(255),
    publishing_house_logo_url character varying(255),
    publishing_house_site_link character varying(255),
    publishing_house_title character varying(20) NOT NULL
);


ALTER TABLE public.publishing_house OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16532)
-- Name: publishing_house_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.publishing_house_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.publishing_house_sequence OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16703)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    role_authority character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16534)
-- Name: role_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_sequence OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16708)
-- Name: state; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state (
    state_id bigint NOT NULL,
    state_name character varying(255) NOT NULL,
    country_id bigint
);


ALTER TABLE public.state OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16536)
-- Name: state_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.state_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.state_sequence OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16713)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_account_non_expired boolean,
    user_account_non_locked boolean,
    user_credentials_non_expired boolean,
    user_enabled boolean,
    user_password character varying(255) NOT NULL,
    user_username character varying(20) NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16718)
-- Name: user_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_data (
    user_data_address character varying(30),
    user_data_email character varying(30),
    user_data_first_name character varying(30),
    user_data_last_name character varying(30),
    user_data_postal_code integer,
    user_id bigint NOT NULL,
    city_id bigint
);


ALTER TABLE public.user_data OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16758)
-- Name: user_has_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_has_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_has_role OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16538)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgres;

--
-- TOC entry 2988 (class 0 OID 16633)
-- Dependencies: 209
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (1, NULL, 'Александр', 'Pushkin', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (2, NULL, 'Александр', 'Pushkin2', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (3, NULL, 'Александр', 'Pushkin3', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (4, NULL, 'Александр', 'Pushkin_4', NULL);


--
-- TOC entry 3004 (class 0 OID 16881)
-- Dependencies: 225
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book (book_id, book_isbn, book_age_restriction, book_description, book_pages, book_pdf_url, book_picture_url, book_price, book_rating, book_size, book_status, book_thumbnail_url, book_title, book_type, book_weight, book_year, importer_id, language_id, producer_id, publishing_house_id) VALUES (5, NULL, '16+', 'description', 123, 'pdfUrl', 'pictureUrl', 123.12, 8, 'size', 1, 'thumbnailUrl', 'title', 1, 123, 1993, 1, 1, 2, 1);
INSERT INTO public.book (book_id, book_isbn, book_age_restriction, book_description, book_pages, book_pdf_url, book_picture_url, book_price, book_rating, book_size, book_status, book_thumbnail_url, book_title, book_type, book_weight, book_year, importer_id, language_id, producer_id, publishing_house_id) VALUES (6, NULL, '16+', 'description', 123, 'pdfUrl', 'pictureUrl', 123.12, 8, 'size', 1, 'thumbnailUrl', 'title', 1, 123, 1993, 1, 2, 2, 3);


--
-- TOC entry 3005 (class 0 OID 16891)
-- Dependencies: 226
-- Data for Name: book_has_author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_has_author (book_id, author_id) VALUES (5, 1);
INSERT INTO public.book_has_author (book_id, author_id) VALUES (6, 1);


--
-- TOC entry 3006 (class 0 OID 16896)
-- Dependencies: 227
-- Data for Name: book_has_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3007 (class 0 OID 16901)
-- Dependencies: 228
-- Data for Name: book_has_translator; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2989 (class 0 OID 16649)
-- Dependencies: 210
-- Data for Name: bookmark; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2990 (class 0 OID 16654)
-- Dependencies: 211
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2991 (class 0 OID 16659)
-- Dependencies: 212
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2992 (class 0 OID 16664)
-- Dependencies: 213
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2993 (class 0 OID 16669)
-- Dependencies: 214
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.language (language_id, language_name, language_tag) VALUES (1, 'English', 'US_en');
INSERT INTO public.language (language_id, language_name, language_tag) VALUES (2, 'Русский', 'RU_ru');


--
-- TOC entry 2994 (class 0 OID 16674)
-- Dependencies: 215
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (1, '2019-04-08 14:28:22.278', NULL, 'qweqwe', 'qweqwe', 'title1', 3, NULL);
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (4, '2019-04-08 14:35:30.437', NULL, 'url', 'url', 'title2', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (5, NULL, '2019-04-08 14:45:58.533', 'url', 'url', 'lolek', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (6, NULL, '2019-04-08 14:46:07.76', 'url', 'url', 'kekkek', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (7, '2019-04-08 14:47:24.517', '2019-04-08 14:47:35.302', 'url', 'url', 'kekkek1', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');


--
-- TOC entry 2995 (class 0 OID 16682)
-- Dependencies: 216
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2996 (class 0 OID 16690)
-- Dependencies: 217
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization (organization_id, organization_title) VALUES (1, 'org1');
INSERT INTO public.organization (organization_id, organization_title) VALUES (2, 'org2');
INSERT INTO public.organization (organization_id, organization_title) VALUES (3, 'org3');
INSERT INTO public.organization (organization_id, organization_title) VALUES (6, 'org6');
INSERT INTO public.organization (organization_id, organization_title) VALUES (4, 'org_4');


--
-- TOC entry 2997 (class 0 OID 16695)
-- Dependencies: 218
-- Data for Name: publishing_house; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (1, 'British publishing house.', NULL, NULL, 'QWERTY');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (3, 'British publishing house.', NULL, NULL, 'QWERTY2');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (5, 'British publishing house.', NULL, NULL, 'QWERTY4');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (4, 'British publishing house.', NULL, NULL, 'QWERTY_4');


--
-- TOC entry 2998 (class 0 OID 16703)
-- Dependencies: 219
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (role_id, role_authority) VALUES (1, 'USER');
INSERT INTO public.role (role_id, role_authority) VALUES (4, 'LOH');
INSERT INTO public.role (role_id, role_authority) VALUES (5, 'QWERTY');
INSERT INTO public.role (role_id, role_authority) VALUES (6, 'ADMIN');
INSERT INTO public.role (role_id, role_authority) VALUES (7, 'LIBRARIAN');


--
-- TOC entry 2999 (class 0 OID 16708)
-- Dependencies: 220
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3000 (class 0 OID 16713)
-- Dependencies: 221
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (2, true, true, true, true, '$2a$10$fHbrEnODOETdKV9oYQL2He2o73QMAmrUYNj5O0nJIrRHrxZD2wrki', 'user');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (3, true, true, true, true, '$2a$10$lz3xPNK/S7/UVq.dwQWHAeRjH0687c6wOk.9.3/H07diPs9rVwhAK', 'admin');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (5, true, true, true, true, '$2a$10$Y7DImZgBEVeX9bO6aDWLsetVWWjHVQDxaCi/1i4HrLGac2Mvc/z42', 'librarian');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (7, true, true, true, true, '$2a$10$BsYpC1y.t3REIuFndRfIgO0PKsnOEfIU689SgGs7Se/ubUwKQIwZe', 'vasya');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (8, true, true, true, true, '$2a$10$a6jl5AgpfyC636QXAqu64uig5EeJDZwrFqSs6TUuhYyL7rT8zOlma', 'vasya1');


--
-- TOC entry 3001 (class 0 OID 16718)
-- Dependencies: 222
-- Data for Name: user_data; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3002 (class 0 OID 16758)
-- Dependencies: 223
-- Data for Name: user_has_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_has_role (user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (3, 1);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (5, 1);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (7, 1);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (8, 1);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (8, 4);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (3, 7);
INSERT INTO public.user_has_role (user_id, role_id) VALUES (3, 6);


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 196
-- Name: author_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_sequence', 5, true);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 197
-- Name: book_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_sequence', 6, true);


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 198
-- Name: bookmark_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookmark_sequence', 1, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 199
-- Name: city_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_sequence', 1, false);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 200
-- Name: country_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_sequence', 1, false);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 224
-- Name: genre_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genre_sequence', 1, false);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 201
-- Name: language_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.language_sequence', 1, false);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 202
-- Name: news_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_sequence', 7, true);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 203
-- Name: order_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_sequence', 1, false);


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 204
-- Name: organization_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_sequence', 6, true);


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 205
-- Name: publishing_house_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.publishing_house_sequence', 5, true);


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 206
-- Name: role_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_sequence', 7, true);


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 207
-- Name: state_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.state_sequence', 1, false);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 208
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 8, true);


--
-- TOC entry 2774 (class 2606 OID 16640)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);


--
-- TOC entry 2826 (class 2606 OID 16895)
-- Name: book_has_author book_has_author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT book_has_author_pkey PRIMARY KEY (book_id, author_id);


--
-- TOC entry 2828 (class 2606 OID 16900)
-- Name: book_has_genre book_has_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_genre
    ADD CONSTRAINT book_has_genre_pkey PRIMARY KEY (book_id, genre_id);


--
-- TOC entry 2830 (class 2606 OID 16905)
-- Name: book_has_translator book_has_translator_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT book_has_translator_pkey PRIMARY KEY (book_id, author_id);


--
-- TOC entry 2822 (class 2606 OID 16888)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- TOC entry 2776 (class 2606 OID 16653)
-- Name: bookmark bookmark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT bookmark_pkey PRIMARY KEY (bookmark_id);


--
-- TOC entry 2778 (class 2606 OID 16658)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (city_id);


--
-- TOC entry 2782 (class 2606 OID 16663)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- TOC entry 2786 (class 2606 OID 16668)
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (genre_id);


--
-- TOC entry 2788 (class 2606 OID 16673)
-- Name: language language_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT language_pkey PRIMARY KEY (language_id);


--
-- TOC entry 2794 (class 2606 OID 16681)
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (news_id);


--
-- TOC entry 2796 (class 2606 OID 16689)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2798 (class 2606 OID 16694)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (organization_id);


--
-- TOC entry 2802 (class 2606 OID 16702)
-- Name: publishing_house publishing_house_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishing_house
    ADD CONSTRAINT publishing_house_pkey PRIMARY KEY (publishing_house_id);


--
-- TOC entry 2806 (class 2606 OID 16707)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 2810 (class 2606 OID 16712)
-- Name: state state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (state_id);


--
-- TOC entry 2790 (class 2606 OID 16732)
-- Name: language uk_6yesqwmvy381wds1y8fuql8dd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT uk_6yesqwmvy381wds1y8fuql8dd UNIQUE (language_tag);


--
-- TOC entry 2824 (class 2606 OID 16890)
-- Name: book uk_7xa554b1vghpa2wt12xcu96h1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT uk_7xa554b1vghpa2wt12xcu96h1 UNIQUE (book_isbn);


--
-- TOC entry 2804 (class 2606 OID 16736)
-- Name: publishing_house uk_aa3bbx1bs03os7jfnfxc65pq1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishing_house
    ADD CONSTRAINT uk_aa3bbx1bs03os7jfnfxc65pq1 UNIQUE (publishing_house_title);


--
-- TOC entry 2780 (class 2606 OID 16726)
-- Name: city uk_djnk44fptegbsu6drhc9xvlfj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT uk_djnk44fptegbsu6drhc9xvlfj UNIQUE (city_name);


--
-- TOC entry 2814 (class 2606 OID 16742)
-- Name: user uk_jnu1quvkutdk73q9fa4d7abe3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_jnu1quvkutdk73q9fa4d7abe3 UNIQUE (user_username);


--
-- TOC entry 2800 (class 2606 OID 16734)
-- Name: organization uk_kx0xkio22n1a7hwi4uo9a4dwh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT uk_kx0xkio22n1a7hwi4uo9a4dwh UNIQUE (organization_title);


--
-- TOC entry 2792 (class 2606 OID 16730)
-- Name: language uk_mpvpyjgetru6cvudxld43ek8p; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT uk_mpvpyjgetru6cvudxld43ek8p UNIQUE (language_name);


--
-- TOC entry 2808 (class 2606 OID 16738)
-- Name: role uk_o79nvg1dfvmabgsx1owdoyfkh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_o79nvg1dfvmabgsx1owdoyfkh UNIQUE (role_authority);


--
-- TOC entry 2784 (class 2606 OID 16728)
-- Name: country uk_qrkn270121aljmucrdbnmd35p; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_qrkn270121aljmucrdbnmd35p UNIQUE (country_name);


--
-- TOC entry 2812 (class 2606 OID 16740)
-- Name: state uk_qtjsbpmp2ejq0753ktldenyqo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT uk_qtjsbpmp2ejq0753ktldenyqo UNIQUE (state_name);


--
-- TOC entry 2818 (class 2606 OID 16722)
-- Name: user_data user_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT user_data_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2820 (class 2606 OID 16762)
-- Name: user_has_role user_has_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT user_has_role_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2816 (class 2606 OID 16717)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2850 (class 2606 OID 16946)
-- Name: book_has_genre fk1gxcd2i1ke4sl62w102j55sbg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_genre
    ADD CONSTRAINT fk1gxcd2i1ke4sl62w102j55sbg FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id);


--
-- TOC entry 2833 (class 2606 OID 16795)
-- Name: bookmark fk3ogdxsxa4tx6vndyvpk1fk1am; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT fk3ogdxsxa4tx6vndyvpk1fk1am FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2836 (class 2606 OID 16810)
-- Name: news fk4538gbwfa03nwr9edl3fdloo9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT fk4538gbwfa03nwr9edl3fdloo9 FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2852 (class 2606 OID 16956)
-- Name: book_has_translator fk57270pfwi4ix2hmgpq8ma4g5l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT fk57270pfwi4ix2hmgpq8ma4g5l FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 2837 (class 2606 OID 16931)
-- Name: order fk5wfkmki9lhx405em2mghlpqvc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk5wfkmki9lhx405em2mghlpqvc FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2844 (class 2606 OID 16906)
-- Name: book fk6igju4vpj65jn5oa1jlrwm724; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk6igju4vpj65jn5oa1jlrwm724 FOREIGN KEY (importer_id) REFERENCES public.organization(organization_id);


--
-- TOC entry 2834 (class 2606 OID 16800)
-- Name: city fk6p2u50v8fg2y0js6djc6xanit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(state_id);


--
-- TOC entry 2832 (class 2606 OID 16790)
-- Name: bookmark fk87b7xna7pgmig5j2p3rkww6op; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT fk87b7xna7pgmig5j2p3rkww6op FOREIGN KEY (language_id) REFERENCES public.language(language_id);


--
-- TOC entry 2840 (class 2606 OID 16830)
-- Name: user_data fkak5ac1lrkb8t4ug4rm4gqqvnt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT fkak5ac1lrkb8t4ug4rm4gqqvnt FOREIGN KEY (city_id) REFERENCES public.city(city_id);


--
-- TOC entry 2842 (class 2606 OID 16870)
-- Name: user_has_role fkc1m07gjgx777ukpfw6wa94dfh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT fkc1m07gjgx777ukpfw6wa94dfh FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 2843 (class 2606 OID 16875)
-- Name: user_has_role fkdtkvc2iy3ph1rkvd67yl2t13m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT fkdtkvc2iy3ph1rkvd67yl2t13m FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2849 (class 2606 OID 16941)
-- Name: book_has_author fke3bv194wk8auirroafh2b90uu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT fke3bv194wk8auirroafh2b90uu FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2835 (class 2606 OID 16805)
-- Name: genre fkelksj2rqr5r0jkf0tds4cwreh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT fkelksj2rqr5r0jkf0tds4cwreh FOREIGN KEY (language_id) REFERENCES public.language(language_id);


--
-- TOC entry 2841 (class 2606 OID 16835)
-- Name: user_data fkelmpammgqm7p72tyao7p2vb00; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT fkelmpammgqm7p72tyao7p2vb00 FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2839 (class 2606 OID 16825)
-- Name: state fkghic7mqjt6qb9vq7up7awu0er; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT fkghic7mqjt6qb9vq7up7awu0er FOREIGN KEY (country_id) REFERENCES public.country(country_id);


--
-- TOC entry 2853 (class 2606 OID 16961)
-- Name: book_has_translator fkl6cov79j6h3xvri0que8d2nv2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT fkl6cov79j6h3xvri0que8d2nv2 FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2847 (class 2606 OID 16921)
-- Name: book fkljoqq01hogerbaa4odx0wkgiu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkljoqq01hogerbaa4odx0wkgiu FOREIGN KEY (publishing_house_id) REFERENCES public.publishing_house(publishing_house_id);


--
-- TOC entry 2845 (class 2606 OID 16911)
-- Name: book fkmrhfp9wfi5dy4bwl87bx8ivua; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkmrhfp9wfi5dy4bwl87bx8ivua FOREIGN KEY (language_id) REFERENCES public.language(language_id);


--
-- TOC entry 2848 (class 2606 OID 16936)
-- Name: book_has_author fkog6a3yjob1latspggxqlmc72j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT fkog6a3yjob1latspggxqlmc72j FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 2846 (class 2606 OID 16916)
-- Name: book fkohik7akb02iea0p0bv12ux4k4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkohik7akb02iea0p0bv12ux4k4 FOREIGN KEY (producer_id) REFERENCES public.organization(organization_id);


--
-- TOC entry 2851 (class 2606 OID 16951)
-- Name: book_has_genre fkoi87rsf0hva9bgyv7up5a47vv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_genre
    ADD CONSTRAINT fkoi87rsf0hva9bgyv7up5a47vv FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2831 (class 2606 OID 16926)
-- Name: bookmark fkpboovgmm276cj01bo2nfkdidq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT fkpboovgmm276cj01bo2nfkdidq FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2838 (class 2606 OID 16820)
-- Name: order fksunlg4strlu6w0662xu56ph2l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fksunlg4strlu6w0662xu56ph2l FOREIGN KEY (user_id) REFERENCES public.user_data(user_id);


-- Completed on 2019-04-08 18:02:54

--
-- PostgreSQL database dump complete
--

