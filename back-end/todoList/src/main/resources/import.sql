INSERT  INTO  tb_user (name, email, password) VALUES ('Guilherme', 'guilherme@gmail.com', '123456');
INSERT  INTO  tb_user (name, email, password) VALUES ('Maria', 'maria@gmail.com', '123456');

INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id) VALUES ('Dever de casa','Fazer a janta',false,DATE '2026-07-09',DATE '2026-07-15','HIGH', 1);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id) VALUES ('Trabalho','Fazer entregas',false,DATE '2026-07-09',DATE '2026-07-15','LOW', 1);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id) VALUES ('Estudos','Estudar Spring',true,DATE '2026-07-09',DATE '2028-09-15','HIGH', 2);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id) VALUES ('Lazer','Ir na Academia',true,DATE '2026-07-09',DATE '2026-07-27','MEDIUM', 2);

