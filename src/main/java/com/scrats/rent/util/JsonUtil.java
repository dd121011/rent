package com.scrats.rent.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Timestamp;
import java.util.List;

/**
 * FastJson的相关方法
 *
 * @author XWK
 */
public class JsonUtil {
	private static SerializeConfig config;
	static {
		config = new SerializeConfig();
		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd hh:mm:ss";
		SimpleDateFormatSerializer df = new SimpleDateFormatSerializer(
				"yyyy-MM-dd hh:mm:ss");
		config.put(java.util.Date.class, df);
		config.put(java.sql.Date.class, df);
		config.put(Timestamp.class, df);
	}

	private static final SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue,// 输出空置字段
			SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};

	/***
	 * 序列化数据
	 *
	 * @param object
	 * @return
	 */
	public static String toJSON(Object object) {
		return JSON.toJSONString(object, config, features);
	}

	/***
	 * 反序列化数据
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	/***
	 * 反序列化数据
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseArray(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
}
