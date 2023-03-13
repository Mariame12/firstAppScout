package org.eclipse.scout.apps.contactscout.server.organization;

import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(IServerSession.class)
public class OrganisationLookupCallTest {
// TODO [mariamesasconte] add test cases

    protected OrganisationLookupCall createLookupCall() {
        return new OrganisationLookupCall();
    }

    @Test
    public void testGetDataByAll() {
        OrganisationLookupCall call = createLookupCall();
// TODO [mariamesasconte] fill call
        List<? extends ILookupRow<String>> data = call.getDataByAll();
// TODO [mariamesasconte] verify data
    }

    @Test
    public void testGetDataByKey() {
        OrganisationLookupCall call = createLookupCall();
// TODO [mariamesasconte] fill call
        List<? extends ILookupRow<String>> data = call.getDataByKey();
// TODO [mariamesasconte] verify data
    }

    @Test
    public void testGetDataByText() {
        OrganisationLookupCall call = createLookupCall();
// TODO [mariamesasconte] fill call
        List<? extends ILookupRow<String>> data = call.getDataByText();
// TODO [mariamesasconte] verify data
    }
}
