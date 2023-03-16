package org.eclipse.scout.apps.contactscout.server.organization;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.organization.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.UUID;

import static org.eclipse.scout.apps.contactscout.server.sql.SQLs.ORGANISATION_DELETE_TABLE;
import static org.eclipse.scout.apps.contactscout.server.sql.SQLs.ORGANIZATION_SELECT;

public class OrganizationService implements IOrganizationService {
    @Override
    public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
        OrganizationTablePageData pageData = new OrganizationTablePageData();
// TODO [mariamesasconte] fill pageData.
        String sql = SQLs.ORGANIZATION_PAGE_SELECT + SQLs.ORGANIZATION_PAGE_DATA_SELECT_INTO;
        SQL.selectInto(sql, new NVPair("page", pageData));
        return pageData;
    }

    @Override
    public OrganizationFormData prepareCreate(OrganizationFormData formData) {
        if (!ACCESS.check(new CreateOrganizationPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public OrganizationFormData create(OrganizationFormData formData) {
        if (!ACCESS.check(new CreateOrganizationPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
        if (StringUtility.isNullOrEmpty(formData.getOrganizationId())) {
            formData.setOrganizationId(UUID.randomUUID().toString());
        }

        SQL.insert(SQLs.ORGANIZATION_INSERT, formData);
        return store(formData);
    }

    @Override
    public OrganizationFormData load(OrganizationFormData formData) {
        if (!ACCESS.check(new ReadOrganizationPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
        SQL.selectInto(ORGANIZATION_SELECT, formData);
        return formData;
    }

    @Override
    public OrganizationFormData store(OrganizationFormData formData) {
        if (!ACCESS.check(new UpdateOrganizationPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

        SQL.update(SQLs.ORGANIZATION_UPDATE, formData);
        return formData;
    }
    @Override
  public void  delete(String OrganizationId){
      if(!ACCESS.check(new DeleteOrganizationPermission())){
        throw new VetoException(TEXTS.get("AuthorizationFailed"));
      }
      SQL.delete( SQLs.WORK_PERSON_DELETE_TABLE_FROM_ORGANISATION,   new NVPair("organizationId",OrganizationId));
      SQL.delete(SQLs.ORGANISATION_DELETE_TABLE, new NVPair("organizationId", OrganizationId));
    }
}
