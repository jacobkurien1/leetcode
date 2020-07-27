package Search;
/*
You have some comics and some coins.
you can exchange 1 comics for the coinsOffered.

You need to get the fiction books.
Each fiction book can be traded by giving up 1comics + coinsNeeded

Your goal is to maximize the number of fiction books that you can get.
 */
/*
The idea is as we increase the number of comics sold, we would reach closer to the Maxima.
And once we hit the maxima, selling more comics will not give us a better value.
So the graph will look something like this
       *
      * *
    *    *
  *        *
 Here the x axis the the number of comics sold and the y is the total number of fiction books purchased.

 Since going 1 step at a time will give us O(n) running time and is not efficient
 Hence we can bring the running time down to O(log(n)) by taking mid point and checking whether this is the peak of the graph.
 if its increasing slope then, go right.
 if the slope is decreasing, go left.

 Running time is O(log(n)) as we eliminate half of the search space in every iteration.
 Space needed is O(1)
 */
public class MaxFictionBooksSale {
    public int getMaxFictionBooks(int comics, int coins, int coinsNeeded, int coinsOffered) {
        if( comics <=0 || coins <=0 ){
            return 0;
        }
        int minComicsSold = 0;
        int maxComicsSold = comics;
        while(minComicsSold<maxComicsSold){
            int mid = minComicsSold + (maxComicsSold-minComicsSold)/2;
            int slope = getSlope(comics, coins, coinsNeeded, mid, coinsOffered);
            if(slope == 0) {
                return fictionBooks(comics-mid, coins+(mid*coinsOffered), coinsNeeded);
            } else if (slope<0){
                maxComicsSold = mid-1;
            } else {
                minComicsSold = mid+1;
            }
        }
        return fictionBooks(comics-minComicsSold, coins+(minComicsSold*coinsOffered), coinsNeeded);
    }


    /*
    This method is use to figure out the slope when a comicsSold represents the number of comics which has been sold
    for the coins offered.
                  zero slope
                   *
  positive slope  * * negative slope
                *    *
              *        *
     */
    int getSlope(int comics, int coins, int coinsNeeded, int comicsSold, int coinsOffered) {
        int comicsSoldMinus1 = fictionBooks(comics-(comicsSold-1), coins+(coinsOffered*(comicsSold-1)), coinsNeeded);
        int comicsSoldCurr = fictionBooks(comics-(comicsSold), coins+(coinsOffered*(comicsSold)), coinsNeeded);
        int comicsSoldPlus1 = fictionBooks(comics-(comicsSold+1), coins+(coinsOffered*(comicsSold+1)), coinsNeeded);
        if(comicsSoldMinus1<comicsSoldCurr && comicsSoldCurr < comicsSoldPlus1){
            return 1;
        } else if (comicsSoldMinus1 > comicsSoldCurr && comicsSoldCurr > comicsSoldPlus1){
            return -1;
        } else {
            return 0;   // maxima case
        }
    }

    /*
    This method returns the total number of fiction books that can be purchased for the comics and coins
     */
    int fictionBooks(int comics, int coins, int coinsNeeded) {
        int fictionBooksFromCoinsOnly = coins/coinsNeeded;
        return Math.min(comics, fictionBooksFromCoinsOnly);
    }
}
