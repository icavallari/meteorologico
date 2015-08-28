/* backup no linux */
sudo -u postgres pg_dump  meteorologico > /home/aluno/base_10_06.sql

/* exibe o total de linhas, o custo de cada nó e a largura de cada linha */
explain select * from altitude;

/* exibe em MB o tamanho de uma base de dados */
SELECT pg_size_pretty(pg_database_size('meteorologico'));

/* executar scripts pelo painel postgres sql shell*/
psql -U postgres -f location.sql

/* apagando o banco atual e criando um novo restaurando */
psql -U postgres -c "DROP DATABASE IF EXISTS meteorologico" && psql -U postgres -c "CREATE DATABASE meteorologico WITH ENCODING 'UTF8'" && psql -U postgres meteorologico < location

/* backup */
pg_dump -U postgres meteorologico > location

/* excluindo dados das tabelas */
truncate altitude, sentidovento, pressaoatmosferica, temperatura, rotacao, velocidade, chuva, umidade

/*manutenção medição_hora*/
delete from temperatura where data < now() - interval '30 days'

/*manutenção medição_ano*/
delete from temperatura where data < now() - interval '5 year'

/* consulta para tabelas auxiliares com campo numerico */
    select
    round(min(t.valor),2) as temperaturaminima, round(avg(t.valor),2) as temperaturamedia , round(max(t.valor),2) as temperaturamaxima,
    round(min(u.valor),2) as umidademinima, round(avg(u.valor),2) as umidademedia , round(max(u.valor),2) as umidademaxima,
    round(min(a.valor),2) as altitudeminima, round(avg(a.valor),2) as altitudemedia , round(max(a.valor),2) as altitudemaxima,
    round(min(r.valor),2) as rotacaominima, round(avg(r.valor),2) as rotacaomedia , round(max(r.valor),2) as rotacaomaxima,
    round(min(v.valor),2) as velocidademinima, round(avg(v.valor),2) as velocidademedia , round(max(v.valor),2) as velocidademaxima,
    round(min(p.valor),2) as pressaominima, round(avg(p.valor),2) as pressaomedia , round(max(p.valor),2) as pressaomaxima,
    max(t.data::timestamp) as data
    from temperatura t, umidade u, altitude a, rotacao r, velocidade v, pressaoatmosferica p
    where extract('day' from t.data) = 28
    group by extract('hour' from t.data) order by data asc

/* testar valores para tabelas auxiliares com campo texto */
select count(valor) from chuva where valor = 'Chuva de Intensidade Alta.' group by extract('hour' from data) order by data asc
select count(valor) from chuva where valor = 'Chuva de Intensidade Moderada.' group by extract('hour' from data) order by data asc
select count(valor) from chuva where valor = 'Chuva de Intensidade Baixa.' group by extract('hour' from data) order by data asc

/* comandos sql uteis */
    SELECT pg_size_pretty(pg_database_size('meteorologico'));
    SELECT pg_size_pretty(pg_table_size('velocidade'))
    SELECT pg_size_pretty(pg_column_size(55.55::numeric))
    SELECT pg_size_pretty(pg_column_size(94555::int))
    SELECT pg_size_pretty(pg_column_size(555.55::float))
    ALTER TABLE altitude ALTER COLUMN valor TYPE float;

 /* executar scripts pelo painel postgres sql shell*/
    psql -U postgres -f location.sql

    /* restore */
    pg_restore -U postgres meteorologico < location

    /* backup */
    pg_dump -U postgres meteorologico > location

    /* comando para pegar os dados agrupados */
    select
    round(min(t.valor),2) as temperaturaminima, round(avg(t.valor),2) as temperaturamedia , round(max(t.valor),2) as temperaturamaxima,
    round(min(u.valor),2) as umidademinima, round(avg(u.valor),2) as umidademedia , round(max(u.valor),2) as umidademaxima,
    round(min(a.valor),2) as altitudeminima, round(avg(a.valor),2) as altitudemedia , round(max(a.valor),2) as altitudemaxima,
    round(min(r.valor),2) as rotacaominima, round(avg(r.valor),2) as rotacaomedia , round(max(r.valor),2) as rotacaomaxima,
    round(min(v.valor),2) as velocidademinima, round(avg(v.valor),2) as velocidademedia , round(max(v.valor),2) as velocidademaxima,
    round(min(p.valor),2) as pressaominima, round(avg(p.valor),2) as pressaomedia , round(max(p.valor),2) as pressaomaxima,
    max(t.data::timestamp) as data
    from temperatura t, umidade u, altitude a, rotacao r, velocidade v, pressaoatmosferica p
    where extract('day' from t.data) = 28
    group by extract('hour' from t.data) order by data asc;