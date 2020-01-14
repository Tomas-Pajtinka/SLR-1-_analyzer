package grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class GrammarForAutomat extends ContextFreeGrammar {
	String epsilon;
	HashSet<String> nEpsilon;
	HashMap<String,HashSet<String>> first;
	HashMap<String,HashSet<String>> follow;
	
	public GrammarForAutomat(HashSet<String> terminals, HashSet<String> nonterminals, HashSet<Rule> rules,String startsymbol, String epsilon) throws Exception{
		super(terminals, nonterminals, rules, startsymbol);
		this.epsilon = epsilon;
		this.nEpsilon = Nepsilon();
		createFirst();
		createFollow();
	}
	
	public String getEpsilon(){
		return epsilon;
	}
	
	
	private HashMap<String,HashSet<String>> createFirst(){
		first = new HashMap<String,HashSet<String>>();
		Iterator<String> nonterminals = this.getNonterminals().iterator();
		while(nonterminals.hasNext()){
			String nonterminal = nonterminals.next();
			first.put(nonterminal, new HashSet<String>());
		}
		
		Iterator<String> terminals = this.getTerminals().iterator();
		while(terminals.hasNext()){
			String terminal = terminals.next();
			first.put(terminal, new HashSet<String>(Arrays.asList(terminal)));
		}
		
		Iterator<Rule> rulesIterator = this.getRules().iterator();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		while(rulesIterator.hasNext()){
			Rule rule = rulesIterator.next();
			if(this.getTerminals().contains(rule.getRightSide().get(0))){
				first.get(rule.getLeftSide().get(0)).add(rule.getRightSide().get(0));
			}else{
				rules.add(rule);
			}
		}
		
		Iterator<String> nEpsilon = this.nEpsilon.iterator();
		while(nEpsilon.hasNext()){
			String n = nEpsilon.next();
			first.get(n).add(this.getEpsilon());
		}
		
		HashMap<String,HashSet<String>> firstPom;
		do{
			firstPom = this.copy(first);
			rulesIterator = rules.iterator();
			while(rulesIterator.hasNext()){
				Rule rule = rulesIterator.next();
				first.get(rule.getLeftSide().get(0)).addAll(this.firstFromString(rule.getRightSide()));
			}
		}while(!first.equals(firstPom));
		
		return first;
	}
	
	
	private HashSet<String> firstFromString(ArrayList<String> array){
		HashSet<String> first = new  HashSet<String>();
		if(array.contains(this.getEpsilon()) || array.size() == 0){
			first.add(this.getEpsilon());
		}else{
			first.addAll(this.first(array.get(0)));
			int i = 0;
			while(first(array.get(i)).contains(this.getEpsilon()) && i < array.size()-1){
				first.remove(this.getEpsilon());
				i++;
				first.addAll(this.first(array.get(i)));
			}
		}
		return first;
	}
	
	private HashMap<String,HashSet<String>> createFollow(){
		follow = new HashMap<String,HashSet<String>>();
		HashMap<String,HashSet<String>> followPom;
		
		Iterator<String> nonterminals = this.getNonterminals().iterator();
		while(nonterminals.hasNext()){
			String nonterminal = nonterminals.next();
			follow.put(nonterminal, new HashSet<String>());
		}
		
		Iterator<Rule> rulesIterator = this.getRules().iterator();
		HashSet<Rule> rules = new HashSet<Rule>();
		while(rulesIterator.hasNext()){
			Rule rule = rulesIterator.next();
			nonterminals = this.getNonterminals().iterator();
			while(nonterminals.hasNext()){
				String nonterminal = nonterminals.next();
				if(rule.getRightSide().contains(nonterminal)){
					rules.add(rule);
				}
			}
		}
		
		follow.get(this.getStartsymbol()).add(this.getEpsilon());
		
		do{
			followPom = this.copy(follow);
			rulesIterator = rules.iterator();
			while(rulesIterator.hasNext()){
				Rule rule = rulesIterator.next();
				for(int i = 0; i < rule.getRightSide().size(); i++){
					if(this.getNonterminals().contains(rule.getRightSide().get(i))){
						ArrayList<String> array = new ArrayList<String>();
						for(int j = i + 1; j < rule.getRightSide().size() ;j++){
							array.add(rule.getRightSide().get(j));
						}
						HashSet<String> followList = this.firstFromString(array);
						if(followList.contains(this.getEpsilon())){
							followList.remove(this.getEpsilon());
							follow.get(rule.getRightSide().get(i)).addAll(this.follow(rule.getLeftSide().get(0)));
						}
						follow.get(rule.getRightSide().get(i)).addAll(followList);
					}
				}
			}
		}while(!follow.equals(followPom));
		
		return follow;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<String> Nepsilon(){
		HashSet<String> nEpsilonPom, nEpsilon = new HashSet<String>();
		do{
			nEpsilonPom = (HashSet<String>) nEpsilon.clone();
			Iterator<Rule> rules = this.getRules().iterator();
			while(rules.hasNext()){
				Rule rule = rules.next();
				if(rule.getRightSide().get(0).equals(this.epsilon) || nEpsilon.containsAll(rule.getRightSide())){
					nEpsilon.add(rule.getLeftSide().get(0));
				}
			}
		}while(!nEpsilon.equals(nEpsilonPom));
		return nEpsilon;
	}
	
	public HashSet<String> first(String symbol){
		return first.get(symbol);
	}
	
	public HashSet<String> follow(String symbol){
		return follow.get(symbol);
	}
	
	private HashMap<String,HashSet<String>> copy(HashMap<String,HashSet<String>> original){
		HashMap<String,HashSet<String>> copy = new HashMap<String,HashSet<String>>();
		for (Map.Entry<String,HashSet<String>> entry : original.entrySet()){
		    copy.put(entry.getKey(),new HashSet<String>(entry.getValue()));
		}
		 return copy;
	}

}
