drop TABLE IF EXISTS t_tenant_data_source;
create table t_tenant_data_source (
    tenant_id varchar(255) not null,
    db_name varchar(255),
    driver_class_name varchar(255),
    password varchar(255),
    port varchar(255),
    schema varchar(255),
    server_name varchar(255),
    username varchar(255),
    url varchar(255),
    primary key (tenant_id)
);
--
