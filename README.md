# notes-service

## Languages and tools

* Spring framework with Java
* Spring dependencies: Data JPA, Lombok, cache, security
* postgres database
* h2 for testing profile database


## Funcionalities

* Login as admin
* Delete a note as admin
* Create a note


## Functionals Requirements

* Cache to all crud operatins
* Note cannot has empty, blank or null properties
* Admin has in memory auth cretendials(not secure)
* Pageable for get methods that returns many notes


## Whats to improve?

* Develop more tests
* use jpa authentication for admins 
