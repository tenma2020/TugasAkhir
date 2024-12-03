package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Masukkan jumlah produk:");
        int productAmount = in.nextInt();
        int[] timeHour = new int[productAmount];
        int[] material = new int[productAmount];
        int[] profit = new int[productAmount];

        System.out.println("masukkan kapasitas waktu kerja mesin(jam):");
        int engineHourCapacity = in.nextInt();
        System.out.println("masukkan kapasitas bahan baku(unit):");
        int materialCapacity = in.nextInt();


        for (int i = 0; i < productAmount; i++) {
            System.out.printf("Masukkan waktu kerja untuk produk %d (jam):", i + 1);
            timeHour[i] = in.nextInt();
            System.out.printf("Masukkan bahan baku untuk produk %d (unit):", i + 1);
            material[i] = in.nextInt();
            System.out.printf("Masukkan keuntungan per unit produk %d (Rp):", i + 1);
            profit[i] = in.nextInt();
        }

    }
}