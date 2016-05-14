CREATE TABLE IF NOT EXISTS estacao (
    id SMALLINT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    localizacao VARCHAR,
    data_criacao DATE NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pressaoatmosferica (
    valor INTEGER NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_pressaoatmosferica_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS altitude (
    valor DECIMAL NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_altitude_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS rotacao (
    valor DECIMAL NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_rotacao_estacao
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS velocidade (
    valor DECIMAL NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_velocidade_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS sentidovento (
    valor VARCHAR(100) NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_sentidovento_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS umidade (
    valor DECIMAL NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_umidade_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS temperatura (
    valor DECIMAL NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_temperatura_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS chuva (
    valor VARCHAR(100) NULL,
    data TIMESTAMP NULL,
    estacao_id SMALLINT NOT NULL,
    PRIMARY KEY (data, estacao_id),
    CONSTRAINT fk_chuva_estacao1
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS medicao_hora(
    data TIMESTAMP NOT NULL PRIMARY KEY,
    estacao_id SMALLINT NOT NULL,
    altitude_minima NUMERIC NULL,
    altitude_media NUMERIC NULL,
    altitude_maxima NUMERIC NULL,
    chuva_moda3 VARCHAR(50) NULL,
    chuva_moda2 VARCHAR(50) NULL,
    chuva_moda1 VARCHAR(50) NULL,
    chuva_qtd_moda3 INTEGER NULL,
    chuva_qtd_moda2 INTEGER NULL,
    chuva_qtd_moda1 INTEGER NULL,
    pressao_minima INTEGER NULL,
    pressao_media INTEGER NULL,
    pressao_maxima INTEGER NULL,
    rotacao_minima NUMERIC NULL,
    rotacao_media NUMERIC NULL,
    rotacao_maxima NUMERIC NULL,
    temperatura_minima NUMERIC NULL,
    temperatura_media NUMERIC NULL,
    temperatura_maxima NUMERIC NULL,
    umidade_minima NUMERIC NULL,
    umidade_media NUMERIC NULL,
    umidade_maxima NUMERIC NULL,
    velocidade_minima NUMERIC NULL,
    velocidade_media NUMERIC NULL,
    velocidade_maxima NUMERIC NULL,
    sentidovento_moda3 VARCHAR(50) NULL,
    sentidovento_moda2 VARCHAR(50) NULL,
    sentidovento_moda1 VARCHAR(50) NULL,
    sentidovento_qtd_moda3 INTEGER NULL,
    sentidovento_qtd_moda2 INTEGER NULL,
    sentidovento_qtd_moda1 INTEGER NULL,
    CONSTRAINT fk_medicaohora_estacao_id
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);

CREATE TABLE IF NOT EXISTS medicao_dia(
    data TIMESTAMP NOT NULL PRIMARY KEY,
    estacao_id SMALLINT NOT NULL,
    altitude_minima NUMERIC NULL,
    altitude_media NUMERIC NULL,
    altitude_maxima NUMERIC NULL,
    chuva_moda3 VARCHAR(50) NULL,
    chuva_moda2 VARCHAR(50) NULL,
    chuva_moda1 VARCHAR(50) NULL,
    chuva_qtd_moda3 INTEGER NULL,
    chuva_qtd_moda2 INTEGER NULL,
    chuva_qtd_moda1 INTEGER NULL,
    pressao_minima INTEGER NULL,
    pressao_media INTEGER NULL,
    pressao_maxima INTEGER NULL,
    rotacao_minima NUMERIC NULL,
    rotacao_media NUMERIC NULL,
    rotacao_maxima NUMERIC NULL,
    temperatura_minima NUMERIC NULL,
    temperatura_media NUMERIC NULL,
    temperatura_maxima NUMERIC NULL,
    umidade_minima NUMERIC NULL,
    umidade_media NUMERIC NULL,
    umidade_maxima NUMERIC NULL,
    velocidade_minima NUMERIC NULL,
    velocidade_media NUMERIC NULL,
    velocidade_maxima NUMERIC NULL,
    sentidovento_moda3 VARCHAR(50) NULL,
    sentidovento_moda2 VARCHAR(50) NULL,
    sentidovento_moda1 VARCHAR(50) NULL,
    sentidovento_qtd_moda3 INTEGER NULL,
    sentidovento_qtd_moda2 INTEGER NULL,
    sentidovento_qtd_moda1 INTEGER NULL,
    CONSTRAINT fk_medicaodia_estacao_id
    FOREIGN KEY (estacao_id)
    REFERENCES estacao (id)
);