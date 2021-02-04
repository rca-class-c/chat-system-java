# CHAT SYSTEM JAVA
Consider this new Project structure which has the source folder with the postgres driver

### Details

This current version of the repo has a file structure implemented as per System Architecture [@Masengesho](https://github.com/donatien2020) suggested,
There is a sample for File Management of the messaging group:
Here are simple details

* ***src/models*** : Here we define Models, Enum of our system, there is a File  class with all fields in the postgres files table with a constructor, setters and getter too.
* ***src/repositories*** : Here we define a Repository for our Model, here we define methods to reach directly to the db simply, aha niho dushyira our db queries check the FileRepositoryClass.
* ***src/services*** : Here goes our logic, services join repositories to controllers, data from repository is manipulated here.  Check the FileService  Class 
* ***src/controllers*** : Here goes the controllers now, these join our views (frontend - or the console), to the services, Simply we defined our APIs here. Check the FileController Class
* ***src/views*** : Here goes our views now the frontend part, ibigaragara muri terminal. Check the FileView and the main View Class.
* ***src/config*** : Our global project configurations
* ***src/exceptions*** : Our Custom Exceptions 
* ***src/utils*** : Our Utilities, some common methods we may need and so on.
* ***src/client***: Client Flow
* ***src/server*** : Server Flow
Also Make sure you run the client.Main class in src folder

### Note
remember to choose sdk while running this projects in you local machines ide. it gave me headache

Credentials
```
 username: Didien
 password: hello123
```
