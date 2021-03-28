module CatCafe.micro.common.main {
  requires static lombok;
  requires org.reactivestreams;
  requires reactor.core;
  requires spring.data.commons;
  requires spring.context;
  requires spring.data.r2dbc;
  requires spring.data.relational;
  requires spring.data.redis;
  requires spring.tx;
  exports mabubu0203.com.github.catcafe.common.config;
  exports mabubu0203.com.github.catcafe.common.controller.mapper.request;
  exports mabubu0203.com.github.catcafe.common.controller.mapper.response;
  exports mabubu0203.com.github.catcafe.common.entity;
  exports mabubu0203.com.github.catcafe.common.service;
  exports mabubu0203.com.github.catcafe.common.source.r2dbc;
  exports mabubu0203.com.github.catcafe.common.source.redis;
  exports mabubu0203.com.github.catcafe.common.source.r2dbc.dto;
  exports mabubu0203.com.github.catcafe.common.source.redis.entity;
  exports mabubu0203.com.github.catcafe.common.service.model;
  exports mabubu0203.com.github.catcafe.common.exception;
}
