package com.spma.licensingservice.config;

import com.spma.licensingservice.repository.OrganizationRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfiguration {

    @Autowired
    private ServiceConfig serviceConfig;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        String hostname = serviceConfig.getRedisServer();
        int port = Integer.parseInt(serviceConfig.getRedisPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        //redisStandaloneConfiguration.setPassword(RedisPassword.of("yourRedisPasswordIfAny"));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        String hostname = serviceConfig.getRedisServer();
//        int port = Integer.parseInt(serviceConfig.getRedisPort());
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConfiguration redis) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
//        template.setConnectionFactory(redis.redisConnectionFactory());
//        template.setKeySerializer(stringSerializer);
//        template.setHashKeySerializer(stringSerializer);
//        template.setValueSerializer(jdkSerializationRedisSerializer);
//        template.setHashValueSerializer(jdkSerializationRedisSerializer);
//        template.setEnableTransactionSupport(true);
//        template.afterPropertiesSet();
//        return template;
//    }

}
