package SLR1_analyzer;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import SLR1_automat.SLR1;
import SLR1_automat.State;
import grammar.ContextFreeGrammar;
import grammar.Rule;


public class SLR1_analyzer implements interfaces.SLR1_analyzer  {
	
	private GOTO goto_table;
	private ACTION action_table;
	private ArrayList<State> states;
	private ContextFreeGrammar grammar;
	
	/*
	 * Vytvori SLR(1) analyzator (SLR(1) automat, tabulky ACTION a GOTO) podla zadanej gramatiky
	 */
	public SLR1_analyzer(ContextFreeGrammar grammar) throws Exception{
		states = SLR1.automat(grammar);
		goto_table = new GOTO(states);
		action_table = new ACTION(states, grammar.getTerminals());
		this.grammar = grammar;
	}
	
	
	public boolean analyze(String input) throws Exception{
		Stack<State> analyzer_stack = new Stack<State>();
		LinkedList<String> input_stack = new  LinkedList<String>();
		String[] input_array = input.split("\\s+");
		for(int i = 0; i < input_array.length ; i++){
			input_stack.add(input_array[i]);
		}
		analyzer_stack.push(states.get(0));
		while(true){
			if(input_stack.peekFirst().equals("e") ){
				input_stack.removeFirst();
			}
			Rule rule;
			try{
			 rule = action_table.action(analyzer_stack.peek().get_name(), input_stack.peekFirst());
			}catch(Exception e){
				throw new Exception(e.getMessage());
			}
			if( rule == null){
				analyzer_stack.push(goto_table.go_to(analyzer_stack.peek().get_name(), input_stack.peekFirst()));
				input_stack.removeFirst();
			}else{
				if(rule.getLeftSide().get(0).equals(grammar.getStartsymbol())){
					if(input_stack.size() == 1){
						return true;
					}
				}
				for(int size = rule.getRightSide().size(); size > 0 && !rule.getRightSide().get(0).equals("e"); size--){
					analyzer_stack.pop();
				}
				analyzer_stack.push(goto_table.go_to(analyzer_stack.peek().get_name(), rule.getLeftSide().get(0)));
			}
		}
	}
}
