package org.eclipse.scout.apps.contactscout.shared.organization;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IOrganizationTableService extends IService {
    OrganizationTableTablePageData getOrganizationTableTableData(SearchFilter filter);
}
