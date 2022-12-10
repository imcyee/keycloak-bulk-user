
Requirement:
-----------------------
Maven

Make sure the targeted `client` has `admin` role* permission.
Also has mapped role to your access token

Get started
-----------------------
Run `mvn package` to generate java package.

Copy jar files inside `target` to your provider folder.


Features:
------------------------
Query users by ids
{realm}/bulkuser?ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f&ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f 


Not supported yet
------------------------
Cache layer (Query part of the users from cache, and others from database)


If there is any missing information, you are welcome to dive into the code. It is really simple to read and modify too.

* not resource role if you need resource role you have to modify the code