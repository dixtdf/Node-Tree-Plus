package club.xtdf.node.tree;

import club.xtdf.node.tree.pojo.User;
import club.xtdf.node.tree.utils.ForestNodeMerger;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<User> list = new LinkedList<>();
        list.add(new User("1"));
        list.add(new User("2", "1"));
        list.add(new User("3", "1"));
        list.add(new User("4", "2"));
        list.add(new User("5", "2"));
        list.add(new User("6", "4"));
        list.add(new User("7", "5"));
        list.add(new User("8", "6"));
        list.add(new User("9", "7"));
        List<User> merge = new ForestNodeMerger<User>().merge(list, User::getId, User::getPid, User::getChildren);
        System.out.println();
    }

}
