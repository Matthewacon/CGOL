# CGoL
A simple implementation of **C**onway's **G**ame **o**f **L**ife, using the [Processing library](https://processing.org/)

![Demo](https://github.com/Matthewacon/CGoL/blob/master/cgol-demo.gif)

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
 - [Conway](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L15)  Standard ruleset of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules)
 - [Anti Conway](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L47) Non-standard ruleset that biases toward living cells
 - [Ulam-Wurburton](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L73) Standard ruleset of [Ulam-Wurburton's Cellular Automaton](https://en.wikipedia.org/wiki/Ulam%E2%80%93Warburton_automaton
 - [Anti Ulam-Wurburton](https://github.com/Matthewacon/CGoL/blob/master/src/java/cgol/src/io/github/matthewacon/cgol/Main.java#L107) Non-standard ruleset that kills overpopulated cells

## License
This project is licensed under the [M.I.T. License](https://github.com/Matthewacon/CGoL/blob/master/LICENSE)
