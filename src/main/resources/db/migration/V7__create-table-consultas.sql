create table consultas(

    id bigint not null auto_increment,
    medico_id bigint,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consulta_medico foreign key(medico_id) references medicos(id),
    constraint fk_consulta_paciente foreign key(paciente_id) references pacientes(id)

);