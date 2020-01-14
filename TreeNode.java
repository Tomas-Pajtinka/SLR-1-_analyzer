package SLR1_analyzer;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeNode {
	String symbol;
	TreeNode parent;
	public int poloha;
	ArrayList<TreeNode> lists = new ArrayList<TreeNode>();
	
	public TreeNode(String symbol, TreeNode parent){
		this.symbol = symbol;
		this.parent = parent;
	}
	
	public void addChildren(ArrayList<String> childrens){
		for(int i = 0; i < childrens.size(); i++){
			lists.add(i, new TreeNode(childrens.get(i), this));
		}
	}
	
	public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(symbol);
        buffer.append('\n');
        for (Iterator<TreeNode> it = lists.iterator(); it.hasNext();) {
            TreeNode next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

}
