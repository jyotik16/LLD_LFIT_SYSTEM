# LLD_LFIT_SYSTEM

### https://www.lldcoding.com/design-lld-lift-machine-coding 

### Requirements
- Lift Movement:

  - The lift should move up and down.

  - It should stop at the specified floors.

- User Interaction:

  - Users should be able to request the lift at a particular floor.

  - Users should be able to select the floor they want to go to.

- Internal Logic:  The lift should have internal logic to decide its movement based on user requests.
- Emergency Handling: There should be a way to handle emergency situations, such as opening the doors manually.
- Status Display: Display the current floor and direction of the lift.


```
+------------------+
|      Lift        |
+------------------+
| - id: int        |
| - currentFloor: int |
| - direction: Direction |
| - state: LiftState |
| - movementStrategy: MovementStrategy |
| - door: Door      |
| - display: Display|
| - requests: Queue<Integer> |
+------------------+
| +handleRequest(floor: int): void |
| +move(): void    |
| +setState(state: LiftState): void |
| +setMovementStrategy(strategy: MovementStrategy): void |
| +openDoor(): void|
| +closeDoor(): void|
| +getCurrentFloor(): int |
| +getDirection(): Direction |
+------------------+

+----------------------+
|     LiftController   |
+----------------------+
| - lifts: List<Lift>  |
+----------------------+
| +requestLift(floor: int): void |
| +assignLift(floor: int): Lift  |
+----------------------+

+------------------+
|     Door         |
+------------------+
| - isOpen: boolean|
+------------------+
| +open(): void    |
| +close(): void   |
+------------------+

+------------------+
|    Display       |
+------------------+
| - floor: int     |
| - direction: Direction |
+------------------+
| +show(): void    |
+------------------+

+------------------+
|  LiftState       |<--interface
+------------------+
| +handleRequest(lift: Lift, floor: int): void |
+------------------+

+----------------------+
|  MovingUpState       |
+----------------------+
| +handleRequest(lift: Lift, floor: int): void |
+----------------------+

+----------------------+
|  MovingDownState     |
+----------------------+
| +handleRequest(lift: Lift, floor: int): void |
+----------------------+

+----------------------+
|  StoppedState        |
+----------------------+
| +handleRequest(lift: Lift, floor: int): void |
+----------------------+

+----------------------+
|  MovementStrategy    |<--interface
+----------------------+
| +move(lift: Lift, floor: int): void |
+----------------------+

+-----------------------------+
|  DefaultMovementStrategy    |
+-----------------------------+
| +move(lift: Lift, floor: int): void |
+-----------------------------+

+-----------------------------+
|  EmergencyMovementStrategy  |
+-----------------------------+
| +move(lift: Lift, floor: int): void |
+-----------------------------+

+------------------+
|  Direction (enum)|
+------------------+
| UP, DOWN, IDLE   |
+------------------+

```
