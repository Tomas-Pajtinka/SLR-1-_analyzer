package SLR1_analyzer;

import grammar.GrammarForAutomat;
import grammar.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Tree {
	TreeNode root = null;
	LinkedList<Rule> rules = new LinkedList<Rule>();
	GrammarForAutomat grammar;
	
	public Tree(GrammarForAutomat grammar){
		this.grammar = grammar;
	}
	
	public void createTree(){
		root = new TreeNode(rules.peek().getLeftSide().get(0),null);
		root.addChildren(rules.peek().getRightSide());
		rules.pop();
		while(!rules.isEmpty()){
			TreeNode node = findNonterminal(root);
			node.addChildren(rules.peek().getRightSide());
			rules.pop();
		}
	}
	
	private TreeNode findNonterminal(TreeNode node){
		if(grammar.getNonterminals().contains(node.symbol) && node.lists.size() == 0){
			return node;
		}else{
			TreeNode pom = null;
			for(int i =  node.lists.size() - 1; i >= 0 && pom == null; i--){
				pom = findNonterminal(node.lists.get(i));
			}
			return pom;
		}
	}
	
	public void addRuleToStack(Rule rule){
		rules.push(rule);
	}
	
	public void print(){
		System.out.println(root.toString());
	}
}
