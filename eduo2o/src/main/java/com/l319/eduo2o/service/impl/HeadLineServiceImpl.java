package com.l319.eduo2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.l319.eduo2o.cache.JedisUtil;
import com.l319.eduo2o.exception.HeadLineOperationException;
import com.l319.eduo2o.mapper.HeadLineMapper;
import com.l319.eduo2o.pojo.HeadLine;
import com.l319.eduo2o.service.HeadLineService;

@Service
@Transactional
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineMapper headlinemapper;
	@Autowired
	private JedisUtil.Keys jedisKey;
	@Autowired
	private JedisUtil.Strings jedisStrings;

	private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		String key = HLLISTKEY;
		List<HeadLine> headLineList = null;
		ObjectMapper objectMapper = new ObjectMapper();
		if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
			key = key + "_" + headLineCondition.getEnableStatus();
		}
		if (!jedisKey.exists(key)) {
			headLineList = headlinemapper.queryHeadLineList(headLineCondition);
			String jsonString;
			try {
				jsonString = objectMapper.writeValueAsString(headLineList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			// 若存在，则直接从redis里面取出相应数据
			String jsonString = jedisStrings.get(key);
			// 指定要将string转换成的集合类型
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
			try {
				// 将相关key对应的value里的的string转换成对象的实体类集合
				headLineList = objectMapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			}
		}
		return headLineList;
	}

}
