package Menu;

import Manager.RamManagerSystem;
import Manager.IRamManagerSystem;

public class Program {
    private IRamManagerSystem RamManagerSystem = new RamManagerSystem();

    public void Program(){
       RamManagerSystem.ReadRamFile();

        Menu<String> menu = new Menu<>();

        menu.add("Add an Item");
        menu.add("Search SubMenu");
        menu.add("Update Item Information");
        menu.add("Delete Item");
        menu.add("Show All Items");
        menu.add("Save File");
        menu.add("Print RAM false");
        menu.add("Quit Menu");
        
        boolean exit = false;
        while (!exit) {
            menu.showMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    do {
                       RamManagerSystem.AddRam();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 2:
                    do {
                        RamManagerSystem.SearchRam();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 3:
                    do {
                        RamManagerSystem.UpdateRam();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 4:
                    do {
                        RamManagerSystem.DeleteRam();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 5:
                    do {
                        RamManagerSystem.PrintAllRamItem();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 6:
                    RamManagerSystem.WriteRamFile();
                    System.out.println("Changes have been successfully saved.");
                    break;
                case 7:
                     do {
                       RamManagerSystem.DisplayDeletedRamItems();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 8:
                    exit = true;
                    System.out.println("Goodbye!");
                    ;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
