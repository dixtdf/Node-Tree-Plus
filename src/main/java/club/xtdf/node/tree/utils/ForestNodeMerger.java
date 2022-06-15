package club.xtdf.node.tree.utils;

import club.xtdf.node.tree.support.NodeFunction;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * 森林节点合并工具
 *
 * @author 杨毅
 */
public class ForestNodeMerger<T> implements Serializable {

    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域） 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return 多棵树的根节点集合
     */
    public List<T> merge(List<T> items, NodeFunction<T, ?> key, NodeFunction<T, ?> parent, NodeFunction<T, ?> children) {
        ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items, key);
        items.forEach(forestNode -> {
            // 查询父节点
            try {
                T t = forestNodeManager.getTreeNodeAT(((String) forestNode.getClass().getDeclaredMethod(new NodeLambdaWrapper<T>().getColumn(parent)).invoke(forestNode)));
                if (t != null) {
                    // 作为子节点保存
                    Collection<T> c = (Collection<T>) t.getClass().getDeclaredMethod(new NodeLambdaWrapper<T>().getColumn(children)).invoke(t);
                    c.add(forestNode);
                } else {
                    // 将父节点存储
                    forestNodeManager.addParentId((String) forestNode.getClass().getDeclaredMethod(new NodeLambdaWrapper<T>().getColumn(key)).invoke(forestNode));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return forestNodeManager.getRoot();
    }

}
