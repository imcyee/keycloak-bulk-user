
Requirement:
-----------------------
- Maven
- targeted `client` has role named `admin` (not resource role or modify `hasRole(AdminRoles.ADMIN);`)
- access token has the role admin mapped
  

Get started
-----------------------
Generate java package,

Run `mvn package`

then,

Copy `.jar` files inside `target` to your `provider` folder.


Features:
------------------------
| features | api | remarks |
|----------|-----|---------|
| Query users by ids | {realm}/bulkuser?ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f&ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f | Can be modified 

 

Not supported yet
------------------------
- Cache layer (Query part of the users from cache, and others from database)


Notes
------------

- If there is any missing information, you are welcome to dive into the code. It is really a simple code. Free free to submit pull request too.
- Main code is at: `BulkuserResourceProviderFactory.java`

