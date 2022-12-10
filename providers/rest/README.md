# Remember to add "View-User" as roles for client 
and mapped it to the access token
this is to make sure, not every authenticated user can call this function to get everyone info.

also need to make sure it has role of admin in your service account. You can override it to any role you wish to.

Example Realm REST Resource provider
====================================

You can deploy as a module by running:

    $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=org.keycloak.examples.hello-rest-example --resources=target/hello-rest-example.jar --dependencies=org.keycloak.keycloak-core,org.keycloak.keycloak-server-spi,org.keycloak.keycloak-server-spi-private,javax.ws.rs.api"

Then registering the provider by editing `standalone/configuration/standalone.xml` and adding the module to the providers element:

    <providers>
        ...
        <provider>module:org.keycloak.examples.hello-rest-example</provider>
    </providers>

Then start (or restart) the server. Once started open http://localhost:8080/auth/realms/master/hello and you should see the message _Hello master_.
You can also invoke the endpoint for other realms by replacing `master` with the realm name in the above url.