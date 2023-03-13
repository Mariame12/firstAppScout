package org.eclipse.scout.apps.contactscout.client.common;

import org.eclipse.scout.apps.contactscout.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = AbstractAddressBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractAddressBox extends AbstractGroupBox {

  @Override
  protected boolean getConfiguredBorderVisible() {
    return false;
  }

  @Override
  protected int getConfiguredGridColumnCount() {
    return 1;
  }

  @Override
  protected int getConfiguredGridH() {
    return 3;
  }

  @Override
  protected int getConfiguredGridW() {
    return 1;
  }

  public  LocationBox.CityField getCityField() {
    return getFieldByClass( LocationBox.CityField.class);
  }

  public  LocationBox.CountryField getCountryField() {
    return getFieldByClass( LocationBox.CountryField.class);
  }

  public LocationBox getLocationBox() {
    return getFieldByClass(LocationBox.class);
  }

  public StreetField getStreetField() {
    return getFieldByClass(StreetField.class);
  }

  @Order(1000)
  public class StreetField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Quartier");
    }

    @Override
    protected int getConfiguredMaxLength() {
      return 128;
    }
    @Override
    protected void execChangedValue() {
      verifyAllFields();
    }
  }

  @Order(2000)
  public class LocationBox extends AbstractSequenceBox {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Location");
    }

    @Override
    protected boolean getConfiguredAutoCheckFromTo() {
      return false;
    }
    @Order(500)
    public class CityField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Villes");
      }

      @Override
      protected int getConfiguredMaxLength() {
        return 128;
      }

      @Override
      protected byte getConfiguredLabelPosition() {
        return LABEL_POSITION_ON_FIELD;
      }

      @Override
      protected void execChangedValue() {
        verifyAllFields();
      }
    }

    @Order(500)
    public class CountryField extends AbstractSmartField<String> {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Pays");
      }

      @Override
      protected void execChangedValue() {
        verifyAllFields();
      }

      @Override
      protected byte getConfiguredLabelPosition() {
        return LABEL_POSITION_ON_FIELD;
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return CountryLookupCall.class;
      }
    }

  }


  protected void verifyAllFields() {
    boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
    boolean hasCity = StringUtility.hasText(getCityField().getValue());

    getCityField().setMandatory(hasStreet);
    getCountryField().setMandatory(hasStreet || hasCity);
  }


}
