--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

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

ALTER TABLE ONLY public.encargar DROP CONSTRAINT pedidos_usuarios_email_fk;
ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pk;
ALTER TABLE ONLY public.productos DROP CONSTRAINT productos_pk;
ALTER TABLE ONLY public.encargar DROP CONSTRAINT pedidos_pk;
ALTER TABLE public.productos ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.encargar ALTER COLUMN id DROP DEFAULT;
DROP TABLE public.usuarios;
DROP SEQUENCE public.productos_id_seq;
DROP TABLE public.productos;
DROP SEQUENCE public.pedidos_id_seq;
DROP TABLE public.encargar;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: encargar; Type: TABLE; Schema: public; Owner: luismi
--

CREATE TABLE public.encargar (
    precio numeric(15,2) NOT NULL,
    usuario character varying(50) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.encargar OWNER TO luismi;

--
-- Name: pedidos_id_seq; Type: SEQUENCE; Schema: public; Owner: luismi
--

CREATE SEQUENCE public.pedidos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedidos_id_seq OWNER TO luismi;

--
-- Name: pedidos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: luismi
--

ALTER SEQUENCE public.pedidos_id_seq OWNED BY public.encargar.id;


--
-- Name: productos; Type: TABLE; Schema: public; Owner: luismi
--

CREATE TABLE public.productos (
    nombre character varying NOT NULL,
    precio double precision NOT NULL,
    autor character varying NOT NULL,
    pais character varying(20) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.productos OWNER TO luismi;

--
-- Name: productos_id_seq; Type: SEQUENCE; Schema: public; Owner: luismi
--

CREATE SEQUENCE public.productos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_id_seq OWNER TO luismi;

--
-- Name: productos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: luismi
--

ALTER SEQUENCE public.productos_id_seq OWNED BY public.productos.id;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: luismi
--

CREATE TABLE public.usuarios (
    email character varying(50) NOT NULL,
    pwhash character(60) NOT NULL,
    tipo_tarjeta character varying(10) NOT NULL,
    num_tarjeta character(16) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO luismi;

--
-- Name: encargar id; Type: DEFAULT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.encargar ALTER COLUMN id SET DEFAULT nextval('public.pedidos_id_seq'::regclass);


--
-- Name: productos id; Type: DEFAULT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.productos ALTER COLUMN id SET DEFAULT nextval('public.productos_id_seq'::regclass);


--
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: luismi
--

COPY public.productos (nombre, precio, autor, pais, id) FROM stdin;
Drums of Passion	16.95	Babatunde Olatunji	Nigeria	4
Kaira	16.95	Tounami Diabate	Mali	5
Rapture	12.95	Nusrat Fateh Ali Khan	Pakistan	10
Cesaria Evora	16.95	Cesaria Evora	Cape Verde	11
Djelika	14.95	Tounami Diabate	Mali	9
Record of Changes	12.95	Samulnori	Korea	8
Dance the Devil Away	14.95	Outback	Australia	7
The Lion is Loose	13.95	Eliades Ochoa	Cuba	6
DAA	50	GSTIC	Spain	12
Yuan	14.95	The Guo Brothers	China	3
\.


--
-- Name: pedidos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: luismi
--

SELECT pg_catalog.setval('public.pedidos_id_seq', 23, true);


--
-- Name: productos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: luismi
--

SELECT pg_catalog.setval('public.productos_id_seq', 12, true);


--
-- Name: encargar pedidos_pk; Type: CONSTRAINT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.encargar
    ADD CONSTRAINT pedidos_pk PRIMARY KEY (id);


--
-- Name: productos productos_pk; Type: CONSTRAINT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pk PRIMARY KEY (id);


--
-- Name: usuarios usuarios_pk; Type: CONSTRAINT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pk PRIMARY KEY (email);


--
-- Name: encargar pedidos_usuarios_email_fk; Type: FK CONSTRAINT; Schema: public; Owner: luismi
--

ALTER TABLE ONLY public.encargar
    ADD CONSTRAINT pedidos_usuarios_email_fk FOREIGN KEY (usuario) REFERENCES public.usuarios(email) ON UPDATE CASCADE;


--
-- PostgreSQL database dump complete
--

