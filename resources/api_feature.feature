Feature: Shortest Distance Between Planets
This feature returns the result of the shortest path from planet A, Earth to any other Node/Planet

Scenario: algorithm that will determine the shortest path between Earth and any destination 

Given I lunch the project and files are loaded
Then I call the required services to get the Nodes and Routes that the algorithm will use. all hosted at http://localhost:8080/swagger-ui.html
Then I set the algorithm API with all the Nodes and Routes from DB
Then I set the start point which is at index 0 for earth always
Then I set the destination point which can be any of the nodes
Then I call the API with the destination node that will return a List of Nodes of the path to the Destination

