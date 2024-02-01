Application Documentation 

Requirements: 
- IDE Intellij
- PostgreSQL
- Postman
- Java development kit
- Apache 



The source code in src/main/java 
- in the main package you will find these packages
	- config -> classes for configuration springboot app
	- Controller -> Rest Controller classes to handle the Api endpoints
	- DTO -> contains the requests and responses
	- Entity -> contains the classes that represents the entities of the application
	- Repository -> for the repository interfaces to access data
	- Service -> Service classes to handle the functions and logic

- In the application.yml you will find the database connection



API Endpoints: 
- Register new User using POST method (Admin user will automatically stored in the database after running 
		     the application/ you can modify it in SpringNoteAppApplication)
- Login using POST method (it will return the token)

- Create Folder using POST method (using the token generated from the login request)

- Retrieve the list of folders for a specific user using GET method (using the token for the authorization)

- Retrieve a specific folder by its ID using GET method  

Note: you can import the collection folder to postman and test the above endpoints


