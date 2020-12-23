package com.kevin.demo.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Aspect
@Configuration
public class SeataConfig {
//    private static final String AOP_POINTCUT_EXPRESSION = "@annotation(io.seata.spring.annotation.GlobalTransactional)";
//
//    @Bean
//    public GlobalTransactionalInterceptor globalTransactionalInterceptor(){
//        GlobalTransactionalInterceptor globalTransactionalInterceptor = new GlobalTransactionalInterceptor(null);
//        return globalTransactionalInterceptor;
//    }
//
//    @Bean
//    public Advisor seataAdviceAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//        return new DefaultPointcutAdvisor(pointcut,globalTransactionalInterceptor());
//    }

    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("kevin-demo-account-group","kevin-demo-group");
    }
}
