create table fornecedores (
    id bigint not null auto_increment primary key,
    nomefornecedores varchar(200),
    telefonefornecedores varchar(20)
);

create table contaspagar (
    id bigint not null auto_increment primary key,
    descricaocontas varchar(255),
    valorcontas decimal(12,2),
    fornecedores_id bigint not null
);

alter table contaspagar add constraint fk_contaspagar_fornecedores
foreign key (fornecedores_id) references fornecedores(id);
