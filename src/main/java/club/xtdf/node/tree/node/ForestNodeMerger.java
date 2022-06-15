package club.xtdf.node.tree.node;

import java.util.List;

public class ForestNodeMerger {

	/**
	 * 将节点数组归并为一个森林（多棵树）（填充节点的children域） 时间复杂度为O(n^2)
	 *
	 * @param items 节点域
	 * @param <T>   T 泛型标记
	 * @return 多棵树的根节点集合
	 */
	public static <T extends INode> List<T> merge(List<T> items) {
		ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
		items.forEach(forestNode -> {
			INode node = forestNodeManager.getTreeNodeAT(forestNode.getPid()); // 查询父节点
			if (node != null) {
				node.getChildren().add(forestNode); // 作为子节点保存
			} else {
				forestNodeManager.addParentId(forestNode.getId()); // 将父节点存储
			}
		});
		return forestNodeManager.getRoot();
	}

	/**
	 * 将节点数组归并为一个数组 (填充节点ID)
	 *
	 * @param <T>
	 * @param items
	 * @return
	 */
	public static <T extends INode> List<String> ids(List<T> items, String root) {
		ForestNodeManager<T> forestNodeManager = new ForestNodeManager<T>(items);
		items.forEach(forestNode -> {
			INode node = forestNodeManager.getTreeNodeAT(forestNode.getPid()); // 查询父节点
			if (node != null) {
				node.getChildren().add(forestNode); // 作为子节点保存
			} else {
				forestNodeManager.addParentId(forestNode.getId()); // 将父节点存储
			}
		});
		return forestNodeManager.getRootIds(root);
	}

}
