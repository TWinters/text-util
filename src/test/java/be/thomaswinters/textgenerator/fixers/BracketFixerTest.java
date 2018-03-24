package be.thomaswinters.textgenerator.fixers;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketFixerTest {

    private static BracketFixer bf;

    @BeforeClass
    public static void setup() {
        bf = new BracketFixer("(", ")");
    }

    @Test
    public void RoundBrackets_OneBracket() {
        assertEquals("(Hello)", bf.fix("Hello)"));
        assertEquals("(Hello)", bf.fix("(Hello"));
        assertEquals("Hello. (I'm a person)", bf.fix("Hello. I'm a person)"));
        assertEquals("Hello. (I'm a person)", bf.fix("Hello. (I'm a person"));
        assertEquals("Hello. I'm a (person).", bf.fix("Hello. I'm a (person."));
        assertEquals("Hello. (I'm a) person.", bf.fix("Hello. I'm a) person."));
        assertEquals("(I'm a person). Hello.", bf.fix("I'm a person). Hello."));
        assertEquals("(I'm a person). Hello.", bf.fix("(I'm a person. Hello."));
        assertEquals("I'm a (person). Hello.", bf.fix("I'm a (person. Hello."));
        assertEquals("(I'm a) person. Hello.", bf.fix("I'm a) person. Hello."));
        assertEquals("(I'm a) (person). Hello.", bf.fix("I'm a) (person. Hello."));

    }

    @Test
    public void RoundBrackets_ProblematicFilterer() {
        assertEquals("Hello", bf.replaceNonProblematicBrackets("Hello"));
        assertEquals("(     Hello     ", bf.replaceNonProblematicBrackets("(  (  Hello  )  "));
        assertEquals("( Hello ", bf.replaceNonProblematicBrackets("((Hello)"));
        assertEquals("  Hello  ", bf.replaceNonProblematicBrackets("((Hello))"));
        assertEquals(" Hello )", bf.replaceNonProblematicBrackets("(Hello))"));
        assertEquals("  Hello    there ", bf.replaceNonProblematicBrackets("((Hello)) (there)"));
        assertEquals("  Hello    there )", bf.replaceNonProblematicBrackets("((Hello)) (there))"));
        assertEquals("Hello) (there", bf.replaceNonProblematicBrackets("Hello) (there"));
    }

}
