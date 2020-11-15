CREATE TABLE revinfo (
  rev int(11) NOT NULL AUTO_INCREMENT,
  revtstmp bigint(20) DEFAULT NULL,
  PRIMARY KEY (rev)
);


CREATE TABLE produto_aud (
  id int(11) auto_increment NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  id_produto bigint, 
  nome varchar(255),
  descricao varchar(2000),
  id_categoria int,
  score int,
  data_criacao date,
  PRIMARY KEY (id,rev)
) 