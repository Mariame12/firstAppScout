package org.eclipse.scout.apps.contactscout.client.person;

import org.eclipse.scout.apps.contactscout.client.common.AbstractEmailField;
import org.eclipse.scout.apps.contactscout.client.person.InfoPersonWorkForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contactscout.client.person.InfoPersonWorkForm.MainBox.GroupBox;
import org.eclipse.scout.apps.contactscout.client.person.InfoPersonWorkForm.MainBox.OkButton;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.apps.contactscout.shared.person.CreateInfoPersonWorkPermission;
import org.eclipse.scout.apps.contactscout.shared.person.IInfoPersonWorkService;
import org.eclipse.scout.apps.contactscout.shared.person.InfoPersonWorkFormData;
import org.eclipse.scout.apps.contactscout.shared.person.UpdateInfoPersonWorkPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = InfoPersonWorkFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class InfoPersonWorkForm extends AbstractForm {
  @FormData
  public String getWorkId() {
    return workId;
  }

  @FormData
  public void setWorkId(String workId) {
    this.workId = workId;
  }

  private String workId;
    @FormData
    public String getPersonId() {
        return personId;
    }

    @FormData
    public void setPersonId(String personId) {
        this.personId= personId;
    }

    private String personId;

    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return TEXTS.get("InformationSurLeTravail0");
    }

    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public GroupBox getGroupBox() {
        return getFieldByClass(GroupBox.class);
    }

    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

 


  @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {
            @Order(40)
            public class PositionField extends AbstractStringField {

                @Override
                protected String getConfiguredLabel() {
                    return "Position";
                }
            }

            @Order(40)
            public class OrganisationField extends AbstractSmartField<String> {

                @Override
                protected String getConfiguredLabel() {
                    return "Organization";
                }

                @Override
                protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                    return OrganisationLookupCall.class;
                }
            }

            @Order(40)
            public class PhoneWorkField extends AbstractStringField {

                @Override
                protected String getConfiguredLabel() {
                    return "Phone";
                }
            }

            @Order(40)
            public class EmailWorkField extends AbstractEmailField {


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
            InfoPersonWorkFormData formData = new InfoPersonWorkFormData();
            exportFormData(formData);
            formData = BEANS.get(IInfoPersonWorkService.class).prepareCreate(formData);
            importFormData(formData);

            setEnabledPermission(new CreateInfoPersonWorkPermission());
        }

        @Override
        protected void execStore() {
            InfoPersonWorkFormData formData = new InfoPersonWorkFormData();
            exportFormData(formData);
            formData = BEANS.get(IInfoPersonWorkService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            InfoPersonWorkFormData formData = new InfoPersonWorkFormData();
            exportFormData(formData);
            formData = BEANS.get(IInfoPersonWorkService.class).load(formData);
            importFormData(formData);

            setEnabledPermission(new UpdateInfoPersonWorkPermission());
        }

        @Override
        protected void execStore() {
            InfoPersonWorkFormData formData = new InfoPersonWorkFormData();
            exportFormData(formData);
            formData = BEANS.get(IInfoPersonWorkService.class).store(formData);
            importFormData(formData);
        }
    }
}
