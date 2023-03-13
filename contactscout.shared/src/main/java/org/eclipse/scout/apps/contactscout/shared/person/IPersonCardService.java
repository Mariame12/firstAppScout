package org.eclipse.scout.apps.contactscout.shared.person;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPersonCardService extends IService {
    PersonCardFormData prepareCreate(PersonCardFormData formData);

    PersonCardFormData create(PersonCardFormData formData);

    PersonCardFormData load(PersonCardFormData formData);

    PersonCardFormData store(PersonCardFormData formData);
}
