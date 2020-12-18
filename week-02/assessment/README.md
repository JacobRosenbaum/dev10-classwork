# Gomoku

## Requirements
* Can set up two players.
* For a human player, collect their name. (A random player's name is randomly generated.)
* For each stone placement, use the player's name to ask questions. Since the random player doesn't require input, the UI should display stone placement and the results of placement. (Random player placement may fail since they don't know what they're doing.)
* Re-prompt on failed placement. The game must not proceed until a player has made a valid move.
* Display the final result of the game.
* Give the option to play again.

## Technical Requirements
* All rules are modeled inside the Gomoku class. You may not modify Gomoku, Player, HumanPlayer, RandomPlayer, Stone, or Result.
* Add your code to the project provided. Be sure to use sensible class names, method names, and packages.
* At least one class beyond the App class is required.
* Stones use 0-based rows and columns.

## Plan
* Create a ui package
* Create a startGame method that initiates the game. It will be in a Do/While loop and run while boolean Exit = false.
* Inside the loop I'll prompt the user to select a player one and a player two. If it is a human, I'll prompt them to enter their name. If it's a random, I'll call the getName method in RandomPlayer to generate the player's name. 
* Print out "Randomizing" and Assign the players to type Gomoku. Call the getCurrent method to see who goes first, print out their name using the getMethod. 
* All the above will be in a SetUp class
* Create a GamePlay class
* In a do while loop, Prompt the currentPlayer (IF Human) for a row number and column number, ELSE call the generateMove method Loop.
* Loop runs until isOver = true;
* Print Board with updated state... TODO figure out how to do this
* If the attempted move in invalid, print the correct error message and re-prompt the user for information or re-call the generateMove method
* Once isOver = true, print a message using the getName method + wins!
* Ask if they want to play again - if yes, continue, if no, boolean Exit = true and the loop ends. 


## Tasks
* [ ] Read through code: Time Estimation - 2 Hours
* [ ] Figure out how to print the board with the updated state: Time Estimation - 5 Hours
* [ ] Figure out Package, Class, and Method structure - including how to run game in App using a proper do while loop: Time Estimation - 1 Hour
* [ ] Figure out if move is invalid and how to print the correct message: Time Estimation - 2 Hours
