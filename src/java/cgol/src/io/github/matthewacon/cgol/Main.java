package io.github.matthewacon.cgol;

import processing.core.PApplet;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;

public class Main extends PApplet {
 public static void main(String[] args) {
  PApplet.main(Main.class.getCanonicalName());
 }

 public static final CGOL.StepFunction
  CONWAY = cgol -> {
   final Boolean[][] board = cgol.peek();
   final LinkedHashMap<SimpleEntry<Integer, Integer>, Boolean> changes = new LinkedHashMap<>();
   for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board[i].length; j++) {
     //Count neighbours
     int neighbours = 0;
     for (int l = -1; l < 2; l++) {
      for (int m = -1; m < 2; m++) {
       final SimpleEntry<Integer, Integer> indices = cgol.wrap(i + l, j + m);
       if (board[indices.getKey()][indices.getValue()]) {
        neighbours++;
       }
      }
     }
     if (board[i][j]) {
      neighbours -= 1;
     }
     if (neighbours == 2) {
      changes.put(new SimpleEntry<>(i, j), board[i][j]);
     } else if (neighbours == 3) {
      changes.put(new SimpleEntry<>(i, j), true);
     } else {
      changes.put(new SimpleEntry<>(i, j), false);
     }
    }
   }
   for (final SimpleEntry<Integer, Integer> change : changes.keySet()) {
    board[change.getKey()][change.getValue()] = changes.get(change);
   }
  },
  ANTI_CONWAY = cgol -> {
   final Boolean[][] board = cgol.peek();
   for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board[i].length; j++) {
     //Count neighbours
     int neighbours = 0;
     for (int l = -1; l < 2; l++) {
      for (int m = -1; m < 2; m++) {
      if (!(l == i && m == j)) {
       final SimpleEntry<Integer, Integer> indices = cgol.wrap(i + l, j + m);
       if (board[indices.getKey()][indices.getValue()]) {
        neighbours++;
       }
       }
      }
     }
//    if (neighbours <= 2 || neighbours > 3) {
//    if (neighbours < 2 || neighbours > 3) {
     if (neighbours == 2 || neighbours == 3) {
      board[i][j] = true;
     } else {
      board[i][j] = false;
     }
    }
   }
  };

 private int
  boardWidth = 100,
  boardHeight = 100,
  cellWidth,
  cellHeight;

 private final CGOL cgol = new CGOL(boardWidth, boardHeight);

 private boolean
  mouseHeld = false,
  shouldUpdate = false,
  enableGrid = false,
  strangeRules = false;

 private long
  lastStep = 0,
  updateDelay = 100;

 public void settings() {
  size(1000, 1000, "processing.awt.PGraphicsJava2D");
 }

 public void setup() {
  cellWidth = Math.floorDiv(width, boardWidth);
  cellHeight = Math.floorDiv(height, boardHeight);
 }

 public void draw() {
  background(127, 127, 127);
  fill(0);
  strokeWeight(0);
  if (enableGrid) {
   stroke(63, 127, 255);
  } else {
   stroke(0);
  }
//  stroke(0);
  //Mouse input
  if (mouseHeld) {
   if (!(mouseX > width - 1 || 0 > mouseX) && !(mouseY > height - 1 || 0 > mouseY)) {
    final int
     x = Math.floorDiv(mouseX, cellWidth),
     y = Math.floorDiv(mouseY, cellHeight);
    if (mouseButton == LEFT) {
     cgol.set(x, y, true);
    } else if (mouseButton == RIGHT) {
     cgol.set(x, y, false);
    }
   }
  }
  //Draw cells
  for (int i = 0; i < boardWidth; i++) {
   for (int j = 0; j < boardHeight; j++) {
    pushMatrix();
    if (cgol.peek()[i][j]) {
     fill(255);
    } else {
     fill(0);
    }
    rect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
    popMatrix();
   }
  }
  if (shouldUpdate && (System.currentTimeMillis() - lastStep) > updateDelay) {
   cgol.step(strangeRules ? ANTI_CONWAY : CONWAY);
   lastStep = System.currentTimeMillis();
  }
 }

 public void keyPressed() {
  if (key == CODED) {
   //
  } else {
   if (key == ' ') {
    shouldUpdate = !shouldUpdate;
   } else if (key == 'r') {
    cgol.generateRandomBoard();
   } else if (key == 'c') {
    cgol.clear();
   } else if (key == '-') {
    updateDelay += 25;
   } else if (key == '=') {
    updateDelay = updateDelay - 25 > 0 ? updateDelay - 25 : updateDelay;
   } else if (key == 'g') {
    enableGrid = !enableGrid;
   } else if (key == 'm') {
    strangeRules = !strangeRules;
   }
  }
 }

 public void mousePressed() {
  mouseHeld = true;
 }

 public void mouseReleased() {
  mouseHeld = false;
 }
}
