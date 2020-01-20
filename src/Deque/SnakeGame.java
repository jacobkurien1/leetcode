package Deque;

import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/design-snake-game/
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
/*
Running time is for move is O(1) and space requirement is O(height*width)
 */
public class SnakeGame {
    int[][] food;
    int foodIndex = 0;
    int height;
    int width;
    Deque<int[]> dq;
    boolean[][] snakePresent;
    int currRow = 0;
    int currCol = 0;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.dq = new LinkedList<>();
        dq.addLast(new int[] {0,0});
        snakePresent = new boolean[height][width];
        snakePresent[0][0] = true;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int nextRow = currRow;
        int nextCol = currCol;
        switch(direction.charAt(0)){
            case 'U' : nextRow--;break;
            case 'L' : nextCol--;break;
            case 'R': nextCol++;break;
            case 'D' : nextRow++;break;
        }
        if(nextRow <0 || nextRow >=height || nextCol <0 || nextCol >=width){
            return -1;
        }
        if(foodIndex < food.length && food[foodIndex][0] == nextRow && food[foodIndex][1] == nextCol){
            // next step is food
            foodIndex++;
        } else {
            // next step is not on food
            int[] snakeTail = dq.pollFirst();
            snakePresent[snakeTail[0]][snakeTail[1]] = false;
        }
        if(snakePresent[nextRow][nextCol]){
            return -1;
        }
        dq.addLast(new int[] {nextRow, nextCol});
        snakePresent[nextRow][nextCol] = true;
        currRow = nextRow;
        currCol = nextCol;
        return dq.size()-1;
    }
}
