import aima.core.logic.fol.domain.DomainFactory;
import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.FOLFCAsk;
import aima.core.logic.fol.inference.InferenceProcedure;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.kb.FOLKnowledgeBaseFactory;
import aima.core.logic.fol.parsing.FOLParser;
import aima.core.logic.fol.parsing.ast.Constant;
import aima.core.logic.fol.parsing.ast.Predicate;
import aima.core.search.csp.Domain;

import java.util.List;

public class Inference {


    public static void main(String[] args){
        DomainFactory.kingsDomain();

        // domain
        FOLDomain domain = new FOLDomain();
        domain.addConstant("A");
        domain.addConstant("B");

        domain.addPredicate("Knight");
        domain.addPredicate("Knave");

//        FOLKnowledgeBaseFactory.createKingsKnowledgeBase()
//        domain.add
//        domain.addPredicate("");

//        FOLParser parser = new FOLParser(domain);

        InferenceProcedure inferenceProcedure = new FOLFCAsk(); // todo richtig?
        FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, inferenceProcedure);
        // A says: B is a knight.
        kb.tell("Knight(B)");
        // B says: A and I are of opposite type.
        kb.tell("(Knight(A) AND Knave(B)) OR (Knave(A) AND Knight(B)))");

//        kb.tell("NOT( Knight(B)) => NOT(Knight(B))");
//        kb.tell("NOT( Knight(A)) => NOT(Knight(A))");
        System.out.println(kb);
        Predicate query = new Predicate("Knight", List.of(new Constant("A")));
        InferenceResult ask = kb.ask(query);

        ask.getProofs().forEach(System.out::println);


    }

}
