--
-- PostgreSQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.5 (Ubuntu 12.5-0ubuntu0.20.04.1)

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
-- Name: gender_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.gender_enum AS ENUM (
    'female',
    'male',
    'other'
);


ALTER TYPE public.gender_enum OWNER TO postgres;

--
-- Name: status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);


ALTER TYPE public.status OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

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
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_groupid_seq'::regclass);


--
-- Name: user_categories categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_categories ALTER COLUMN categoryid SET DEFAULT nextval('public.user_categories_categoryid_seq'::regclass);


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, group_name, description, group_creator) FROM stdin;
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
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


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

