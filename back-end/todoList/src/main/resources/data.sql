INSERT INTO tb_user (name, email, password) VALUES ('Guilherme', 'guilherme@gmail.com', '123456');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'maria@gmail.com', '123456');

INSERT INTO tb_category (name, color) VALUES ('Trabalho', '#2563EB');
INSERT INTO tb_category (name, color) VALUES ('Estudos', '#22C55E');
INSERT INTO tb_category (name, color) VALUES ('Pessoal', '#F59E0B');
INSERT INTO tb_category (name, color) VALUES ('Casa', '#EF4444');

-- TRABALHO (3 tarefas) categoria_id = 1

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Finalizar relatório', 'Enviar relatório semanal para equipe', false, CURRENT_TIMESTAMP, DATE '2026-07-20', 'HIGH', 1, 1);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Organizar documentos', 'Separar documentos importantes do setor', true, CURRENT_TIMESTAMP, DATE '2026-07-18', 'MEDIUM', 1, 1);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Reunião da equipe', 'Participar da reunião mensal', false, CURRENT_TIMESTAMP, DATE '2026-07-25', 'LOW', 2, 1);


-- ESTUDOS (2 tarefas) categoria_id = 2

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Estudar Spring Boot', 'Revisar JPA e Hibernate', false, CURRENT_TIMESTAMP, DATE '2026-07-22', 'HIGH', 1, 2);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Praticar React', 'Criar componentes com TypeScript', true, CURRENT_TIMESTAMP, DATE '2026-07-30', 'MEDIUM', 2, 2);


-- PESSOAL (7 tarefas) categoria_id = 3

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Ir ao mercado', 'Comprar alimentos da semana', false, CURRENT_TIMESTAMP, DATE '2026-07-17', 'MEDIUM', 1, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Pagar contas', 'Realizar pagamentos mensais', true, CURRENT_TIMESTAMP, DATE '2026-07-10', 'HIGH', 1, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Lavar o carro', 'Fazer limpeza completa do carro', false, CURRENT_TIMESTAMP, DATE '2026-07-21', 'LOW', 2, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Marcar consulta', 'Agendar consulta de rotina', false, CURRENT_TIMESTAMP, DATE '2026-08-01', 'HIGH', 2, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Comprar presente', 'Comprar presente de aniversário', true, CURRENT_TIMESTAMP, DATE '2026-07-19', 'MEDIUM', 1, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Organizar quarto', 'Arrumar armário e gavetas', false, CURRENT_TIMESTAMP, DATE '2026-07-28', 'LOW', 2, 3);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Resolver pendências', 'Finalizar tarefas pessoais atrasadas', false, CURRENT_TIMESTAMP, DATE '2026-08-05', 'HIGH', 1, 3);


-- LAZER (9 tarefas) categoria_id = 4

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Treino de academia', 'Treinar musculação', true, CURRENT_TIMESTAMP, DATE '2026-07-16', 'HIGH', 1, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Assistir filme', 'Assistir filme no final de semana', false, CURRENT_TIMESTAMP, DATE '2026-07-26', 'LOW', 2, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Jogar videogame', 'Jogar algumas horas', true, CURRENT_TIMESTAMP, DATE '2026-07-23', 'LOW', 1, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Passear com amigos', 'Encontrar amigos no sábado', false, CURRENT_TIMESTAMP, DATE '2026-08-02', 'MEDIUM', 2, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Andar de moto', 'Fazer passeio de moto', false, CURRENT_TIMESTAMP, DATE '2026-08-10', 'MEDIUM', 1, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Ler livro', 'Ler capítulo novo do livro', true, CURRENT_TIMESTAMP, DATE '2026-07-31', 'LOW', 2, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Fazer churrasco', 'Preparar churrasco com família', false, CURRENT_TIMESTAMP, DATE '2026-08-15', 'HIGH', 1, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Viajar', 'Planejar viagem de férias', false, CURRENT_TIMESTAMP, DATE '2026-09-01', 'MEDIUM', 2, 4);

INSERT INTO tb_task
(title, description, done, created_at, due_date, priority, user_id, category_id)
VALUES
    ('Correr no parque', 'Fazer corrida ao ar livre', true, CURRENT_TIMESTAMP, DATE '2026-07-29', 'LOW', 1, 4);

