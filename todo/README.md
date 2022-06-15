# Online-TODO-List
`A simple online TODO list supporting back-end .`

`The application storing necessary data in H2 Database.`

`Built with java 8 and spring boot framework. `

`The functionality  should be demonstrable through API testing (postman)`



## Resources and samples

1.local port : 9032

2.H2 database console url: [h2-console url](http://localhost:9032/api/todoItems/h2-console)

3.postman collection name     : To-Do Online tool.postman_collection

4.postman collection location : \projects\Online-ToDo-Application

5.input json sample
    `{
    "task": "Workout Daily",
    "done": false
    }`


### Execution steps:

1.Navigate to project folder

2.Execute mvn clean install

3.Execute java -jar C:\Lincy\projects\Online-ToDo-Application\todo\target\todo-0.0.1-SNAPSHOT.jar

4.Application should be up and running

5.import To-Do Echo.postman_collection to the postman

6.trigger user save api

7.trigger get/save/update/delete functionalities

