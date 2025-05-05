package Interview2;

public class Singleton {
    private int waterVolume = 100;

    private Singleton() {
    }

    ;
    private static Singleton singletonI = null;

    static Singleton getInstanse() {
        if (singletonI == null) {
            singletonI = new Singleton();
        }

        return singletonI;
    }

    public int getWater(int water){
        return waterVolume - water;
    }

}
