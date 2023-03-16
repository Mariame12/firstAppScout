package org.eclipse.scout.apps.contactscout.client.organization;

import org.eclipse.scout.apps.contactscout.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contactscout.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.apps.contactscout.client.person.PersonTablePage;
import org.eclipse.scout.apps.contactscout.shared.organization.IOrganizationService;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.apps.contactscout.shared.person.IInfoPersonWorkService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.List;
import java.util.Set;

@Data(OrganizationTablePageData.class)
public class OrganizationTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return false;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IOrganizationService.class).getOrganizationTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return"Organization";
    }
  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    OrganizationNodePage childPage = new OrganizationNodePage();
    childPage.setOrganizationId(getTable().getOrganizationIdColumn().getValue(row));
    return childPage;
  }
    public class Table extends AbstractTable {
      public OrganizationIdColumn getOrganizationIdColumn() {
        return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
      }
        @Order(1000)
        public class OrganizationIdColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return "identiantOrg";
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
        public class NameColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return "Nom Organisation";
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }

        }

        @Order(3000)
        public class  CityColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return "ville";
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(4000)
        public class CountryColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return ("Pays");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }

            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                return CountryLookupCall.class;
            }
        }

      @Order(1000)
      public class EditerMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("edit");
        }

        @Override
        protected void execAction() {
          final OrganizationForm form = new OrganizationForm();
          form.setOrganizationId(getOrganizationIdColumn().getSelectedValue());
          form.addFormListener(e -> {
            if (FormEvent.TYPE_CLOSED == e.getType() && form.isFormStored()) {
              reloadPage();
            }
          });

          form.startModify();

        }
      }

      @Order(2000)
      public class NewMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("New");
        }
        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection, TableMenuType.EmptySpace);
        }


        @Override
        protected void execAction() {
          final OrganizationForm form = new OrganizationForm();
          form.addFormListener(e -> {
            if (FormEvent.TYPE_CLOSED == e.getType() && form.isFormStored()) {
              reloadPage();
            }
          });

          form.startNew();

        }
      }

      @Order(3000)
      public class DeleteMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Delete");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
          IOrganizationService service = BEANS.get(IOrganizationService.class);
          service.delete(getOrganizationIdColumn().getSelectedValue());
          reloadPage();

        }
      }

    }
}
