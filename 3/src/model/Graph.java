package model;

import java.util.*;

public class Graph {

    private HashMap<Integer, LinkedList<Integer>> adjList;
    private HashMap<Integer, Node> nodes;
    private static boolean flag = false;

    public Graph() {
        adjList = new HashMap<>();
        nodes = new HashMap<>();
    }

    public HashMap<Integer, LinkedList<Integer>> getAdjList() {
        return adjList;
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public void addNode(int id, String name, String key, String value) {

        if (isNodePresent(id)) {
            System.out.println("NODE ALREADY EXISTS\n");
            return;
        }
        Node node = new Node();
        node.setId(id);
        node.setName(name);
        HashMap<String, String> info = new HashMap<>();
        info.put(key, value);
        node.setInfo(info);
        nodes.put(id, node);
        adjList.put(id, new LinkedList<>());
        System.out.println("Node Added Successfully!");
    }

    public void addDependency(int parentId, int childId) {
        if (parentId == childId) {
            System.out.println("Parent and child ids cannot be same!");
            return;
        }
        if (!isNodePresent(parentId)) {
            System.out.println("Invalid parent node");
            return;
        }
        if (!isNodePresent(childId)) {
            System.out.println("Invalid child node");
            return;
        }

        if (dependencyExists(parentId, childId)) {
            System.out.println("Dependency already exists from parent Node: " + parentId + " to child Node : " + childId);
            return;
        }
        if (isDescendant(childId, parentId)) {
            System.out.println("Cannot add cyclic dependency :  Node: " + parentId + " is a descendent Node : " + childId);
            flag = false;
            return;
        }
        adjList.get(parentId).add(childId);
        System.out.println("Added dependency successfully!");
    }

    public void deleteDependency(int parentId, int childId) {
        if (parentId == childId) {
            System.out.println("Parent and child ids cannot be same!");
            return;
        }
        if (!isNodePresent(parentId)) {
            System.out.println("Invalid parent node");
            return;
        }
        if (!isNodePresent(childId)) {
            System.out.println("Invalid child node");
            return;
        }
        if (!dependencyExists(parentId, childId)) {
            System.out.println("Dependency does not exists from parent Node: " + parentId + " to child Node :" + childId);
            return;
        }
        LinkedList<Integer> list = adjList.get(parentId);
        for (int i = 0; i < list.size(); i++)
            if (list.get(i) == childId)
                list.remove(i);
        System.out.println("Deleted dependency successfully!");
    }

    public void deleteNode(int nodeId) {
        if (!isNodePresent(nodeId)) {
            System.out.println("Node with given Id does not exist");
            return;
        }
        nodes.remove(nodeId);
        adjList.remove(nodeId);
        for (LinkedList<Integer> list : adjList.values()) {
            for (int i = 0; i < list.size(); i++)
                if (list.get(i) == nodeId)
                    list.remove(i);
        }
        System.out.println("Deleted node successfully!");
    }

    public void getImmidiateParents(int nodeId) {
        if (!isNodePresent(nodeId))
            System.out.println("Invalid node id");
        for (int key : adjList.keySet())
            if (adjList.get(key).contains(nodeId))
                System.out.println(key + ": " + nodes.get(key).getName());
    }

    public void getDescendants(int nodeId) {
        if (!isNodePresent(nodeId))
            System.out.println("Invalid node id");
        for (int key : adjList.get(nodeId)) {
            System.out.println(key + ": " + nodes.get(key).getName());
            getDescendants(key);
        }
    }

    public void getAncestors(int nodeId) {
        if (!isNodePresent(nodeId))
            System.out.println("Invalid node id");
        for (int key : adjList.keySet())
            if (adjList.get(key).contains(nodeId)) {
                System.out.println(key + ": " + nodes.get(key).getName());
                getAncestors(key);
            }
    }

    public void getImmediateChildren(int nodeId) {
        if (!isNodePresent(nodeId))
            System.out.println("Invalid node id");
        for (int key : adjList.get(nodeId))
            System.out.println(key + ": " + nodes.get(key).getName());
    }

    public boolean isNodePresent(int id) {
        if (adjList.containsKey(id))
            return true;
        return false;
    }

    public boolean dependencyExists(int parent, int child) {
        if (adjList.get(parent).contains(child))
            return true;
        return false;
    }

    public boolean isDescendant(int parentId, int childId) {
        for (int key : adjList.get(parentId)) {
            if (key == childId) {
                flag = true;
                break;
            }
            isDescendant(key, childId);
        }
        return flag;
    }
}