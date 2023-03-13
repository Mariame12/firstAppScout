package org.eclipse.scout.apps.contactscout.server.person;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.security.DeleteCustomColumnPermission;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PersonService implements IPersonService {
    @Override
    public PersonTablePageData getPersonTableData(SearchFilter filter) {
        PersonTablePageData pageData = new PersonTablePageData();

      // if an organization is defined, restrict result set to persons that are linked to it
      /*if (StringUtility.hasText(organizationId)) {
        sql.append(String.format("WHERE LOWER(organization_id) LIKE LOWER('%s') ",
          organizationId));
      }*/
      String sql = SQLs.PERSON_PAGE_SELECT + SQLs.PERSON_PAGE_DATA_SELECT_INTO;
      SQL.selectInto(sql, new NVPair("page", pageData));


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
        return formData;
    }

    @Override
    public PersonFormData store(PersonFormData formData) {
        if (!ACCESS.check(new UpdatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
      SQL.update(SQLs.PERSON_UPDATE, formData);
        return formData;
    }
    @Override
  public void delete(String PersonId) {
    if (!ACCESS.check(new DeletePersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
// TODO [mariamesasconte] add business logic here.
    SQL.delete(SQLs.PERSON_DELETE_TABLE, new NVPair("personId", PersonId));

  }


}
