package club.xtdf.node.tree.node;

import java.util.ArrayList;
import java.util.List;

public class data implements INode {
    private String id;
    private String pid;
    private List<INode> children = new ArrayList<>();

    public static data newRoot() {
        data root = new data();
        root.setId("0");
        return root;
    }

    public data() {
    }

    public data(String id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    /**
     * 树形结构
     *
     * @param data    树形节点数据
     * @param topRoot 是否需要顶级
     * @param rootIds 根节点ID
     * @return 树
     */
    public static List tree(List data1, Boolean topRoot, String... rootIds) {
        List<INode> tree = ForestNodeMerger.merge(data1);
        if (topRoot) {
            data root = data.newRoot();
            root.setChildren(tree);
            List<INode> result = new ArrayList<>(1);
            result.add(root);
            return result;
        }
        return tree;
    }

    /**
     * 树形结构
     *
     * @param data 树形节点数据
     * @return 树
     */
    public static List tree(List data) {
        return tree(data, false, null);
    }

    /**
     * 树形结构
     *
     * @param data    树形节点数据
     * @param topRoot 是否需要顶级
     * @return 树
     */
    public static List tree(List data, Boolean topRoot) {
        return tree(data, topRoot, null);
    }

    /**
     * 树形结构
     *
     * @param data    树形节点数据
     * @param rootIds 根节点ID
     * @return 树
     */
    public static List tree(List data, String... rootIds) {
        return tree(data, false, rootIds);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public List<INode> getChildren() {
        return children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setChildren(List<INode> children) {
        this.children = children;
    }
}
