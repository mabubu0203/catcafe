module CatCafe.micro.api.main {
  requires static lombok;
  requires java.annotation;
  requires java.validation;
  requires reactor.core;
  requires spring.core;
  requires spring.web;
  requires spring.context;
  requires spring.tx;
  requires spring.beans;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.security.config;
  requires spring.security.core;
  requires spring.security.web;
  requires io.swagger.v3.oas.models;
  requires io.swagger.v3.oas.annotations;
  requires org.springdoc.openapi.common;
  requires org.hibernate.validator;
  requires com.fasterxml.jackson.annotation;
  requires transitive CatCafe.micro.infra.main;
  exports mabubu0203.com.github.catcafe.api;
}