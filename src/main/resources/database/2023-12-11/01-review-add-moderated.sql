--liquibase formatted sql
--changeset pstanczyk:9
alter table review add moderated boolean default false;