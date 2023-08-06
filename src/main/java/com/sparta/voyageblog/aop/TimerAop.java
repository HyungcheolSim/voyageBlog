package com.sparta.voyageblog.aop;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j(topic = "TimerAop")
@Aspect
@Component
@RequiredArgsConstructor
public class TimerAop {

  @Around("@annotation(com.sparta.voyageblog.aop.Timer)")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    //측정 시작 시간
    long startTime = System.currentTimeMillis();

    try {
      Object output = joinPoint.proceed();
      return output;
    } finally {
      //측정 종료 시간
      long endTime = System.currentTimeMillis();
      //수행 시간
      long runTime = endTime - startTime;
      log.info("API 수행 완료 | 수행 시간: "+runTime/1000.0+" 초");
    }
  }
}

