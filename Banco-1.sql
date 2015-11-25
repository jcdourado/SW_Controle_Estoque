CREATE DATABASE estoque
go
USE estoque

CREATE TABLE tipo(
codTipo INT IDENTITY,
nome VARCHAR(50) NOT NULL
PRIMARY KEY (codTipo)
)

CREATE TABLE tipoRestricao(
codTipo INT NOT NULL,
codRestTipo_FK INT NOT NULL
PRIMARY KEY (codTipo, codRestTipo_FK),
FOREIGN KEY (codRestTipo_FK) REFERENCES tipo(codTipo),
FOREIGN KEY (codTipo) REFERENCES tipo(codTipo)
)

CREATE TABLE produto(
codProduto INT IDENTITY,
nome VARCHAR(50) NOT NULL,
uso VARCHAR(50),
qtdMinima DECIMAL(6,2) NOT NULL,
qtdSeguranca DECIMAL(6,2),
qtdMaxima DECIMAL(6,2) NOT NULL,
consumoPrevisto VARCHAR(50),
preco MONEY NOT NULL,
peso DECIMAL (6,2) NOT NULL,
codTipo int NOT NULL,
CONSTRAINT chk_max_min CHECK ((qtdMaxima >= qtdMinima) AND (qtdMaxima >= qtdSeguranca)),
PRIMARY KEY(codProduto),
FOREIGN KEY (codTipo) REFERENCES tipo(codTipo)
)

CREATE TABLE saida(
codSaida INT IDENTITY,
data DATETIME NOT NULL,
descricao VARCHAR(18)
PRIMARY KEY(codSaida)
)

CREATE TABLE item(
codItem INT IDENTITY,
codProduto INT NOT NULL,
codSaida INT,
codEntrada INT NOT NULL,
PRIMARY KEY (codItem),
FOREIGN KEY (codProduto) REFERENCES Produto(codProduto),
FOREIGN KEY (codSaida) REFERENCES Saida(codSaida),
FOREIGN KEY (codEntrada) REFERENCES Entrada(codEntrada)
)

CREATE TABLE entrada(
codEntrada INT IDENTITY,
data DATETIME NOT NULL,
tipoTransferencia VARCHAR(50) NOT NULL,
NFE VARCHAR(20),
dataEmissaoNFE DATETIME,
tempo DECIMAL(6,2),
codFornecedor INT
PRIMARY KEY(codEntrada),
FOREIGN KEY(codFornecedor) REFERENCES Fornecedor(codFornecedor)
)

CREATE TABLE responsavel(
codResponsavel INT IDENTITY,
nome VARCHAR(50) NOT NULL,
telefone VARCHAR(20) NOT NULL
PRIMARY KEY(codResponsavel)
)

CREATE TABLE departamento(
codDepartamento INT IDENTITY,
nome VARCHAR(50) NOT NULL,
andar VARCHAR(50) NOT NULL,
predio VARCHAR(50) NOT NULL,
telefone VARCHAR(11) NOT NULL,
codResponsavel INT NOT NULL,
PRIMARY KEY(codDepartamento),
FOREIGN KEY (codResponsavel) REFERENCES Responsavel(codResponsavel)
)

CREATE TABLE fornecedor(
codFornecedor INT IDENTITY,
rua VARCHAR(50) NOT NULL,
numero int NOT NULL,
bairro VARCHAR(50) NOT NULL,
cidade VARCHAR(50) NOT NULL,
estado VARCHAR(20) NOT NULL,
nome VARCHAR(50) NOT NULL,
telefone VARCHAR(20) NOT NULL
PRIMARY KEY(codFornecedor)
)

CREATE TABLE solicitacao_Fornecedor(
codSolicitacao INT IDENTITY,
codFornecedor INT NOT NULL,
data DATETIME NOT NULL
PRIMARY KEY(codSolicitacao),
FOREIGN KEY (codFornecedor) REFERENCES Fornecedor(codFornecedor)
)

CREATE TABLE solicitacao_Departamento(
codSolicitacao INT IDENTITY,
codDepartamento INT NOT NULL,
data DATETIME NOT NULL
PRIMARY KEY (codSolicitacao),
FOREIGN KEY (codDepartamento) REFERENCES departamento(codDepartamento)
)

CREATE TABLE solicitacao_Produto_Departamento(
id_Produto int NOT NULL,
id_Solicitacao int NOT NULL,
quantidade DECIMAL(6,2) NOT NULL
PRIMARY KEY (id_Produto, id_Solicitacao),
FOREIGN KEY (id_Produto) REFERENCES produto(codProduto),
FOREIGN KEY (id_Solicitacao) REFERENCES solicitacao_Departamento(codSolicitacao)
)

CREATE TABLE solicitacao_Produto_Fornecedor(
quantidade DECIMAL(6,2) NOT NULL,
idProduto INT NOT NULL,
idSolicitacao INT NOT NULL
PRIMARY KEY (idProduto, idSolicitacao),
FOREIGN KEY (idProduto) REFERENCES produto(codProduto),
FOREIGN KEY (idSolicitacao) REFERENCES solicitacao_Fornecedor(codSolicitacao)
)

CREATE TABLE produto_Solicitacao_Entrada(
codSolicitacaoEntrada INT IDENTITY,
quantidade DECIMAL(6,2) NOT NULL,
uso VARCHAR(18),
idProduto INT NOT NULL,
idEntrada INT NOT NULL,
idSolicitacao INT
PRIMARY KEY(codSolicitacaoEntrada),
FOREIGN KEY(idProduto) REFERENCES produto(codProduto),
FOREIGN KEY(idEntrada) REFERENCES entrada(codEntrada),
FOREIGN KEY(idSolicitacao) REFERENCES solicitacao_Fornecedor(codSolicitacao)
)

CREATE TABLE produto_Solicitacao_Saida(
codSolicitacaoSaida INT IDENTITY,
quantidade DECIMAL (6,2) NOT NULL,
uso VARCHAR(18),
codSaida INT NOT NULL,
codProduto INT NOT NULL,
codSolicitacao INT
PRIMARY KEY (codSolicitacaoSaida),
FOREIGN KEY (codSaida) REFERENCES Saida(codSaida),
FOREIGN KEY (codProduto) REFERENCES Produto(codProduto),
FOREIGN KEY (codSolicitacao) REFERENCES solicitacao_Departamento(codSolicitacao)
)

INSERT INTO tipo(nome) VALUES ('Bebida'),('Alimento'),('Limpeza'),('Eletrônicos'),('Frutas'),('Vestuário'),('Entretenimento'),('Outros')

INSERT INTO tipoRestricao VALUES (1,8),(2,7),(3,6),(4,5),(5,4),(6,3),(7,2),(8,1)

SELECT codRestTipo_FK, tp.nome, tipo.codTipo FROM tipo
INNER JOIN tipoRestricao
ON tipo.codTipo = tipoRestricao.codTipo
INNER JOIN tipo tp
ON tp.codTipo = tipoRestricao.codRestTipo_FK


INSERT INTO produto (nome,uso,qtdMinima,qtdSeguranca,qtdMaxima,consumoPrevisto,preco,peso,codTipo) VALUES
 ('Bombril','Limpeza',5,10,15,'Mensal',2.30,0.2,3),
 ('Ipê','Detergente',2,4,6,'Semanal',1.20,0.5,3),
 ('Papel Higiênico','Limpar a bunda',10,20,30,'Semanal',2.50,1,3),
 ('Chocolate','Alimento',20,50,80,'Semanal',3.50,1,2),
 ('Banana','Fruta',10,20,30,'Diário',4,5,5),
 ('Camisa','Vestuário',1,2,3,'Anual',20,1,6),
 ('Carteira','Vestuário',1,2,3,'Anual',10,1,6),
 ('Refrigerante', 'Alimento',4,8,12,'Mensal',5,2,2),
 ('Ventilador', 'Eletrônico', 1,2,3,'Anual',50,5,4),
 ('Celular', 'Eletrônicos', 1,2,3,'Anual',500,0.5,4),
 ('Maçã', 'Fruta', 5,10,15,'Diário',4,0.5,5),
 ('Omo', 'Amaciante', 2,4,6,'Mensal',10,2,3),
 ('DVD Player', 'Eletrônicos', 1,2,3,'Anual',150,5,4),
 ('Pregador', 'Prendedor de roupas', 5,10,15,'Mensal',5,0.4,8),
 ('Pão de Queijo', 'Alimento', 3,6,9,'Mensal',10,2,2),
 ('Computador', 'Eletrônicos', 1,2,3,'Anual',1000,5,4),
 ('Blink-182 - Greatest Hits', 'CD Musical', 1,2,3,'Anual',30,0.5,7),
 ('Camisa do Petr Cech', 'Artigos Esportivos', 1,2,3,'Anual',200,1,6),
 ('Cachaça', 'Bebidas Alcoólicas', 1,2,3, 'Semestral', 10,2,1)
 
INSERT INTO saida(data,descricao) VALUES 
('01/01/2008', 'Saida'),
('02/02/2008', 'Reposicao'),
('03/03/2008', 'Consumo'),
('04/04/2008', 'Consumo'),
('05/05/2008', 'Entrega mensal'),
('06/06/2008', 'Saida'),
('07/07/2008', 'Consumo'),
('08/08/2008', 'Consumo'),
('09/09/2008', ''),
('10/10/2008', 'Atendimento'),
('11/11/2008', 'Reposicao'),
('12/12/2008', 'Consumo'),
('01/01/2009', ''),
('02/02/2009', ''),
('03/03/2009', 'Necessario'),
('04/04/2009', 'Reposicao Seg'),
('05/05/2009', 'Consumo'),
('06/06/2009', 'Consumo'),
('06/06/2009', ''),
('07/07/2009', 'Consumo')

INSERT INTO item(codProduto,codSaida,codEntrada) VALUES
(10,20,2),
(11,19,2),
(12,18,3),
(13,17,4),
(14,16,5),
(15,15,6),
(16,14,7),
(17,13,8),
(18,12,9),
(19,11,10),
(19,10,11),
(19,9,12),
(18,8,13),
(17,7,14),
(16,6,15),
(15,5,16),
(14,4,17),
(13,3,18),
(12,2,19),
(11,1,20),
(10,1,20),
(9,2,19),
(8,3,18),
(7,4,17),
(6,5,16),
(5,6,15),
(4,7,14),
(3,8,13),
(2,9,12),
(1,10,11),
(1,11,10),
(2,12,9),
(3,13,8),
(4,14,7),
(5,15,6),
(6,16,5),
(7,17,4),
(8,18,3),
(9,19,2),
(10,20,3)

INSERT INTO entrada(data, tipoTransferencia, NFE, dataEmissaoNFE, tempo,codFornecedor) VALUES
('12/03/2006', 'Cheque', 'NFE1','14/03/2006',2.5,1),
('13/04/2006', 'Cartão', 'NFE2','15/04/2006',2,1),
('14/05/2006', 'Cheque', 'NFE3','16/05/2006',3.5,2),
('15/06/2006', 'Dinheiro', 'NFE4','17/06/2006',3,2),
('16/07/2006', 'Cartão', 'NFE5','18/07/2006',4,3),
('17/08/2006', 'Dinheiro', 'NFE6','19/08/2006',4.5,3),
('18/09/2006', 'Cheque', 'NFE7','20/09/2006',6,4),
('19/10/2006', 'Cheque', 'NFE8','21/10/2006',2.5,4),
('20/11/2006', 'Cartão', 'NFE9','22/11/2006',2,5),
('21/12/2006', 'Dinheiro', 'NFE10','23/12/2006',1.5,5),
('22/01/2007', 'Dinheiro', 'NFE11','24/01/2007',1,6),
('23/02/2007', 'Cartão', 'NFE12','25/02/2007',6.5,6),
('24/03/2007', 'Cheque', 'NFE13','26/03/2007',4,7),
('25/04/2007', 'Cheque', 'NFE14','27/04/2007',3.5,7),
('26/05/2007', 'Cheque', 'NFE15','28/05/2007',2,8),
('27/06/2007', 'Dinheiro', 'NFE16','29/06/2007',3.5,8),
('28/07/2007', 'Cartão', 'NFE17','30/07/2007',1.5,9),
('29/08/2007', 'Cartão', 'NFE18','01/09/2007',2,9),
('30/09/2007', 'Cartão', 'NFE19','02/10/2007',2.5,10),
('31/10/2007', 'Dinheiro', 'NFE20','03/11/2007',3.5,10)

INSERT INTO responsavel(nome,telefone) VALUES
('Eike Batista','2345-5542'),
('Ronaldinho Gaúcho','2110-4563'),
('Tico Santa Cruz', '6666-6969'),
('Pepe Moreno', '5502-3411'),
('Dilma Rousseff', '4401-4122'),
('David Luiz', '7171-7171')

INSERT INTO departamento(nome,andar,predio,telefone,codResponsavel) VALUES
('RH', 1, 'World Trade Center', '1111-1111',2),
('Limpeza', 2, 'Predio principal', '2222-2222',1),
('Administração', 3, 'Prédio que não é o principal', '3333-3333',4),
('Contábil', 4, 'Prédio secundário', '4444-4444',3),
('Financeiro', 5, 'Prédio bonito', '5555-5555',6),
('Gestão', 6, 'Prédio feio', '6666-6666',5)


INSERT INTO fornecedor(rua,numero,bairro,cidade,estado,nome,telefone) VALUES
('Rua 1', 100, 'São Miguel', 'São Paulo', 'São Paulo', 'Leandro', '4002-8922'),
('Rua 2', 200, 'Cidade AE Carvalho', 'São Paulo', 'São Paulo', 'Bruno', '4003-8933'),
('Rua 3', 300, 'Rio do Ouro', 'São Paulo', 'São Paulo', 'Rafael', '4004-8944'),
('Rua 4', 400, 'Bairro dos Alemães de cabelo escuro', 'Pomerode', 'Santa Catarina', 'Cech', '4005-8955'),
('Rua 5', 500, 'Bairro Aleatório', 'Rio de Janeiro', 'Rio de Janeiro', 'Fabio', '4006-8966'),
('Rua 6', 600, 'San Andreas', 'Bahia', 'Salvador', 'Julio', '4007-8977'),
('Rua 7', 700, 'Nazista', 'Rio Grande do Sul', 'Porto Alegre', 'Roberto', '4008-8988'),
('Rua 8', 800, 'Itaim Paulista', 'São Paulo', 'São Paulo', 'William', '4009-8999'),
('Rua 9', 900, 'Penha', 'São Paulo', 'São Paulo', 'Vinicius', '4010-8910'),
('Rua 10', 1000, 'Tatuapé', 'São Paulo', 'São Paulo', 'Neymar', '4011-8911')

INSERT INTO solicitacao_Fornecedor(codFornecedor,data) VALUES 
(2,'01/01/2010'),
(4,'02/02/2010'),
(6,'03/03/2010'),
(8,'04/04/2010'),
(10,'05/05/2010'),
(1,'06/06/2010'),
(3,'07/07/2010'),
(5,'08/08/2010'),
(7,'09/09/2010'),
(9,'10/10/2010'),
(9,'11/11/2010'),
(7,'12/12/2010'),
(5,'01/01/2011'),
(3,'02/02/2011'),
(1,'03/03/2011'),
(10,'04/04/2011'),
(8,'05/05/2011'),
(6,'06/06/2011'),
(4,'07/07/2011'),
(2,'08/08/2011')

INSERT INTO solicitacao_Departamento(codDepartamento,data) VALUES 
(3,'01/01/2010'),
(6,'02/02/2010'),
(5,'03/03/2010'),
(4,'04/04/2010'),
(2,'05/05/2010'),
(1,'06/06/2010'),
(1,'07/07/2010'),
(2,'08/08/2010'),
(4,'09/09/2010'),
(5,'10/10/2010'),
(3,'11/11/2010'),
(6,'12/12/2010')

INSERT INTO solicitacao_Produto_Departamento(id_Solicitacao,id_Produto,quantidade) VALUES 
(2,18,200),
(3,17,300),
(4,16,400),
(5,15,500),
(6,14,600),
(7,13,700),
(8,12,800),
(9,11,900),
(10,10,1000),
(11,9,1100),
(12,8,1200),
(1,7,1300),
(2,6,1400),
(3,5,1500),
(4,4,1600),
(5,3,1700),
(6,2,1800),
(7,1,1900),
(8,1,2000)

INSERT INTO  solicitacao_Produto_Fornecedor(quantidade,idProduto,idSolicitacao) VALUES 
(2000,1,10),
(1900,2,9),
(1800,3,8),
(1700,4,7),
(1600,5,6),
(1500,6,5),
(1400,7,4),
(1300,8,3),
(1200,9,2),
(1100,10,1),
(1000,11,1),
(900,12,9),
(800,13,8),
(700,14,7),
(600,15,6),
(500,16,5),
(400,17,4),
(300,18,3),
(200,19,2),
(100,19,1)

INSERT INTO produto_Solicitacao_Entrada(quantidade,uso,idProduto,idEntrada,idSolicitacao) VALUES 
(100, '',19,2,20),
(200, '',18,2,19),
(300, 'Pedido',17,3,18),
(400, '',16,4,17),
(500, 'Solicitado',15,5,16),
(600, '',14,6,15),
(700, 'Consumo',13,7,14),
(800, '',12,8,13),
(900, '',11,9,12),
(1000, '',10,10,11),
(1100, '',9,11,10),
(1200, '',8,9,12),
(1300, '',7,8,13),
(1400, '',6,7,14),
(1500, '',5,6,15),
(1600, 'Consumo',4,5,16),
(1700, 'Reposicao',3,4,17),
(1800, '',2,3,18),
(1900, 'Seguranca',1,2,19),
(2000, '',2,3,20)

INSERT INTO produto_Solicitacao_Saida(quantidade,uso,codSaida,codProduto,codSolicitacao) VALUES
(100, '',20,1,1),
(200, '',19,2,2),
(300, 'Saida',18,3,3),
(400, 'Consumo',17,4,4),
(500, '',16,5,5),
(600, 'Pedido',15,6,6),
(700, '',14,7,7),
(800, '',13,8,8),
(900, '',12,9,9),
(1000, '',11,10,10),
(1100, 'Consumo',10,11,11),
(1200, '',9,12,12),
(1300, '',8,13,1),
(1400, '',7,14,2),
(1500, '',6,15,3),
(1600, '',5,16,4),
(1700, '',4,17,5),
(1800, 'Uso número 18',3,18,6),
(1900, 'Uso número 19',2,19,7),
(2000, 'Uso número 20',1,19,8)
