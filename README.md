# Take Home Test API - Nutech Integrasi

#####
an API Contract SIMS PPOB <br>
by Damar Galih Anshary

### Tools Used

* Spring Web (REST API)
* Spring Security (JWT Authentication)
* Spring Actuator
* MySQL
* Jdbc Template
* Cloudinary
* Railway (deployment)


## Configuration

There is some configuration you need to do before running the project

### Database Config
In this project, you need to set your data source by changing/adding these environment variables 

```properties
DB_URL=your-database-url
DB_USERNAME=your-database-username
DB_PASSWORD=your-database-password
```

### Cloudinary config
```properties
CLOUDINARY_NAME=your-cloudinary-cloud-name
CLOUDINARY_API_KEY=your-cloudinary-api-key
CLOUDINARY_API_SECRET=your-cloudinary-api-secret
```

## Running the Application

You can run this application from your favorite IDE or by running the following command:
```bash
./mvnw spring-boot:run
```

## API Documentation

After you run the project, you can check the API documentation with Swagger UI at

```
http://your-host:your-port/swagger-ui/index.html
``` 