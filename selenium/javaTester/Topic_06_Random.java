package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // Java Builtin (Cung cap san - lay ra su dung)

    // Java Libtaries (Do 1 ca nhan/ to chuc tu viet)

    public static void main(String[] args){
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());

        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextBoolean());

        System.out.println("huong" + rand.nextInt(9999) + "@gmail.com");
        System.out.println("huong" + rand.nextInt(9999) + "@gmail.com");
        System.out.println("huong" + rand.nextInt(9999) + "@gmail.com");
        System.out.println("huong" + rand.nextInt(9999) + "@gmail.com");

    }
}
