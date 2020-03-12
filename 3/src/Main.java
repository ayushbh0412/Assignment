import menu.Menu;

public class Main {
    public static void main(String[] args) {
        do {
            System.out.println("\nENTER CHOICE\n"
                    + "1.ADD A NODE\n"
                    + "2.ADD A DEPENDENCY\n"
                    + "3.DELETE A NODE\n"
                    + "4.DELETE A DEPENDENCY\n"
                    + "5.GET IMMEDIATE NODE\n"
                    + "6.GET IMMEDIATE PARENT\n"
                    + "7.GET ANCESTORS\n"
                    + "8.GET DESCENDANT\n"
                    + "9.EXIT");
            Menu.menuDataEntry();
        } while (true);
    }
}