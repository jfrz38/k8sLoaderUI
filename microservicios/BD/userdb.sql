--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 10.4

-- Started on 2018-07-27 10:46:14

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
-- TOC entry 1 (class 3079 OID 12435)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 25013)
-- Name: cosuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cosuser (
    id bigint NOT NULL,
    username text,
    password text,
    role text,
    profile bigint,
    camid character varying(255)
);


ALTER TABLE public.cosuser OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 25011)
-- Name: cosuser_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cosuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cosuser_id_seq OWNER TO postgres;

--
-- TOC entry 2218 (class 0 OID 0)
-- Dependencies: 183
-- Name: cosuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cosuser_id_seq OWNED BY public.cosuser.id;


--
-- TOC entry 182 (class 1259 OID 25003)
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    camid character varying(255) NOT NULL
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 25001)
-- Name: profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_id_seq OWNER TO postgres;

--
-- TOC entry 2219 (class 0 OID 0)
-- Dependencies: 181
-- Name: profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profile_id_seq OWNED BY public.profile.id;


--
-- TOC entry 186 (class 1259 OID 25030)
-- Name: token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.token (
    id bigint NOT NULL,
    token text NOT NULL,
    creationdate timestamp without time zone,
    expirationdate timestamp without time zone,
    uid bigint
);


ALTER TABLE public.token OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 25028)
-- Name: token_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.token_id_seq OWNER TO postgres;

--
-- TOC entry 2220 (class 0 OID 0)
-- Dependencies: 185
-- Name: token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.token_id_seq OWNED BY public.token.id;


--
-- TOC entry 2074 (class 2604 OID 25016)
-- Name: cosuser id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cosuser ALTER COLUMN id SET DEFAULT nextval('public.cosuser_id_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 25006)
-- Name: profile id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile ALTER COLUMN id SET DEFAULT nextval('public.profile_id_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 25033)
-- Name: token id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token ALTER COLUMN id SET DEFAULT nextval('public.token_id_seq'::regclass);


--
-- TOC entry 2206 (class 0 OID 25013)
-- Dependencies: 184
-- Data for Name: cosuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cosuser (id, username, password, role, profile, camid) FROM stdin;
134	aaa	$2a$10$YbExfyVEJOnfi0MfpwZSc.HP86je9dtYdKietRRn0mET8YM6Yil0K	ROLE_ADMIN	1	1
135	bbb	$2a$10$rqatOn2ucYoN97QIT6DgkOgOPE8VpuYuLLD5UXTtd4Xft9bnwDa9S	ROLE_ADMIN	1	1
136	usuario	$2a$10$VlfmOY4gtjhw4wD2MJn4RuQbhxEjSLhcO0w5b9/ZznEl1pAxvdb6a	ROLE_ADMIN	1	1
137	javi	$2a$10$ukcK.q.oRDsqdvpBdjl0kevZkWP7QipfvJnRQtiDnzC45.Z7es/7e	ROLE_ADMIN	1	CAM-Pjavi
138	usuario07	$2a$10$7CVfkyCh/UAoPubk0Onw4uY6GEbcixKR9HkM9ylqJcMmxZVVJnnuS	ROLE_ADMIN	1	1
139	MANUEL000	$2a$10$bkIlG0IhJOIMxM6dG8xnFuZzaJkQDQfXQCTCL.HhaGHD7waFzlvia	ROLE_ADMIN	1	1
85	pepe	$2a$10$nFkwt6GSI/bRDuYcEcnBF.fUIGyX/M7rZNDSnORgG/3n39wjc0Som	ROLE_ADMIN	1	1
86	juan	$2a$10$xBwAtMywTHiH8lnlxx7eS.EKzP/5givvwl17y5GqeqCdbrbefEHjy	ROLE_ADMIN	1	1
87	maria	$2a$10$memm/Rc0Zp1pslawU15Sket4s8CDI95dn53SlqFtiAphPS.RLjLDu	ROLE_PUBLIC	1	1
89	manuel	$2a$10$dUw6m8HmIvqY018/k5AnO.qr463JXLB2AT7aNYiGhm.0B2JqSb4Zu	ROLE_ADMIN	1	1
90	lucia	$2a$10$65md3tTAtUCGs8ja2d5zgeuioE3pfAicxwDWESXq3.QUpERq1Awsi	ROLE_ADMIN	1	1
91	pedro	$2a$10$CmM6hQp1ctxe5BGRM1HBGOjhZ7w8lKf6butVPmbELEG3BuFgWOb56	ROLE_ADMIN	1	1
\.


--
-- TOC entry 2204 (class 0 OID 25003)
-- Dependencies: 182
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (id, name, camid) FROM stdin;
1	profile01	1
\.


--
-- TOC entry 2208 (class 0 OID 25030)
-- Dependencies: 186
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.token (id, token, creationdate, expirationdate, uid) FROM stdin;
\.


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 183
-- Name: cosuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cosuser_id_seq', 139, true);


--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 181
-- Name: profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_id_seq', 1, true);


--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 185
-- Name: token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.token_id_seq', 1, true);


--
-- TOC entry 2082 (class 2606 OID 25021)
-- Name: cosuser cosuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cosuser
    ADD CONSTRAINT cosuser_pkey PRIMARY KEY (id);


--
-- TOC entry 2079 (class 2606 OID 25008)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2086 (class 2606 OID 25038)
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- TOC entry 2080 (class 1259 OID 25027)
-- Name: cosuser_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX cosuser_id_uindex ON public.cosuser USING btree (id);


--
-- TOC entry 2083 (class 1259 OID 25113)
-- Name: cosuser_username_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX cosuser_username_uindex ON public.cosuser USING btree (username);


--
-- TOC entry 2076 (class 1259 OID 25009)
-- Name: profile_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX profile_id_uindex ON public.profile USING btree (id);


--
-- TOC entry 2077 (class 1259 OID 25010)
-- Name: profile_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX profile_name_uindex ON public.profile USING btree (name);


--
-- TOC entry 2084 (class 1259 OID 25044)
-- Name: token_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX token_id_uindex ON public.token USING btree (id);


--
-- TOC entry 2087 (class 2606 OID 25022)
-- Name: cosuser cosuser_profile_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cosuser
    ADD CONSTRAINT cosuser_profile_id_fk FOREIGN KEY (profile) REFERENCES public.profile(id);


--
-- TOC entry 2088 (class 2606 OID 25039)
-- Name: token token_cosuser_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_cosuser_id_fk FOREIGN KEY (uid) REFERENCES public.cosuser(id);


--
-- TOC entry 2216 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-07-27 10:46:15

--
-- PostgreSQL database dump complete
--

