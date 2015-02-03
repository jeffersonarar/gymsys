
CREATE TABLE usuario
(
  idusuario serial NOT NULL,
  nome character varying(50),
  senha character varying(300),
  tipousuario integer,
  ativo boolean,
  CONSTRAINT usuario_pkey PRIMARY KEY (idusuario)
);


CREATE TABLE aluno
(
  idaluno serial NOT NULL,
  nome character varying(50),
  idusuario integer,
  cpf character varying(20),
  datanascimento date,
  celular character varying(15),
  altura numeric,
  peso numeric,
  cintura numeric,
  antebraco numeric,
  torax numeric,
  abdomen numeric,
  pescoco numeric,
  coxa numeric,
  ombro numeric,
  data timestamp without time zone,
  ativo boolean NOT NULL,
  CONSTRAINT aluno_pkey PRIMARY KEY (idaluno)
);

CREATE TABLE categoria
(
  idcategoria serial NOT NULL,
  nome character varying(120),
  descricao character varying(300),
  supcategoria integer,
  ativo boolean NOT NULL,
  CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria),
  CONSTRAINT fkidcategoriasupercategoria FOREIGN KEY (supcategoria)
      REFERENCES categoria (idcategoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE exercicio
(
  idexercicio serial NOT NULL,
  nome character varying(50) NOT NULL,
  idcategoria integer NOT NULL,
  responsavel character varying(50) NOT NULL,
  descricao character varying(200) NOT NULL,
  ativo boolean NOT NULL,
  CONSTRAINT exercicio_pkey PRIMARY KEY (idexercicio),
  CONSTRAINT fkidexerciciocategoria FOREIGN KEY (idcategoria)
      REFERENCES categoria (idcategoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE fichatreino
(
  idfichatreino serial NOT NULL,
  semana character varying(20),
  serie integer,
  repeticao character varying(20),
  ativo boolean,
  datainsercao date,
  idexercicio integer,
  idcategoria integer,
  idaluno integer,
  CONSTRAINT fichatreino_pkey PRIMARY KEY (idfichatreino),
  CONSTRAINT fichatreino_idcategoria_fkey FOREIGN KEY (idcategoria)
      REFERENCES categoria (idcategoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fichatreino_idexercicio_fkey FOREIGN KEY (idexercicio)
      REFERENCES exercicio (idexercicio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE multimidia
(
  idmultimidia serial NOT NULL,
  nome character varying(50),
  descricao character varying(300),
  multimidia character varying(200),
  ativo boolean,
  tipomultimidia boolean,
  idexercicio integer,
  idcategoria integer,
  idaluno integer,
  datainsercao timestamp without time zone,
  CONSTRAINT multimidia_pkey PRIMARY KEY (idmultimidia),
  CONSTRAINT multimidia_idcategoria_fkey FOREIGN KEY (idcategoria)
      REFERENCES categoria (idcategoria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT multimidia_idexercicio_fkey FOREIGN KEY (idexercicio)
      REFERENCES exercicio (idexercicio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE historicoaluno
(
  idhistorico serial NOT NULL,
  nome character varying(50),
  idusuario integer,
  cpf character varying(20),
  datanascimento date,
  celular character varying(15),
  altura numeric,
  peso numeric,
  cintura numeric,
  antebraco numeric,
  torax numeric,
  abdomen numeric,
  pescoco numeric,
  coxa numeric,
  ombro numeric,
  idaluno integer,
  data timestamp without time zone,
  ativo boolean,
  CONSTRAINT historicoaluno_pkey PRIMARY KEY (idhistorico)
);

CREATE OR REPLACE FUNCTION backup_alunos_update()
RETURNS TRIGGER AS
$$
  BEGIN
   INSERT INTO historicoaluno(nome, idusuario, cpf, datanascimento, celular, altura, peso, cintura, 
       antebraco, torax, abdomen, pescoco, coxa, ombro, idaluno, 
       data, ativo) 
values (NEW.nome, NEW.idusuario, NEW.cpf, NEW.datanascimento, NEW.celular, NEW.altura, NEW.peso, NEW.cintura, 
       NEW.antebraco, NEW.torax, NEW.abdomen, NEW.pescoco, NEW.coxa, NEW.ombro, NEW.idaluno, 
       NEW.data, NEW.ativo);
    RETURN NEW;
  END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_alunos_update BEFORE UPDATE
    ON aluno FOR EACH ROW
    EXECUTE PROCEDURE backup_alunos_update();

