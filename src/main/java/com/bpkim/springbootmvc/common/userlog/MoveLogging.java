package com.bpkim.springbootmvc.common.userlog;

import java.lang.annotation.*;

// @Retention(RetentionPolicy.CLASS)
// 애노테이션 정보를 얼마나 유지할것인가
//  RetentionPolicy.CLASS > 컴파일하고 클래스에도 남길 것
//  RetentionPolicy.SOURCE> 소스에만 있음
//  기본 값으로 해도 된다.
@Documented
@Target(ElementType.METHOD) // 메소드에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface MoveLogging {
//    String value();
}

