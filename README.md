# Login Authentication
## login using username and password 

This is a simple username password based login system which uses bcrypt to encrypt password in the backend and use Sql as database 


## Features

- User can register using his username(required), password(required), phone number
- User can login using the earlier username and password


## EndPoints

Endpoints of the current api are:

- /user - for users to register using username and password 

![image](https://user-images.githubusercontent.com/60959718/124639052-50b7e380-dea9-11eb-80ee-020b2b85a31b.png)
-when request is valid
- respose Status 201

- /authenticate- for users to login using login username and password
 ![image](https://user-images.githubusercontent.com/60959718/124638162-434e2980-dea8-11eb-852f-fe5a23854660.png)
- When credentials are correct
- respose Status 200
- response body
 ![image](https://user-images.githubusercontent.com/60959718/124638496-a63fc080-dea8-11eb-998f-d904202a544b.png)
- when invalid request  
-  respose Status 401
