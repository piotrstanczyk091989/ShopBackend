--liquibase formatted sql
--changeset pstanczyk:2
alter table product add image varchar(128) after currency;