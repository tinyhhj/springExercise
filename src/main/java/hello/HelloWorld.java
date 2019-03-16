package hello;

import java.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {

        LocalTime currentTime = LocalTime.now();
        System.out.println("the current local time is" + currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}