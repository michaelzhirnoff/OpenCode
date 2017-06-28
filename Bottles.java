package opencode;

public class Bottles {
    public static void main(String[] args) {
        int fullBottles;
        int emptyBottles = 0;
        for (fullBottles = 99; fullBottles > 0; fullBottles--) {
            System.out.format("%d bottles of beer on the wall, %d bottles of beer.\n", fullBottles, fullBottles);
            emptyBottles++;
            System.out.format("Put a bottle in the recycling bin, there are now %d empty bottles in the bin.\n", emptyBottles);
        }

        if (fullBottles == 0)
                System.out.println("There are simply no more bottles of beer on the wall.");
    }
}
