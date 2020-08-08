package Backtracking;

import java.util.*;

/*
https://leetcode.com/problems/reconstruct-itinerary/
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
 */
/*
Running time is O(E^d) E is the total number of flights(edges) and d is the max number of flights from one airport
Space needed is O(E)
 */
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> {
            if(a.get(0).equals(b.get(0))){
                return a.get(1).compareTo(b.get(1));
            }
            return a.get(0).compareTo(b.get(0));
        }); // needed for the lexiographic sorting
        HashMap<String, Deque<String>> legs = new HashMap<>();// leg of journey
        for(List<String> ticket: tickets){
            Deque<String> leg = legs.getOrDefault(ticket.get(0), new LinkedList<String>());
            leg.addLast(ticket.get(1));
            legs.put(ticket.get(0), leg);
            if(!legs.containsKey(ticket.get(1))){
                legs.put(ticket.get(1), new LinkedList<String>());
            }
        }
        List<String> itinerary = new ArrayList<>();
        if(!legs.containsKey("JFK")){
            return itinerary;
        }
        // do dfs with backtracking
        dfs(legs, "JFK", itinerary, new HashSet<>(), tickets.size()+1);
        return itinerary;
    }

    boolean dfs(HashMap<String, Deque<String>> legs, String src, List<String> itinerary, HashSet<String> visited, int totalLegs){
        itinerary.add(src);
        if(totalLegs == itinerary.size()){
            return true;
        }
        int totalDest = legs.get(src).size();
        while(totalDest-- >0){
            String dest = legs.get(src).pollFirst();
            if(dfs(legs, dest, itinerary, visited, totalLegs)){
                return true;
            }
            legs.get(src).addLast(dest); //back tracking on the Deque
        }
        itinerary.remove(itinerary.size()-1); // backtracking on the list
        return false;
    }
}
