# asteroid
System Design :
![image](https://github.com/diasgsputra/asteroid/assets/60877754/79803df7-39b6-41cb-bad3-5686771a4e7c)



Description :
This application is showing closest asteroid to earth. Calling to NASA Open API.

Prerequisites :
JDK 17,
Maven 4, Springboot 3.5.4

Installation and Setup :
Follow these steps to run the application:
Clone the repository
```git clone git@github.com:diasgsputra/asteroid.git```

Open the project with your editor

Build the Application
```mvn clean install```

Run the Application
```mvn spring-boot:run```

Once the application is running, you can access it at :
```http://localhost:8080```

Configuration
Change the database from server to local if you want to use your local database (in application.properties) 
and import the asteroids.sql to your local database

This repository is deployed at :
Back-end :
```https://asteroid-fe.onrender.com/api/asteroids```

Front-end website :
```https://asteroid-fe.onrender.com/```

Documentation
See the documentation at :
```https://asteroid-lq4t.onrender.com/swagger-ui/index.html#/asteroid-controller```

Because the deployed service using free server, so you may experience some slow response issue :
1. At the first time we hit the endpoint, it may take quite long time because it need more time when first run. After that the response will be faster.
2. Service 'Mapping one year asteroids' (2nd api in system design) will run slowly because it takes time to get data from NASA API then insert into database (looping per 7 days until 1 year)
3. If you face error 500 at service 'Get asteroid by distance'(3rd api in system design), it may because the database got to much request. You can try several minutes later.

