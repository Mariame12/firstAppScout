package org.eclipse.scout.apps.contactscout.server.organization;

import org.eclipse.scout.apps.contactscout.shared.organization.IOrganizationTableService;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganizationTableTablePageData;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationTableService implements IOrganizationTableService {
    @Override
    public OrganizationTableTablePageData getOrganizationTableTableData(SearchFilter filter) {
        OrganizationTableTablePageData pageData = new OrganizationTableTablePageData();
// TODO [mariamesasconte] fill pageData.
        return pageData;
    }
}
