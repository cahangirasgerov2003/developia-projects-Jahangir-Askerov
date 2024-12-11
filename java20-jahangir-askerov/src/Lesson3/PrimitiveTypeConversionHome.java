package Lesson3;

public class PrimitiveTypeConversionHome {
    public static void main(String[] args) {

        int variable = 55;
        byte variable2 = (byte) variable;

        System.out.println("Result : " + variable2 );

        short variable3 = 636;
        byte variable4 = (byte) variable3;

        System.out.println("Result2 : " + variable4 );

        long variable5 = 458;
        short variable6 = (short) variable5;

        System.out.println("Result3 : " + variable6 );

        long variable7 = 92523635483L;
        int variable8 = (int) variable7;

        System.out.println("Result4 : " + variable8 );

        double variable9 = 65787.3;
        int variable10 = (int) variable9;

        System.out.println("Result5 : " + variable10 );

        int variable11 = 'q';

        System.out.println("Result6 : " + variable11 );

        int variable12 = 266;
        char variable13 = (char) variable12;

        System.out.println("Result7 : " + variable13);
    }
}
