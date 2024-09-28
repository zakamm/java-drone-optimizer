
# Island Explorer

- Authors:
  - [Ayush, Patel](patea237@mcmaster.ca) 
  - [Waqar, Ul-Hassan](waqarulw@mcmaster.ca)
  - [Muhammad, Zaka](zakam@mcmaster.ca)

## Product Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. This project provided valuable experience in teamwork, project management, and software development using agile methodologies. The team's successful implementation of the exploration command center demonstrates our ability to collaborate effectively and deliver high-quality software.

- The `ca.mcmaster.se2aa4.island.team217.Explorer` class implements the command center using Java for a drone simulation game. 
- The `Runner` class allows one to run the command center on a specific map.


## Development Process
- **Agile Developement**: This project was implemented in an iterative and incremental fashion. We conducted regular team meetings to define project goals and prioritize tasks
- **GitHub Issue Tracking**: Used a Kanban board on GitHub to manage tasks, assign responsibilities, and track project progress
- **Documentation**: Documented the team's progress in a project report, which includes the team's thought process, and justification for key design choices
  
## Technical Details
- **Object Oriented Programming**: Adhered to OOP principles for modular and maintainable code
- **SOLID Principles**: Incorporated SOLID principles taught in class for robust and scalable code
- **UML Diagrams**: Utilized UML diagrams (class and sequence diagrams) to visualize the system design and interactions
- **Unit Testing**: Developed and maintained comprehensive unit tests to improve code quality and reliability
  
## How to compile and run the project

### Compiling the project:

```
mosser@azrael a2-template % mvn clean package
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.960 s
[INFO] Finished at: 2024-01-20T18:26:43-05:00
[INFO] ------------------------------------------------------------------------
mosser@azrael a2-template % 
```

This creates one jar file in the `target` directory.

As the project is intended to run in the competition arena, this jar is not executable. 

### Run the project

The project is not intended to be started by the user, but instead to be part of the competition arena. However, one might one to execute their command center on local files for testing purposes.

To do so, we ask maven to execute the `Runner` class, using a map provided as parameter:

```
mosser@azrael a2-template % mvn exec:java -q -Dexec.args="./maps/map03.json"
```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.
