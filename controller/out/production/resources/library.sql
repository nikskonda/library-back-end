--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-04-08 17:21:32

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
-- TOC entry 2990 (class 1262 OID 16393)
-- Name: library; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE library WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE library OWNER TO postgres;

\connect library

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
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16394)
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
-- TOC entry 213 (class 1259 OID 16514)
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
-- TOC entry 197 (class 1259 OID 16402)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_id bigint NOT NULL,
    book_isbn character varying(255),
    book_age_restriction character varying(255),
    book_description character varying(255) NOT NULL,
    book_language bytea NOT NULL,
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
    producer_id bigint NOT NULL
);


ALTER TABLE public.book OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16499)
-- Name: book_has_author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_has_author (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE public.book_has_author OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16504)
-- Name: book_has_translator; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_has_translator (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE public.book_has_translator OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16516)
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
-- TOC entry 198 (class 1259 OID 16410)
-- Name: bookmark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookmark (
    bookmark_id bigint NOT NULL,
    bookmark_page integer NOT NULL,
    book_id bigint,
    user_id bigint
);


ALTER TABLE public.bookmark OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16518)
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
-- TOC entry 199 (class 1259 OID 16415)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    city_id bigint NOT NULL,
    city_name character varying(255) NOT NULL,
    state_id bigint
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16520)
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
-- TOC entry 200 (class 1259 OID 16420)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    country_id bigint NOT NULL,
    country_name character varying(255) NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16522)
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
-- TOC entry 201 (class 1259 OID 16425)
-- Name: language; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.language (
    language_id bigint NOT NULL,
    language_name character varying(20) NOT NULL,
    language_tag character varying(5) NOT NULL
);


ALTER TABLE public.language OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16524)
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
-- TOC entry 202 (class 1259 OID 16430)
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
-- TOC entry 219 (class 1259 OID 16526)
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
-- TOC entry 203 (class 1259 OID 16438)
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
-- TOC entry 220 (class 1259 OID 16528)
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
-- TOC entry 204 (class 1259 OID 16446)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    organization_id bigint NOT NULL,
    organization_title character varying(255) NOT NULL
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16530)
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
-- TOC entry 205 (class 1259 OID 16451)
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
-- TOC entry 222 (class 1259 OID 16532)
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
-- TOC entry 206 (class 1259 OID 16459)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    role_authority character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16534)
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
-- TOC entry 207 (class 1259 OID 16464)
-- Name: state; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state (
    state_id bigint NOT NULL,
    state_name character varying(255) NOT NULL,
    country_id bigint
);


ALTER TABLE public.state OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16536)
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
-- TOC entry 208 (class 1259 OID 16469)
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
-- TOC entry 209 (class 1259 OID 16474)
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
-- TOC entry 212 (class 1259 OID 16509)
-- Name: user_has_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_has_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_has_role OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16538)
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
-- TOC entry 2955 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (1, NULL, 'Александр', 'Pushkin', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (2, NULL, 'Александр', 'Pushkin2', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (3, NULL, 'Александр', 'Pushkin3', NULL);
INSERT INTO public.author (author_id, author_description, author_first_name, author_last_name, author_wiki_link) VALUES (4, NULL, 'Александр', 'Pushkin_4', NULL);


--
-- TOC entry 2956 (class 0 OID 16402)
-- Dependencies: 197
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book (book_id, book_isbn, book_age_restriction, book_description, book_language, book_pages, book_pdf_url, book_picture_url, book_price, book_rating, book_size, book_status, book_thumbnail_url, book_title, book_type, book_weight, book_year, importer_id, producer_id) VALUES (5, NULL, '16+', 'description', '\xaced00057372002062792e626e74752e666974722e6d6f64656c2e626f6f6b2e4c616e67756167652bf35f858d37f1bb0200024c00046e616d657400124c6a6176612f6c616e672f537472696e673b4c000374616771007e00017872001d62792e626e74752e666974722e6d6f64656c2e42617365456e7469747962a30a912658e8030200014c000269647400104c6a6176612f6c616e672f4c6f6e673b78707372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000000000000017070', 123, 'pdfUrl', 'pictureUrl', 123.12, 8, 'size', 1, 'thumbnailUrl', 'title', 1, 123, 1993, 1, 2);
INSERT INTO public.book (book_id, book_isbn, book_age_restriction, book_description, book_language, book_pages, book_pdf_url, book_picture_url, book_price, book_rating, book_size, book_status, book_thumbnail_url, book_title, book_type, book_weight, book_year, importer_id, producer_id) VALUES (6, NULL, '16+', 'description', '\xaced00057372002062792e626e74752e666974722e6d6f64656c2e626f6f6b2e4c616e67756167652bf35f858d37f1bb0200024c00046e616d657400124c6a6176612f6c616e672f537472696e673b4c000374616771007e00017872001d62792e626e74752e666974722e6d6f64656c2e42617365456e7469747962a30a912658e8030200014c000269647400104c6a6176612f6c616e672f4c6f6e673b78707372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000000000000017070', 123, 'pdfUrl', 'pictureUrl', 123.12, 8, 'size', 1, 'thumbnailUrl', 'title', 1, 123, 1993, 1, 2);


--
-- TOC entry 2969 (class 0 OID 16499)
-- Dependencies: 210
-- Data for Name: book_has_author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_has_author (book_id, author_id) VALUES (5, 1);
INSERT INTO public.book_has_author (book_id, author_id) VALUES (6, 1);


--
-- TOC entry 2970 (class 0 OID 16504)
-- Dependencies: 211
-- Data for Name: book_has_translator; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2957 (class 0 OID 16410)
-- Dependencies: 198
-- Data for Name: bookmark; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2958 (class 0 OID 16415)
-- Dependencies: 199
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2959 (class 0 OID 16420)
-- Dependencies: 200
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2960 (class 0 OID 16425)
-- Dependencies: 201
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.language (language_id, language_name, language_tag) VALUES (1, 'English', 'US_en');
INSERT INTO public.language (language_id, language_name, language_tag) VALUES (2, 'Русский', 'RU_ru');


--
-- TOC entry 2961 (class 0 OID 16430)
-- Dependencies: 202
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (1, '2019-04-08 14:28:22.278', NULL, 'qweqwe', 'qweqwe', 'title1', 3, NULL);
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (4, '2019-04-08 14:35:30.437', NULL, 'url', 'url', 'title2', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (5, NULL, '2019-04-08 14:45:58.533', 'url', 'url', 'lolek', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (6, NULL, '2019-04-08 14:46:07.76', 'url', 'url', 'kekkek', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');
INSERT INTO public.news (news_id, news_creation_date, news_modification_date, news_picture_url, news_thumbnail_url, news_title, user_id, news_text) VALUES (7, '2019-04-08 14:47:24.517', '2019-04-08 14:47:35.302', 'url', 'url', 'kekkek1', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam dapibus massa in porttitor finibus. Nunc suscipit auctor diam at venenatis. Ut pretium a felis vitae sagittis. Sed pharetra in leo non laoreet. In ac erat quis mauris pretium ullamcorper at ac nulla. Maecenas consectetur purus eget quam auctor, at fringilla sapien auctor.');


--
-- TOC entry 2962 (class 0 OID 16438)
-- Dependencies: 203
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2963 (class 0 OID 16446)
-- Dependencies: 204
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization (organization_id, organization_title) VALUES (1, 'org1');
INSERT INTO public.organization (organization_id, organization_title) VALUES (2, 'org2');
INSERT INTO public.organization (organization_id, organization_title) VALUES (3, 'org3');
INSERT INTO public.organization (organization_id, organization_title) VALUES (6, 'org6');
INSERT INTO public.organization (organization_id, organization_title) VALUES (4, 'org_4');


--
-- TOC entry 2964 (class 0 OID 16451)
-- Dependencies: 205
-- Data for Name: publishing_house; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (1, 'British publishing house.', NULL, NULL, 'QWERTY');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (3, 'British publishing house.', NULL, NULL, 'QWERTY2');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (5, 'British publishing house.', NULL, NULL, 'QWERTY4');
INSERT INTO public.publishing_house (publishing_house_id, publishing_house_description, publishing_house_logo_url, publishing_house_site_link, publishing_house_title) VALUES (4, 'British publishing house.', NULL, NULL, 'QWERTY_4');


--
-- TOC entry 2965 (class 0 OID 16459)
-- Dependencies: 206
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (role_id, role_authority) VALUES (1, 'USER');
INSERT INTO public.role (role_id, role_authority) VALUES (4, 'LOH');
INSERT INTO public.role (role_id, role_authority) VALUES (5, 'QWERTY');
INSERT INTO public.role (role_id, role_authority) VALUES (6, 'ADMIN');
INSERT INTO public.role (role_id, role_authority) VALUES (7, 'LIBRARIAN');


--
-- TOC entry 2966 (class 0 OID 16464)
-- Dependencies: 207
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2967 (class 0 OID 16469)
-- Dependencies: 208
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (2, true, true, true, true, '$2a$10$fHbrEnODOETdKV9oYQL2He2o73QMAmrUYNj5O0nJIrRHrxZD2wrki', 'user');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (3, true, true, true, true, '$2a$10$lz3xPNK/S7/UVq.dwQWHAeRjH0687c6wOk.9.3/H07diPs9rVwhAK', 'admin');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (5, true, true, true, true, '$2a$10$Y7DImZgBEVeX9bO6aDWLsetVWWjHVQDxaCi/1i4HrLGac2Mvc/z42', 'librarian');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (7, true, true, true, true, '$2a$10$BsYpC1y.t3REIuFndRfIgO0PKsnOEfIU689SgGs7Se/ubUwKQIwZe', 'vasya');
INSERT INTO public."user" (user_id, user_account_non_expired, user_account_non_locked, user_credentials_non_expired, user_enabled, user_password, user_username) VALUES (8, true, true, true, true, '$2a$10$a6jl5AgpfyC636QXAqu64uig5EeJDZwrFqSs6TUuhYyL7rT8zOlma', 'vasya1');


--
-- TOC entry 2968 (class 0 OID 16474)
-- Dependencies: 209
-- Data for Name: user_data; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2971 (class 0 OID 16509)
-- Dependencies: 212
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
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 213
-- Name: author_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_sequence', 5, true);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 214
-- Name: book_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_sequence', 6, true);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 215
-- Name: bookmark_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookmark_sequence', 1, true);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 216
-- Name: city_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_sequence', 1, false);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 217
-- Name: country_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_sequence', 1, false);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 218
-- Name: language_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.language_sequence', 1, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 219
-- Name: news_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_sequence', 7, true);


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 220
-- Name: order_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_sequence', 1, false);


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 221
-- Name: organization_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_sequence', 6, true);


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 222
-- Name: publishing_house_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.publishing_house_sequence', 5, true);


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 223
-- Name: role_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_sequence', 7, true);


--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 224
-- Name: state_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.state_sequence', 1, false);


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 8, true);


--
-- TOC entry 2764 (class 2606 OID 16401)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);


--
-- TOC entry 2812 (class 2606 OID 16503)
-- Name: book_has_author book_has_author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT book_has_author_pkey PRIMARY KEY (book_id, author_id);


--
-- TOC entry 2814 (class 2606 OID 16508)
-- Name: book_has_translator book_has_translator_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT book_has_translator_pkey PRIMARY KEY (book_id, author_id);


--
-- TOC entry 2766 (class 2606 OID 16409)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- TOC entry 2770 (class 2606 OID 16414)
-- Name: bookmark bookmark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT bookmark_pkey PRIMARY KEY (bookmark_id);


--
-- TOC entry 2772 (class 2606 OID 16419)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (city_id);


--
-- TOC entry 2776 (class 2606 OID 16424)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- TOC entry 2780 (class 2606 OID 16429)
-- Name: language language_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT language_pkey PRIMARY KEY (language_id);


--
-- TOC entry 2786 (class 2606 OID 16437)
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (news_id);


--
-- TOC entry 2788 (class 2606 OID 16445)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2790 (class 2606 OID 16450)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (organization_id);


--
-- TOC entry 2794 (class 2606 OID 16458)
-- Name: publishing_house publishing_house_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishing_house
    ADD CONSTRAINT publishing_house_pkey PRIMARY KEY (publishing_house_id);


--
-- TOC entry 2798 (class 2606 OID 16463)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 2802 (class 2606 OID 16468)
-- Name: state state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (state_id);


--
-- TOC entry 2782 (class 2606 OID 16488)
-- Name: language uk_6yesqwmvy381wds1y8fuql8dd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT uk_6yesqwmvy381wds1y8fuql8dd UNIQUE (language_tag);


--
-- TOC entry 2768 (class 2606 OID 16480)
-- Name: book uk_7xa554b1vghpa2wt12xcu96h1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT uk_7xa554b1vghpa2wt12xcu96h1 UNIQUE (book_isbn);


--
-- TOC entry 2796 (class 2606 OID 16492)
-- Name: publishing_house uk_aa3bbx1bs03os7jfnfxc65pq1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishing_house
    ADD CONSTRAINT uk_aa3bbx1bs03os7jfnfxc65pq1 UNIQUE (publishing_house_title);


--
-- TOC entry 2774 (class 2606 OID 16482)
-- Name: city uk_djnk44fptegbsu6drhc9xvlfj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT uk_djnk44fptegbsu6drhc9xvlfj UNIQUE (city_name);


--
-- TOC entry 2806 (class 2606 OID 16498)
-- Name: user uk_jnu1quvkutdk73q9fa4d7abe3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_jnu1quvkutdk73q9fa4d7abe3 UNIQUE (user_username);


--
-- TOC entry 2792 (class 2606 OID 16490)
-- Name: organization uk_kx0xkio22n1a7hwi4uo9a4dwh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT uk_kx0xkio22n1a7hwi4uo9a4dwh UNIQUE (organization_title);


--
-- TOC entry 2784 (class 2606 OID 16486)
-- Name: language uk_mpvpyjgetru6cvudxld43ek8p; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language
    ADD CONSTRAINT uk_mpvpyjgetru6cvudxld43ek8p UNIQUE (language_name);


--
-- TOC entry 2800 (class 2606 OID 16494)
-- Name: role uk_o79nvg1dfvmabgsx1owdoyfkh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_o79nvg1dfvmabgsx1owdoyfkh UNIQUE (role_authority);


--
-- TOC entry 2778 (class 2606 OID 16484)
-- Name: country uk_qrkn270121aljmucrdbnmd35p; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_qrkn270121aljmucrdbnmd35p UNIQUE (country_name);


--
-- TOC entry 2804 (class 2606 OID 16496)
-- Name: state uk_qtjsbpmp2ejq0753ktldenyqo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT uk_qtjsbpmp2ejq0753ktldenyqo UNIQUE (state_name);


--
-- TOC entry 2810 (class 2606 OID 16478)
-- Name: user_data user_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT user_data_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2816 (class 2606 OID 16513)
-- Name: user_has_role user_has_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT user_has_role_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2808 (class 2606 OID 16473)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2820 (class 2606 OID 16555)
-- Name: bookmark fk3ogdxsxa4tx6vndyvpk1fk1am; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT fk3ogdxsxa4tx6vndyvpk1fk1am FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2822 (class 2606 OID 16565)
-- Name: news fk4538gbwfa03nwr9edl3fdloo9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT fk4538gbwfa03nwr9edl3fdloo9 FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2830 (class 2606 OID 16605)
-- Name: book_has_translator fk57270pfwi4ix2hmgpq8ma4g5l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT fk57270pfwi4ix2hmgpq8ma4g5l FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 2823 (class 2606 OID 16570)
-- Name: order fk5wfkmki9lhx405em2mghlpqvc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk5wfkmki9lhx405em2mghlpqvc FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2817 (class 2606 OID 16540)
-- Name: book fk6igju4vpj65jn5oa1jlrwm724; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk6igju4vpj65jn5oa1jlrwm724 FOREIGN KEY (importer_id) REFERENCES public.organization(organization_id);


--
-- TOC entry 2821 (class 2606 OID 16560)
-- Name: city fk6p2u50v8fg2y0js6djc6xanit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(state_id);


--
-- TOC entry 2826 (class 2606 OID 16585)
-- Name: user_data fkak5ac1lrkb8t4ug4rm4gqqvnt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT fkak5ac1lrkb8t4ug4rm4gqqvnt FOREIGN KEY (city_id) REFERENCES public.city(city_id);


--
-- TOC entry 2832 (class 2606 OID 16615)
-- Name: user_has_role fkc1m07gjgx777ukpfw6wa94dfh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT fkc1m07gjgx777ukpfw6wa94dfh FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 2833 (class 2606 OID 16620)
-- Name: user_has_role fkdtkvc2iy3ph1rkvd67yl2t13m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_role
    ADD CONSTRAINT fkdtkvc2iy3ph1rkvd67yl2t13m FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2829 (class 2606 OID 16600)
-- Name: book_has_author fke3bv194wk8auirroafh2b90uu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT fke3bv194wk8auirroafh2b90uu FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2827 (class 2606 OID 16590)
-- Name: user_data fkelmpammgqm7p72tyao7p2vb00; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT fkelmpammgqm7p72tyao7p2vb00 FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- TOC entry 2825 (class 2606 OID 16580)
-- Name: state fkghic7mqjt6qb9vq7up7awu0er; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT fkghic7mqjt6qb9vq7up7awu0er FOREIGN KEY (country_id) REFERENCES public.country(country_id);


--
-- TOC entry 2831 (class 2606 OID 16610)
-- Name: book_has_translator fkl6cov79j6h3xvri0que8d2nv2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_translator
    ADD CONSTRAINT fkl6cov79j6h3xvri0que8d2nv2 FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2828 (class 2606 OID 16595)
-- Name: book_has_author fkog6a3yjob1latspggxqlmc72j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_has_author
    ADD CONSTRAINT fkog6a3yjob1latspggxqlmc72j FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 2818 (class 2606 OID 16545)
-- Name: book fkohik7akb02iea0p0bv12ux4k4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkohik7akb02iea0p0bv12ux4k4 FOREIGN KEY (producer_id) REFERENCES public.organization(organization_id);


--
-- TOC entry 2819 (class 2606 OID 16550)
-- Name: bookmark fkpboovgmm276cj01bo2nfkdidq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmark
    ADD CONSTRAINT fkpboovgmm276cj01bo2nfkdidq FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 2824 (class 2606 OID 16575)
-- Name: order fksunlg4strlu6w0662xu56ph2l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fksunlg4strlu6w0662xu56ph2l FOREIGN KEY (user_id) REFERENCES public.user_data(user_id);


-- Completed on 2019-04-08 17:21:33

--
-- PostgreSQL database dump complete
--

