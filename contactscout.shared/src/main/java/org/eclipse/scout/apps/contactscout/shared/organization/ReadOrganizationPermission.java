package org.eclipse.scout.apps.contactscout.shared.organization;

import org.eclipse.scout.rt.security.AbstractPermission;

public class ReadOrganizationPermission extends AbstractPermission {
    private static final long serialVersionUID = 1L;

    public ReadOrganizationPermission() {
        super("ReadOrganizationPermission");
    }
}
