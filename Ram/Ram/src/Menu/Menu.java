package Menu;

import java.util.ArrayList;

import Inptter.Inputted;

public class Menu<T> extends ArrayList<String> implements IMenu<T> {


    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public int getChoice() {
        return Inputted.GetInt(" Enter your choice: ", 1, this.size());
    }

    @Override
    public void showMenu() {
        System.out.println("******************* MENU *******************");
        for (int i = 0; i < this.size(); i++) {
            System.out.printf("* %2d.  %-25s %10s*\n", i+1, this.get(i), " ");
        }
        System.out.println("********************************************");
    }

    @Override
    public boolean confirmYesNo(String prompt) {
        while (true) {
            String flag = Inputted.GetString(prompt);
            if (flag.equalsIgnoreCase("Y")) {
                return true;
            } else if (flag.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Please enter 'Y' or 'N' only.");
            }
        }
    }

    
}

