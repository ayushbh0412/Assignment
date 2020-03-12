package menu;

import model.Graph;
import util.IOUtil;

public class Menu {
    public static void menuDataEntry(){
        Graph graph=new Graph();
        int choice=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
        switch(choice)
        {
            case 1: System.out.print("ENTER NODE ID\t");
                int id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                System.out.print("ENTER NODE NAME\t");
                String name=IOUtil.getStringInput(IOUtil.getBufferedReader());
                System.out.print("ENTER KEY\t");
                String key=IOUtil.getStringInput(IOUtil.getBufferedReader());
                System.out.print("ENTER INFO\t");
                String value=IOUtil.getStringInput(IOUtil.getBufferedReader());
                graph.addNode(id, name, key, value);
                break;
            case 2:	System.out.print("ENTER PARENT NODE ID\t");
                int pid=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                System.out.print("ENTER CHILD NODE ID\t");
                int cid=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.addDependency(pid, cid);
                break;
            case 3: System.out.print("ENTER NODE ID\t");
                id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.deleteNode(id);
                break;
            case 4: System.out.print("ENTER PARENT NODE ID\t");
                pid=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                System.out.print("ENTER CHILD NODE ID\t");
                cid=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.deleteDependency(pid, cid);
                break;
            case 5: System.out.print("ENTER NODE ID\t");
                id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.getImmidiateParents(id);
                break;
            case 6: System.out.print("ENTER NODE ID\t");
                id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.getImmediateChildren(id);
                break;
            case 7:	System.out.print("ENTER NODE ID\t");
                id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.getAncestors(id);
                break;
            case 8:	System.out.print("ENTER NODE ID\t");
                id=IOUtil.getIntegerInput(IOUtil.getBufferedReader());
                graph.getDescendants(id);
                break;
            case 9: System.exit(0);
                break;
            default: System.out.println("INVALID CHOICE\n");
        }
    }
}
