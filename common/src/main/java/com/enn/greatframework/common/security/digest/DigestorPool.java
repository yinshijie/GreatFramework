/*
 * @title com.enn.greatframework.common.security.digest.DigestorPool.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年10月14日 下午2:21:47
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.enn.greatframework.common.GreatFrameworkException;

/**
 * @ClassName DigestorPool
 * @Description TODO
 * @author yinshijie
 * @date 2016年10月14日 下午2:21:47
 *
 */
public class DigestorPool {

	private static final Logger LOGGER = Logger.getLogger(DigestorPool.class);

	/**
	 * 消息签名工具
	 */
	private Digestor digestor;
	/**
	 * 连接池活动状态
	 */
	private boolean isActive = false;
	/**
	 * 记录创建的总数
	 */
	private int countAll = 0;

	/**
	 * 空闲集合
	 */
	private List<MessageDigest> freeList = new Vector<MessageDigest>();
	/**
	 * 活动集合
	 */
	private List<MessageDigest> activeList = new Vector<MessageDigest>();
	/**
	 * 将线程和连接绑定，保证事务能统一执行
	 */
	private static ThreadLocal<MessageDigest> threadLocal = new ThreadLocal<MessageDigest>();

	/**
	 * 创建一个实例 DigestorPool.
	 *
	 * @Description: TODO
	 * @param messageDigestor
	 */
	public DigestorPool(Digestor digestor) {
		this.digestor = digestor;
		init();
		cheackPool();
	}

	/**
	 * 初始化方法
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.init(...)
	 *
	 * @return
	 */
	private boolean init() {
		try {
			for (int i = 0; i < digestor.getInitialSize(); i++) {
				MessageDigest messageDigest = creatNewBean();
				if (messageDigest != null) {
					freeList.add(messageDigest);
					// contActive++;
				}
			}
			this.isActive = Boolean.TRUE;
		} catch (GreatFrameworkException e) {
			LOGGER.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 创建新bean方法
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.creatNewBean(...)
	 *
	 * @return
	 * @throws JedidiahException
	 */
	private synchronized MessageDigest creatNewBean() throws GreatFrameworkException {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(digestor.getAlgorithmName());
		} catch (NoSuchAlgorithmException e) {
			throw new GreatFrameworkException("创建新信息签名工具失败！");
		}
		countAll++;
		return messageDigest;
	}

	/**
	 * 获取资源
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.getMessageDigest(...)
	 *
	 * @return
	 */
	public synchronized MessageDigest getMessageDigest() {
		MessageDigest messageDigest = null;
		try {
			if (activeList.size() < digestor.getMaxActive()) { // 判断是否超过最大激活数
				if (freeList.size() > 0) { // 当空闲池有资源时从空闲池获取
					messageDigest = freeList.get(0);
					if (messageDigest != null) {
						threadLocal.set(messageDigest);
					}
					freeList.remove(0);
				} else {
					messageDigest = creatNewBean(); // 空闲池没有新建资源
				}
				if (isValid(messageDigest)) { // 检查资源
					activeList.add(messageDigest);
				}
			} else {
				wait(digestor.getMaxWait()); // 超过最大激活数--线程等待MaxWait时长后重新获取资源
				messageDigest = getMessageDigest();
			}
		} catch (GreatFrameworkException | InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return messageDigest;
	}

	/**
	 * 检查资源可用
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.isValid(...)
	 *
	 * @param messageDigest
	 * @return
	 */
	private boolean isValid(MessageDigest messageDigest) {
		if (messageDigest != null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 释放资源
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.releaseConn(...)
	 *
	 * @param conn
	 * @throws JedidiahException
	 */
	public synchronized void returnDigest(MessageDigest messageDigest) throws GreatFrameworkException {
		if (isValid(messageDigest) && (freeList.size() < digestor.getMaxIdle())) {
			freeList.add(messageDigest);
			activeList.remove(messageDigest);
			threadLocal.remove();
			// 唤醒所有正待等待的线程，去抢连接
			notifyAll();
		} else if (isValid(messageDigest) && (freeList.size() >= digestor.getMaxIdle())) {
			activeList.remove(messageDigest);
			messageDigest = null;
			countAll--;
		} else {
			throw new GreatFrameworkException("Digest pool unkown exception!");
		}
	}

	/**
	 * 销毁资源池
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.destroy(...)
	 *
	 */
	public synchronized void destroy() {
		for (MessageDigest messageDigest : freeList) {
			if (isValid(messageDigest)) {
				messageDigest = null;
			}
		}
		for (MessageDigest messageDigest : activeList) {
			if (isValid(messageDigest)) {
				messageDigest = null;
			}
		}
		isActive = false;
	}

	/**
	 * 检查资源池状态
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.isActive(...)
	 *
	 * @return
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * 定时检查资源池状态
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorPool.cheackPool(...) long delay, long period
	 */
	public void cheackPool() {
		if (digestor.isCheakPool()) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					/**
					 * 1.对线程里面的连接状态
					 * 2.连接池最小 最大连接数
					 * 3.其他状态进行检查，因为这里还需要写几个线程管理的类，暂时就不添加了
					 */
					LOGGER.trace("[" + digestor.getAlgorithmName() + "]空闲资源数：" + freeList.size());
					LOGGER.trace("[" + digestor.getAlgorithmName() + "]使用资源数：" + activeList.size());
					LOGGER.trace("[" + digestor.getAlgorithmName() + "]有效资源数：" + countAll);
				}
			}, digestor.getCheckDelayTime(), digestor.getCheckPeriodTime());
		}
	}
}
