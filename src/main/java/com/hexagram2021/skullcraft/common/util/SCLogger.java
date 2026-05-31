package com.hexagram2021.skullcraft.common.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 模组日志工具类，提供统一的日志输出接口喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("unused")
public final class SCLogger {
	/** Log4j 日志记录器喵~ */
	public static final Logger logger = LogManager.getLogger(MODID);

	/**
	 * 以指定日志级别输出消息喵~
	 *
	 * @param logLevel 日志级别喵~
	 * @param object   消息对象喵~
	 */
	public static void log(Level logLevel, Object object) {
		logger.log(logLevel, String.valueOf(object));
	}

	/**
	 * 输出错误级别日志喵~
	 *
	 * @param object 消息对象喵~
	 */
	public static void error(Object object) {
		log(Level.ERROR, object);
	}

	/**
	 * 输出信息级别日志喵~
	 *
	 * @param object 消息对象喵~
	 */
	public static void info(Object object) {
		log(Level.INFO, object);
	}

	/**
	 * 输出警告级别日志喵~
	 *
	 * @param object 消息对象喵~
	 */
	public static void warn(Object object) {
		log(Level.WARN, object);
	}

	/**
	 * 输出带格式化参数的错误级别日志喵~
	 *
	 * @param message 格式化消息喵~
	 * @param params  格式化参数喵~
	 */
	public static void error(String message, Object... params) {
		logger.log(Level.ERROR, message, params);
	}

	/**
	 * 输出带格式化参数的信息级别日志喵~
	 *
	 * @param message 格式化消息喵~
	 * @param params  格式化参数喵~
	 */
	public static void info(String message, Object... params) {
		logger.log(Level.INFO, message, params);
	}

	/**
	 * 输出带格式化参数的警告级别日志喵~
	 *
	 * @param message 格式化消息喵~
	 * @param params  格式化参数喵~
	 */
	public static void warn(String message, Object... params) {
		logger.log(Level.WARN, message, params);
	}

	/**
	 * 输出带异常的错误级别日志喵~
	 *
	 * @param message 消息喵~
	 * @param e       异常喵~
	 */
	public static void error(String message, Throwable e) {
		logger.log(Level.ERROR, message, e);
	}

	/**
	 * 输出带异常的信息级别日志喵~
	 *
	 * @param message 消息喵~
	 * @param e       异常喵~
	 */
	public static void info(String message, Throwable e) {
		logger.log(Level.INFO, message, e);
	}

	/**
	 * 输出带异常的警告级别日志喵~
	 *
	 * @param message 消息喵~
	 * @param e       异常喵~
	 */
	public static void warn(String message, Throwable e) {
		logger.log(Level.WARN, message, e);
	}

	/**
	 * 输出调试日志，仅在调试模式下生效喵~
	 *
	 * @param object 消息对象喵~
	 */
	public static void debug(Object object) {
		log(Level.DEBUG, object);
	}

	/**
	 * 输出带格式化参数的调试日志，仅在调试模式下生效喵~
	 *
	 * @param message 格式化消息喵~
	 * @param params  格式化参数喵~
	 */
	public static void debug(String message, Object... params) {
		logger.log(Level.DEBUG, message, params);
	}

	private SCLogger() {
	}
}
