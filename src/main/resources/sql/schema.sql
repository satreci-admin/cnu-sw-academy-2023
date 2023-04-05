create table members (
                         id BINARY(16) not null,
                         primary key (id)
)

create table work_specification (
                                    id BINARY(16) not null,
                                    memo varchar(255),
                                    name varchar(20) not null,
                                    schedule varchar(255) not null,
                                    member_id BINARY(16),
                                    robot_id BINARY(16),
                                    primary key (id),
                                    foreign key (member_id) references members(id)
)

create table robots (
                        id BINARY(16) not null,
                        ip varchar(255),
                        memo varchar(255),
                        name varchar(255),
                        port integer,
                        ssh_id varchar(255),
                        ssh_pw varchar(255),
                        work_spec_id BINARY(16),
                        primary key (id),
                        foreign key (work_spec_id) references work_specification(id)
)


create table tasks (
                       task_type varchar(31) not null,
                       id BINARY(16) not null,
                       work_spec_id BINARY(16),
                       primary key (id),
                       foreign key (work_spec_id) references work_specification(id)
)
