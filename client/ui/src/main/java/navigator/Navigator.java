package navigator;


import menu.Menu;
import utility.TextWorker;

public class Navigator {

    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        setCurrentMenu(currentMenu);
    }

    public void printMenu() {

        for (int i = 0; i < this.currentMenu.getMenuItem().size(); i++) {
            System.out.println(i + " - " + this.currentMenu.getNameMenuItem(i));
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void navigate(int index) {
        try {
            this.currentMenu.getMenuItem().get(index).doAction();
            setCurrentMenu(this.currentMenu.getMenuItem().get(index).getNextMenu());
        } catch (Exception e) {
            TextWorker textWorker=new TextWorker();
            textWorker.println("navigator error"+e);
        }
    }

    public void update() {
        System.out.println("You in " + currentMenu.getName());
    }
}
