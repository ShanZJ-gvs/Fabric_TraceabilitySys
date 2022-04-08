package com.gvssimux.pojo.fabquery;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class QueryResult {
    private String key;

    private String json;
}
