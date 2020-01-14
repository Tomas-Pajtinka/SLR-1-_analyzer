package Tests;

import grammar.GrammarForAutomat;
import grammar.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import SLR1_analyzer.SLR1_analyzer;

public abstract class TestAnalyzer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HashSet<String> terminals;
		HashSet<String> nonterminals;
		Rule rule0;
		Rule rule1;
		Rule rule2;
		Rule rule3;
		Rule rule4;
		Rule rule5;
		Rule rule6;
		Rule rule7;
		HashSet<Rule> rules;
		String startsymbol;
		/*
		terminals = new HashSet<String>(Arrays.asList("(",")","a","b","+"));
		nonterminals = new HashSet<String>(Arrays.asList("S","A","B")); 
		rule1 = new Rule(new ArrayList<String>(Arrays.asList("S")), new ArrayList<String>(Arrays.asList("A","(","S",")")));//
		rule2 = new Rule(new ArrayList<String>(Arrays.asList("S")), new ArrayList<String>(Arrays.asList("a")));//
		rule3 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("b","B")));//
		rule4 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("b")));//
		rule5 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("+","A")));//
		rule6 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("a")));//
		rules= new HashSet<Rule>(Arrays.asList(rule1 ,rule2 ,rule3 ,rule4, rule5, rule6 ));
		
		
		terminals = new HashSet<String>(Arrays.asList("(",")","id",":=","e","+","$"));
		nonterminals = new HashSet<String>(Arrays.asList("S","E","P","B")); 
		rule0 = new Rule(new ArrayList<String>(Arrays.asList("S")), new ArrayList<String>(Arrays.asList("id",":=","E","$")));//
		rule1 = new Rule(new ArrayList<String>(Arrays.asList("E")), new ArrayList<String>(Arrays.asList("E","+","P")));//
		rule2 = new Rule(new ArrayList<String>(Arrays.asList("E")), new ArrayList<String>(Arrays.asList("P")));//
		rule3 = new Rule(new ArrayList<String>(Arrays.asList("P")), new ArrayList<String>(Arrays.asList("id")));//
		rule4 = new Rule(new ArrayList<String>(Arrays.asList("P")), new ArrayList<String>(Arrays.asList("(","E",")")));//
		rule5 = new Rule(new ArrayList<String>(Arrays.asList("P")), new ArrayList<String>(Arrays.asList("id",":=","E")));//
		rule6 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("e")));//
		rules= new HashSet<Rule>(Arrays.asList(rule0 ,rule1 ,rule2 ,rule3 ,rule4, rule5, rule6 ));
		/*
		terminals = new HashSet<String>(Arrays.asList("a","b","c","$"));
		nonterminals = new HashSet<String>(Arrays.asList("S","A","B"));
		rule0 = new Rule(new ArrayList<String>(Arrays.asList("S")), new ArrayList<String>(Arrays.asList("B","$")));//
		rule1 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("a","B","b")));//
		rule2 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("A")));//
		rule3 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("b","a")));//
		rule4 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("c")));//
		
		*/
		
		terminals = new HashSet<String>(Arrays.asList("a","b","(",")","[","]","$","c","d"));
		nonterminals = new HashSet<String>(Arrays.asList("S","A","B","C"));
		rule0 = new Rule(new ArrayList<String>(Arrays.asList("S")), new ArrayList<String>(Arrays.asList("(","A",")","$")));//
		rule1 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("[","A","]")));//
		rule2 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("epsilon")));//
		rule3 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("b","a","B")));//
		rule4 = new Rule(new ArrayList<String>(Arrays.asList("A")), new ArrayList<String>(Arrays.asList("c","c")));//
		rule5 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("a","A","C")));//
		rule6 = new Rule(new ArrayList<String>(Arrays.asList("B")), new ArrayList<String>(Arrays.asList("d","A","a")));//
		rule7 = new Rule(new ArrayList<String>(Arrays.asList("C")), new ArrayList<String>(Arrays.asList("(","A",")")));//
		rules= new HashSet<Rule>(Arrays.asList(rule0 ,rule1 ,rule2 ,rule3 ,rule4, rule5, rule6, rule7 ));
		
		startsymbol="S";
		GrammarForAutomat g = new GrammarForAutomat(terminals, nonterminals, rules, startsymbol,"epsilon");
		
		System.out.println(g.getStartrule().getRightSide().toString());
		System.out.println( "FIRST S:"+g.first("S").toString());
		System.out.println( "FIRST A:"+g.first("A").toString());
		System.out.println( "FIRST B:"+g.first("B").toString());
		System.out.println( "FIRST C:"+g.first("C").toString());
		System.out.println( "FOLLOW S:"+g.follow("S").toString());
		System.out.println( "FOLLOW A:"+g.follow("A").toString());
		System.out.println( "FOLLOW B:"+g.follow("B").toString());
		System.out.println( "FOLLOW C:"+g.follow("C").toString());
		
			SLR1_analyzer analyzer = new SLR1_analyzer(g);
		try{
			System.out.println(analyzer.analyze("( b a a [ ] ( c c ) ) $"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
