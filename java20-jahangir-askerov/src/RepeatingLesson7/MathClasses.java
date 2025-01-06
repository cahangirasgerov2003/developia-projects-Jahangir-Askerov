package RepeatingLesson7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathClasses {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("1.20005222222");
        BigDecimal bigDecimal1 = new BigDecimal("1.2");
        if(bigDecimal.equals(bigDecimal1)){
            System.out.println("Is equal ( equal method )");
        }else{
            System.out.println("Is not equal ( equal method )");
        }

        if(bigDecimal.compareTo(bigDecimal1) == 0){
            System.out.println("Is equal ( compareTo() method )");
        }else{
            System.out.println("Is not equal ( compareTo() method )");
        }

        System.out.println(bigDecimal1.intValue());
        System.out.println(bigDecimal1.doubleValue());
        System.out.println(bigDecimal1.longValue());

        System.out.println(bigDecimal.setScale(5, RoundingMode.HALF_UP));
    }
}
