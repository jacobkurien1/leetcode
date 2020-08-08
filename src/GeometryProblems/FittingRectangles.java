package GeometryProblems;

import java.util.HashSet;
import java.util.Objects;

/*
https://leetcode.com/problems/perfect-rectangle/
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point.
For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.




Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.




Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.




Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
 */
/*
Running time O(n)
Space needed is O(1)
 */
public class FittingRectangles {
    public boolean isRectangleCover(int[][] rectangles) {
        int minx1 = Integer.MAX_VALUE;
        int miny1 = Integer.MAX_VALUE;
        int maxx2 = Integer.MIN_VALUE;
        int maxy2 = Integer.MIN_VALUE;
        int area = 0;
        HashSet<Point> pointSet = new HashSet<>();

        for(int r = 0; r<rectangles.length; r++){
            int x1 = rectangles[r][0];
            int y1 = rectangles[r][1];
            int x2 = rectangles[r][2];
            int y2 = rectangles[r][3];
            minx1 = Math.min(minx1, x1);
            miny1 = Math.min(miny1, y1);
            maxx2 = Math.max(maxx2, x2);
            maxy2 = Math.max(maxy2, y2);

            area += (y2-y1) * (x2-x1);
            removeIfPresent(pointSet, x1, y1);
            removeIfPresent(pointSet, x2, y1);
            removeIfPresent(pointSet, x1, y2);
            removeIfPresent(pointSet, x2, y2);
        }

        return pointSet.size() == 4 &&
                pointSet.contains(new Point(minx1, miny1)) &&
                pointSet.contains(new Point(minx1, maxy2)) &&
                pointSet.contains(new Point(maxx2, miny1)) &&
                pointSet.contains(new Point(maxx2, maxy2)) &&
                area == (maxy2-miny1)*(maxx2-minx1);
    }

    void removeIfPresent(HashSet<Point> pointSet, int x , int y){
        Point p = new Point(x, y);
        if(pointSet.contains(p)){
            pointSet.remove(p);
        } else {
            pointSet.add(p);
        }
    }

    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o == null || o.getClass() != getClass()) return false;
            Point that = (Point) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x) * 31 + Objects.hash(y);
        }
    }
}
