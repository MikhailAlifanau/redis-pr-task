# redis-pr-task
This repository shows redis and redisson capabilities:

1. Redis database
2. Redis pub/sub
3. Redisson cache
4. Redisson 2nd layer cache
5. Redisson pub/sub

Postman collection for testing: [collection](https://www.getpostman.com/collections/ddf6a78d834b46b32289)    
</br>
Product objects are stored in redis database.
</br>
Customer objects are stored in h2, and utilizing hibernate 2nd layer cache. 
</br>
Products can be published with redis topics.
</br>
Customers can be published with redisson Rtopics.

Data prerequisites:
1. There are 3 customers pre-created as per data.sql file
2. There are no products pre-created, so at least one should be inserted via API.

Application uses 3 separate redis instances for caching, 
DB and 2nd layer cache. Instances can be started
via docker compose in the project. 
Ports for redis deployment in docker can be changed,
if provided ports in current config are occupied. 
