package ex04;

public interface Message {

	void sayHello();
	void sayHi(String ... names);
	//매개변수를 자유자재로 넣을수있다 1개,2개,...10개 ..n개
	//names변수는 String[] 배열타입
}
