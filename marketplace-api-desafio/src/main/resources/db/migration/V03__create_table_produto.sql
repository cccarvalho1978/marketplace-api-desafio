create table produto(
	id_produto bigint auto_increment, 
	nome varchar(255),
	descricao varchar(2000),
	id_categoria int,
	score int,
	data_criacao date,
	PRIMARY KEY (id_produto)
);


 alter table produto
    add foreign key (id_categoria) 
    references categoria(id_categoria);
    

insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 1','Produto 1 teste',1,'2020-10-01');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 2','Produto 2 teste',1,'2020-10-02');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 3','Produto 3 teste',1,'2020-10-01');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 4','Produto 4 teste',1,'2020-10-11');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 5','Produto 5 teste',1,'2020-10-10');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 6','Produto 6 teste',2,'2020-10-01');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 7','Produto 7 teste',2,'2020-10-05');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 8','Produto 8 teste',2,'2020-10-07');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 9','Produto 9 teste',2,'2020-10-08');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 10','Produto 10 teste',2,'2020-10-09');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 11','Produto 11 teste',3,'2020-10-02');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 12','Produto 12 teste',3,'2020-10-05');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 13','Produto 13 teste',4,'2020-10-01');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 14','Produto 14 teste',4,'2020-10-02');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 15','Produto 15 teste',4,'2020-10-07');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 16','Produto 16 teste',5,'2020-10-09');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 17','Produto 17 teste',5,'2020-10-13');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 18','Produto 18 teste',6,'2020-10-14');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 19','Produto 19 teste',6,'2020-10-10');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 20','Produto 20 teste',7,'2020-10-11');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 21','Produto 21 teste',7,'2020-10-13');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 22','Produto 22 teste',7,'2020-10-14');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 23','Produto 23 teste',7,'2020-10-14');
insert into produto (nome,descricao,id_categoria,data_criacao) values ('Produto 24','Produto 24 teste',7,'2020-10-14');



