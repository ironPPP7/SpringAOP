package com.example.demo2.annotation;

import java.lang.annotation.*;

/**
 * 日志注解用于controller标识日志
 * @Author wmy
 * @Date 2020/6/4 14:36
 * @Version 1.0
 */
@Documented//注解是否将包含在 JavaDoc 中
@Retention(RetentionPolicy.RUNTIME)// 什么时候使用该注解，我们定义为运行时；
@Target(ElementType.METHOD)//用于什么地方，我们定义为作用于方法上；
@Inherited
public @interface DemoAnnotation {
    /**
     * 操作描述
     *
     * @return
     */
    String description() default "";

    /**
     * 任务名称
     *
     * @return
     */
    String jobName() default "";

    /**
     * 任务名称
     *
     * @return
     */
    String triggerName() default "";

    /**
     * 忽略参数记录
     *
     * @return
     */
    boolean ignoreParam() default false;

    /**
     * 忽略结果记录
     *
     * @return
     */
    boolean ignoreResult() default true;

    /**
     * 操作类型
     *
     * @return
     */
    OperationType type() default OperationType.NONE;
}
