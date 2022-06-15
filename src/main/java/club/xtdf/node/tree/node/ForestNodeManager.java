package club.xtdf.node.tree.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForestNodeManager<T extends INode> {

    /**
     * 森林的所有节点
     * Key INode。id
     */
    private Map<String, Integer> index = new HashMap<String, Integer>();

    private List<T> node;

    /**
     * 森林的父节点ID
     */
    private List<String> parentIds = new ArrayList<String>();

    public ForestNodeManager(List<T> items) {
        for(int i = 0; i < items.size(); i++) {
        	index.put(items.get(i).getId(), i);
        }
        node = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return 对应的节点对象
     */
    public T getTreeNodeAT(String id) {
    	Integer in = index.get(id);
    	if(in == null) {
    		return null;
    	}

    	return node.get(in);
    }


    /**
     * 增加父节点ID
     *
     * @param parentId 父节点ID
     */
    public void addParentId(String parentId) {
        parentIds.add(parentId);
    }

    /**
     * 获取树的根节点(一个森林对应多颗树)
     *
     * @return 树的根节点集合
     */
    public List<T> getRoot() {
    	Stream<T> stream = parentIds.parallelStream().flatMap((key) -> {
    		return Stream.of(getTreeNodeAT(key));
    	}).filter((data) -> {
    		return data != null;
    	});
        return stream.collect(Collectors.toList());
    }

    public List<String> getRootIds(String root) {
    	List<String> ids = new ArrayList<String>();
    	ids.add(root);

    	T t = getTreeNodeAT(root);
    	if(t == null || t.getChildren() == null) {
    		return ids;
    	}

    	t.getChildren().forEach(m -> {
    		List<String> list = getRootIds(m.getId());
    		if(list != null) {
    			ids.addAll(list);
    		}
    	});

        return ids;
    }

}
