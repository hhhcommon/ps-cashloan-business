package com.adpanshi.cashloan.business.core.common.cache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * redis 配置信息
 * @author zhubingbing
 *
 */
public class RedisConfig extends PropertyPlaceholderConfigurer{

	private static Map<String, Object> ctxPropertiesMap; 
	 
    @Override 
    protected void processProperties( 
            ConfigurableListableBeanFactory beanFactoryToProcess, 
            Properties props) throws BeansException { 
        super.processProperties(beanFactoryToProcess, props); 
        ctxPropertiesMap = new HashMap<String, Object>(); 
        for (Object key : props.keySet()) { 
            String keyStr = key.toString(); 
            String value = props.getProperty(keyStr); 
            ctxPropertiesMap.put(keyStr, value); 
        } 
    } 
 
    public static Object getContextProperty(String name) { 
        return ctxPropertiesMap.get(name); 
    } 
	
}
