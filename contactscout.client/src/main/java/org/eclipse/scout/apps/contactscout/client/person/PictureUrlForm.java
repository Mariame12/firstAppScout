package org.eclipse.scout.apps.contactscout.client.person;

import org.eclipse.scout.apps.contactscout.client.person.PictureUrlForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contactscout.client.person.PictureUrlForm.MainBox.UrlBox ;
import org.eclipse.scout.apps.contactscout.client.person.PictureUrlForm.MainBox.OkButton;
import org.eclipse.scout.apps.contactscout.shared.Icons;
import org.eclipse.scout.apps.contactscout.shared.person.CreatePictureUrlPermission;
import org.eclipse.scout.apps.contactscout.shared.person.IPictureUrlService;
import org.eclipse.scout.apps.contactscout.shared.person.PictureUrlFormData;
import org.eclipse.scout.apps.contactscout.shared.person.UpdatePictureUrlPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.accordion.AbstractAccordion;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.accordionfield.AbstractAccordionField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = PictureUrlFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PictureUrlForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return "PictureUrl";
    }


    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public UrlBox  getUrlBox () {
        return getFieldByClass(UrlBox .class);
    }

    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

    public UrlBox.InfoField getInfoField() {
        return getFieldByClass(UrlBox.InfoField.class);
    }

    public UrlBox.UrlField getUrlField() {
        return getFieldByClass(UrlBox.UrlField.class);
    }

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class UrlBox  extends AbstractGroupBox {
            @Order(1000)
            public class UrlField extends AbstractStringField {
                @Override
                protected boolean getConfiguredLabelVisible() {
                    return false;
                }
                @Override
                protected boolean getConfiguredStatusVisible() {
                    return false;
                }

                @Override
                protected int getConfiguredGridW() {
                    return 2;
                }

            }

            @Order(2000)
            public class InfoField extends AbstractHtmlField {

                @Override
                protected int getConfiguredGridW() {
                    return 2;
                }

                @Override
                protected boolean getConfiguredLabelVisible() {
                    return false;
                }

                @Override
                protected boolean getConfiguredStatusVisible() {
                    return false;
                }
                @Override
                protected boolean getConfiguredGridUseUiHeight() {
                    return true;
                }
                @Override
                protected void execInitField() {
                    setValue(HTML.fragment(HTML.icon(Icons.Info), HTML.bold(" " + TEXTS.get("PleaseNote") + ": "), TEXTS.get("SecurityUrlRestrictedMsg")).toHtml());
                }
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
       /* @Override
        protected void execLoad() {
            PictureUrlFormData formData = new PictureUrlFormData();
            exportFormData(formData);
            formData = BEANS.get(IPictureUrlService.class).prepareCreate(formData);
            importFormData(formData);

            setEnabledPermission(new CreatePictureUrlPermission());
        }

        @Override
        protected void execStore() {
            PictureUrlFormData formData = new PictureUrlFormData();
            exportFormData(formData);
            formData = BEANS.get(IPictureUrlService.class).create(formData);
            importFormData(formData);
        }*/
    }

    public class ModifyHandler extends AbstractFormHandler {
       /* @Override
        protected void execLoad() {
            PictureUrlFormData formData = new PictureUrlFormData();
            exportFormData(formData);
            formData = BEANS.get(IPictureUrlService.class).load(formData);
            importFormData(formData);

            setEnabledPermission(new UpdatePictureUrlPermission());
        }

        @Override
        protected void execStore() {
            PictureUrlFormData formData = new PictureUrlFormData();
            exportFormData(formData);
            formData = BEANS.get(IPictureUrlService.class).store(formData);
            importFormData(formData);
        }*/
    }
}
