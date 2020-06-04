package com.example.demo2.enums;

/**
 * 日志操作类型
 * @Author wmy
 * @Date 2020/6/4 16:15
 * @Version 1.0
 */
public enum OperationType {
    /**
     * 插入
     */
    PAGE_INSERT("PAGE_INSERT","插入"),
    /**
     * 修改
     */
    PAGE_UPDATE("PAGE_UPDATE","修改"),
    /**
     * 删除
     */
    PAGE_DELETE("PAGE_DELETE","删除"),
    /**
     * 读取
     */
    PAGE_READ("PAGE_READ","读取"),

    /**
     * 接口调用
     */
    ITERFACE_CALL("ITERFACE_CALL","接口调用"),
    /**
     * 批处理操作
     */
    BATCH_HANDLE("BATCH_HANDLE","批处理操作"),
    NONE("","无类型");

    private String type;
    private String desc;

    OperationType(String type,String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
