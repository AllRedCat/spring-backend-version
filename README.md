# Spring Boot Backend Version

## About:
This is a restructuring of the VagasLivre® API using Spring Boot
### Objetive:
Get a better approach for the RESTful API that is originally made in TypeScript with Elysia.
In this version, it gets a stronger structure and maintainable of the original API.

#### Todo:

- [ ] Add Cripto to User's password
- [ ] Add Auth services
- [ ] Add remaining routes
- [ ] Add App Users Controllers
- [ ] Add WebHook controller
- [ ] Add WebSocket system

## Structure:
```
src/main/java/com/vagaslivre/backend
│
├── BackendApplication.java         # Main class
│
├── Controller/                     # REST controllers (entrypoints)
│
├── DTO/                            # DTOs (Data Transfer Objects)
│
├── enums/                          # Enumerations
│
├── Exception/                      # Response exceptions handling
│
├── Mapper/                         # Mappers (Entity <-> DTO conversion)
│
├── Model/                          # JPA Entities (represent tables)
│
├── Repository/                     # Spring Data Repositories (DB access)
│
├── Service/                        # Business logic
│
└── config/                         # Configuration (CORS, Security, etc)
```