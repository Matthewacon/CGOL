package io.github.matthewacon.cgol;

import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

//TODO functional interface for underlying structure
//Functional interface for neighbour checking
//Functional interface for rule set
//Default implementation of iteration
public final class CGOL {
 interface StepFunction {
  void step(final CGOL board);
 }

 private Boolean[][] board;

 public CGOL(final int width, final int height) {
  board = new Boolean[width][height];
  clear();
 }

 public void generateRandomBoard() {
  final Random random = new Random();
  for (int i = 0; i < board.length; i++) {
   for (int j = 0; j < board[i].length; j++) {
//    board[i][j] = random.nextBoolean();
    //Add (1/4) bias (on/off)
    board[i][j] = (random.nextInt(100000) + 1) < 25000;
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
  return new SimpleEntry<>(i, j);
 }

 public Boolean[][] step(final StepFunction... sfs) {
////  final Boolean[][] future = Arrays.copyOf(board, board.length);
////  final Boolean[][] future = new Boolean[board.length][board[0].length];
//  for (int i = 0; i < board.length; i++) {
//   for (int j = 0; j < board[i].length; j++) {
//    //Count neighbours
//    int neighbours = 0;
//    for (int l = -1; l < 2; l++) {
//     for (int m = -1; m < 2; m++) {
////      if (!(l == i && m == j)) {
////      if (l != 0 && m != 0) {
//       final SimpleEntry<Integer, Integer> indices = wrap(i + l, j + m);
//       if (board[indices.getKey()][indices.getValue()]) {
//        neighbours++;
////       }
//      }
//     }
//    }
//    if (board[i][j]) {
//     neighbours -= 1;
//    }
//    changes.put(new SimpleEntry<>(i, j), rules.step(i, j, neighbours, board[i][j]));
//
////////    if (neighbours <= 2 || neighbours > 3) {
//////    if (neighbours < 2 || neighbours > 3) {
////////     future[i][j] = false;
//////     changes.put(new SimpleEntry<>(i, j), true);
//////    } else {
////////     future[i][j] = true;
//////     changes.put(new SimpleEntry<>(i, j), false);
//////    }
////
//////    if (neighbours == 2 || neighbours == 3) {
//////     future[i][j] = true;
//////    } else {
//////     future[i][j] = false;
//////    }
////
////    if (neighbours == 2) {
////     changes.put(new SimpleEntry<>(i, j), board[i][j]);
//////     future[i][j] = board[i][j];
////    } else if (neighbours == 3) {
////     changes.put(new SimpleEntry<>(i, j), true);
//////     future[i][j] = true;
////    } else {
////     changes.put(new SimpleEntry<>(i, j), false);
//////     future[i][j] = false;
////    }
//   }
//  }
//  for (final SimpleEntry<Integer, Integer> change : changes.keySet()) {
//   board[change.getKey()][change.getValue()] = changes.get(change);
//  }
//  changes.clear();
  for (final StepFunction sf : sfs) {
   sf.step(this);
  }
  return board;
 }

 public Boolean[][] peek() {
//  return Arrays.copyOf(board, board.length);
  return board;
 }

 public void set(int i, int j, boolean value) {
  final SimpleEntry<Integer, Integer> coords = wrap(i, j);
  board[coords.getKey()][coords.getValue()] = value;
 }
}
