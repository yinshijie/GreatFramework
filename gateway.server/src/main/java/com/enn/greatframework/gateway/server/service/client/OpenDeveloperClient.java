/*
 * @title com.enn.greatframework.gateway.server.service.client.OpenDeveloperClient.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 下午4:27:32
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.gateway.server.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开发服务客户端
 * @ClassName OpenDeveloperClient
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 下午4:27:32
 *
 */
@FeignClient("AuthorizeService")
public interface OpenDeveloperClient {
	@RequestMapping(value = "/open/getDeveloperInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getDeveloperInfo(@RequestParam("developerId") String developerId);

	@RequestMapping(value = "/open/getApplicationInfoByToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getApplicationInfoByToken(@RequestParam("appToken") String appToken);
}
