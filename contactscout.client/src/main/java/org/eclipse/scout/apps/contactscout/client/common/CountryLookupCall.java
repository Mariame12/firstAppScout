package org.eclipse.scout.apps.contactscout.client.common;

import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryLookupCall extends LocalLookupCall<String> {
    private static final long serialVersionUID = 1L;

    @Override
    protected List<LookupRow<String>> execCreateLookupRows() {
        List<LookupRow<String>> rows = new ArrayList<>();

        for (String countryCode : Locale.getISOCountries()) {
            Locale country = new Locale("", countryCode);
            rows.add(new LookupRow<>(countryCode, country.getDisplayCountry()));
        }

        return rows;
    }
}
