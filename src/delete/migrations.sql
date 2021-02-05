--
-- PostgreSQL database dump
--

-- Dumped from database version 10.15
-- Dumped by pg_dump version 10.15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE chat_system; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE chat_system IS 'RCA Chat System in JAVA';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: gender_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.gender_enum AS ENUM (
    'female',
    'male',
    'other'
);


ALTER TYPE public.gender_enum OWNER TO postgres;

--
-- Name: message_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.message_status AS ENUM (
    'SEEN',
    'UNSEEN',
    'DELETED',
    'EDITED',
    'UNSEEN_EDITED',
    'SEEN_EDITED',
    'UNSEEN_DELETED',
    'SEEN_DELETED'
);


ALTER TYPE public.message_status OWNER TO postgres;

--
-- Name: status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);


ALTER TYPE public.status OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: db-config; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."db-config" (
    c1 text
);


ALTER TABLE public."db-config" OWNER TO postgres;

--
-- Name: files; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.files (
    id integer NOT NULL,
    url character varying(255) NOT NULL,
    file_name character varying(50) NOT NULL,
    file_type character varying(50) NOT NULL,
    file_size character varying(50) NOT NULL,
    file_size_type character varying(50) NOT NULL,
    sender_id integer,
    created_at date DEFAULT now() NOT NULL
);


ALTER TABLE public.files OWNER TO postgres;

--
-- Name: files_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.files_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.files_id_seq OWNER TO postgres;

--
-- Name: files_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.files_id_seq OWNED BY public.files.id;


--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id integer NOT NULL,
    group_name character varying(40) NOT NULL,
    description character varying(40) NOT NULL,
    group_creator integer
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: groups_groupid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groups_groupid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_groupid_seq OWNER TO postgres;

--
-- Name: groups_groupid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_groupid_seq OWNED BY public.groups.group_id;


--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    content text NOT NULL,
    sender integer NOT NULL,
    user_receiver integer,
    group_receiver integer,
    original_message integer,
    sent_at date DEFAULT now() NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissions (
    permission_id integer NOT NULL,
    name character varying(24) NOT NULL,
    status character varying(20) NOT NULL,
    created_at date,
    updated_at date
);


ALTER TABLE public.permissions OWNER TO postgres;

--
-- Name: permissions_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.permissions ALTER COLUMN permission_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.permissions_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_categories (
    categoryid integer NOT NULL,
    name character varying(255) NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL
);


ALTER TABLE public.user_categories OWNER TO postgres;

--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_categories_categoryid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_categories_categoryid_seq OWNER TO postgres;

--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_categories_categoryid_seq OWNED BY public.user_categories.categoryid;


--
-- Name: user_category_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_category_permissions (
    user_permission_id integer NOT NULL,
    category_id integer,
    permission_id integer,
    created_at date,
    updated_at date
);


ALTER TABLE public.user_category_permissions OWNER TO postgres;

--
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group (
    group_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.user_group OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    username character varying(40) NOT NULL,
    email character varying(40) NOT NULL,
    gender public.gender_enum NOT NULL,
    pass_word character varying(40) NOT NULL,
    dob date,
    created_at date,
    updated_at date,
    status public.status
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: files id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files ALTER COLUMN id SET DEFAULT nextval('public.files_id_seq'::regclass);


--
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_groupid_seq'::regclass);


--
-- Name: user_categories categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_categories ALTER COLUMN categoryid SET DEFAULT nextval('public.user_categories_categoryid_seq'::regclass);


--
-- Data for Name: db-config; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."db-config" (c1) FROM stdin;
--
-- PostgreSQL database dump
--
\N
-- Dumped from database version 12.5
-- Dumped by pg_dump version 12.5
\N
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
\N
--
-- Name: gender_enum; Type: TYPE; Schema: public; Owner: postgres
--
\N
CREATE TYPE public.gender_enum AS ENUM (
    'female',
    'male',
    'other'
);
\N
\N
ALTER TYPE public.gender_enum OWNER TO postgres;
\N
--
-- Name: message_status; Type: TYPE; Schema: public; Owner: postgres
--
\N
CREATE TYPE public.message_status AS ENUM (
    'SEEN',
    'UNSEEN',
    'DELETED',
    'EDITED',
    'UNSEEN_EDITED',
    'SEEN_EDITED',
    'UNSEEN_DELETED',
    'SEEN_DELETED'
);
\N
\N
ALTER TYPE public.message_status OWNER TO postgres;
\N
--
-- Name: status; Type: TYPE; Schema: public; Owner: postgres
--
\N
CREATE TYPE public.status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);
\N
\N
ALTER TYPE public.status OWNER TO postgres;
\N
SET default_tablespace = '';
\N
SET default_table_access_method = heap;
\N
--
-- Name: files; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.files (
    id integer NOT NULL,
    url character varying(255) NOT NULL,
    file_name character varying(50) NOT NULL,
    file_type character varying(50) NOT NULL,
    file_size character varying(50) NOT NULL,
    file_size_type character varying(50) NOT NULL,
    sender_id integer,
    created_at date DEFAULT now() NOT NULL
);
\N
\N
ALTER TABLE public.files OWNER TO postgres;
\N
--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.groups (
    group_id integer NOT NULL,
    group_name character varying(40) NOT NULL,
    description character varying(40) NOT NULL,
    group_creator integer
);
\N
\N
ALTER TABLE public.groups OWNER TO postgres;
\N
--
-- Name: groups_groupid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
\N
CREATE SEQUENCE public.groups_groupid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
\N
\N
ALTER TABLE public.groups_groupid_seq OWNER TO postgres;
\N
--
-- Name: groups_groupid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--
\N
ALTER SEQUENCE public.groups_groupid_seq OWNED BY public.groups.group_id;
\N
\N
--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.messages (
    id integer NOT NULL,
    content text NOT NULL,
    sender integer NOT NULL,
    user_receiver integer,
    group_receiver integer,
    original_message integer,
    sent_at date DEFAULT now() NOT NULL
);
\N
\N
ALTER TABLE public.messages OWNER TO postgres;
\N
--
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.permissions (
    permission_id integer NOT NULL,
    name character varying(24) NOT NULL,
    status character varying(20) NOT NULL,
    created_at date,
    updated_at date
);
\N
\N
ALTER TABLE public.permissions OWNER TO postgres;
\N
--
-- Name: permissions_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
\N
ALTER TABLE public.permissions ALTER COLUMN permission_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.permissions_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
\N
\N
--
-- Name: user_categories; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.user_categories (
    categoryid integer NOT NULL,
    name character varying(255) NOT NULL,
    created_at date NOT NULL,
    updated_at date NOT NULL
);
\N
\N
ALTER TABLE public.user_categories OWNER TO postgres;
\N
--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
\N
CREATE SEQUENCE public.user_categories_categoryid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
\N
\N
ALTER TABLE public.user_categories_categoryid_seq OWNER TO postgres;
\N
--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--
\N
ALTER SEQUENCE public.user_categories_categoryid_seq OWNED BY public.user_categories.categoryid;
\N
\N
--
-- Name: user_category_permissions; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.user_category_permissions (
    user_permission_id integer NOT NULL,
    category_id integer,
    permission_id integer,
    created_at date,
    updated_at date
);
\N
\N
ALTER TABLE public.user_category_permissions OWNER TO postgres;
\N
--
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.user_group (
    group_id integer NOT NULL,
    user_id integer NOT NULL
);
\N
\N
ALTER TABLE public.user_group OWNER TO postgres;
\N
--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--
\N
CREATE TABLE public.users (
    user_id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    username character varying(40) NOT NULL,
    email character varying(40) NOT NULL,
    gender public.gender_enum NOT NULL,
    pass_word character varying(40) NOT NULL,
    dob date,
    created_at date,
    updated_at date,
    status public.status
);
\N
\N
ALTER TABLE public.users OWNER TO postgres;
\N
--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
\N
ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
\N
\N
--
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_groupid_seq'::regclass);
\N
\N
--
-- Name: user_categories categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_categories ALTER COLUMN categoryid SET DEFAULT nextval('public.user_categories_categoryid_seq'::regclass);
\N
\N
--
-- Data for Name: files; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.files (id, url, file_name, file_type, file_size, file_size_type, sender_id, created_at) FROM stdin;
\\.
\N
\N
--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.groups (group_id, group_name, description, group_creator) FROM stdin;
\\.
\N
\N
--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.messages (id, content, sender, user_receiver, group_receiver, original_message, sent_at) FROM stdin;
\\.
\N
\N
--
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.permissions (permission_id, name, status, created_at, updated_at) FROM stdin;
\\.
\N
\N
--
-- Data for Name: user_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.user_categories (categoryid, name, created_at, updated_at) FROM stdin;
\\.
\N
\N
--
-- Data for Name: user_category_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.user_category_permissions (user_permission_id, category_id, permission_id, created_at, updated_at) FROM stdin;
\\.
\N
\N
--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.user_group (group_id, user_id) FROM stdin;
\\.
\N
\N
--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--
\N
COPY public.users (user_id, first_name, last_name, username, email, gender, pass_word, dob, created_at, updated_at, status) FROM stdin;
\\.
\N
\N
--
-- Name: groups_groupid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--
\N
SELECT pg_catalog.setval('public.groups_groupid_seq', 1, false);
\N
\N
--
-- Name: permissions_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--
\N
SELECT pg_catalog.setval('public.permissions_permission_id_seq', 2, true);
\N
\N
--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--
\N
SELECT pg_catalog.setval('public.user_categories_categoryid_seq', 1, false);
\N
\N
--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--
\N
SELECT pg_catalog.setval('public.users_user_id_seq', 3, true);
\N
\N
--
-- Name: files files_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);
\N
\N
--
-- Name: files files_url_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_url_key UNIQUE (url);
\N
\N
--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);
\N
\N
--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);
\N
\N
--
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (permission_id);
\N
\N
--
-- Name: user_categories user_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_pkey PRIMARY KEY (categoryid);
\N
\N
--
-- Name: user_category_permissions user_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_pkey PRIMARY KEY (user_permission_id);
\N
\N
--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
\N
\N
--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
\N
\N
--
-- Name: files files_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id);
\N
\N
--
-- Name: messages messages_group_receiver_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_group_receiver_fkey FOREIGN KEY (group_receiver) REFERENCES public.users(user_id);
\N
\N
--
-- Name: messages messages_original_message_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_original_message_fkey FOREIGN KEY (original_message) REFERENCES public.users(user_id);
\N
\N
--
-- Name: messages messages_sender_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_sender_fkey FOREIGN KEY (sender) REFERENCES public.users(user_id);
\N
\N
--
-- Name: messages messages_user_receiver_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_user_receiver_fkey FOREIGN KEY (user_receiver) REFERENCES public.users(user_id);
\N
\N
--
-- Name: user_category_permissions user_category_permissions_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_category_permissions_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.user_categories(categoryid);
\N
\N
--
-- Name: user_group user_group_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
\N
\N
--
-- Name: user_group user_group_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);
\N
\N
--
-- Name: user_category_permissions user_permissions_permission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id);
\N
\N
--
-- Name: user_category_permissions user_permissions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
\N
ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_user_id_fkey FOREIGN KEY (category_id) REFERENCES public.users(user_id);
\N
\N
--
-- PostgreSQL database dump complete
--
\N
\.


--
-- Data for Name: files; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.files (id, url, file_name, file_type, file_size, file_size_type, sender_id, created_at) FROM stdin;
1	sdfa	java	PDF	3	MB	1	2021-02-02
3	SDFAsdfa	DSF	DSFA	3	DA	2	2021-02-02
4	url	dsfa	dsfa	3	dsfa	2	2021-02-02
5	null	sdfa	sdfa	2	3	2	2021-02-02
9	C:\\Users\\DELL\\OneDrive\\Studies\\OOP\\Projects\\Class\\chat-system-java/public/assets/74b209dbbbe845479c43e4f519d728ad	sdf	dsf	3	dfa	2	2021-02-02
10	C:\\Users\\DELL\\OneDrive\\Studies\\OOP\\Projects\\Class\\chat-system-java/public/assets/a573516e36064dbdad4b88a40c3336e8pdf	TIMETABLE	ODF	3	DF	2	2021-02-02
11	C:\\Users\\DELL\\OneDrive\\Studies\\OOP\\Projects\\Class\\chat-system-java/public/assets/972d2f99c2b347d59bdea3ac32e515a8pdf	TIMETABLE	DFSA	3	DSFA	2	2021-02-02
13	C:\\Users\\DELL\\OneDrive\\Studies\\OOP\\Projects\\Class\\chat-system-java/public/assets/Time Table-52fe901ce56f4c829ebe894d20f4af21.pdf	Time Table	application/pdf	277	KB	1	2021-02-03
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, group_name, description, group_creator) FROM stdin;
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, content, sender, user_receiver, group_receiver, original_message, sent_at) FROM stdin;
\.


--
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissions (permission_id, name, status, created_at, updated_at) FROM stdin;
1	CAN_SEND_INVITATION	ACTIVE	\N	\N
2	ACTIVATE_USER	ACTIVE\n	\N	\N
\.


--
-- Data for Name: user_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_categories (categoryid, name, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: user_category_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_category_permissions (user_permission_id, category_id, permission_id, created_at, updated_at) FROM stdin;
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group (group_id, user_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, first_name, last_name, username, email, gender, pass_word, dob, created_at, updated_at, status) FROM stdin;
1	uwera	Gahamanyi	Guwera	u@gmail.com	female	hello	2004-12-02	2020-12-12	2019-12-12	\N
2	Uwizeye	bella	Ubella\n	Bella@gmail.com	female	thisIsMe	2008-12-12	2020-12-12	\N	\N
3	Kalisa	Mahirwe	Kmahirwe	Mak@gmail.com	female	thisIsYou	2009-12-12	2020-12-12	\N	\N
\.


--
-- Name: files_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.files_id_seq', 13, true);


--
-- Name: groups_groupid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_groupid_seq', 1, false);


--
-- Name: permissions_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permissions_permission_id_seq', 2, true);


--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_categories_categoryid_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 3, true);


--
-- Name: files files_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);


--
-- Name: files files_url_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_url_key UNIQUE (url);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (permission_id);


--
-- Name: user_categories user_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_pkey PRIMARY KEY (categoryid);


--
-- Name: user_category_permissions user_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_pkey PRIMARY KEY (user_permission_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: files files_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id);


--
-- Name: messages messages_group_receiver_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_group_receiver_fkey FOREIGN KEY (group_receiver) REFERENCES public.users(user_id);


--
-- Name: messages messages_original_message_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_original_message_fkey FOREIGN KEY (original_message) REFERENCES public.users(user_id);


--
-- Name: messages messages_sender_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_sender_fkey FOREIGN KEY (sender) REFERENCES public.users(user_id);


--
-- Name: messages messages_user_receiver_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_user_receiver_fkey FOREIGN KEY (user_receiver) REFERENCES public.users(user_id);


--
-- Name: user_category_permissions user_category_permissions_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_category_permissions_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.user_categories(categoryid);


--
-- Name: user_group user_group_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- Name: user_group user_group_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: user_category_permissions user_permissions_permission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id);


--
-- Name: user_category_permissions user_permissions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_permissions_user_id_fkey FOREIGN KEY (category_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

