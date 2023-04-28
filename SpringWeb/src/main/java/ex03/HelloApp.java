package ex03;
import org.springframework.context.*;
import org.springframework.context.support.*;
//IoC(Inversion of Contorl) : ������� ������ ���׳ʰ� ���´�
//�����������̳ʴ� ��������(applicationContext.xml)�̳� ������̼��� �о ��ü�� �޸𸮿� �ø���
public class HelloApp {

	public static void main(String[] args) {
		String config="src/main/java/ex03/applicationContext.xml";
		
		//������ �����̳ʰ� �˾Ƽ� xml������ �о bean���� ��ϵ� ��ü�� �����ؼ� �޸𸮿� �ø���
		ApplicationContext ctx=new FileSystemXmlApplicationContext(config);
		
		//DL(dependecy Lookup) : �޸𸮿� �ö� ��ü�� �̸����� ã�� ��
		MessageBean mb=(MessageBean)ctx.getBean("mbEn");
		mb.sayHello("BTS");
	}

}
