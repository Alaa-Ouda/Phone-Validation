# Phone Validation

This project was generated using Angular version 12.2.0 & Spring boot version 2.5.5

# Description

This project is to list and categorize customer phone numbers according to their countries and if they are valid or not.

# Running the application

You need Docker installed in your machine.\
From backend directory run the following to build the image:\
`docker build -t spring/phonevalidation .`\
Then run the app using the docker image you created:\
`docker run -p 8080:8080 spring/phonevalidation`

From frontend directory run the following to build the image:\
`docker build -t angular/phonevalidation .`\
Then run the app using the docker image you created:\
`docker run -p 8081:80 angular/phonevalidation`\
Then go to the browser and visit:\
`http://localhost:8081/`

