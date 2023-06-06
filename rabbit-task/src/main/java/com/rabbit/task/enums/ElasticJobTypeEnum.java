package com.rabbit.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/6 22:48
 */
@Getter
@AllArgsConstructor
public enum  ElasticJobTypeEnum {

    SIMPLE("SimpleJob", "简单类型job"),
    DATAFLOW("DataFlowJob", "流式类型job"),
    SCRIPT("ScriptJob", "简单类型job");

    private final String type;

    private final String desc;

}
