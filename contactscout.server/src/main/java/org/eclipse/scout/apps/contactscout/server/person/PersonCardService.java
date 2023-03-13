package org.eclipse.scout.apps.contactscout.server.person;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class PersonCardService implements IPersonCardService {
    @Override
    public PersonCardFormData prepareCreate(PersonCardFormData formData) {
        if (!ACCESS.check(new CreatePersonCardPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PersonCardFormData create(PersonCardFormData formData) {
        if (!ACCESS.check(new CreatePersonCardPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public PersonCardFormData load(PersonCardFormData formData) {
        if (!ACCESS.check(new ReadPersonCardPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

// TODO [mariamesasconte] add business logic here.
        SQL.selectInto(SQLs.WORK_AND_PERSON_PAGE_SELECT, formData);;
        return formData;
    }

    @Override
    public PersonCardFormData store(PersonCardFormData formData) {
        if (!ACCESS.check(new UpdatePersonCardPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
      SQL.selectInto(SQLs.WORK_PERSON_UPDATE, formData);
        return formData;
    }
}
