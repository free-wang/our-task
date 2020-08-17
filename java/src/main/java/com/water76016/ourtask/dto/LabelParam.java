package com.water76016.ourtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelParam {
    /**
     * 标签id
     * */
    Integer id;
    /**
     * 标签名称
     * */
    String name;
    /**
     * 所含清单总数
     * */
    Integer count;

}
