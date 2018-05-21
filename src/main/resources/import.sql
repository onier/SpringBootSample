/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  MyPC
 * Created: 2018-5-20
 */

--
--    Copyright 2015-2017 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

-- create table if not exists city;
-- 
-- create table user (bigint int primary key auto_increment, user_name varchar, password varchar);
-- 
-- create table if not exists org;

create table org(
 id bigint not null AUTO_INCREMENT,
name varchar(1024),
description varchar(1024),
depth int ,
parent_id bigint,
org_path varchar(1024),
primary key(id));

 