create database if not exists epidemic;
use epidemic;
drop table if exists province;
drop table if exists country;

/*==============================================================*/
/* Table: country                                               */
/*==============================================================*/
create table country
(
   country_abbreviation varchar(5) not null,
   iso                  int ,
   country_name         varchar(20) not null,
   population           int ,
   confirmed            int ,
   recovered            int ,
   deaths               int ,
   sq_km_area           double ,
   life_expectancy      double ,
   elevation_in_meters  double ,
   continent            varchar(20) ,
   location             varchar(20) ,
   capital_city         varchar(20) ,
   primary key (country_abbreviation)
);
/*==============================================================*/
/* Table: province                                                  */
/*==============================================================*/
create table province
(
   id                   int not null auto_increment,
   province_name        varchar(50) not null,
   confirmed            int ,
   recovered            int ,
   deaths               int ,
   latitude             double ,
   longitude            double ,
   updated_time         varchar(50) ,
   country_abbreviation varchar(5),
   primary key (id)
);

alter table province add constraint FK_Country_province foreign key (country_abbreviation)
      references country (country_abbreviation) on delete cascade on update cascade;







