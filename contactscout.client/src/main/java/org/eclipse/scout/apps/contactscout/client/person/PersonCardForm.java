package org.eclipse.scout.apps.contactscout.client.person;
import org.eclipse.scout.apps.contactscout.client.common.AbstractAddressBox;
import org.eclipse.scout.apps.contactscout.client.common.AbstractEmailField;
import org.eclipse.scout.apps.contactscout.client.person.PersonCardForm.MainBox.GroupBox;
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
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = PersonCardFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonCardForm extends AbstractForm {

  @FormData
  public String getPersonId() {
    return personId;
  }

  @FormData
  public void setPersonId(String personId) {
    this.personId = personId;
  }

  private String personId ;


  @FormData
  public String getWorkId() {
    return workId;
  }

  @FormData
  public void setWorkId(String workId) {
    this.workId = workId;
  }

  private  String workId;

    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return TEXTS.get("PersonCard");
    }
    @Override
    protected int getConfiguredDisplayHint() {
        return IForm.DISPLAY_HINT_VIEW;
    }


    public MainBox.DetailBox getDetailBox() {
        return getFieldByClass(MainBox.DetailBox.class);
    }

    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public GroupBox getGroupBox() {
        return getFieldByClass(GroupBox.class);
    }

  public MainBox.ModifyButton getModifyButton() {
    return getFieldByClass(MainBox.ModifyButton.class);
  }



  @Order(1000)
    public class MainBox extends AbstractGroupBox {

        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

          protected boolean getConfiguredEnabled() {
            return false;
          }


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
            public class WorkBox extends AbstractGroupBox {
                @Override
                protected String getConfiguredLabel() {
                    return "Travail";
                }
              @Override
              protected boolean getConfiguredEnabled() {
                return false;
              }

                @Order(60)
                public class PositionField extends AbstractStringField {


                    @Override
                    protected String getConfiguredLabel() {
                        return "Position";
                    }
                }

                @Order(60)
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

                @Order(60)
                public class PhoneWorkField extends AbstractStringField {


                    @Override
                    protected String getConfiguredLabel() {
                        return "Phone";
                    }
                }

                @Order(60)
                public class EmailWorkField extends AbstractStringField {


                    @Override
                    protected String getConfiguredLabel() {
                        return "Email";
                    }
                }
            }


        }

        @Order(2000)
        public class DetailBox extends AbstractTabBox {
          protected boolean getConfiguredEnabled() {
            return false;
          }
            @Order(10)
            public class ContactInfoBox extends AbstractGroupBox {

                @Order(40)
                public class PhoneField extends AbstractStringField {


                    @Override
                    protected String getConfiguredLabel() {
                        return "Phone";
                    }
                }

                @Order(40)
                public class MobileField extends AbstractStringField {


                    @Override
                    protected String getConfiguredLabel() {
                        return "Mobile";
                    }
                }

                @Order(40)
                public class EmailField extends AbstractEmailField {
                }


                @Order(10)
                public class AddressBox extends AbstractAddressBox {


                }


            }
        }


      @Order(3000)
      public class ModifyButton extends AbstractButton {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Modifier");
        }

        @Override
        protected void execClickAction() {
         InfoPersonWorkForm form = new InfoPersonWorkForm();
         form.setWorkId(getWorkId());
          form.startModify();
        }
      }
    }




    //public void starModify() {startInternalExclusive(new ModifyHandler());}
  public void starRead() {
    startInternalExclusive(new ReadHandler ());
  }
  /*  public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            PersonCardFormData formData = new PersonCardFormData();
            exportFormData(formData);
            formData = BEANS.get(IPersonCardService.class).load(formData);
            importFormData(formData);

            setEnabledPermission(new UpdatePersonCardPermission());
        }

        @Override
        protected void execStore() {
            PersonCardFormData formData = new PersonCardFormData();
            exportFormData(formData);
            formData = BEANS.get(IPersonCardService.class).store(formData);
            importFormData(formData);
        }
    }*/
  public class ReadHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      PersonCardFormData formData = new PersonCardFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonCardService.class).load(formData);
      importFormData(formData);

    }


  }
}
 /* public void startNew() {
        startInternal(new NewHandler());
    }*/

   /* public class NewHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            PersonCardFormData formData = new PersonCardFormData();
            exportFormData(formData);
            formData = BEANS.get(IPersonCardService.class).prepareCreate(formData);
            importFormData(formData);

            setEnabledPermission(new CreatePersonCardPermission());
        }

        @Override
        protected void execStore() {
            PersonCardFormData formData = new PersonCardFormData();
            exportFormData(formData);
            formData = BEANS.get(IPersonCardService.class).create(formData);
            importFormData(formData);
        }
    }*/
