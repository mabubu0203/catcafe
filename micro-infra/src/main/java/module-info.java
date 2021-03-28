module CatCafe.micro.infra.main {
  requires static lombok;
  requires static reactor.core;
  requires r2dbc.spi;
  requires spring.context;
  requires spring.boot.autoconfigure;
  requires spring.data.r2dbc;
  requires spring.data.redis;
  requires spring.beans;
  requires spring.data.commons;
  requires spring.data.relational;
  requires com.fasterxml.jackson.annotation;
  requires transitive CatCafe.micro.domain.main;
  exports mabubu0203.com.github.catcafe.infra;
  exports mabubu0203.com.github.catcafe.infra.config;
}