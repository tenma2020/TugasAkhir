package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] timeHour; //menyimpan nilai dari durasi pengerjaan dalam jam
    static int[] material; //menyimpan nilai dari material yang dibutuhkan per produk dalam unit
    static long[] profit; //menyimpan nilai dari keuntungan yang didapat per produk dalam rupiah (Rp)
    static int engineHourCapacity; //menyimpan nilai dari durasi kerja mesin dalam satuan jam
    static int materialCapacity; //menyimpan nilai dari kapasitas material untuk pabrik dalam satuan unit
    // deklarasi variabel untuk menandai jenis produk
    static char[] label = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //deklarasi scanner

        System.out.println("=== Program Optimasi Produksi Bengkel ===");

        System.out.println();

        System.out.print("Masukkan jumlah produk: ");
        int productAmount = in.nextInt(); //memasukkan jumlah produk menggunakan scanner
        timeHour = new int[productAmount]; // instansiasi variabel timeHour dengan panjang array sebesar variabel productAmount
        material = new int[productAmount]; // instansiasi variabel material dengan panjang array sebesar variabel productAmount
        profit = new long[productAmount]; // instansiasi variabel profit dengan panjang array sebesar variabel productAmount

        System.out.println();

        System.out.print("Masukkan kapasitas waktu kerja mesin (jam): ");
        engineHourCapacity = in.nextInt(); //memasukkan nilai waktu kerja mesin dalam satuan jam
        System.out.print("Masukkan kapasitas bahan baku (unit): ");
        materialCapacity = in.nextInt(); //memasukkan nilai kapasitas bahan baku dalam satuan unit

        System.out.println();
        
        for (int i = 0; i < productAmount; i++) { //perulangan input atribut produk
            System.out.printf("Masukkan waktu kerja untuk produk %d (jam):", label[i]);
            timeHour[i] = in.nextInt(); //input nilai atribut timeHour
            System.out.printf("Masukkan bahan baku untuk produk %d (unit):", label[i]);
            material[i] = in.nextInt(); //input nilai atribut material
            System.out.printf("Masukkan keuntungan per unit produk %d (Rp):", label[i]);
            profit[i] = in.nextLong(); //input nilai atribut 
            System.out.println();
        }

        in.close();

        searchMaxProfit(new int[productAmount], 0); //mencari keuntungan tertinggi menggunakan method

        System.out.println("=== Proses Pencarian Kombinasi ===");

        System.out.println();

        searchAndPrintOptimalCombination(new int[productAmount], 0); //mencari dan mencetak kombinasi optimal menggunakan method
        
        System.out.println();
        
        System.out.println("=== Hasil Optimasi ===");

        System.out.println();

        System.out.println("Produksi Optimal:");

        for (int i = 0; i < productAmount; i++) { //perulangan untuk mencetak 
            System.out.println("Produk " + label[i] + " = " + optimalComb[i] + " unit"); 
        }

        System.out.println();

        System.out.println("Keuntungan maksimal: Rp" + maxProfit); //mencetak nilai keuntungan maksimal
    }

    static long maxProfit = 0; //deklarasi variabel untuk menyimpan nilai keuntungan maksimal

    public static void searchMaxProfit(int[] combination, int i) {
        if (i == combination.length) { //seleksi kondisi jika nilai i sama dengan panjang dari array combination
            int curTimeHour = 0; //deklarasi nilai untuk menyimpan nilai waktu pengerjaan terikini
            int curMaterial = 0; //deklarasi nilai untuk menyimpan nilai material terkini
            int curProfit = 0; //deklarasi nilai untuk menyimpan nilai keuntungan terkini
            //perulangan untuk assignment nilai variabel untuk mendapatkan kombinasi terbaik
            for (int unit = 0; unit < combination.length; unit++) { 
                int curUnit = combination[unit];
                curTimeHour += timeHour[unit] * curUnit;
                curMaterial += material[unit] * curUnit;
                curProfit += profit[unit] * curUnit;
            }
            //seleksi kondisi untuk membatasi perulangan hingga kapasitas unit atau waktu kerja mesin habis
            if (curTimeHour > engineHourCapacity || curMaterial > materialCapacity) { 
                return;
            }

            if (curProfit > maxProfit) maxProfit = curProfit; //seleksi kondisi untuk menentukan profit tertinggi

            return;
        }
        //deklarasi variabel yang menyimpan nilai maksimal dari unit yang bisa dibuat
        int maxUnit = Math.min(engineHourCapacity / timeHour[i], materialCapacity / material[i]);
        //perurlangan rekursif untuk menemukan nilai maksimum dari profit yang bisa dicari
        for (int count = 0; count <= maxUnit; count++) { 
            combination[i] = count;
            searchMaxProfit(combination, i + 1);
        }
    }
    //deklarasi variabel array untuk menyimpan nilai kombinasi optimal
    static int[] optimalComb; 
    //method untuk mencari dan mencetak nilai dari kombinasi optimal
    public static void searchAndPrintOptimalCombination(int[] combination, int i) {
        if (i > combination.length - 1) { //jika nilai i sebesar nilai panjang array combination dikurang 1
            int curTimeHour = 0; //deklarasi nilai untuk menyimpan nilai waktu pengerjaan terikini
            int curMaterial = 0; //deklarasi nilai untuk menyimpan nilai material terkini
            int curProfit = 0; //deklarasi nilai untuk menyimpan nilai keuntungan terkini
            //perulangan untuk assignment nilai variabel untuk mendapatkan kombinasi terbaik
            for (int unit = 0; unit < combination.length; unit++) {
                int curUnit = combination[unit];
                curTimeHour += timeHour[unit] * curUnit;
                curMaterial += material[unit] * curUnit;
                curProfit += profit[unit] * curUnit;
            }
            //seleksi kondisi untuk membatasi perulangan hingga kapasitas unit atau waktu kerja mesin habis
            if (curTimeHour > engineHourCapacity || curMaterial > materialCapacity) {
                return;
            }

            System.out.print("Kombinasi: ");
            System.out.print("Produk ");
            for (int count = 0; count < combination.length; count++) { //perulangan untuk mencetak nilai dari suatu kombinasi
                System.out.print((count + 1) + " = " + combination[count] + ", ");
            }
            System.out.print("-> Waktu Mesin = " + curTimeHour + ", "); //mencetak nilai waktu mesin untuk suatu kombinasi
            System.out.print("Bahan Baku = " + curMaterial + ", "); //mencetak nilai material untuk suatu kombinasi
            System.out.print("Keuntungan = Rp" + curProfit); //mencetak profit untuk suatu kombinasi

            if (curProfit == maxProfit) { //seleksi kondisi untuk menyatakan suatu kombinasi optimal atau tidak
                System.out.print(" (Optimal)");
                optimalComb = Arrays.copyOf(combination, combination.length);
            }

            System.out.println();

            return;
        }
        //deklarasi variabel yang menyimpan nilai maksimal dari unit yang bisa dibuat
        int maxUnit = Math.min(engineHourCapacity / timeHour[i], materialCapacity / material[i]);
        //perurlangan rekursif untuk mencari dan menecetak kombinasi optimal untuk profit yang bisa dicari
        for (int count = 0; count <= maxUnit; count++) {
            combination[i] = count;
            searchAndPrintOptimalCombination(combination, i + 1);
        }
    }
}