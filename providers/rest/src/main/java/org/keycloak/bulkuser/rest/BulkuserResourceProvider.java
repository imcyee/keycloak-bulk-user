package org.keycloak.bulkuser.rest;

import org.jboss.resteasy.annotations.cache.NoCache;

import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.models.jpa.entities.UserEntity;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserProvider;
import org.keycloak.models.RealmModel; 
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.AdminRoles; 
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager; 
import org.keycloak.services.resource.RealmResourceProvider; 
 
import java.util.ArrayList;
import java.util.List; 
import java.util.Objects; 
import java.util.stream.Stream;
 
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder; 
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.ws.rs.GET;
//import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
 
import static org.keycloak.models.jpa.PaginationUtils.paginateQuery;
import static org.keycloak.utils.StreamsUtil.closing;

/**
 * @author imcyee
 */
public class BulkuserResourceProvider implements RealmResourceProvider {

    private KeycloakSession session;
    private final AuthenticationManager.AuthResult auth;

    public BulkuserResourceProvider(KeycloakSession session) {
        this.session = session;
        this.auth = new AppAuthManager.BearerTokenAuthenticator(session).authenticate();
    }

    @Override
    public Object getResource() {
        return this;
    }

    // public  void isAuthenticated() { 
    //     isAuthenticated(auth);
    // }

    public  void isAuthenticated(AuthenticationManager.AuthResult authResult) {
        if (authResult == null) {
            throw new NotAuthorizedException("Bearer token required");
        }
    }
    
    public  void hasRole(String role) {
        // isAuthenticated(auth);
        if (auth.getToken().getRealmAccess() == null
        		|| !auth.getToken().getRealmAccess().isUserInRole(role)) {
            throw new ForbiddenException("You do not have the required credentials for this action");
        }
    }


    public void isAdmin(KeycloakSession session) {
    	isAuthenticated(auth);
    	hasRole(AdminRoles.ADMIN);
    }
 

    /**
     * the actual get request 
     * 
     */
    // example: http://localhost:8080/realms/dev/bulkuser?ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f&ids=ba7dba10-9aa9-4c1a-bcc8-f601852bea5f
    @GET 
    @NoCache
    @Consumes(MediaType.APPLICATION_JSON)
    public Stream<UserRepresentation> get(
    	// For post
        // BulkuserBodyRepresentation idsFromBody,
        @QueryParam("ids") final List<String> ids
    ) {
        // System.out.println(idsFromBody.ids.size());

        // authorization
    	isAdmin(session);
    	
        // get realm
    	RealmModel realm = session.getContext().getRealm();

        // query users 
        EntityManager em = session.getProvider(JpaConnectionProvider.class).getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> queryBuilder = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = queryBuilder.from(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> attributePredicates = new ArrayList<>();
        predicates.add(root.get("id").in(ids));
        predicates.add(builder.equal(root.get("realmId"), realm.getId()));
        queryBuilder.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<UserEntity> query = em.createQuery(queryBuilder);
        UserProvider users = session.users();
        Integer firstResult = 0;
        Integer maxResults = 10;
        Stream<UserModel> userModels = closing(
            paginateQuery(query, firstResult, maxResults).getResultStream())
                .map(userEntity -> users.getUserById(realm, userEntity.getId()))
                .filter(Objects::nonNull);
        return userModels.filter(user -> true)
                .map(
                    user -> {
                        UserRepresentation userRep = ModelToRepresentation.toBriefRepresentation(user);
                        return userRep;
                    }
                );
    }

    @Override
    public void close() { }

}
