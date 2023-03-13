package org.eclipse.scout.apps.contactscout.shared.person;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IPersonService extends IService {
    PersonTablePageData getPersonTableData(SearchFilter filter);

    PersonFormData prepareCreate(PersonFormData formData);

    PersonFormData create(PersonFormData formData);

    PersonFormData load(PersonFormData formData);

    PersonFormData store(PersonFormData formData);
    void delete (String PersonId);
}
