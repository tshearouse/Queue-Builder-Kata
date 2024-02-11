# Queue Builder Kata

### Background
This kata starts with some existing code. There are several queue implementations. 
They are not good queue implementations, but that's not the point. 
The goal is to refactor this into a decent SDK. 
Any refactoring is allowed, but be sure you maintain good unit test coverage.

### Goal
Create a factory or builder that makes it easy to construct a queue. 
Whatever pattern you choose, it should be capable of accepting several options. Include at least the following:
 - Queue type (LIFO, FIFO, or Unique)
 - Capacity (the maximum number of items the queue is allowed to hold)
 - Priority (should we build a Priority Queue?)

### Bonus Goal
There's a lot of code duplication in this library. How much can you improve it?
