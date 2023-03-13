package org.eclipse.scout.apps.contactscout.client.common;

import org.eclipse.scout.apps.contactscout.client.person.PictureUrlForm;
import org.eclipse.scout.apps.contactscout.shared.Icons;
import org.eclipse.scout.apps.contactscout.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ImageFieldMenuType;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

import java.util.Set;

@FormData(value = AbstractUrlImageFieldData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractUrlImageField extends AbstractImageField {

  private String url;

  @FormData
  public String getUrl() {
    return url;
  }

  @FormData
  public void setUrl(String url) {
    this.url = url;
    updateImage();
  }

  @Override
  protected boolean getConfiguredLabelVisible() {
    return false;
  }

  @Override
  protected int getConfiguredGridH() {
    return 5;
  }

  @Override
  protected boolean getConfiguredAutoFit() {
    return true;
  }

  @Override
  protected String getConfiguredImageId() {
    return Icons.PersonSolid;
  }


  protected void updateImage() {
    setImageUrl(this.url);
  }

  @Order(1000)
  public class EditURLMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return "EditUrl";
    }

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.hashSet(ImageFieldMenuType.ImageId, ImageFieldMenuType.ImageUrl, ImageFieldMenuType.Image);
    }

    @Override
    protected void execAction() {
      PictureUrlForm form = new PictureUrlForm();
      String oldUrl = getUrl();
      if (StringUtility.hasText(oldUrl)) {
        form.getUrlField().setValue(oldUrl);
      }

      form.startModify();
      form.waitFor();

      if (form.isFormStored()) {
        setUrl(form.getUrlField().getValue());
        getForm().touch();
      }

    }
  }
}
