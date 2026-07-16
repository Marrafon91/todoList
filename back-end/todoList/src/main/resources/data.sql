INSERT INTO tb_user (name, email, password) VALUES ('Guilherme', 'guilherme@gmail.com', '123456');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'maria@gmail.com', '123456');

INSERT INTO tb_category (name, color) VALUES ('Trabalho', '#3B82F6');
INSERT INTO tb_category (name, color) VALUES ('Estudos', '#22C55E');
INSERT INTO tb_category (name, color) VALUES ('Pessoal', '#F59E0B');
INSERT INTO tb_category (name, color) VALUES ('Casa', '#EF4444');

INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id, category_id) VALUES ('Dever de casa','Fazer a janta',false, CURRENT_TIMESTAMP,DATE '2026-07-15','HIGH', 1, 4);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id, category_id) VALUES ('Trabalho','Fazer entregas',false, CURRENT_TIMESTAMP,DATE '2026-07-15','LOW', 1,1);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id, category_id) VALUES ('Estudos','Estudar Spring',true,CURRENT_TIMESTAMP,DATE '2028-09-15','HIGH', 2, 2);
INSERT INTO tb_task (title, description, done, created_at, due_date, priority, user_id, category_id) VALUES ('Lazer','Ir na Academia',true,CURRENT_TIMESTAMP,DATE '2026-07-27','MEDIUM', 2, 3);

