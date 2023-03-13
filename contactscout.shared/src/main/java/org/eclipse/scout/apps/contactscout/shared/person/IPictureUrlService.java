package org.eclipse.scout.apps.contactscout.shared.person;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPictureUrlService extends IService {
    PictureUrlFormData prepareCreate(PictureUrlFormData formData);

    PictureUrlFormData create(PictureUrlFormData formData);

    PictureUrlFormData load(PictureUrlFormData formData);

    PictureUrlFormData store(PictureUrlFormData formData);
}
