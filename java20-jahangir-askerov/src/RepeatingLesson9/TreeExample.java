package RepeatingLesson9;

public class TreeExample {
    public static void main(String[] args) {
        // 0.5 same 0.5 metr
        int pastDays = 0;
        double lengthTree = 0.5;
        double volumeWater = 0.5;
        double growthLength = 0.2;
        double goalLengthTree = 3;
        while (lengthTree < goalLengthTree) {
            lengthTree += volumeWater * growthLength;
            volumeWater *= 2;
            pastDays++;
        }

        System.out.println("The time it takes for a tree to cross " + goalLengthTree + " meters = " + pastDays);
    }
}
