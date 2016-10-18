# Login Test

Programming Examination that uses Spring technology stack and MySQL

## Documentaton

To read more about the project documentation, you can acces */test/v2/swagger-ui.html* after building and running this project.

## Deployement

http://104.199.175.110/test

### Endpoints

**GET:** http://104.199.175.110/test/dates <br>
Retrieves all of the unique dates (ignoring time) in the table. All of the dates are sorted ascending.

**GET:** http://104.199.175.110/test/users <br>
Retrieves all of the unique users for which there is a login record between the start and end date.

**GET:** http://104.199.175.110/test/logins <br>
Retrieves an object where the key is the user name and the value is the number of times a user logged on between the start and the end date.

You can also access all of the resources in one place, accessible at: <br>
http://104.199.175.110/test/swagger-ui.html

## Author

* *Sam Christoffer Cruz*

