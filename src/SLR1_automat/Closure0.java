package SLR1_automat;

import java.util.Iterator;

import grammar.ContextFreeGrammar;
import grammar.Rule;

public class Closure0 {
	
	/** Spravi uzaver daneho stavu
	 * * @param stav, ktory sa ma uzavriet
	 * * @param gramatika
	 **/
	public static void closure(State state, ContextFreeGrammar grammar) throws Exception{
		while(state.has_unprocessed_rules()){
			Rule_in_State rule = state.get_unprocessed_rule();
			String nonterminal = rule.get_pointer_element();
			Iterator<Rule> grammar_rules = grammar.getRules().iterator();
			while (grammar_rules.hasNext()){
				Rule grammar_rule = grammar_rules.next();
				if(grammar_rule.getLeftSide().get(0).equals(nonterminal)){
					state.add_rule(grammar_rule);
				}
			}
			rule.set_processed();
		}
	}
}
