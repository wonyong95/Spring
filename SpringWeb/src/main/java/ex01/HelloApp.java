package ex01;

public class HelloApp {

	public static void main(String[] args) {
//		MessageBeanEn mb=new MessageBeanEn();
		MessageBeanKo mb=new MessageBeanKo();
		
		mb.sayHello("������");
		/*HelloApp�� MEssageBeanEn ��ü�� ���(use)�Ѵ�
		 * ==> HelloApp�� MessageBeanEn�� ����(dependency)�Ѵ�
		 * : �� �� �������ִ� ��ü�鰣�� ���յ��� �߿��ϴ�
		 * ���յ��� ���ϸ�, ���� ��ü�� ��ü�ϰ��� �Ҷ� ������ �� �� �ִ�.
		 * */
	}

}
