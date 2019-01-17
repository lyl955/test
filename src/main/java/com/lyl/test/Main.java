package com.lyl.test;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Node> nodes = getNodes();

        for (Node nodet : nodes) {
            System.out.println(nodet.getId()+":"+nodet.getLevel()+":"+nodet.getPid()+":"+nodet.getSortNum());
        }
        System.out.println("ok");
    }

    @NotNull
    private static List<Node> getNodes() {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 1; i <= 2; i++) {
            Node node = new Node();
            node.setId(i);
            node.setLevel(1);
            node.setSortNum(i);
            nodes.add(node);
        }

        Node node = new Node();
        node.setId(3);
        node.setLevel(2);
        node.setSortNum(1);
        node.setPid(1);
        nodes.add(node);

        Node node2 = new Node();
        node2.setId(4);
        node2.setLevel(2);
        node2.setSortNum(1);
        node2.setPid(2);

        nodes.add(node2);

        Node node3 = new Node();
        node3.setId(5);
        node3.setLevel(2);
        node3.setSortNum(2);
        node3.setPid(2);

        nodes.add(node3);
        return nodes;
    }
}
