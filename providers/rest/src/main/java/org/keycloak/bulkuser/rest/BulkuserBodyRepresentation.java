 package org.keycloak.bulkuser.rest;

import java.io.Serializable;
import java.util.List;

/**
 * @author imcyee
 */
public class BulkuserBodyRepresentation implements Serializable {
    protected List<String> ids;
    
    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
