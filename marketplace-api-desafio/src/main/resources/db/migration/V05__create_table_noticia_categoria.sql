create table noticia_categoria(
	id_noticia_categoria bigint auto_increment, 
	id_categoria int,
	total_noticia int,
	data_pesquisa timestamp,
	PRIMARY KEY (id_noticia_categoria)
);


 alter table noticia_categoria
    add foreign key (id_categoria) 
    references categoria(id_categoria);
    

insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (1,34,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (2,14,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (3,17,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (4,24,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (5,33,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (6,12,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (7,45,'2020-09-29');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (1,21,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (2,14,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (3,23,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (4,12,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (5,14,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (6,32,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (7,23,'2020-09-30');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (1,27,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (2,12,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (3,18,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (4,61,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (5,24,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (6,45,'2020-10-01');
insert into noticia_categoria (id_categoria,total_noticia,data_pesquisa) values (7,11,'2020-10-01');

