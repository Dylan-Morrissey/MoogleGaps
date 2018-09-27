# MoogleGaps

This Project is based on a Route Finder, which lets the user select a start and end point from a pre-selected list of locations. The user then searches by either the shortest route or the quickest route. The screen then displays the shortest/quickest route, giving the user the destinations needed to pass through to get to the destination. Also displays an estimated journey time, route length and the average speed the user would be travelling.

### Prerequisites

For this project, I used the Intellij IDEA Java IDE, which allowed me to write the programme and also run the code, including running the tests

### The Project Contents

This project involved using a custom linked list structure with a costed path setup applied. The Linked List has been constructed using Nodes, where every object is first created, then inserted into their respective Node templates, and each node is then added to a linked list (essentially having each node "point" to a different node). These "links" are then given a value (length, speed, etc) and these contribute to the costed path structure.
I used 3 core objects, "CostedPath", "GraphNode",  and "GraphLink". The "GraphNode" objects hold the locations, the "GraphLink" objects hold the roads between the locations, and the "CostedPath" objects hold the values of the length of the road and the speed allowed to be travelled on the road.
The "Shortest Path" option was implemented using Dijkstra's algorithm.

For this project I also included FXMl User Friendly Interface front page, which was coded using some HTML and CSS. This front end section was designed and implemented using the JavaFX Scene Builder application.

## Tests

I have included 6 JUnit tests in this project. 
BedTest;
      This test attempts to add a newly created Bed to a Room in a Property. If said Property and/or Room does not exist, the programme will catch this and give the user an error message and ask them to try again. It also tests that the adding to the list function works by creating multiple properties and rooms and adding them to their specific lists.
      
PropertyListTest;
      This test simply checks that adding to a list works, by creating multiple Property objects and adding them to a Property List. The validation then does a search of certain addresses in the list to ensure the properties have been added.
      

StudentListTest;
      This test does the same as the PropertyListTest above, but for Students
      
      
PropertyTest;
      This test checks the construstor and the getters and setters for creating a Property Object, ensureing proper validation techniques have been implemented.
      
      
RoomTest;
      This test does the same as the Property Test above, but for Room Objects
      
StudentTest;
      This test does the same as both the PropertyTest and RoomTest above, but for Students.


## Built With

* [Intellij IDEA](https://www.jetbrains.com/idea/) - The IDE environment used
* [Github](https://github.com/) - Version Control System used


## Authors

* **Daniel Collins**

This was a solo project completed as part of my studies in the WIT I.o.T Degreee
