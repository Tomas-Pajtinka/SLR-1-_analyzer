package SLR1_automat;

import grammar.GrammarForAutomat;
import java.util.ArrayList;
import java.util.HashMap;

public class SLR1 {

	/** Vutvori SLR(1) automat
	 * * @param gramatika
	 * * @return ArrayList obsahujuci zoznam stavov
	 **/
	public static ArrayList<State> automat(GrammarForAutomat grammar) throws Exception{
		ArrayList<State> states = new ArrayList<State>();
		int number_of_states = 0;
		ArrayList<Rule_in_State> start_rule = new ArrayList<Rule_in_State>();
		start_rule.add(new Rule_in_State(grammar.getStartrule(), 0, grammar));
		State new_state = new State(number_of_states, start_rule, grammar);
		Closure0.closure(new_state, grammar);
		states.add(new_state);
		number_of_states++;
		for(int i = 0; i < states.size(); i++){
			State processing_state = states.get(i);
			processing_state.set_completed();
			HashMap<String,ArrayList<Rule_in_State>> next_rules = processing_state.get_rules_of_next_state();
			for(String symbol : next_rules.keySet()){
				new_state = new State(number_of_states, next_rules.get(symbol), grammar);
				Closure0.closure(new_state, grammar);
				for(int j = 0; j < number_of_states && j != -1; j++){
					if(states.get(j).compareTo(new_state)){
						processing_state.add_switch(symbol, states.get(j));
						j = -2;
					}
				}
				if(!processing_state.contains_switch(symbol)){
					states.add(new_state);
					number_of_states++;
					processing_state.add_switch(symbol, new_state);					
				}
			}
		}
		return states;
	}
}
