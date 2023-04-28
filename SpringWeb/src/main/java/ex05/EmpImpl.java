package ex05;

import java.util.Random;

public class EmpImpl implements Emp {

   //property
   private String dept;
   private String name;
   private int sal;
   private Random ran;
   
   public EmpImpl() {
      
   }
   public EmpImpl(String name) {
      this.name=name;
   }
   
   public EmpImpl(Random ran) {
      this.ran =ran;
   }
   public EmpImpl(String d,String n ) {
      this.dept=d; this.name=n;
   }
   public EmpImpl(String d,String n, int s ) {
      this.dept=d; this.name=n; this.sal = s;
   }
   
   @Override
   public void info1() {
      System.out.println("Name: "+name);
   }

   @Override
   public void info2() {
      this.info1();//이름
      System.out.println("Dept: "+dept);
   }

   @Override
   public void info3() {
      this.info2();//이름, 부서
      System.out.println("sal: "+sal);
   }

   @Override
   public void info4() {
      System.out.println("행운의 숫자: "+(ran.nextInt(100)+1));
   }

}