CREATE TABLE Bebe (
  idBebe INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome CHAR NULL,
  sexo BOOL NULL,
  nascimento DATE NULL,
  PRIMARY KEY(idBebe)
);

CREATE TABLE RegistroAlimentacao (
  idRegistroAlimentacao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Bebe_idBebe INTEGER UNSIGNED NOT NULL,
  idBebe INTEGER UNSIGNED NOT NULL,
  inicio DATETIME NULL,
  fim DATETIME NULL,
  PRIMARY KEY(idRegistroAlimentacao),
  INDEX RegistroAlimentacao_FKIndex1(Bebe_idBebe)
);

CREATE TABLE RegistroSono (
  idRegistroSono INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idBebe INTEGER UNSIGNED NOT NULL,
  Bebe_idBebe INTEGER UNSIGNED NOT NULL,
  inicio DATETIME NULL,
  fim DATETIME NULL,
  PRIMARY KEY(idRegistroSono, idBebe),
  INDEX RegistroSono_FKIndex1(Bebe_idBebe)
);


