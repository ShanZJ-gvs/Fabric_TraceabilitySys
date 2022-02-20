package com.gvssimux.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtil {
    //简单的getJson
    public static String getJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static String getJson(Object obj,String dataFormat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //自定义日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        mapper.setDateFormat(simpleDateFormat);
        return  mapper.writeValueAsString(obj);
    }


    /**
     * 对集合进行转json
     * */
    public static String getJson(List<Object> objList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(objList);
        return json;
    }

}
