create table categoria(
	id_categoria bigint auto_increment, 
	nome varchar(255),
	PRIMARY KEY (id_categoria)
);
insert into categoria (nome) values ('business');
insert into categoria (nome) values ('entertainment');
insert into categoria (nome) values ('general');
insert into categoria (nome) values ('health');
insert into categoria (nome) values ('science');
insert into categoria (nome) values ('sports');
insert into categoria (nome) values ('technology');



