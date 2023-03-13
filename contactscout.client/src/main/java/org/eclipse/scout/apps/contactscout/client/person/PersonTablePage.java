package org.eclipse.scout.apps.contactscout.client.person;

import org.eclipse.scout.apps.contactscout.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contactscout.client.person.PersonTablePage.Table;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.apps.contactscout.shared.person.IPersonService;
import org.eclipse.scout.apps.contactscout.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.*;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import javax.swing.*;
import java.util.Set;

@Data(PersonTablePageData.class)
public class PersonTablePage extends AbstractPageWithTable<Table> {

    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return "Personnes";
    }

    public class Table extends AbstractTable {

      public PersonIdColumn getPersonIdColumn() {
        return getColumnSet().getColumnByClass(PersonIdColumn.class);
      }

      @Order(1000)
      public class PersonIdColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Identifiant";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }


        @Override
        protected boolean getConfiguredPrimaryKey() {
          return true;
        }

      }

      @Order(2000)
      public class FirstNameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Prenom";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3000)
      public class LastNameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Nom";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4000)
      public class CityColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Villes";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(5000)
      public class CountryColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Pays";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        //@Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return CountryLookupCall.class;
        }

      }

      @Order(6000)
      public class PhoneColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("phone");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

      }

      @Order(6500)
      public class MobileColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Mobile";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }
      @Order(7000)
      public class EmailColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Email";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      /*@Order(8000)
      public class OrganizationColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return "Organisanistion";
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
        //@Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return OrganisationLookupCall.class;
        }

      }*/


      @Override
      protected Class<? extends IMenu> getConfiguredDefaultMenu() {
        return EditMenu.class;
      }

      @Order(1000)
      public class  EditMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Editer";
        }

        @Override
        protected void execAction() {
          PersonForm form = new PersonForm();
          form.setPersonId(getPersonIdColumn().getSelectedValue());
          form.addFormListener(new PersonFormListener());
          form.startModify();
        }
      }

      @Order(2000)
      public class NewMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Nouveau";
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection, TableMenuType.EmptySpace);
        }

        @Override
        protected void execAction() {
          PersonForm form = new PersonForm();
         // form.getOrganizationField().setValue(getOrganizationId());
          form.addFormListener(new PersonFormListener());
          form.startNew();
        }
      }

      @Order(3000)
      public class DeleteMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Delete0");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
        IPersonService service = BEANS.get(IPersonService.class);
        service.delete(getPersonIdColumn().getSelectedValue());
        reloadPage();

        }
      }

      @Order(4000)
      public class NewWorkMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Add0");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
          InfoPersonWorkForm form = new InfoPersonWorkForm();
          form.setPersonId(getPersonIdColumn().getSelectedValue());
          form.startNew();

        }
      }

      @Order(5000)
      public class ViewMenu extends org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("View");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
          PersonCardForm form = new PersonCardForm();
          form.setPersonId(getPersonIdColumn().getSelectedValue());
          form.starRead();

        }
      }
      private class PersonFormListener implements FormListener {

        @Override
        public void formChanged(FormEvent e) {
          // reload page to reflect new/changed data after saving any changes
          if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
            reloadPage();
          }
        }
      }


    }


}
