/*
 * @title com.enn.greatframework.authorize.config.RedisConfig.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年3月15日 下午4:06:09
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月15日 下午4:06:09
 *
 */
@Configuration
@ImportResource(locations = { "classpath:config/spring-cache-redis.xml" })
public class RedisConfig {
}
