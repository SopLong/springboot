package com.example.backdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 转换解析器
 */
@Configuration
public class MappingConverterAdapter {
    /***
     * 日期参数接收转换器，将json字符串转为日期类型
     * @return
     */
    @Bean
    public Converter<String, Date> DateConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}
