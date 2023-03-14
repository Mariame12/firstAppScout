package org.eclipse.scout.apps.contactscout.client.person;
import org.eclipse.scout.apps.contactscout.client.common.*;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
//import org.eclipse.scout.rt.client.ui.form.fields.smartfield.SmartField;



@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {


  private String personId;

  private String parentId;


  @Override
  public Object computeExclusiveKey() {
    return getPersonId();
  }




  @FormData
  public String getPersonId() {
    return personId;
  }


  @FormData
  public void setPersonId(String personId) {
    this.personId = personId;
  }


  @Override
  protected int getConfiguredDisplayHint() {
    return IForm.DISPLAY_HINT_DIALOG;
  }


  @Override
  protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
    return "Person";
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }


  public MainBox.GeneralBox getGroupBox() {
    return getFieldByClass(MainBox.GeneralBox.class);
  }
  //public MainBox.GeneralBox.OrganizationField  getOrganizationField(){return getFieldByClass(MainBox.GeneralBox.OrganizationField.class);}
  public MainBox.GeneralBox.FirstNameField getFirstNameField(){return getFieldByClass(MainBox.GeneralBox.FirstNameField.class);}
  public MainBox.GeneralBox.LastNameField getLastNameField(){return getFieldByClass(MainBox.GeneralBox.LastNameField.class) ;}

  public MainBox.GeneralBox.ParentIdField getParentIdField() {
    return getFieldByClass(MainBox.GeneralBox.ParentIdField.class);
  }

  public MainBox.GeneralBox.RelationTypeField getRelationTypeField() {return getFieldByClass(MainBox.GeneralBox.RelationTypeField.class);}
  @Order(1000)
  public class MainBox extends AbstractGroupBox {
    @Order(1000)
    public class GeneralBox extends AbstractGroupBox {
      /*@Order(10)
      public class PictureUrlField extends AbstractStringField {


        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }


      @Order(20)
      public class PictureField extends AbstractUrlImageField {


      }*/


      @Order(10)
      public class FirstNameField extends AbstractStringField {


        @Override
        protected String getConfiguredLabel() {
          return "Prenom";
        }
      }


      @Order(20)
      public class LastNameField extends AbstractStringField {


        @Override
        protected String getConfiguredLabel() {
          return "Nom";
        }
      }


      @Order(30)
      public class DateOfBirthField extends AbstractDateField {


        @Override
        protected String getConfiguredLabel() {
          return "Date de naissance";
        }
      }


      @Order(40)
      public class GenderGroup extends AbstractRadioButtonGroup<String> {


        @Override
        protected String getConfiguredLabel() {
          return "Genre";
        }


        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return GenderCodeType.class;
        }
      }
      @Order(50)
      public class PhoneField extends AbstractStringField {


        @Override
        protected String getConfiguredLabel() {
          return "Phone";
        }
      }
      @Order(60)
      public class MobileField extends AbstractStringField {


        @Override
        protected String getConfiguredLabel() {
          return "Mobile";
        }
      }
      @Order(70)
      public class EmailField extends AbstractEmailField {
      }


      @Order(80)
      public class AddressBox extends AbstractAddressBox {


      }
      /*@Order(90)
      public class OrganizationField extends AbstractSmartField<String>{

        @Override
        protected String getConfiguredLabel() {
          return "Organization";
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return OrganisationLookupCall.class;
        }
      }*/
      @Order(90)
      public class RelationTypeField extends AbstractSmartField<String> {
        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return RelationCodeType.class;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }
      }

      @Order(95)
      public class ParentIdField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IdPARENT");
        }

        @Override
        protected int getConfiguredMaxLength() {
          return 128;
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }

      @Order(100)
      public class NotesBox extends AbstractNotesBox {


      }


      protected boolean execValidate() {
        boolean noFirstName = StringUtility.isNullOrEmpty(getFirstNameField().getValue());
        boolean noLastName = StringUtility.isNullOrEmpty(getLastNameField().getValue());


        if (noFirstName && noLastName) {
          getFirstNameField().requestFocus();


          throw new VetoException(TEXTS.get("MissingName"));
        }


        return true;
      }


    }



    @Order(2000)
    public class OkButton extends AbstractOkButton {
      @Override
      protected String getConfiguredLabel() {
        return "Sauvegarder";
      }
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
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonService.class).prepareCreate(formData);
      importFormData(formData);


      setEnabledPermission(new CreatePersonPermission());
    }


    @Override
    protected void execStore() {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonService.class).create(formData);
      importFormData(formData);
    }
  }


  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonService.class).load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdatePersonPermission());
    }


    @Override
    protected void execStore() {
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonService.class).store(formData);
      importFormData(formData);
    }
  }




}





