--C
INSERT INTO public.categoria
(id_categoria, nombre)
VALUES(nextval('categoria_id_categoria_seq'::regclass), 'Artefactos');
--R
SELECT id_categoria, nombre
FROM public.categoria;
--U
UPDATE public.categoria
SET nombre='Comidas'
WHERE id_categoria=1;
--D
DELETE FROM public.categoria
WHERE id_categoria=1;