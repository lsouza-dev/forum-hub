CREATE TABLE IF NOT EXISTS perfis(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS cursos(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
categoria VARCHAR(45) NOT NULL,
PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS usuarios(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
email VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
perfil INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_usuarios_perfis
FOREIGN KEY (perfil)
REFERENCES perfis(id)
);

CREATE TABLE IF NOT EXISTS topicos(
id INT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(45) NOT NULL,
mensagem VARCHAR(45) NOT NULL,
dataCriacao DATETIME NOT NULL,
statusTopico VARCHAR(45) NOT NULL,
autor INT NOT NULL,
curso INT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_topicos_usuarios
FOREIGN KEY (autor)
REFERENCES usuarios(id),
CONSTRAINT fk_topicos_cursos
FOREIGN KEY (curso)
REFERENCES cursos(id)
);

CREATE TABLE IF NOT EXISTS respostas(
id INT NOT NULL AUTO_INCREMENT,
mensagem VARCHAR(45) NOT NULL,
topico INT NOT NULL,
dataCriacao DATETIME NOT NULL,
autor INT NOT NULL,
solucao VARCHAR(255) NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_respostas_autores
FOREIGN KEY (autor)
REFERENCES usuarios(id),
CONSTRAINT fk_respostas_topicos
FOREIGN KEY (topico)
REFERENCES topicos(id)
);
