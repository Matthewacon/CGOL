package io.github.matthewacon.cgol;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Random;

public final class CGOL {
 private Boolean[][] board;

 public CGOL(final int width, final int height) {
  board = new Boolean[width][height];
  clear();
 }

 public void generateRandomBoard() {
  final Random random = new Random();
  for (int i = 0; i < board.length; i++) {
   for (int j = 0; j < board[i].length; j++) {
    board[i][j] = random.nextBoolean();
//    board[i][j] = (random.nextInt(100000) + 1) < 50000;
   }
  }
 }

 public void clear() {
  for (int i = 0; i < board.length; i++) {
   for (int j = 0; j < board[i].length; j++) {
    board[i][j] = false;
   }
  }
 }

 //TODO there has to be a better way to do this
 public SimpleEntry<Integer, Integer> wrap(int i, int j) {
  if (i < 0) {
   while ((i += board.length) < 0);
  } else if (i > board.length - 1) {
   while ((i -= board.length) >= board.length - 1);
  }
  if (j < 0) {
   while ((j += board[0].length) < 0);
  } else if (j > board[0].length - 1) {
   while ((j -= board[0].length) >= board[0].length);
  }
//  System.out.println(i + " :: " + j);
  return new SimpleEntry<>(i, j);
 }

 public Boolean[][] step() {
  final Boolean[][] future = Arrays.copyOf(board, board.length);
  for (int i = 0; i < board.length; i++) {
   for (int j = 0; j < board[i].length; j++) {
    //Count neighbours
    int neighbours = 0;
    for (int l = -1; l <= 1; l++) {
     for (int m = -1; m <= 1; m++) {
//      if (!(l == i && m == j)) {
       final SimpleEntry<Integer, Integer> indices = wrap(i + l, j + m);
       if (board[indices.getKey()][indices.getValue()]) {
        neighbours++;
       }
//      }
     }
    }
//    if (board[i][j]) {
//     neighbours -= 1;
//    }
    if (neighbours <= 2 || neighbours > 3) {
     future[i][j] = false;
    } else {
     future[i][j] = true;
    }
   }
  }
  return board = future;
 }

 public Boolean[][] peek() {
  return Arrays.copyOf(board, board.length);
 }

 public void set(int i, int j, boolean value) {
  final SimpleEntry<Integer, Integer> coords = wrap(i, j);
  board[coords.getKey()][coords.getValue()] = value;
 }
}
