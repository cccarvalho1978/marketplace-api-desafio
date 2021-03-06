create table venda(
	id_venda bigint auto_increment, 
	id_vendedor bigint,
	id_comprador bigint,
	data_venda date,
	PRIMARY KEY (id_venda)
);

 alter table venda
    add foreign key (id_vendedor) 
    references vendedor(id_vendedor);
    
 alter table venda
    add foreign key (id_comprador) 
    references comprador(id_comprador);    


create table item_venda(
	id_item_venda bigint auto_increment, 
	id_venda bigint,
	id_produto bigint,
	quantidade int,
	valor decimal(20,2),
	PRIMARY KEY (id_item_venda)
);

 alter table item_venda
    add foreign key (id_venda) 
    references venda(id_venda);
    
 alter table item_venda
    add foreign key (id_produto) 
    references produto(id_produto);  

create table avaliacao_item_venda(
	id_avaliacao_item_venda bigint auto_increment, 
	id_item_venda bigint,
	avaliacao int,
	PRIMARY KEY (id_avaliacao_item_venda)
);

 alter table avaliacao_item_venda
    add foreign key (id_item_venda) 
    references item_venda(id_item_venda);  




insert into venda (id_vendedor,id_comprador,data_venda) values (1,1,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),1,2,20.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),3);

insert into venda (id_vendedor,id_comprador,data_venda) values (1,2,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),1,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),5);

insert into venda (id_vendedor,id_comprador,data_venda) values (2,3,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),3,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),2);

insert into venda (id_vendedor,id_comprador,data_venda) values (2,4,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),4,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),5);

insert into venda (id_vendedor,id_comprador,data_venda) values (3,5,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),5,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),1);

insert into venda (id_vendedor,id_comprador,data_venda) values (5,6,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),6,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),4);

insert into venda (id_vendedor,id_comprador,data_venda) values (4,6,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),6,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),3);

insert into venda (id_vendedor,id_comprador,data_venda) values (1,2,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),10,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),4);

insert into venda (id_vendedor,id_comprador,data_venda) values (1,2,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),11,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),3);

insert into venda (id_vendedor,id_comprador,data_venda) values (4,3,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),13,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),4);

insert into venda (id_vendedor,id_comprador,data_venda) values (2,3,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),15,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),5);

insert into venda (id_vendedor,id_comprador,data_venda) values (5,6,CURRENT_DATE);
insert into item_venda (id_venda,id_produto,quantidade,valor) values (IDENTITY(),18,1,10.00);
insert into avaliacao_item_venda (id_item_venda,avaliacao) values (IDENTITY(),5);

