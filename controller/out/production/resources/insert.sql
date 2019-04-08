--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-04-08 17:23:09

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


-- Completed on 2019-04-08 17:23:09

--
-- PostgreSQL database dump complete
--

