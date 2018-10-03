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

I have included 2 JUnit tests in this project.

findShortestPathDijkstrasTest;
            This was the most important part of my project to test. It involves creating a few nodes and connecting them together, while giving each "Path" a set "Cost". Dijkstras algorithm then goes through all of the route options to get from one specified start point to another specified end point, at the end selecting the with the lowest "cost".

GraphNodeTest2;
            This test checked to see that creating graphNodes and linking them together worked. 

## Built With

* [Intellij IDEA](https://www.jetbrains.com/idea/) - The IDE environment used
* [Github](https://github.com/) - Version Control System used


## Authors

* **Daniel Collins**
** Dylan Morrissey**

This was a solo project completed as part of my studies in the WIT I.o.T Degreee
