package org.eclipse.scout.apps.contactscout.client.person;

import org.eclipse.scout.apps.contactscout.client.common.AbstractAddressBox;
import org.eclipse.scout.apps.contactscout.client.common.AbstractEmailField;
import org.eclipse.scout.apps.contactscout.client.person.PersonCardForm.MainBox.GroupBox;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.apps.contactscout.shared.person.*;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
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
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.Set;

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

  private String personId;


  @FormData
  public String getWorkId() {
    return workId;
  }

  @FormData
  public void setWorkId(String workId) {
    this.workId = workId;
  }

  private String workId;

  @Override
  protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
    return TEXTS.get("PersonCard");
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return IForm.DISPLAY_HINT_VIEW;
  }

  public MainBox.DetailBox.ChieldBox getChieldBox() {
    return getFieldByClass(MainBox.DetailBox.ChieldBox.class);
  }

  public MainBox.DetailBox.ChieldBox.ChieldTableField getChieldTableField() {
    return getFieldByClass(MainBox.DetailBox.ChieldBox.ChieldTableField.class);
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

  public MainBox.GroupBox.RelationTypeField getRelationTypeField() {
    return getFieldByClass(MainBox.GroupBox.RelationTypeField.class);
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
      public class RelationTypeField extends AbstractSmartField<String> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Relation");
        }

        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return RelationCodeType.class;
        }
      }
    }

    @Order(2000)
    public class DetailBox extends AbstractTabBox {
      @Order(10)
      public class ContactInfoBox extends AbstractGroupBox {
        @Override
        protected String getConfiguredLabel() {
          return "Contacts";
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

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

      @Order(20)
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

      @Order(2000)
      public class ChieldBox extends AbstractGroupBox {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Children");
        }

        @Order(1000)
        public class ChieldTableField extends AbstractTableField<ChieldTableField.Table> {
          //@Override

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredGridH() {
            return 6;
          }

          @ClassId("7aedb3a5-77e2-45a9-ae84-8260654739c5")
          public class Table extends AbstractTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            public DateOfBirthColumn getDateOfBirthColumn() {
              return getColumnSet().getColumnByClass(DateOfBirthColumn.class);
            }

            public FirstNameColumn getFirstNameColumn() {
              return getColumnSet().getColumnByClass(FirstNameColumn.class);
            }

            public GenderGroupColumn getGenderColumn() {
              return getColumnSet().getColumnByClass(GenderGroupColumn.class);
            }

            public LastNameColumn getLastNameColumn() {
              return getColumnSet().getColumnByClass(LastNameColumn.class);
            }

            public PhoneColumn getPhoneColumn() {
              return getColumnSet().getColumnByClass(PhoneColumn.class);
            }

            @Order(1000)
            public class FirstNameColumn extends AbstractStringColumn {
              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Firstname");
              }

              @Override
              protected int getConfiguredWidth() {
                return 100;
              }
            }

            @Order(2000)
            public class LastNameColumn extends AbstractStringColumn {
              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Lastname0");
              }

              @Override
              protected int getConfiguredWidth() {
                return 100;
              }
            }

            @Order(3000)
            public class DateOfBirthColumn extends AbstractStringColumn {
              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("DateOfBirth");
              }

              @Override
              protected int getConfiguredWidth() {
                return 100;
              }
            }

            @Order(4000)
            public class GenderGroupColumn extends AbstractStringColumn {
              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Gender");
              }

              @Override
              protected int getConfiguredWidth() {
                return 100;
              }
            }

            @Order(5000)
            public class PhoneColumn extends AbstractStringColumn {
              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Phone1");
              }

              @Override
              protected int getConfiguredWidth() {
                return 100;
              }
            }

            @Order(6000)
            public class NewMenu extends AbstractMenu {
              @Override
              protected String getConfiguredText() {
                return TEXTS.get("Ajout");
              }

              @Override
              protected void execAction() {
                PersonForm form = new PersonForm();
                form.getRelationTypeField().setValue(RelationCodeType.ChildrenCode.ID);
                form.getParentIdField().setValue(getPersonId());
                form.startNew();
              }

              @Override
              protected Set<? extends IMenuType> getConfiguredMenuTypes() {
                return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection, TableMenuType.EmptySpace);
              }
            }

          }


        }


      }
    }


    @Order(3000)
    public class ModifyButton extends AbstractButton {
      protected boolean getConfiguredEnabled() {
        return true;
      }

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

  public void starRead() {

    startInternalExclusive(new ReadHandler());
  }

  public class ReadHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      PersonCardFormData formData = new PersonCardFormData();
      exportFormData(formData);
      formData = BEANS.get(IPersonCardService.class).load(formData);
      importFormData(formData);
      if (getRelationTypeField().getValue().equals(RelationCodeType.ChildrenCode.ID)) {
        getChieldBox().setVisible(false);
      }

    }


  }
}
