package io.github.matthewacon.cgol;

import processing.core.PApplet;

public class Main extends PApplet {
 public static void main(String[] args) {
  PApplet.main(Main.class.getCanonicalName());
 }

 private int
  boardWidth = 50,
  boardHeight = 50,
  cellWidth,
  cellHeight;

 private final CGOL cgol = new CGOL(boardWidth, boardHeight);

 private boolean
  mouseHeld = false,
  shouldUpdate = false;

 private long
  lastStep = 0,
  updateDelay = 800;

 public void settings() {
  size(800, 800, "processing.awt.PGraphicsJava2D");
 }

 public void setup() {
  cellWidth = Math.floorDiv(width, boardWidth);
  cellHeight = Math.floorDiv(height, boardHeight);
//  cgol.generateRandomBoard();
 }

 public void draw() {
  background(127, 127, 127);
  fill(0);
  strokeWeight(0);
//  stroke(63, 127, 255);
  stroke(0);
  //Mouse input
  if (mouseHeld) {
   final int
    x = Math.floorDiv(mouseX, cellWidth),
    y = Math.floorDiv(mouseY, cellHeight);
   if (mouseButton == LEFT) {
    cgol.set(x, y, true);
   } else if (mouseButton == RIGHT) {
    cgol.set(x, y, false);
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
  //Staggered grid updates
  if (shouldUpdate && (System.currentTimeMillis() - lastStep) / (updateDelay + 0d) >= 1d) {
   cgol.step();
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
    updateDelay += 50;
   } else if (key == '=') {
    updateDelay = updateDelay - 50 > 0 ? updateDelay - 50 : updateDelay;
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
