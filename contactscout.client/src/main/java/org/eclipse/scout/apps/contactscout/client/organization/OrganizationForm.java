package org.eclipse.scout.apps.contactscout.client.organization;

import org.eclipse.scout.apps.contactscout.client.common.AbstractAddressBox;
import org.eclipse.scout.apps.contactscout.client.common.AbstractEmailField;
import org.eclipse.scout.apps.contactscout.client.common.AbstractNotesBox;
import org.eclipse.scout.apps.contactscout.client.common.AbstractUrlImageField;
import org.eclipse.scout.apps.contactscout.client.organization.OrganizationForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contactscout.client.organization.OrganizationForm.MainBox.GeneralBox;
import org.eclipse.scout.apps.contactscout.client.organization.OrganizationForm.MainBox.OkButton;
import org.eclipse.scout.apps.contactscout.client.person.PersonForm;
import org.eclipse.scout.apps.contactscout.shared.organization.CreateOrganizationPermission;
import org.eclipse.scout.apps.contactscout.shared.organization.IOrganizationService;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganizationFormData;
import org.eclipse.scout.apps.contactscout.shared.organization.UpdateOrganizationPermission;
import org.eclipse.scout.apps.contactscout.shared.person.IPersonService;
import org.eclipse.scout.apps.contactscout.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = OrganizationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class OrganizationForm extends AbstractForm {
  private String workId;
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
  public Object computeExclusiveKey() {
    return getOrganizationId();
  }
    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return TEXTS.get("Organization");
    }
  @Override
  protected int getConfiguredDisplayHint() {
    return IForm.DISPLAY_HINT_VIEW;
  }

    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public GeneralBox getGroupBox() {
        return getFieldByClass(GeneralBox.class);
    }

    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

    public MainBox.DetailsBox.ContactInfoBox getContactInfoBox() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.class);
    }

    public MainBox.DetailsBox getDetailsBox() {
        return getFieldByClass(MainBox.DetailsBox.class);
    }

    public GeneralBox.NameField getNameField() {
        return getFieldByClass(GeneralBox.NameField.class);
    }

    public GeneralBox.PictureField getPictureField() {
        return getFieldByClass(GeneralBox.PictureField.class);
    }

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
      @Order(1000)
      public class GeneralBox extends AbstractGroupBox {
        @Order(1000)
        public class PictureField extends AbstractUrlImageField {
          @Override
          protected int getConfiguredGridH() {
            return 4;
          }

          @Override
          protected double getConfiguredGridWeightY() {
            return 0;
          }
        }

        @Order(2000)
        public class NameField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return "Nom";
          }

          @Override
          protected boolean getConfiguredMandatory() {
            return true;
          }

        }
      }

      @Order(1500)
      public class DetailsBox extends AbstractTabBox {
        @Order(1000)
        public class ContactInfoBox extends AbstractGroupBox {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Contactinfo");
          }

          @Order(1000)
          public class AddressBox extends AbstractAddressBox {
          }

          @Order(20)
          public class PhoneField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Phone");
            }
          }

          @Order(30)
          public class EmailField extends AbstractEmailField {
          }
        }

        @Order(20)

        public class NotesBox extends AbstractNotesBox {
        }


      }

      @Order(2000)
      public class OkButton extends AbstractOkButton {

      }

      @Order(3000)
      public class CancelButton extends AbstractCancelButton {

      }
    }
        public void startModify() {
            startInternalExclusive(new ModifyHandler());
        }

        public void startNew() {
            startInternal(new NewHandler());
        }

        public class NewHandler extends AbstractFormHandler {
            @Override
            protected void execLoad() {
                OrganizationFormData formData = new OrganizationFormData();
                exportFormData(formData);
                formData = BEANS.get(IOrganizationService.class).prepareCreate(formData);
                importFormData(formData);

                setEnabledPermission(new CreateOrganizationPermission());
            }

            @Override
            protected void execStore() {
                OrganizationFormData formData = new OrganizationFormData();
                exportFormData(formData);
                formData = BEANS.get(IOrganizationService.class).create(formData);
                importFormData(formData);
            }
        }

        public class ModifyHandler extends AbstractFormHandler {
            @Override
            protected void execLoad() {
                OrganizationFormData formData = new OrganizationFormData();
                exportFormData(formData);
                formData = BEANS.get(IOrganizationService.class).load(formData);
                importFormData(formData);

                setEnabledPermission(new UpdateOrganizationPermission());
            }

            @Override
            protected void execStore() {
                OrganizationFormData formData = new OrganizationFormData();
                exportFormData(formData);
                formData = BEANS.get(IOrganizationService.class).store(formData);
                importFormData(formData);
            }
        }
}


