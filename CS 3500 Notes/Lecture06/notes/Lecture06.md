# Lecture 06: Model-View-Controller (MVC) Pattern and Builder Pattern
###### May 16th - May 18th, 2022

## What Is Model-View-Controller?
- Separates responsibility for holding data, visualizing data, and dispatching events


- **Controller**: Take user input and decide what to do with it
- **View**: Display an interface to the user
- **Model**: Domain specific information

## What Does a Model Do?
- Holds onto data
- Enforces information integrity
- Business logic: How do we manipulate the data?

### Model for Tic-Tac-Toe
#### Object-Oriented Analysis
- Play a few sample games
  - **Absolutely _DEMOLISH_ the other side of the room**
  - You can _tie_
- Operations for Model of Tic-Tac-Toe
  - Place an X/O
  - ~~Is the game over?~~ Find out if the game is over
    - Who won? (determine who won)
  - Is this move valid?
  - ~~Whose turn is it?~~ Find out whose turn it is
  - Get a piece at a given location
    - Get the contents of the board

##### Move Validity
A move is invalid if:
- Place is already taken
- Not your turn
- Place in an invalid cell
- Place anything other than X or O
- Play after game ends

> Note: Operations should be _VERBS!_ not data representations

## Connect4
- 7x6 only
- line up 4 to win
- 2 player

## ConnectN
- Any dimensions (7x6 by default)
- Line up N to win
- 2 players

### Operations for ConnectN
- Place a coin in a column
- Find out if the game is over
- Find the winner of the game
- Get whatever is at a certain row/col
- Other stuff but I forgot to pay attention

### Invalid Things
- Invalid number-to-line-up for those dimensions
- Invalid moves
  - Placing in a full column
  - Placing in an invalid space
- Playing out of turn
- Asking who won when the game isn't over
- Asking who is next when the game is over

Honestly I forgot to write the rest of these notes b/c I was talking on Discord. See the code 
for details!

## Builder Pattern
- Issue: Slightly configurable constructors w/o writing a bajillion constructors
  - Class to create objects
- Builders are `static` inner classes

### Creating a Builder:
- Initialize defaults
- Write setters in the form of:
```java

public ClassName param(T t) {
  // mutate the parameter
  this.t = t;
  return this; // return the object
}

```
- Write a build function:
```java
public ClassName build() {
  return new ClassName(...params...);
}
```

# Next Piece of the Lecture:

## Controllers and Mocks
### The Controller

- Controllers take input from the user and decide what to do
- Glue/Brains of the system
- Controls how and when the model is used
- Controls what must be shown to the view and when
- Controls what action must be taken when user interacts with system
- Controls the sequence of operations in an application

- Model - OFFERS operations
  - Oblivious to when they are needed/how to present results
- View - Presents results
  - Oblivious to how they are produced/what to do next
- Controller - delegates to either
  - Oblivious to how the model completes operations or how specifically the view shows output

### How to Handle User Input
#### Synchronous Controllers
- Determines the sequence of operations of the system
- Determines when and how the system interacts with something external
- Suitable for pre-baked _rules-based_ applications, like games
- Challenges:
  - How to truly separate controller from view so each one is replaceable?
  - What to do when system is _reactive_ in nature (to user input)?
    - Interaction decides program behavior, not controller
    - Examples: Microsoft Word, IntelliJ

#### Asynchronous Controllers
- Controller _gets_ control when external input is given
- Controller methods are called as a response to external input
- Such methods in the controller are called _callbacks_
- GUI programs work this way
  - User clicks a button => controller method is called
  - In OO GUIs, callbacks are often _wrapped_ in classes called listeners

#### Who creates the controller, model, view, etc.?
- The `main` method!
```java
public class MainRunner {
  public static void main(String[] args) {
    IModel theModel = makeAModel();
    IView aView = makeAView();
    IController theController = makeController(theModel, aView);
    theController.go();
  }
}
```

- Simple I/O
  - Printing to console: `System.out.print()`/`System.out.println()`
  - Reading from keyboard with: `Scanner`
  - How do we test this? - MOCKS