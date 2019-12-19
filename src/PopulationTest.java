import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {
    void testToString(int n, int l, String expected) {
        Random generator = new Random(0);
        Population population = new Population(n, l, generator);
        assertEquals(expected, population.toString());
    }

    void testBinaryTournamentSelection(int n, int l, List<Double> fitnesses, String expected){
        Random generator = new Random(0);
        Population population = new Population(n,l,generator,fitnesses);
        Population newGen = population.binaryTournamentSelection(generator);
        assertEquals(expected,newGen.toString());
    }

    void testSUS(List<Double> fitnesses, List<String> geneticCodes, String expected){
        Random rng = new Random(0);
        Population pop = new Population(geneticCodes,fitnesses);
        Population newGen = pop.SUS(rng);
        assertEquals(expected,newGen.toString());
    }

    @Test
    void testToString(){
        testToString(4,3,
                "101" + lineSeparator() +
                        "110" + lineSeparator() +
                        "011" + lineSeparator() +
                        "100" + lineSeparator()
        );
    }

    @Test
    void testBinaryTournamentSelection(){
        List<Double> fitnesses = new ArrayList<>();
        fitnesses.add(8.0);
        fitnesses.add(7.0);
        fitnesses.add(23.0);
        fitnesses.add(5.0);
        testBinaryTournamentSelection(4,3,fitnesses,
                "101" + lineSeparator() +
                        "011" + lineSeparator() +
                        "011" + lineSeparator() +
                        "011" + lineSeparator()
                );
    }

    @Test
    void testSUS(){
        List<Double> fitnesses = new ArrayList<>();
        fitnesses.add(1.0);
        fitnesses.add(3.0);
        fitnesses.add(4.0);
        fitnesses.add(2.0);
        List<String> geneticCodes = new ArrayList<>();
        geneticCodes.add("110");
        geneticCodes.add("001");
        geneticCodes.add("101");
        geneticCodes.add("111");
        testSUS(fitnesses,geneticCodes,
                "001" + lineSeparator() +
                "101" + lineSeparator() +
                "101" + lineSeparator() +
                "111" + lineSeparator());
    }

    void tournamentSelectionWithoutReplacement(int s, List<String> geneticCodes, List<Double> fitnessList, String expected) {
        Random rng = new Random(0);
        Population population = new Population(geneticCodes, fitnessList);
        Population actual = population.tournamentWithoutReplacement(rng, s);
        assertEquals(expected, actual.toString());
    }

    @Test
    void tournamentSelectionWithoutReplacement_1() {
        tournamentSelectionWithoutReplacement(
                2,
                List.of("110", "001", "101", "111"),
                List.of(8.0, 7.5, 23.0, 5.0),
                "101" + lineSeparator() +
                        "110" + lineSeparator() +
                        "101" + lineSeparator() +
                        "001" + lineSeparator()
        );
    }

}
