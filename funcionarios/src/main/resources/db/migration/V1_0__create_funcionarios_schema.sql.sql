create sequence hibernate_sequence start 1 increment 1;
create table funcionario
(
    id              int8        not null,
    cpf             varchar(11) not null,
    data_nascimento timestamp,
    endereco        varchar(150),
    nome            varchar(150),
    salario         numeric(14, 2),
    telefone        varchar(14),
    primary key (id)
);
alter table funcionario
    add constraint UK_rxosr8731eb3gbnlbt2mqfan8 unique (cpf);
