package com.sxj.annotatioin;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.PARAMETER}) //���÷�Χ�����ڲ�����  
@Retention(RetentionPolicy.RUNTIME) //ע�����class�ֽ����ļ��д��ڣ�������ʱ����ͨ�������ȡ��
@Documented //����ע�⽫��������javadoc�� @Inherited��˵��֮����Լ̳и����еĸ�ע��

public @interface EnjoyRequestParam {
	String value() default "";
}
