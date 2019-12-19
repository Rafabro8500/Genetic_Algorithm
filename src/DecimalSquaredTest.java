import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalSquaredTest {

    void testFitness(Individual individual, int expected) {
        DecimalSquared ds = new DecimalSquared();
        int result = (int) ds.fitness(individual);
        assertEquals(expected,result);
    }

    @Test
    void testFitness1(){
        Individual individual = new Individual(new String("1101"));
        testFitness(individual,169);
    }
    @Test
    void testFitness2(){
        Individual individual = new Individual(new String("11"));
        testFitness(individual,9);
    }
}