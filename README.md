# Defective Chessboard
**Background**

The defective chessboard problem is a common problem used to develop the divide and conquer methodology when it comes to writing algorithms. In the problem we are given a 2<sup>n</sup> by 2<sup>n</sup> chessboard where the top right corner is removed. Given this chessboard we are able to cover the remaining tiles on the board with L-shaped pieces (called trimino) made of three tiles. These tiles can be rotated as needed. The idea is to recursively solve n/2 by n/2 boards until the entir board is solved.  

**Implementation**

The whole program was made using Java and JavaFx for the GUI. In my implementation, I coloured the triminoes with the top-right tile being black. Since the board size grows rather quickly, I can generate up to 2<sup>8</sup> by 2<sup>8</sup> until I encounter isues. Since the tiles are of fixed size, at 256 tiles by 256 tiles it becomes hard to view the entirety of the board. 

**Images of GUI**

<img src="https://user-images.githubusercontent.com/46686623/75642544-df5b8700-5c09-11ea-8ff1-bc90e621cb16.png" width=24%> <img src="https://user-images.githubusercontent.com/46686623/75642530-d36fc500-5c09-11ea-87b8-a25c6410a0b2.png" width="24%"> <img src="https://user-images.githubusercontent.com/46686623/75642552-ea161c00-5c09-11ea-91d9-87d9d8a21232.png" width="24%"> <img src="https://user-images.githubusercontent.com/46686623/75642559-eda9a300-5c09-11ea-870f-b496a4c5a453.png" width="24%">

**Possible Improvements**

Outstanding tasks to improve the program
- [ ] Reduce the size of the array to only necessary size when rotating
- [ ] Added input validation to ensure on `int` are entered
