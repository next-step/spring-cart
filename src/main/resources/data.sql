create table product
(
    ID    BIGINT,
    NAME  CHARACTER VARYING(50),
    IMAGE CHARACTER VARYING(300),
    PRICE DOUBLE PRECISION
);

insert into product (ID, NAME, IMAGE, PRICE)
values (1,'치킨','https://i.namu.wiki/i/XKWB62KmN4OJyHfKh-WIcwQvxQttQxCXvT8WdmLxshN-0T-6bjRgvZCiohOjAzFKWt-uF5jwN_GDxHO_4wo6tQjFDeIzamfw8ASkvEJkdxURmMUCZcdTUXd-BLDXg_vG_4Y4CMZmLj-mseoAjr7hIA.webp',10000);

insert into product (ID, NAME, IMAGE, PRICE)
values (2,'샐러드','https://i.namu.wiki/i/ZoB2vB9L_Do-LeBWyob8D0h2ibeQ0xBdqxA8KQjuD6RNtL9cBmavn92Jo5jLRS9uoD_-B5-ELKWE6d2NToOScgt4cKTeNIEXkGhu6dThUUgyoGKD-NJvzSjBYyWV7l7DjX1VwuBohjCffif4vMAWFw.webp',20000);

insert into product (ID, NAME, IMAGE, PRICE)
values (3,'피자','https://i.namu.wiki/i/HiqfW42Vm1Fblprs59MT3pFS0CSGlwBel5cem5ILozlV6Q9dCyATb9KzoBMiRd43S9eyJ4bfi8nPvCCfrSjtqNhoa7W_cHbM8YbuuNVAlsRfVRvmxqgEt6xMuvjmHAUjCvbeLOk7Ka6Vff3f8oHB8w.webp',13000);
