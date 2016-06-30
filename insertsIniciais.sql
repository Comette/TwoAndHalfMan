INSERT INTO USUARIO(ID_USUARIO, DS_EMAIL,DS_SENHA,DS_SITUACAO,DS_USER_NAME, NM_USUARIO, TP_PERMISSAO) VALUES (SEQ_USUARIO.nextVal ,'gerente@gerente.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ATIVO', 'GERENTE','Gerente gerente','GERENTE');
INSERT INTO USUARIO(ID_USUARIO, DS_EMAIL,DS_SENHA,DS_SITUACAO,DS_USER_NAME, NM_USUARIO, TP_PERMISSAO) VALUES (SEQ_USUARIO.nextVal ,'admin@admin.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ATIVO', 'ADMIN','Admin admin','ADMINISTRADOR');
INSERT INTO USUARIO(ID_USUARIO, DS_EMAIL,DS_SENHA,DS_SITUACAO,DS_USER_NAME, NM_USUARIO, TP_PERMISSAO) VALUES (SEQ_USUARIO.nextVal ,'gerente1@gerente.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ATIVO', 'GERENTE1','Gerente1 gerente1','GERENTE');

INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO ,DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'MENSAL', 'CNY', 'ATIVO','teste.com.br', 'Github', 265.35, 40, 3);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO ,DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'MENSAL', 'JPY', 'ATIVO','teste.com.br', 'Office', 1030.50, 10, 3);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO ,DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'MENSAL', 'CNY', 'CANCELADO','teste.com.br', 'Github', 265.35, 40, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO ,DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'MENSAL', 'BRL', 'INATIVO','teste.com.br', 'DellSuporte', 64.74, 20, 3);

INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'ANUAL', 'JPY','ATIVO' ,'ajuda.com.br', 'Slack', 3087.52, 2.5, 3);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'ANUAL', 'JPY','ATIVO' ,'ajuda.com.br', 'Slack', 3087.52, 2.5, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'ANUAL', 'CHF','CANCELADO' ,'ajuda.com.br', 'TypeForm', 48.98, 4.17, 3);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'ANUAL', 'GBP','INATIVO' ,'ajuda.com.br', 'SQLServer', 66.90, 7.5, 3);

INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'SEMESTRAL', 'EUR',  'ATIVO' ,'seila.com.br', 'Kanban', 89.90, 16.7, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'SEMESTRAL', 'AUD',  'ATIVO' ,'seila.com.br', 'BitBucket', 107.50, 13.4, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'SEMESTRAL', 'CAD',  'CANCELADO' ,'seila.com.br', 'Winrar', 90.62, 11.6, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'SEMESTRAL', 'BRL',  'INATIVO' ,'seila.com.br', 'AVG', 194.80, 10, 1);

INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'TRIMESTRAL', 'CNY',  'ATIVO' ,'biroska.com.br', 'Avast', 298.63, 15, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'TRIMESTRAL', 'JPY',  'ATIVO' ,'biroska.com.br', 'SublimeText', 3601.51, 11.7, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'TRIMESTRAL', 'BRL',  'CANCELADO' ,'biroska.com.br', 'Komodo', 80.92, 8.4, 1);
INSERT INTO SERVICO(ID_SERVICO, DS_DESCRICAO, DS_PERIODICIDADE, DS_SIMBOLO_MOEDA,DS_SITUACAO , DS_WEBSITE, NM_SERVICO, VL_TOTAL_SERVICO, VL_MENSAL_USD, USUARIO_ID_USUARIO) VALUES(SEQ_SERVICO.nextVal, 'Testando banco de dados', 'TRIMESTRAL', 'JPY',  'INATIVO' ,'biroska.com.br', 'VisualStudio', 1543.55, 5, 1);

COMMIT;