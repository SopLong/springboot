package com.example.backdemo.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 将默认的json处理改为fastjson
 */
@Configuration
public class JsonConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();

        //2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, //是否输出值为null的字段,默认为false
                SerializerFeature.WriteNullNumberAsZero,//数值字段如果为null,输出为0,而非null
                SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullBooleanAsFalse,//字段如果为null,输出为false,而非null
                SerializerFeature.DisableCircularReferenceDetect //
        );
        // 格式化所有日期格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //3. 在converter中添加配置信息
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
}
