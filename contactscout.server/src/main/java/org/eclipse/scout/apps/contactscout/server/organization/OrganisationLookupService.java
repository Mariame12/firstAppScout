package org.eclipse.scout.apps.contactscout.server.organization;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.organization.IOrganisationLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;
import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import java.util.List;

public class OrganisationLookupService extends AbstractSqlLookupService<String> implements IOrganisationLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return SQLs.ORGANIZATION_LOOKUP;
  }


}
