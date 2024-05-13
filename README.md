# Connect 4 with AI Agent
**Introduction**
This project implements the classic game Connect 4 with an AI agent capable of making strategic moves using the Minimax algorithm with and without alpha-beta pruning. Connect 4 is a two-player connection game in which the players choose a color and then take turns dropping colored discs into a grid. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs.

# Algorithms
**Minimax:** A decision-making algorithm used in two-player games to minimize the possible loss for a worst-case scenario. In Connect 4, Minimax evaluates the game tree by recursively exploring all possible moves to a certain depth and choosing the best move based on a scoring function.
**Minimax with Alpha-Beta Pruning:** An enhancement of the Minimax algorithm that reduces the number of nodes evaluated in the game tree by pruning branches that are guaranteed to be worse than previously examined branches. This optimization significantly improves the performance of the algorithm, especially in games with high branching factors like Connect 4.

# Assumptions & Details
**State Representation:** The state of the Connect 4 game is represented by six integers, each integer representing a row in the game grid.
**GUI Visualization:** The game tree is visualized using a graphical user interface (GUI) to demonstrate the decision-making process of the AI agent.
Heuristic Evaluation Function: The estimation function used to evaluate the utility of a game state includes various factors such as the number of potential winning moves, blocking opponent's winning moves, number of threes and twos, control of center and edges, vertical stacks, and empty spaces near existing discs.
**Optimization:** To optimize both algorithms, a HashMap<Long, Integer> named "explored" is utilized to store previously explored states and their estimates. This prevents the unnecessary traversal of an explored node tree if it is encountered again from a different order of moves. Hashing is performed to generate unique keys for each game state using a predefined technique involving random numbers.
Data Structures: Various data structures are employed in the implementation including HashMaps, ArrayLists, int arrays, and HashSet for efficient storage and retrieval of game states and related information.

# Usage
**Compile:** Compile the Java files using any Java compiler.
**Run:** Execute the compiled Java program to start the Connect 4 game with the AI agent.
**GUI:** The graphical user interface will display the game board and the AI agent's moves as it evaluates the game tree using the selected algorithm.
**Interact:** Play against the AI agent or watch the AI agent play against itself.

# Implementation Details
**Data Structures Used**
**ParentMap:** HashMap<T, T> - Stores parent-child relationships between game states.
**Tree:** HashMap<T, ArrayList<T>> - Represents the game tree structure with states and their child states.
**Explored:** HashMap<Long, Integer> - Stores previously explored states and their estimates.
**NextStates:** ArrayList<T> - Holds the next possible states from a given state.
**Rows:** int[] - Represents the rows of the Connect 4 game grid.

# Classes
**State:** Represents the state of the Connect 4 game with methods for calculating the utility, generating child states, and performing other operations.
**Hasher:** Generates unique hash keys for game states using random numbers and hashing techniques.

# Conclusion
This Connect 4 project with an AI agent demonstrates the implementation of Minimax and Minimax with alpha-beta pruning algorithms for decision-making in a game scenario. The use of heuristic evaluation functions and efficient data structures enhances the performance and strategic capabilities of the AI agent, making it a challenging opponent for players.

# Contributors
- [Omar Mahmoud](https://github.com/OmarMahmoud11)

- [Karim Fathy](https://github.com/karimfathy054)

- [Mohamed Amr](https://github.com/MohamedAmr982)
