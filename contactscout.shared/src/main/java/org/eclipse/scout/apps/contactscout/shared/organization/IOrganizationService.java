package org.eclipse.scout.apps.contactscout.shared.organization;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IOrganizationService extends IService {
    OrganizationTablePageData getOrganizationTableData(SearchFilter filter);

    OrganizationFormData prepareCreate(OrganizationFormData formData);

    OrganizationFormData create(OrganizationFormData formData);

    OrganizationFormData load(OrganizationFormData formData);

    OrganizationFormData store(OrganizationFormData formData);
}
