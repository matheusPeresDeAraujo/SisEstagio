/*
SQLyog Ultimate v9.30 
MySQL - 5.5.27 : Database - sisestagio
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sisestagio` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sisestagio`;

/*Table structure for table `aluno` */

DROP TABLE IF EXISTS `aluno`;

CREATE TABLE `aluno` (
  `idAluno` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL,
  `fone2` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `idCurriculo` int(11) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAluno`),
  KEY `FK589C4EB93185BD5` (`idCurriculo`),
  CONSTRAINT `FK589C4EB93185BD5` FOREIGN KEY (`idCurriculo`) REFERENCES `curriculo` (`idCurriculo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `aluno` */

insert  into `aluno`(`idAluno`,`bairro`,`celular`,`cep`,`cidade`,`complemento`,`email`,`fone1`,`fone2`,`matricula`,`nome`,`numero`,`rua`,`uf`,`idCurriculo`,`senha`) values (2,'São Mateus','(32)8814-3669','36025-001','Juiz de fora','','neuberknupp@gmail.com','','','9102044','Neuber Knupp da Cunha',1208,'Rua São Mateus','MG',4,'90h7ELT7XmJp4VKqbooLGA=='),(3,'Centro','','36027-052','Juiz de fora','','fabio.nascimento@granbery.edu.br','','','9102045','Fabio Medeiros Nascimento',2550,'Av. Rio Branco','MG',4,'90h7ELT7XmJp4VKqbooLGA=='),(4,'Cidade do Sol','','36085-440','Juiz de fora','','tamires.vga19@gmail.com','','','9102046','Tamires Sales Faria',93,'José Teixeira da Silva','MG',4,'90h7ELT7XmJp4VKqbooLGA=='),(5,'São Mateus','','36025-001','Juiz de Fora','','nubioknupp@gmail.com','','','9102043','Núbio Knupp da Cunha',1208,'São Mateus','MG',4,'90h7ELT7XmJp4VKqbooLGA==');

/*Table structure for table `curriculo` */

DROP TABLE IF EXISTS `curriculo`;

CREATE TABLE `curriculo` (
  `idCurriculo` int(11) NOT NULL AUTO_INCREMENT,
  `horasDeEstagio` double DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCurriculo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `curriculo` */

insert  into `curriculo`(`idCurriculo`,`horasDeEstagio`,`numero`) values (4,340,2);

/*Table structure for table `distrato` */

DROP TABLE IF EXISTS `distrato`;

CREATE TABLE `distrato` (
  `idDistrato` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `dataTermino` datetime DEFAULT NULL,
  `idTermoDeCompromisso` int(11) NOT NULL,
  PRIMARY KEY (`idDistrato`),
  UNIQUE KEY `idTermoDeCompromisso` (`idTermoDeCompromisso`),
  KEY `FK11391790DE54AC2A` (`idTermoDeCompromisso`),
  CONSTRAINT `FK11391790DE54AC2A` FOREIGN KEY (`idTermoDeCompromisso`) REFERENCES `termodecompromisso` (`idTermoDeCompromisso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `distrato` */

/*Table structure for table `empresa` */

DROP TABLE IF EXISTS `empresa`;

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `cnpj` bigint(20) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `contato` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fone1` varchar(255) DEFAULT NULL,
  `fone2` varchar(255) DEFAULT NULL,
  `foneContato` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `empresa` */

insert  into `empresa`(`idEmpresa`,`bairro`,`cep`,`cidade`,`cnpj`,`complemento`,`contato`,`email`,`fone1`,`fone2`,`foneContato`,`nome`,`numero`,`rua`,`site`,`uf`) values (1,'Centro','36016-200','Juiz de fora',71058701000120,'','','aue@auesoftware.com','','','','AuE Software',1281,'Espirito Santo','','MG'),(2,'São Mateus','36025-001','Juiz de fora',21447857000124,'','','sos@sossoftware.com.br','','','','SOS Software',1208,'São Mateus','','MG'),(4,'Centro','36025-001','Juiz de fora',83177172000162,'','','social@stefanini.com','','','','Stefanini',2000,'Frei Caneca','','MG'),(5,'São Mateus','36016-030','Juiz de Fora',97372427000152,'','','info@handcom.com.br','','','','Handcom Inovações Tecnológicas',24,'Rua Candido Tostes','','MG'),(6,'Granbery','36010-530','Juiz de Fora',74025571000117,'','','comunicacao@granbery.edu.br','','','','Instituto Metodista Granbery',1145,'Batista de Oliveira','','MG');

/*Table structure for table `projetoestagio` */

DROP TABLE IF EXISTS `projetoestagio`;

CREATE TABLE `projetoestagio` (
  `idProjetoDeEstagio` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  PRIMARY KEY (`idProjetoDeEstagio`),
  UNIQUE KEY `idAluno` (`idAluno`),
  KEY `FKAB93CBAF1BC58753` (`idAluno`),
  CONSTRAINT `FKAB93CBAF1BC58753` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`idAluno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `projetoestagio` */

/*Table structure for table `projetofinal` */

DROP TABLE IF EXISTS `projetofinal`;

CREATE TABLE `projetofinal` (
  `idProjetoFinal` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  PRIMARY KEY (`idProjetoFinal`),
  UNIQUE KEY `idAluno` (`idAluno`),
  KEY `FKB15D42131BC58753` (`idAluno`),
  CONSTRAINT `FKB15D42131BC58753` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`idAluno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `projetofinal` */

/*Table structure for table `relatoriodeacompanhamento` */

DROP TABLE IF EXISTS `relatoriodeacompanhamento`;

CREATE TABLE `relatoriodeacompanhamento` (
  `idRelatorioDeAcompanhamento` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  PRIMARY KEY (`idRelatorioDeAcompanhamento`),
  UNIQUE KEY `idAluno` (`idAluno`),
  KEY `FK6D50B3911BC58753` (`idAluno`),
  CONSTRAINT `FK6D50B3911BC58753` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`idAluno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `relatoriodeacompanhamento` */

/*Table structure for table `relatoriomensal` */

DROP TABLE IF EXISTS `relatoriomensal`;

CREATE TABLE `relatoriomensal` (
  `idRelatorioMensal` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `horasPenalizadas` double DEFAULT NULL,
  `horasRegistradas` double DEFAULT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `dataInicioAtividade` datetime DEFAULT NULL,
  `dataTerminoAtividade` datetime DEFAULT NULL,
  `horasAproveitadas` double DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  PRIMARY KEY (`idRelatorioMensal`),
  KEY `FK2816017D1BC58753` (`idAluno`),
  CONSTRAINT `FK2816017D1BC58753` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`idAluno`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `relatoriomensal` */

insert  into `relatoriomensal`(`idRelatorioMensal`,`dataEntrega`,`protocolo`,`horasPenalizadas`,`horasRegistradas`,`observacoes`,`dataInicioAtividade`,`dataTerminoAtividade`,`horasAproveitadas`,`idAluno`) values (1,'2013-01-02 00:00:00','850',20,100,NULL,'2013-11-02 00:00:00','2013-12-02 00:00:00',80,3),(2,'2013-12-02 00:00:00','851',0,120,NULL,'2013-11-02 00:00:00','2013-12-02 00:00:00',120,2),(3,'2013-10-02 00:00:00','852',0,110,NULL,'2013-09-02 00:00:00','2013-10-02 00:00:00',110,4),(4,'2013-12-09 00:00:00','34343',18,80,NULL,'2013-11-01 00:00:00','2013-11-30 00:00:00',62,3);

/*Table structure for table `termoaditivo` */

DROP TABLE IF EXISTS `termoaditivo`;

CREATE TABLE `termoaditivo` (
  `idTermoAditivo` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `dataInicio` datetime DEFAULT NULL,
  `dataTermino` datetime DEFAULT NULL,
  `idTermoDeCompromisso` int(11) NOT NULL,
  PRIMARY KEY (`idTermoAditivo`),
  KEY `FK4E4B2E91DE54AC2A` (`idTermoDeCompromisso`),
  CONSTRAINT `FK4E4B2E91DE54AC2A` FOREIGN KEY (`idTermoDeCompromisso`) REFERENCES `termodecompromisso` (`idTermoDeCompromisso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `termoaditivo` */

/*Table structure for table `termodecompromisso` */

DROP TABLE IF EXISTS `termodecompromisso`;

CREATE TABLE `termodecompromisso` (
  `idTermoDeCompromisso` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrega` datetime DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `dataInicio` datetime DEFAULT NULL,
  `dataTermino` datetime DEFAULT NULL,
  `declaracaoDeTrabalho` bit(1) DEFAULT NULL,
  `idAluno` int(11) NOT NULL,
  `idEmpresa` int(11) NOT NULL,
  `trouxeCtps` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idTermoDeCompromisso`),
  KEY `FKB70A00E3EA93134F` (`idEmpresa`),
  KEY `FKB70A00E31BC58753` (`idAluno`),
  CONSTRAINT `FKB70A00E31BC58753` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`idAluno`),
  CONSTRAINT `FKB70A00E3EA93134F` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `termodecompromisso` */

insert  into `termodecompromisso`(`idTermoDeCompromisso`,`dataEntrega`,`protocolo`,`dataInicio`,`dataTermino`,`declaracaoDeTrabalho`,`idAluno`,`idEmpresa`,`trouxeCtps`) values (1,'2013-12-02 00:00:00','78452254','2013-11-26 00:00:00','2014-11-26 00:00:00','\0',3,6,NULL),(2,'2013-12-02 00:00:00','78452255','2013-11-01 00:00:00','2014-12-02 00:00:00','\0',4,5,NULL),(3,'2013-10-02 00:00:00','78452256','2013-10-02 00:00:00','2014-10-02 00:00:00','\0',2,4,NULL);

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipoAcesso` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`idUsuario`,`email`,`nome`,`senha`,`tipoAcesso`) values (19,'sisestagio@gmail.com','Administrador do SisEstagio','VaONaX6TB/AAeNieNh9MAw==',1),(25,'nubioknupp@gmail.com','Núbio Knupp','jBu4unjVkEYc9WWuasyZYQ==',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
