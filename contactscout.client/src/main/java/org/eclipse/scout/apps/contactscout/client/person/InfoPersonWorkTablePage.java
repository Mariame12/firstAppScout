package org.eclipse.scout.apps.contactscout.client.person;

import org.eclipse.scout.apps.contactscout.client.person.InfoPersonWorkTablePage.Table;
import org.eclipse.scout.apps.contactscout.shared.organization.OrganisationLookupCall;
import org.eclipse.scout.apps.contactscout.shared.person.IInfoPersonWorkService;
import org.eclipse.scout.apps.contactscout.shared.person.InfoPersonWorkTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.Set;

@Data(InfoPersonWorkTablePageData.class)
public class InfoPersonWorkTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IInfoPersonWorkService.class).getInfoPersonWorkTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [mariamesasconte] verify translation
        return TEXTS.get("InformationSurLeTravail");
    }

    public class Table extends AbstractTable {

        public EmailColumn getEmailColumn() {
            return getColumnSet().getColumnByClass(EmailColumn.class);
        }

        public OrganisationColumn getOrganizationColumn() {
            return getColumnSet().getColumnByClass(OrganisationColumn.class);
        }

        public PersonColumn getPersonColumn() {
            return getColumnSet().getColumnByClass(PersonColumn.class);
        }

        public PhoneColumn getPhoneColumn() {
            return getColumnSet().getColumnByClass(PhoneColumn.class);
        }

        public PositionColumn getPositionColumn() {
            return getColumnSet().getColumnByClass(PositionColumn.class);
        }

        public WorkIdColumn getWorkIdColumn() {
            return getColumnSet().getColumnByClass(WorkIdColumn.class);
        }

        @Order(1000)
        public class WorkIdColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("WordId");
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
        public class PositionColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Position");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(3000)
        public class OrganisationColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Organisation");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
            @Override
            protected boolean getConfiguredVisible() {
                return false;
            }
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                return OrganisationLookupCall.class;
            }
        }

        @Order(4000)
        public class PhoneColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Phone0");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(5000)
        public class EmailColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Email0");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(6000)
        public class PersonColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Personne");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }
        }
    }
}
