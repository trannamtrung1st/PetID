/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.data.daos;

import javax.persistence.EntityManager;
import petid.data.models.BreedInfo;

/**
 *
 * @author TNT
 */
public class BreedInfoDAO extends BaseDAO<BreedInfo> {
    
    public BreedInfoDAO(EntityManager eManager) {
        super(eManager);
    }
    
}
