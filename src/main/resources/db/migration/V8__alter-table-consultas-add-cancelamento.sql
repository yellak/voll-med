alter table consultas add cancelada tinyint not null default 0;
alter table consultas add motivo_cancelamento varchar(500);