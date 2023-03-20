package org.eclipse.scout.apps.contactscout.server.person;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class PersonService implements IPersonService {
  private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Override
    public PersonTablePageData getPersonTableData(SearchFilter filter,String organizationId) {
        PersonTablePageData pageData = new PersonTablePageData();
      StringBuilder sql = new StringBuilder(SQLs.PERSON_PAGE_SELECT);
      // if an organization is defined, restrict result set to persons that are linked to it
      if (StringUtility.hasText(organizationId)) {
        sql.append(String.format("JOIN WORK_PERSON wp  ON p.person_id=wp.person_id  " +
          "WHERE wp.organization_id = '%s' ", organizationId));
      }
      sql.append(SQLs.PERSON_PAGE_DATA_SELECT_INTO);
      SQL.selectInto(sql.toString(), new NVPair("page", pageData));


        return pageData;
    }

    @Override
    public PersonFormData prepareCreate(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PersonFormData create(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
       // return formData;
      if (StringUtility.isNullOrEmpty(formData.getPersonId())) {
        formData.setPersonId(UUID.randomUUID().toString());
      }
      SQL.insert(SQLs.PERSON_INSERT, formData);

      return store(formData);
    }

    @Override
    public PersonFormData load(PersonFormData formData) {
        if (!ACCESS.check(new ReadPersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

// TODO [mariamesasconte] add business logic here.
      SQL.selectInto(SQLs.PERSON_SELECT, formData);
      Map<String, Object []> pers = new TreeMap<>();

      Object [][] rows= SQL.select(SQLs.PERSON_SELECT_FULL);

        for (Object[]row:rows) {
          Object [] t = new Object[]{row[1], row[2]};
          pers.put((String)row[0], t);
        }
        LOG.info("Entrees : {}", pers.entrySet());
        for (Map.Entry<String, Object[]> entry:pers.entrySet()){
          LOG.info("Cles : {}", entry.getKey());
          for (Object  i :entry.getValue()) {
            LOG.info("Valeur : {}", i);
          }
        }


     // }

      return formData;
    }

    @Override
    public PersonFormData store(PersonFormData formData) {
        if (!ACCESS.check(new UpdatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

      SQL.update(SQLs.PERSON_UPDATE, formData);
        return formData;
    }
    @Override
  public void delete(String PersonId) {
    if (!ACCESS.check(new DeletePersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
      StringHolder workId= new StringHolder();
    SQL.selectInto(SQLs.PERSON_SELECT_WORK_ID, new NVPair("workId", workId), new NVPair("personId", PersonId) );
    SQL.delete(SQLs.WORK_PERSON_DELETE_TABLE ,new NVPair("workId", workId.getValue()));
    SQL.delete(SQLs.PERSON_DELETE_TABLE, new NVPair("personId", PersonId));

  }


}
