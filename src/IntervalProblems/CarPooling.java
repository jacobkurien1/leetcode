package IntervalProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
You are driving a vehicle that has capacity empty seats initially available for passengers.
The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip:
the number of passengers that must be picked up, and the locations to pick them up and drop them off.
The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true



Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
 */
/*
Running time is O(Nlog(N))
Space needed is O(N)
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        List<PeopleInCar> allPeopleInCar = new ArrayList<>();
        for(int trip = 0; trip<trips.length; trip++){
            allPeopleInCar.add(new PeopleInCar(trips[trip][1], trips[trip][0], true));
            allPeopleInCar.add(new PeopleInCar(trips[trip][2], trips[trip][0], false));
        }
        Collections.sort(allPeopleInCar, (a, b) -> {
            // location ascending, end before start for same location
            if(a.location!=b.location){
                return Integer.compare(a.location, b.location);
            }
            return (a.isStart) ? 1 : -1;
        });
        int currPeopleInCar=0;
        for(PeopleInCar peopleInCar : allPeopleInCar){
            if(peopleInCar.isStart){
                currPeopleInCar += peopleInCar.peopleCount;
                if(currPeopleInCar > capacity){
                    return false;
                }
            }else{
                currPeopleInCar -= peopleInCar.peopleCount;
            }
        }
        return true;
    }

    class PeopleInCar{
        int location;
        int peopleCount;
        boolean isStart;
        public PeopleInCar(int location, int peopleCount, boolean isStart){
            this.location = location;
            this.peopleCount = peopleCount;
            this.isStart = isStart;
        }
    }
}
