package ConwaysGames;

import java.util.ArrayList;
import java.util.Random;

public class ConwaysGamesofLife {
	  private static final String LIFE = "@"; // ya�ayan h�creler @ isareti ile g�sterilir
	    private static final String DEATH = "."; //�l� h�creler . seklinde g�sterilir
	   
	    public static void main(String... args) {
	       
	        String[][] grid = generateGrid(20,20); // 20x20 lik ilk basta random grid adl� matris metodunu yazip cagirdik       
	        int gen = 0; // gen sayisini tutan degisken
	       
	        while(true) {
	            clearScreen(); // silme fonksiyonu 
	            printGrid(grid); // matrisi basan fonksiyon
	            System.out.println("Gen " + (gen++) + ""); // gen sayisini yaziyor
	           
	            String[][] superGrid = copyGrid(grid);    // grid matrisi kopyalaniyor       
	   
	            for (int i = 0; i < grid.length; i++) { // matrisin her indexi(20 tane) icin 
	                for (int j = 0; j < grid[i].length; j++) { // diger indexi 20 kez dolas�yor
	                    //System.out.println(grid[i][j] + " " + i + ":" + j);
	   
	                    int life_forms = 0;
	   
	                    ArrayList<String> neighbors = new ArrayList<String>();
	                   
	                 // mevcut h�creye kom�u h�creleri bul.
	                   
	                    neighbors.add(grid[Math.floorMod(i - 1, grid.length)][Math.floorMod(j - 1, grid[i].length)]);
	                    neighbors.add(grid[Math.floorMod(i - 1, grid.length)][j]);
	                    neighbors.add(grid[Math.floorMod(i - 1, grid.length)][Math.floorMod(j + 1, grid[i].length)]);
	                    neighbors.add(grid[i][Math.floorMod(j - 1, grid[i].length)]);
	                    neighbors.add(grid[i][Math.floorMod(j + 1, grid[i].length)]);
	                    neighbors.add(grid[Math.floorMod(i + 1, grid.length)][Math.floorMod(j - 1, grid[i].length)]);
	                    neighbors.add(grid[Math.floorMod(i + 1, grid.length)][j]);
	                    neighbors.add(grid[Math.floorMod(i + 1, grid.length)][Math.floorMod(j + 1, grid[i].length)]);
	                   
	                    for (int k = 0; k < neighbors.size(); k++)
	                        if (neighbors.get(k).compareTo(LIFE) == 0) // komu�u h�cre hayatta m�
	                            life_forms += 1;
	   
	                    if (grid[i][j].compareTo(DEATH) == 0) { // komusu h�cre �l� m�
	                        // yeni bir h�cre yap�p yapamayaca��m� kontrol et
	                        if (life_forms == 3) {
	                            superGrid[i][j] = LIFE;
	                        }
	                    } else { // baska h�cre yas�yorsa �h�crenin a��r� pop�lasyonda m� yoksa d���k pop�lasyonda m� �lmesi gerekti�ini kontrol et
	                        if (life_forms < 2 || life_forms > 3) {
	                            superGrid[i][j] = DEATH;
	                        }
	                    }
	                }
	            }
	           
		            /// yeni nesiller i�eren grid  bir tanesine kopyala
		            
	            grid = copyGrid(superGrid);
	           
	         // d�ng�y� tekrar etmeden �nce 200 milisaniye bekle
	            try {
	                Thread.sleep(100);
	            } catch(InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    }

	    private static String[][] copyGrid(String[][] grid) {
	        String[][] tempGrid = new String[grid.length][grid[0].length];

	        for (int i = 0; i < grid.length; i++) {
	            for (int j = 0; j < grid[i].length; j++) {
	                tempGrid[i][j] = grid[i][j];
	            }
	        }

	        return tempGrid;
	    }

	    public static String[][] generateGrid(int width, int height) {
	        String[][] randomGrid = new String[height][width];
	        Random rand = new Random();

	        for (int i = 0; i < randomGrid.length; i++) {
	            for (int j = 0; j < randomGrid[i].length; j++) {
	                Boolean god = rand.nextBoolean();

	                if (god)
	                    randomGrid[i][j] = LIFE;
	                else
	                    randomGrid[i][j] = DEATH;
	            }
	        }

	        return randomGrid;
	    }
	   
	    private static void clearScreen() {
	        for(int i = 0; i < 100; i++)
	            System.out.println();
	    }

	    public static void printGrid(String[][] matrix) {
	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = 0; j < matrix[i].length; j++) {
	                System.out.print(" " + matrix[i][j] + " ");
	            }

	            System.out.println();
	        }
	    }
}
