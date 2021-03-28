module CatCafe.micro.domain.main {
  requires lombok;
  requires reactor.core;
  requires spring.context;
  requires transitive CatCafe.micro.common.main;
  exports mabubu0203.com.github.catcafe.domain;
  exports mabubu0203.com.github.catcafe.domain.entity.authentication;
  exports mabubu0203.com.github.catcafe.domain.entity.cast;
  exports mabubu0203.com.github.catcafe.domain.entity.notice;
  exports mabubu0203.com.github.catcafe.domain.entity.store;
  exports mabubu0203.com.github.catcafe.domain.repository.authentication;
  exports mabubu0203.com.github.catcafe.domain.repository.cast;
  exports mabubu0203.com.github.catcafe.domain.repository.notice;
  exports mabubu0203.com.github.catcafe.domain.repository.store;
  exports mabubu0203.com.github.catcafe.domain.value;
}