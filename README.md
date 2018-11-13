# CGoL
A simple implementation of **C**onway's **G**ame **o**f **L**ife, using the [Processing library](https://processing.org/)

## Pronounciation
#### "Seagull" `/ˈsiːˌɡʌl/`

## Requirements
 - JDK 8+
 - Gradle 4.8+

## Building
`gradle clean build jar`

## Running
`gradle run`

## Usage
#### Keyboard Controls:
 - `[SPACE]` Pause / resume the simulation
 - `r` Generate a random board
 - `c` Clear the board
 - `-` Slow down the simulation speed
 - `=` Increase the simulation speed
 - `m` Switch the ruleset
 - `g` Toggle the background grid
 
#### Mouse Controls:
 - `LEFT` Set a cell
 - `RIGHT` Delete a cell
 
#### Current Rulesets: (_more indev_)
 - [CONWAY](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L14)  Standard ruleset of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules)
 - [ANTI_CONWAY](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L45) Non-standard ruleset that biases toward living cells

## License
This project is licensed under the [M.I.T. License](https://github.com/Matthewacon/CGoL/blob/master/LICENSE)
