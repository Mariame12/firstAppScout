package org.eclipse.scout.apps.contactscout.client.contact;

import java.util.List;

import org.eclipse.scout.apps.contactscout.client.organization.OrganizationTablePage;
import org.eclipse.scout.apps.contactscout.client.person.PersonTablePage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.apps.contactscout.shared.Icons;

/**
 * @author mariamesasconte
 */
@Order(1000)
public class ContactOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new PersonTablePage());
    pageList.add(new OrganizationTablePage());
  }

  @Override
  protected String getConfiguredTitle() {
    return "Contacts";
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Folder;
  }
}
