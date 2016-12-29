Creating maven project:
----------------------

1- use command line to create maven proj from archetype:
   mvn archetype:generate

2- choose archetype maven webapp, then enter groupID, artifactID, and the others.

3- now after build successfully, alter pom.xml file to add all needed dependencies, plugins, 
   and source directories.( get all of these from the book examples pom files).

4- now alter the folder structure to follow the book's folder structure:
  
../  
   |
   |__/source
   |   |_____/production
   |   |             |_______/java
   |   |             |_______/resources     
   |   |       
   |   |_____/test
   |          |_______/java
   |                   |______unit  
   |
   |__/web
   |   |______/META-INF
   |   |______/WEB-INF
   |   |       |
   |   |       |_____/jsp
   |   |       |      |_______/views
   |   |       |
   |   |       |  
   |   |       |_____web.xml
   |   |
   |   |______index.jsp
   |
   |
   |
   |__pom.xml


5- finally, import the project into Eclips and start working.   
