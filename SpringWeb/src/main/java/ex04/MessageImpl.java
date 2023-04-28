package ex04;

import java.util.Date;
import java.util.Random;

public class MessageImpl implements Message {
   
   //property
   private String name;
   private String greeting;
   
   private Date today;
   private Random ran;
   @Override
   public void sayHello() {
      System.out.println(greeting+" "+name);
      System.out.println("**********************************************************");
      System.out.println(today.toString());
   }

   @Override
   public void sayHi(String... names) {
      System.out.print(greeting);
      if(name!=null) {
         for(String name : names) {
            System.out.print(name+", ");
         }
      }
      System.out.println();
      System.out.println("**********************************************************");
      System.out.println("Lucky Number: "+(ran.nextInt(100)+1));
   }
   // setter, getter ---

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
      System.out.println("setName(): "+name);
   }

   public String getGreeting() {
      return greeting;
   }

   public void setGreeting(String greeting) {
      this.greeting = greeting;
   }

   public Date getToday() {
      return today;
   }

   public void setToday(Date today) {
      this.today = today;
   }

   public Random getRan() {
      return ran;
   }

   public void setRan(Random ran) {
      this.ran = ran;
   }
   

}