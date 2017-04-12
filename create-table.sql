-- Table: public.circle

-- DROP TABLE public.circle;

CREATE TABLE public.circle
(
    id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT circle_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.circle
    OWNER to postgres;

    INSERT INTO public.circle(
	id, name)
	VALUES (1, "Equilateral");