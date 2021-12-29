module CatCafe.micro.api.main {
  requires static lombok;
  requires static reactor.core;
  requires com.fasterxml.jackson.annotation;
  requires io.swagger.v3.oas.models;
  requires io.swagger.v3.oas.annotations;
  requires java.annotation;
  requires java.validation;
  requires org.springdoc.openapi.common;
  requires org.hibernate.validator;
  requires spring.beans;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.core;
  requires spring.security.config;
  requires spring.security.core;
  requires spring.security.crypto;
  requires spring.security.web;
  requires spring.tx;
  requires spring.web;
  requires spring.webflux;
  requires CatCafe.micro.infra.main;
  exports org.openapitools.api;
  exports org.openapitools.model;
  opens mabubu0203.com.github.catcafe.api;
  opens mabubu0203.com.github.catcafe.api.config;
  opens mabubu0203.com.github.catcafe.api.components.security;
}