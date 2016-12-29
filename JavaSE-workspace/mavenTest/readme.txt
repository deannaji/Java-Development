Maven instructions:
1- create an empty folder for your app.
2- store your .java file in the folder.
3- inside that folder, type: 
     mvn archetype: generate

4- maven will generate the proper folder structure for you, and will provide the pom.xml file.
5- In the folder where pom.xml is located, type: 
     mvn compile

6- Then type:
     mvn package

7- finally, running: ex:
    java -cp target/mavenTest-1.0-SNAPSHOT.jar mavenTest.App



*mavenTest: is the package name, i.e the folder that is in classes folder.
*mavenTest-1.0-SNAPSHOT.jar: is the jar file created in step 6. usually lives in the 
                             Target folder
