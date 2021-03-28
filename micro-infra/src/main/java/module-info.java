module CatCafe.micro.infra.main {
  requires spring.context;
  requires r2dbc.spi;
  requires spring.boot.autoconfigure;
  requires spring.data.r2dbc;
  requires spring.data.redis;
  requires spring.beans;
  requires lombok;
  requires reactor.core;
  requires spring.data.commons;
  requires spring.data.relational;
  requires com.fasterxml.jackson.annotation;
  requires transitive CatCafe.micro.domain.main;
  exports mabubu0203.com.github.catcafe.infra;
  exports mabubu0203.com.github.catcafe.infra.config;
}