package org.eclipse.scout.apps.contactscout.server.person;

import org.eclipse.scout.apps.contactscout.server.sql.SQLs;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.UUID;

public class InfoPersonWorkService implements IInfoPersonWorkService {
    @Override
    public InfoPersonWorkFormData prepareCreate(InfoPersonWorkFormData formData) {
        if (!ACCESS.check(new CreateInfoPersonWorkPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        return formData;
    }

    @Override
    public InfoPersonWorkFormData create(InfoPersonWorkFormData formData) {
        if (!ACCESS.check(new CreateInfoPersonWorkPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        if (StringUtility.isNullOrEmpty(formData.getWorkId())) {
            formData. setWorkId(UUID.randomUUID().toString());
        }
        SQL.insert(SQLs.WORK_PERSON_INSERT, formData);

        return formData;
    }

    @Override
    public InfoPersonWorkFormData load(InfoPersonWorkFormData formData) {
        if (!ACCESS.check(new ReadInfoPersonWorkPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        SQL.selectInto(SQLs.WORK_PERSON_SELECT, formData);
        return formData;
    }

    @Override
    public InfoPersonWorkFormData store(InfoPersonWorkFormData formData) {
        if (!ACCESS.check(new UpdateInfoPersonWorkPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [mariamesasconte] add business logic here.
        SQL.update(SQLs.WORK_PERSON_UPDATE, formData);
        return formData;
    }

    @Override
    public InfoPersonWorkTablePageData getInfoPersonWorkTableData(SearchFilter filter) {
        InfoPersonWorkTablePageData pageData = new InfoPersonWorkTablePageData();
        String sql = SQLs.WORK_PERSON_PAGE_SELECT + SQLs.WORK_PERSON_PAGE_DATA_SELECT_INTO;
        SQL.selectInto(sql, new NVPair("page", pageData));
        return pageData;
    }
  @Override
  public void  delete(String workId ){
    if(!ACCESS.check(new DeleteWorkPermission())){
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.delete(SQLs.WORK_PERSON_DELETE_TABLE , new NVPair("workID",workId));

  }

}
