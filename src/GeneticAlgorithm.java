import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    public static double CROSSOVER_PROBABILITY = 0.5;
    public static double MUTATION_PROBABILITY = 0.05;
    public static IProblem problem = new OneMax();
    private Random rng = new Random(0);
    private Population population;
    private int tournamentSize;


    public GeneticAlgorithm(int populationSize, int chromosomeLength, int tournamentSize) {
        population = new Population(populationSize, chromosomeLength, rng);
        this.tournamentSize = tournamentSize;
    }

    public void nextGeneration() {
        population = population.tournamentWithoutReplacement(rng, tournamentSize);
       // System.out.println("Start:\n"+population);
        population = population.crossover(rng);
        //System.out.println("Crossover:\n"+population );
        population = population.mutation(rng );
        //System.out.println("Crossover + Mutation:\n" + population);
    }




    public Population getPopulation() {
        return population;
    }

}
