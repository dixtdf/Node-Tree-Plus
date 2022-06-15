package club.xtdf.node.tree;

import club.xtdf.node.tree.node.data;
import club.xtdf.node.tree.utils.ForestNodeMerger;

import java.util.LinkedList;
import java.util.List;

public class m {
    public static void main(String[] args) {
        long x2 = System.currentTimeMillis();
        System.out.println("data开始" + x2);
        List<data> list1 = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(new data(String.valueOf(i), String.valueOf(i + 1)));
        }
        List<data> merge1 = new ForestNodeMerger<data>().merge(list1, data::getId, data::getPid, data::getChildren);
        long x3 = System.currentTimeMillis();
        System.out.println("data结束" + x3);
        System.out.println("data计算" + (x3 - x2));
    }
}
