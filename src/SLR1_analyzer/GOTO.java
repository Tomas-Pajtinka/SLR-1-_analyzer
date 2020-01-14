package SLR1_analyzer;

import interfaces.SLR1_GOTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import SLR1_automat.State;

public class GOTO implements SLR1_GOTO{

	private ArrayList<HashMap<String,State>> table = new ArrayList<HashMap<String,State>>();
	/*
	 * Vytvori tabulku GOTO a ulozi ju do table
	 */
	public GOTO(ArrayList<State> states){
		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = iterator.next();
			table.add(state.get_switches());
		}		
	}
	
	
	public State go_to(int state, String symbol)throws Exception{
		if(table.get(state).get(symbol) == null){
			throw new Exception("Syntax error");
		}else{
			return table.get(state).get(symbol);
		}
	}
}
