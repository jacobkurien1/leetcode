package Search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class MaxFictionBooksSaleTest {

    @Test
    void getMaxFictionBooks() {
        MaxFictionBooksSale maxFictionBooksSale = new MaxFictionBooksSale();
        Assertions.assertEquals(5, maxFictionBooksSale.getMaxFictionBooks(5,5,1, 1));
        Assertions.assertEquals(5, maxFictionBooksSale.getMaxFictionBooks(5,5,1, 2));
        Assertions.assertEquals(8, maxFictionBooksSale.getMaxFictionBooks(10,5,1, 2));
        Assertions.assertEquals(getExpectedMaxFictionBooks(500,250, 50, 100),
                maxFictionBooksSale.getMaxFictionBooks(500,250,50, 100));
    }

    @Test
    void getMaxFictionBooksRandom(){
        Random random = new Random();
        MaxFictionBooksSale maxFictionBooksSale = new MaxFictionBooksSale();
        for(int i = 0; i<1000; i++){
            int comics = random.nextInt(5000)+1;
            int coins = random.nextInt(5000)+1;
            int coinsNeeded = random.nextInt(50)+1;
            int coinsOffered = random.nextInt(100)+1;
            Assertions.assertEquals(getExpectedMaxFictionBooks(comics,coins, coinsNeeded, coinsOffered),
                    maxFictionBooksSale.getMaxFictionBooks(comics,coins,coinsNeeded, coinsOffered));
        }
    }

    /*
    This is the O(n) alternative, to give me the expected maximum fiction books in my random testing
     */
    int getExpectedMaxFictionBooks(int comics, int coins, int coinsNeeded, int coinsOffered) {
        int max = 0;
        MaxFictionBooksSale maxFictionBooksSale = new MaxFictionBooksSale();
        for(int i = 0; i<comics; i++){
            int fictionBooks = maxFictionBooksSale.getMaxFictionBooks(comics, coins, coinsNeeded, coinsOffered);
            if(max>= fictionBooks){
                break;
            }
            max = fictionBooks;
        }
        return max;
    }
}