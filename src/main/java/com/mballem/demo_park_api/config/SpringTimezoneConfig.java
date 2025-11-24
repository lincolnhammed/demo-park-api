package com.mballem.demo_park_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

//para que a classe seja uma clase de configura
@Configuration
public class SpringTimezoneConfig {
    //apos a classe ser inicializada pelo spring o metodo construtor dela é executado e ai sim o primeiro metodo a ser executado é o timezoneconfig
    @PostConstruct// assinatura do metodo
    public void timezoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Lisbon"));
    }
}
