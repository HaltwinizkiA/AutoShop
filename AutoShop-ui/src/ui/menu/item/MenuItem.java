package ui.menu.item;
import ui.api.IAction;
import ui.menu.Menu;


import java.io.Serializable;

public class MenuItem implements Serializable {
    private String title;
    private Menu nextMenu;
    private IAction action;


  public MenuItem() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Menu getNextMenu() {
    return nextMenu;
  }

  public void setNextMenu(Menu nextMenu) {
    this.nextMenu = nextMenu;

  }

  public IAction getAction() {
    return action;
  }

  public void setAction(IAction action) {
    this.action = action;
  }

  public MenuItem(String title, IAction action, Menu nextMenu ) {
    this.title = title;
    this.nextMenu = nextMenu;
    this.action = action;
  }

  public void doAction() {
      if(action!=null){
        action.execute();
      }
    }

}
