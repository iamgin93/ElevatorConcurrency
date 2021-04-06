# ElevatorConcurrency
This object-oriented multi-threaded java-based elevator controller application can process and respond to user-driven command-line input of the following format:
src1:dest1, src2:dest2, src3:dest3
src4:dest4, src5:dest5
where each src:dest pair represents one elevator journey; src represents the source pick-up floor of passengers for a given set of elevators; and dest represents the drop-off destination floor of passengers.
For example:
1:2, 3:4, 5:1
will result in an elevator pick-up on floor 1 and drop-off on floor 2; with an elevator pick-up on floor 3 and drop-off on floor 4; and with an elevator pick-up on floor 5 and drop-off on floor 1, with each instruction being queued in turn, and processed as soon as the system is able.
