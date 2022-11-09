package org.dmitrysulman.codewars.greed;

public class Greed {
    public static int greedy(int[] dice){
        int[] count = new int[6];
        int[] threePoints = {1000, 200, 300, 400, 500, 600};
        int sum = 0;
        for (int i : dice) {
            count[i - 1]++;
        }
        for (int i = 0; i < 6; i++) {
            if (count[i] >= 3) {
                sum += threePoints[i];
                count[i] = Math.max(0, count[i] - 3);
                break;
            }
        }
        sum += 100 * count[0];
        sum += 50 * count[4];

        return sum;
    }
}
