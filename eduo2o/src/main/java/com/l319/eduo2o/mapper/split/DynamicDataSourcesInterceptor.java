package com.l319.eduo2o.mapper.split;

import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class DynamicDataSourcesInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourcesInterceptor.class);
	private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		/**
		 * 当前是否为事务
		 */
		boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
		Object[] objects = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) objects[0];
		String lookUpKey = DynamicDataSourceHolder.DB_MASTER;
		if (synchronizationActive != true) {
			if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
				// selectKey为自增id查询主键
				if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
					lookUpKey = DynamicDataSourceHolder.DB_MASTER;
				} else {
					BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
					String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
					if (sql.matches(REGEX)) {
						lookUpKey = DynamicDataSourceHolder.DB_MASTER;
					} else {
						lookUpKey = DynamicDataSourceHolder.DB_SLAVE;
					}
				}
			}
		} else {
			lookUpKey = DynamicDataSourceHolder.DB_MASTER;
		}
		logger.debug("设置方法[{}]use[{}]Strategy,SqlCommanType[{}]..", mappedStatement.getId(), lookUpKey,
				mappedStatement.getSqlCommandType().name());
		DynamicDataSourceHolder.setDbType(lookUpKey);
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
