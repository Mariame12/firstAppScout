package org.eclipse.scout.apps.contactscout.client.common;

import org.eclipse.scout.apps.contactscout.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.accordion.AbstractAccordion;
import org.eclipse.scout.rt.client.ui.form.fields.accordionfield.AbstractAccordionField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = AbstractNotesBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractNotesBox extends AbstractGroupBox {
  @FormData
  public String getNotes() {
    return notes;
  }

  @FormData
  public void setNotes(String notes) {
    this.notes = notes;
  }

  private String notes;
  

  public NotesField getNotesField() {
    return getFieldByClass(NotesField.class);
  }

  @Override
  protected String getConfiguredLabel() {
    return TEXTS.get("Notes");
  }


  @Order(1000)
  public class NotesField extends AbstractStringField {
    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected int getConfiguredGridH() {
      return 3;
    }

    @Override
    protected boolean getConfiguredMultilineText() {
      return true;
    }

  }
}

