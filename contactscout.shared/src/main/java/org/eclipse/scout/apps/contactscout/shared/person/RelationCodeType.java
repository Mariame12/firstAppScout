package org.eclipse.scout.apps.contactscout.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;





public class RelationCodeType extends AbstractCodeType<String, String> {

    private static final long serialVersionUID = 1L;

    public static final String ID = "RelationType ";

    @Override
    public String getId() {
        return ID;
    }

    @Order(1000)
    public static class ParentCode extends AbstractCode<String> {
        private static final long serialVersionUID = 1L;
        public static final String ID = "PARENT";

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("PARENT");
        }

        @Override
        public String getId() {
            return ID;
        }
    }

    @Order(2000)
    public static class ChildrenCode extends AbstractCode<String> {
        private static final long serialVersionUID = 1L;
        public static final String ID = "CHILD";

        @Override
        protected String getConfiguredText() {
            return TEXTS.get("Child0");
        }

        @Override
        public String getId() {
            return ID;
        }
    }
}


