package ex03;
import org.springframework.context.*;
import org.springframework.context.support.*;
//IoC(Inversion of Contorl) : 제어권을 스프링 컨테너가 갖는다
//스프링컨테이너는 설정파일(applicationContext.xml)이나 어노테이션을 읽어서 객체를 메모리에 올린다
public class HelloApp {

	public static void main(String[] args) {
		String config="src/main/java/ex03/applicationContext.xml";
		
		//스프링 컨테이너가 알아서 xml파일을 읽어서 bean으로 등록된 객체를 생성해서 메모리에 올린다
		ApplicationContext ctx=new FileSystemXmlApplicationContext(config);
		
		//DL(dependecy Lookup) : 메모리에 올라간 객체를 이름으로 찾는 것
		MessageBean mb=(MessageBean)ctx.getBean("mbEn");
		mb.sayHello("BTS");
	}

}
