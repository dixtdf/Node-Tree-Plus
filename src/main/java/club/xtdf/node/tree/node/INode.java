package club.xtdf.node.tree.node;

import java.util.List;

/**
 * Created by Blade.
 *
 * @author Chill
 */
public interface INode {

    /**
     * 主键
     *
     * @return String
     */
    String getId();

    /**
     * 父主键
     *
     * @return String
     */
    String getPid();

    /**
     * 子孙节点
     *
     * @return List
     */
    List<INode> getChildren();

}
