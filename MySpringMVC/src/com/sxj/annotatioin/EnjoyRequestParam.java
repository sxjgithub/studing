package com.sxj.annotatioin;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.PARAMETER}) //作用范围：用在参数上  
@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented //明该注解将被包含在javadoc中 @Inherited：说明之类可以继承父类中的改注解

public @interface EnjoyRequestParam {
	String value() default "";
}
