package SLR1_analyzer;

import grammar.Rule;
import interfaces.SLR1_ACTION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import SLR1_automat.State;

public class ACTION implements SLR1_ACTION{
	
	private ArrayList<HashMap<String,Rule>> table = new ArrayList<HashMap<String,Rule>>();
	/*
	 * Vytvori tabulku ACTION a ulozi ju table
	 */
	public ACTION(ArrayList<State> states, HashSet<String> terminals) throws Exception{
		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = iterator.next();
			table.add(state.get_rules_to_action());
		}	
	}
	
	
	public Rule action(int state, String symbol) throws Exception{
		if(table.get(state).containsKey(symbol)){
			return table.get(state).get(symbol);
		}else{
			throw new Exception("Syntax error");
			
		}
	}
}
