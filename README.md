
Requirement:
-----------------------
- Maven
- add role named `admin` to targeted `client`
([keycloak console] select your client -> service account -> add `admin`)
- access token has `realm role` mapped to access token
([keycloak console] add client scope -> User Realm Role -> token claim name = realm_access.roles -> add to access token)
  

Get started
-----------------------
- Run `mvn package`to generate java package,

- Copy `.jar` files inside `target` to your `provider` folder.

- Run `/bin/kc.sh build` 


Features:
------------------------
API format: {url}/realms/{realm}/bulkuser?ids={user_uuid}&ids={user_uuid}

| features | api | remarks |
|----------|-----|---------|
| Query users by ids | http://localhost:8080/realms/dev/bulkuser?ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f&ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f | Can be modified 

 
Troubleshoot
-----------------
Check provider info under `realm-restapi-extension`, is there `bulkuser`, if not there, it might be caused by a missing step in get started instruction.


Notes
------------

- If there is any missing information, you are welcome to dive into the code. It is really a simple code. Free free to submit pull request too.
- Main code is at: `BulkuserResourceProviderFactory.java`


Not supported yet
------------------------
- Cache layer (Query part of the users from cache, and others from database)


