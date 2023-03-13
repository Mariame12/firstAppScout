package org.eclipse.scout.apps.contactscout.server.person;

import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;

public class PictureUrlService implements IPictureUrlService {
    @Override
    public PictureUrlFormData prepareCreate(PictureUrlFormData formData) {
        if (!ACCESS.check(new CreatePictureUrlPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PictureUrlFormData create(PictureUrlFormData formData) {
        if (!ACCESS.check(new CreatePictureUrlPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PictureUrlFormData load(PictureUrlFormData formData) {
        if (!ACCESS.check(new ReadPictureUrlPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PictureUrlFormData store(PictureUrlFormData formData) {
        if (!ACCESS.check(new UpdatePictureUrlPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }
}
