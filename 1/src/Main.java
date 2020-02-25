import model.Item;
import util.IOUtil;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String ch = "y";
        int i = 0;
        do {
            i++;
            Item item = new Item();
            System.out.println("ENTER THE ITEM " + i + " DETAILS");
            System.out.print("NAME \t\t");
            try {
                item.setName(IOUtil.getBufferedReader().readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("PRICE \t\t");
            try {
                item.setPrice(Double.parseDouble(IOUtil.getBufferedReader().readLine()));
            } catch (IOException | NumberFormatException ie) {
                ie.printStackTrace();
            }
            System.out.print("QUANTITY \t");
            try {
                item.setQuantity(Integer.parseInt(IOUtil.getBufferedReader().readLine()));
            } catch (IOException | NumberFormatException ie) {
                ie.printStackTrace();
            }
            System.out.print("CHOOSE THE TYPE:\n1. RAW\n2. MANUFACTURED \n3. IMPORTED\n");
            try {
                item.getTaxForParticularType(Integer.parseInt(IOUtil.getBufferedReader().readLine()));
            } catch (IOException | NumberFormatException ie) {
                ie.printStackTrace();
            }
            System.out.println("\n\n\t\tSHOWING ITEM " + i + " DETAILS:");
            item.printData();

            System.out.print("\nDO YOU WANT TO ENTER MORE ITEM?(y/n):\t\t");
            try {
                ch = IOUtil.getBufferedReader().readLine();
                if (ch.toLowerCase().equals("n")) {
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Invalid choice");
            }
        } while (ch.toLowerCase().equals("y"));
    }
}