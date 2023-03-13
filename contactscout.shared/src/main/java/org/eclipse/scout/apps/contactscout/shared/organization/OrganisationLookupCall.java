package org.eclipse.scout.apps.contactscout.shared.organization;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class OrganisationLookupCall extends LookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService<String>> getConfiguredService() {
    return IOrganisationLookupService.class;
  }
}
