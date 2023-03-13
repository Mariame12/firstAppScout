package org.eclipse.scout.apps.contactscout.shared.organization;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IOrganisationLookupService extends ILookupService<String> {

}
