# --- !Ups

create table "people" (
  "id" bigserial primary key,
  "name" varchar not null,
  "age" int not null,
  "last_update" timestamp not null
);

# --- !Downs

drop table if exists "people";
