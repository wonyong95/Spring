package ex04;

import org.springframework.context.*;
import org.springframework.context.support.*;

public class HelloAppSpring {

	public static void main(String[] args) {
		String config="src/main/java/ex04/appContext.xml";
		ApplicationContext ctx=new FileSystemXmlApplicationContext(config);//������ �����̳�
		
		Message msg=ctx.getBean("mb2", Message.class);
		msg.sayHello();
		msg.sayHi("����","�ȴϿ�","����","BTS");
		
	}

}
