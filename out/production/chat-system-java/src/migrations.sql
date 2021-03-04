--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
    'male',
    'female',
    'other'
);


ALTER TYPE public.gender_enum OWNER TO postgres;

--
-- Name: invite_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.invite_status AS ENUM (
    'PENDING',
    'ACCEPTED',
    'DENIED'
);


ALTER TYPE public.invite_status OWNER TO postgres;

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

SET default_table_access_method = heap;

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
    sender_id integer NOT NULL,
    created_at date DEFAULT now() NOT NULL
);


ALTER TABLE public.files OWNER TO postgres;

--
-- Name: files_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.files_id_seq
    AS integer
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
    group_name character varying(50) NOT NULL,
    description character varying(255) NOT NULL,
    group_creator integer NOT NULL,
    created_at date DEFAULT now() NOT NULL,
    updated_at date DEFAULT now() NOT NULL
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: groups_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groups_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_group_id_seq OWNER TO postgres;

--
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;


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
    sent_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    message_status public.message_status DEFAULT 'UNSEEN'::public.message_status
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_id_seq OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissions (
    permission_id integer NOT NULL,
    name character varying(24) NOT NULL,
    status public.status NOT NULL,
    created_at date DEFAULT now(),
    updated_at date DEFAULT now()
);


ALTER TABLE public.permissions OWNER TO postgres;

--
-- Name: permissions_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permissions_permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissions_permission_id_seq OWNER TO postgres;

--
-- Name: permissions_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permissions_permission_id_seq OWNED BY public.permissions.permission_id;


--
-- Name: sent_invitations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sent_invitations (
    sent_id integer NOT NULL,
    admin_id integer NOT NULL,
    sent_to character varying(255) NOT NULL,
    verificationcode integer NOT NULL,
    status public.invite_status DEFAULT 'PENDING'::public.invite_status NOT NULL
);


ALTER TABLE public.sent_invitations OWNER TO postgres;

--
-- Name: sent_invitations_sent_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sent_invitations_sent_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sent_invitations_sent_id_seq OWNER TO postgres;

--
-- Name: sent_invitations_sent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sent_invitations_sent_id_seq OWNED BY public.sent_invitations.sent_id;


--
-- Name: user_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_categories (
    categoryid integer NOT NULL,
    name character varying(255) NOT NULL,
    created_at date DEFAULT now(),
    updated_at date DEFAULT now()
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
    category_id integer NOT NULL,
    permission_id integer NOT NULL
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
    created_at date DEFAULT now() NOT NULL,
    updated_at date DEFAULT now() NOT NULL,
    status public.status DEFAULT 'ACTIVE'::public.status,
    categoryid integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: files id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files ALTER COLUMN id SET DEFAULT nextval('public.files_id_seq'::regclass);


--
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);


--
-- Name: permissions permission_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissions ALTER COLUMN permission_id SET DEFAULT nextval('public.permissions_permission_id_seq'::regclass);


--
-- Name: sent_invitations sent_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sent_invitations ALTER COLUMN sent_id SET DEFAULT nextval('public.sent_invitations_sent_id_seq'::regclass);


--
-- Name: user_categories categoryid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_categories ALTER COLUMN categoryid SET DEFAULT nextval('public.user_categories_categoryid_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: files; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.files (id, url, file_name, file_type, file_size, file_size_type, sender_id, created_at) FROM stdin;
2	https://drive.google.com/drive/#folders/	testing_file	PDF	1.06	MB	2	2021-02-05
3	C:\\Users\\DELL\\OneDrive\\Documents\\2021\\Java\\Chat_System_Class_C\\chat-system-java/public/assets/question1-2e0538e436cb41d19dea063f319fdfaf.asc	question1	null	0	TB	2	2021-03-01
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, group_name, description, group_creator, created_at, updated_at) FROM stdin;
1	General	The General Chat Room for communication to all members of the application	1	2021-02-05	2021-02-05
2	February	Group made in feb 2021	2	2021-02-24	2021-02-24
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, content, sender, user_receiver, group_receiver, original_message, sent_at, message_status) FROM stdin;
3	Hello	1	\N	1	\N	2021-02-05 13:10:42.605528	UNSEEN
5	Hey	2	\N	1	3	2021-02-05 13:11:58.257878	SEEN
8	Welcome to chat	1	\N	1	7	2021-02-09 21:43:09.392622	UNSEEN
12	hello in group	2	\N	1	\N	2021-02-24 13:50:05.954462	UNSEEN
21	hhhhhhhhhh	2	\N	\N	15	2021-03-01 20:13:31.633627	UNSEEN
22	hhhhhhhhhhhhhh	2	\N	\N	15	2021-03-01 20:16:39.432167	UNSEEN
28	hello chanelle	2	4	\N	\N	2021-03-02 06:58:29.34719	UNSEEN
6	Dear Student	2	1	\N	\N	2021-02-05 13:13:02.403584	SEEN
11	you mean me?	1	1	\N	\N	\N	SEEN
13	hello there	2	1	\N	\N	2021-02-24 14:10:50.572851	SEEN
15	d	2	1	\N	\N	2021-03-01 08:25:27.364826	SEEN
17	yes g how are you??	2	1	\N	\N	2021-03-01 09:25:43.193639	SEEN
19	hello there	2	1	\N	\N	2021-03-01 18:13:09.984602	SEEN
20	hellosdfsdaf	2	1	\N	\N	2021-03-01 18:25:00.364982	SEEN
26	reka!	2	1	\N	6	2021-03-01 21:17:41.583275	SEEN
29	hello there	2	1	\N	27	2021-03-02 10:39:22.509086	SEEN
27	Reka nze mpindure message content	2	1	\N	\N	2021-03-01 23:28:08.61478	SEEN
31	Yes chanelle bimeze bite s mn	3	4	\N	\N	2021-03-02 11:48:56.792177	UNSEEN
18	hi there	2	3	\N	\N	2021-03-01 17:49:35.695474	SEEN
32	hhhhh ndasetse	2	1	\N	\N	2021-03-02 11:57:13.328055	UNSEEN
35	yo mn	2	1	\N	\N	2021-03-03 08:35:13.766613	UNSEEN
7	Dear tester	1	2	\N	6	2021-02-05 13:14:19.194834	SEEN
9	hello	2	2	\N	\N	\N	SEEN
10	I was just testing	2	2	\N	\N	\N	SEEN
16	hello there	2	2	\N	\N	2021-03-01 09:24:59.100426	SEEN
24	babyy	2	2	\N	6	2021-03-01 21:14:04.675176	SEEN
25	ffffffff	2	2	\N	6	2021-03-01 21:15:49.228922	SEEN
33	helllo there is it tru?	2	1	\N	27	2021-03-03 07:26:26.166633	UNSEEN
34	yes chanelle	2	1	\N	\N	2021-03-03 07:27:18.367885	UNSEEN
\.


--
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissions (permission_id, name, status, created_at, updated_at) FROM stdin;
1	DELETE_USER	ACTIVE	2021-02-05	2021-02-05
2	CREATE_GROUP	ACTIVE	2021-02-05	2021-02-05
4	INVITE_USER	ACTIVE	2021-02-09	2021-02-09
5	DELETE_GROUP	ACTIVE	2021-02-09	2021-02-09
6	DEACTIVATE_USER	ACTIVE	2021-02-09	2021-02-09
7	VIEW_STATISTICS	ACTIVE	2021-02-09	2021-02-09
8	REMOVE_FROM_GROUP	ACTIVE	2021-02-09	2021-02-09
9	ADD_TO_GROUP	ACTIVE	2021-02-09	2021-02-09
10	SEND_MESSAGE	ACTIVE	2021-02-09	2021-02-09
\.


--
-- Data for Name: sent_invitations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sent_invitations (sent_id, admin_id, sent_to, verificationcode, status) FROM stdin;
1	1	classc@gmail.com	12345	PENDING
2	1	oclassc@gmail.com	9645	PENDING
3	1	hello@gmail.com	23560	PENDING
4	1	hello@gmail.com	95656	PENDING
5	1	didiermunezer38@gmail.com	86583	PENDING
6	1	hirwa.chanelle@gmail.com	50508	PENDING
7	1	hirwa.chanelle@gmail.com	16266	PENDING
\.


--
-- Data for Name: user_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_categories (categoryid, name, created_at, updated_at) FROM stdin;
1	ADMIN	2021-02-05	2021-02-05
2	USER	2021-02-05	2021-02-05
3	GROUP_CREATOR	2021-02-09	2021-02-09
\.


--
-- Data for Name: user_category_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_category_permissions (category_id, permission_id) FROM stdin;
1	1
2	2
1	4
3	5
1	6
1	7
3	8
3	9
2	10
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group (group_id, user_id) FROM stdin;
1	1
1	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, first_name, last_name, username, email, gender, pass_word, dob, created_at, updated_at, status, categoryid) FROM stdin;
2	Test	Tester	test	test@gmail.com	female	test	2020-12-12	2021-02-05	2021-02-05	ACTIVE	2
1	Student	classC	classC	c@gmail.com	male	2021	2021-01-01	2021-02-05	2021-02-05	ACTIVE	1
4	Chanelle	Hirwa	chanelle	hirwa.chanelle@gmail.com	male	chanizo	2004-06-27	2021-03-01	2021-03-01	ACTIVE	1
3	Bella	Mellisa	melissa	mellisaIrwanda@gmail.com	female	melissa	2004-02-01	2021-03-01	2021-03-01	INACTIVE	1
\.


--
-- Name: files_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.files_id_seq', 3, true);


--
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 2, true);


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_id_seq', 35, true);


--
-- Name: permissions_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permissions_permission_id_seq', 10, true);


--
-- Name: sent_invitations_sent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sent_invitations_sent_id_seq', 7, true);


--
-- Name: user_categories_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_categories_categoryid_seq', 3, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 4, true);


--
-- Name: files files_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);


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
-- Name: sent_invitations sent_invitations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sent_invitations
    ADD CONSTRAINT sent_invitations_pkey PRIMARY KEY (sent_id);


--
-- Name: user_categories user_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_pkey PRIMARY KEY (categoryid);


--
-- Name: user_category_permissions user_category_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_category_permissions_pkey PRIMARY KEY (category_id, permission_id);


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (group_id, user_id);


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
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: files files_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id);


--
-- Name: groups groups_group_creator_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_group_creator_fkey FOREIGN KEY (group_creator) REFERENCES public.users(user_id);


--
-- Name: messages messages_group_receiver_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_group_receiver_fkey FOREIGN KEY (group_receiver) REFERENCES public.groups(group_id);


--
-- Name: messages messages_original_message_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_original_message_fkey FOREIGN KEY (original_message) REFERENCES public.messages(id);


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
-- Name: sent_invitations sent_invitations_admin_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sent_invitations
    ADD CONSTRAINT sent_invitations_admin_id_fkey FOREIGN KEY (admin_id) REFERENCES public.users(user_id);


--
-- Name: user_category_permissions user_category_permissions_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_category_permissions_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.user_categories(categoryid);


--
-- Name: user_category_permissions user_category_permissions_permission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_category_permissions
    ADD CONSTRAINT user_category_permissions_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id);


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
-- Name: users users_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.user_categories(categoryid);


--
-- PostgreSQL database dump complete
--

