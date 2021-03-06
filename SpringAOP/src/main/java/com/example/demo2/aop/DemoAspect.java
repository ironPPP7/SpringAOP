package com.example.demo2.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo2.annotation.DemoAnnotation;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author wmy
 * @Date 2020/6/4 14:32
 * @Version 1.0
 */
@Aspect
@Component
@Log4j2
public class DemoAspect {
    private static final Logger logger = LoggerFactory.getLogger(DemoAspect.class);

    /**
     * 切入点
     *  @Pointcut("@annotation(cn.van.annotation.annotation.WebLog)")
     * **/

    @Pointcut("execution(public * com.example.demo2.controller.*.*(..)) && @annotation(com.example.demo2.annotation.DemoAnnotation)")
    public void addAdvice(){
    }

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest requests = (HttpServletRequest) args[0];
        log.info("============打印日志开始============");
        log.info("URL: " + requests.getRequestURL().toString());
        log.info("============打印日志结束============");
//        LOG.info("before....");
    }


    @Pointcut("@annotation(com.example.demo2.annotation.DemoAnnotation)")
    public void webLogPointcut(){
    }

    // 在执行方法前后调用Advice，相当于@Before和@AfterReturning一起做的事儿；
    @Around("webLogPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取注解的属性值
        DemoAnnotation demoAnnotation = ((MethodSignature)pjp.getSignature()).getMethod().getAnnotation(DemoAnnotation.class);
        logger.info("请求方法描述：" + demoAnnotation.description() );
        logger.info("请求开始时间："+ LocalDateTime.now());
        // 记录下请求内容
        logger.info("请求Url : " + request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("请求ip : " + request.getRemoteAddr());
        logger.info("请求方法 : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(pjp.getArgs()));
        Object obj = pjp.proceed();
        logger.info("请求结束时间："+ LocalDateTime.now());
        logger.info("请求返回 : " + JSON.toJSONString(obj));
        logger.info("日志耗时：{} ms",(System.currentTimeMillis() - startTime));
        return obj;
    }

}
