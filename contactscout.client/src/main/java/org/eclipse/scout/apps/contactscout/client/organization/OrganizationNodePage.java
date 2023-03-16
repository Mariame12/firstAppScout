package org.eclipse.scout.apps.contactscout.client.organization;

import org.eclipse.scout.apps.contactscout.client.organization.OrganizationNodePage.Table;
import org.eclipse.scout.apps.contactscout.client.person.InfoPersonWorkTablePage;
import org.eclipse.scout.apps.contactscout.client.person.PersonTablePage;
import org.eclipse.scout.apps.contactscout.shared.organization.IOrganizationNodeService;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganizationNodeTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.List;

@Data(OrganizationNodeTablePageData.class)
public class OrganizationNodePage extends AbstractPageWithNodes {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }
  private String organizationId;

  @FormData
    public String getOrganizationId() {
    return organizationId;
  }
  @FormData
  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }
  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    PersonTablePage personTablePage = new PersonTablePage();
    personTablePage.setOrganizationId(getOrganizationId());
    pageList.add(personTablePage);

  }

    public class Table extends AbstractTable {

    }
}
