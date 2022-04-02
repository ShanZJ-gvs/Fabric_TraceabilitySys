package com.gvssimux.pojo.fabquery;



import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TeaAreaQueryResult<T> {

    private String key;

    private T record;

}
