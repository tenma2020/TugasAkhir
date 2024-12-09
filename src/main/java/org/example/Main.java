package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //initialize attributes
    static int[] timeHour;
    static int[] material;
    static long[] profit;
    static int engineHourCapacity;
    static int materialCapacity;

    //main method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //Declare Scanner

        System.out.println("=== Program Optimasi Produksi Bengkel ===");

        System.out.println();

        System.out.print("Masukkan jumlah produk: ");
        int productAmount = in.nextInt(); //input product
        timeHour = new int[productAmount]; //initialize hour array
        material = new int[productAmount]; //initialize material array
        profit = new long[productAmount]; //initialize profit array

        System.out.println();

        System.out.print("Masukkan kapasitas waktu kerja mesin (jam): ");
        engineHourCapacity = in.nextInt(); //input factory machine worktime per hour
        System.out.print("Masukkan kapasitas bahan baku (unit): ");
        materialCapacity = in.nextInt(); //input available material per unit

        System.out.println();

        for (int i = 0; i < productAmount; i++) {
            //input time cost per product in hour
            System.out.printf("Masukkan waktu kerja untuk produk %d (jam):", i + 1);
            timeHour[i] = in.nextInt();

            //input material cost per product per unit
            System.out.printf("Masukkan bahan baku untuk produk %d (unit):", i + 1);
            material[i] = in.nextInt();

            //input profit per unit
            System.out.printf("Masukkan keuntungan per unit produk %d (Rp):", i + 1);
            profit[i] = in.nextLong();
            System.out.println();
        }

        in.close();

        searchMaxProfit(new int[productAmount], 0); // search the highest profit according to entries

        System.out.println("=== Proses Pencarian Kombinasi ===");

        System.out.println();

        searchAndPrintOptimalCombination(new int[productAmount], 0);
        
        System.out.println();
        
        System.out.println("=== Hasil Optimasi ===");

        System.out.println();

        System.out.println("Produksi Optimal:");

        for (int i = 0; i < productAmount; i++) {
            System.out.println("Produk " + (i + 1) + " = " + optimalComb[i] + " unit");
        }

        System.out.println();

        System.out.println("Keuntungan maksimal: Rp" + maxProfit);
    }

    static long maxProfit = 0;

    //to search higest profit
    public static void searchMaxProfit(int[] combination, int i) {

        
        if (i == combination.length) {
            int curTimeHour = 0;
            int curMaterial = 0;
            int curProfit = 0;

            for (int unit = 0; unit < combination.length; unit++) {
                int curUnit = combination[unit];
                curTimeHour += timeHour[unit] * curUnit;
                curMaterial += material[unit] * curUnit;
                curProfit += (int) (profit[unit] * curUnit);
            }

            if (curTimeHour > engineHourCapacity || curMaterial > materialCapacity) {
                return;
            }

            if (curProfit > maxProfit) maxProfit = curProfit;

            return;
        }

        int maxUnit = Math.min(engineHourCapacity / timeHour[i], materialCapacity / material[i]);

        for (int count = 0; count <= maxUnit; count++) {
            combination[i] = count;
            searchMaxProfit(combination, i + 1);
        }
    }

    static int[] optimalComb;

    public static void searchAndPrintOptimalCombination(int[] combination, int i) {
        if (i > combination.length - 1) {
            int curTimeHour = 0;
            int curMaterial = 0;
            int curProfit = 0;

            for (int unit = 0; unit < combination.length; unit++) {
                int curUnit = combination[unit];
                curTimeHour += timeHour[unit] * curUnit;
                curMaterial += material[unit] * curUnit;
                curProfit += (int) (profit[unit] * curUnit);
            }

            if (curTimeHour > engineHourCapacity || curMaterial > materialCapacity) {
                return;
            }

            System.out.print("Kombinasi: ");
            System.out.print("Produk ");
            for (int count = 0; count < combination.length; count++) {
                System.out.print((count + 1) + " = " + combination[count] + ", ");
            }
            System.out.print("-> Waktu Mesin = " + curTimeHour + ", ");
            System.out.print("Bahan Baku = " + curMaterial + ", ");
            System.out.print("Keuntungan = Rp" + curProfit);

            if (curProfit == maxProfit) {
                System.out.print(" (Optimal)");
                optimalComb = Arrays.copyOf(combination, combination.length);
            }

            System.out.println();

            return;
        }

        int maxUnit = Math.min(engineHourCapacity / timeHour[i], materialCapacity / material[i]);

        for (int count = 0; count <= maxUnit; count++) {
            combination[i] = count;
            searchAndPrintOptimalCombination(combination, i + 1);
        }
    }
}