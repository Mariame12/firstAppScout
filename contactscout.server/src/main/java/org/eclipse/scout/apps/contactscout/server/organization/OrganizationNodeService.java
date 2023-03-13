package org.eclipse.scout.apps.contactscout.server.organization;

import org.eclipse.scout.apps.contactscout.shared.organization.IOrganizationNodeService;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganizationNodeTablePageData;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationNodeService implements IOrganizationNodeService {
    @Override
    public OrganizationNodeTablePageData getOrganizationNodeTableData(SearchFilter filter) {
        OrganizationNodeTablePageData pageData = new OrganizationNodeTablePageData();
// TODO [mariamesasconte] fill pageData.
        return pageData;
    }
}
