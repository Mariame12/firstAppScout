package org.eclipse.scout.apps.contactscout.shared.person;

import org.eclipse.scout.apps.contactscout.shared.organization.DeleteOrganizationPermission;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IInfoPersonWorkService extends IService {
    InfoPersonWorkFormData prepareCreate(InfoPersonWorkFormData formData);

    InfoPersonWorkFormData create(InfoPersonWorkFormData formData);

    InfoPersonWorkFormData load(InfoPersonWorkFormData formData);

    InfoPersonWorkFormData store(InfoPersonWorkFormData formData);

    InfoPersonWorkTablePageData getInfoPersonWorkTableData(SearchFilter filter);
}
