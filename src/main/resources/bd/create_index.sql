CREATE SEQUENCE seq_estacao START 1;

CREATE INDEX altitude_estacao_id_idx ON altitude (estacao_id);
CREATE INDEX chuva_estacao_id_idx ON chuva (estacao_id);
CREATE INDEX rotacao_estacao_id_idx ON rotacao (estacao_id);
CREATE INDEX pressaoatmosferica_estacao_id_idx ON pressaoatmosferica (estacao_id);
CREATE INDEX velocidade_estacao_id_idx ON velocidade (estacao_id);
CREATE INDEX sentidovento_estacao_id_idx ON sentidovento (estacao_id);
CREATE INDEX umidade_estacao_id_idx ON umidade (estacao_id);
CREATE INDEX temperatura_estacao_id_idx ON temperatura (estacao_id);
CREATE INDEX medicao_hora_estacao_id_idx ON medicao_hora (estacao_id);
CREATE INDEX medicao_dia_estacao_id_idx ON medicao_dia (estacao_id);