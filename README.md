
Requirement:
-----------------------
- Maven
- add role named `admin` to targeted `client` has  (not resource role or modify `hasRole(AdminRoles.ADMIN);`)
- access token has the role admin mapped
  

Get started
-----------------------
- Run `mvn package`to generate java package,

- Copy `.jar` files inside `target` to your `provider` folder.

- Run `/bin/kc.sh build` 

Features:
------------------------
| features | api | remarks |
|----------|-----|---------|
| Query users by ids | {realm}/bulkuser?ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f&ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f | Can be modified 

 
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


