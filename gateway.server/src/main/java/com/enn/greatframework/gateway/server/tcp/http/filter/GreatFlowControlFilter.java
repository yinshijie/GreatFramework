/*
 * @title com.enn.greatframework.gateway.server.tcp.http.filter.GreatFlowControlFilter.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年5月5日 下午2:59:43
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.gateway.server.tcp.http.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.date.DateTimeFormator;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.utils.HttpRequestUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 流量控制
 * @ClassName GreatFlowControlFilter
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年5月5日 下午2:59:43
 *
 */
@Service("greatFlowControlFilter")
public class GreatFlowControlFilter extends ZuulFilter {

	private static final Logger LOGGER = Logger.getLogger(GreatFlowControlFilter.class);

	private static Map<String, HashMap<String, FlowControlCounter>> apptokenFlowControllor = new HashMap<String, HashMap<String, FlowControlCounter>>();

	private static Map<String, HashMap<String, FlowControlCounter>> ipFlowControllor = new HashMap<String, HashMap<String, FlowControlCounter>>();

	/*
	 * @Title: shouldFilter
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * @Title: run
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		ResponseContent responseBody = null;
		RequestContext ctx = RequestContext.getCurrentContext();

		try {
			/** 解决输入流只能读取一次的问题 */
			HttpServletRequest request = ctx.getRequest();
			/** 获取传入参数 */
			String requestUri = request.getRequestURI();
			String ip = HttpRequestUtils.getRequestIp(request);
			String appToken = request.getParameter(GreatFrameworkConst.REQUEST_PARAM_APPTOKEN);

			if (!flowControl(requestUri, appToken, ip)) {
				responseBody = ResponseContent.REQUEST_MULTIFARIOUS_ERROR();
				ctx.setSendZuulResponse(Boolean.FALSE);
				ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value()); // 请求过于频繁
				ctx.setResponseBody(responseBody.toString());
				LOGGER.warn("流量控制检查失败！请求过于频繁！");
			}
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			ctx.setSendZuulResponse(Boolean.FALSE);
			ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // 平台内部错误
			ctx.setResponseBody(responseBody.toString());
			LOGGER.warn("平台内部错误!");
		}
		return null;
	}

	/*
	 * @Title: filterType
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/*
	 * @Title: filterOrder
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 102;
	}

	/**
	 * 流量控制
	 * @Description  TODO
	 * @Call com.enn.greatframework.gateway.server.tcp.http.filter.GreatFlowControlFilter.flowControl(...)
	 *
	 * @param requestUri
	 * @param appToken
	 * @param ip
	 * @return
	 */
	private boolean flowControl(String requestUri, String appToken, String ip) {
		long timestamp = System.currentTimeMillis();
		long nowMin = timestamp / 1000 / 60;
		long nowHour = nowMin / 60;
		long nowDay = nowHour / 24;

		return Boolean.FALSE;
	}

	public static void main1(String[] args) {
		long timestamp = System.currentTimeMillis();
		long nowMin = timestamp / 1000 / 60;
		long nowHour = nowMin / 60;
		long nowDay = nowHour / 24;

		System.out
		        .println(DateTimeFormator.jFormatDate(new Date(timestamp), DateTimeFormator.CURRENT_TIMESTAMP_FORMAT));
		System.out.println(
		        DateTimeFormator.jFormatDate(new Date(nowMin * 60 * 1000), DateTimeFormator.CURRENT_TIMESTAMP_FORMAT));
		System.out.println(DateTimeFormator.jFormatDate(new Date(nowHour), DateTimeFormator.CURRENT_TIMESTAMP_FORMAT));
		System.out.println(DateTimeFormator.jFormatDate(new Date(nowDay), DateTimeFormator.CURRENT_TIMESTAMP_FORMAT));
		System.out.println(Integer.MAX_VALUE);
	}
}

/**
 * 流量控制计数器
 * @ClassName FlowControlCounter
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年5月5日 下午3:17:46
 *
 */
class FlowControlCounter {

	private String flag;
	private AtomicInteger count;

	/**
	 * @return the flag
	 * @since JDK1.5
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 * @since JDK1.5
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the count
	 * @since JDK1.5
	 */
	public AtomicInteger getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 * @since JDK1.5
	 */
	public void setCount(AtomicInteger count) {
		this.count = count;
	}
}
