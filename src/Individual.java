import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {
    private String geneticCode;
    private double fitness;



    public Individual(int chromosomeLength, Random rng) {
        StringBuilder geneticCode = new StringBuilder();
        for (int i = 0; i < chromosomeLength ; i++) {
            double d = rng.nextDouble();
            char gene = d < 0.5 ? '0' : '1';
            geneticCode.append(gene);
        }
        this.geneticCode = geneticCode.toString();
        this.fitness = GeneticAlgorithm.problem.fitness(this);
    }

    public Individual (int chromosomeLength, Random rng, double fitness){
        this.fitness = fitness;
        StringBuilder geneticCode = new StringBuilder();
        for (int i = 0; i < chromosomeLength ; i++) {
            double d = rng.nextDouble();
            char gene = d < 0.5 ? '0' : '1';
            geneticCode.append(gene);
        }
        this.geneticCode = geneticCode.toString();

    }

    public Individual(String geneticCode){
        this.geneticCode = geneticCode;
        fitness = GeneticAlgorithm.problem.fitness(this);
    }
    public Individual(String geneticCode, Double fitness){
        this.geneticCode = geneticCode;
        this.fitness = fitness;
    }

    public List<Individual> uniformCrossover(Individual other, Random rng){
        List<Individual> result = new ArrayList<>();
        StringBuilder child1 = new StringBuilder(geneticCode);
        StringBuilder child2 = new StringBuilder(other.getGeneticCode());
        for(int i = 0; i<geneticCode.length(); i++){
            double coin = rng.nextDouble();
            if(coin<0.5){ //if coin < 0.5 (heads) swap parents genes
                char temp = child1.charAt(i);
                child1.setCharAt(i,child2.charAt(i));
                child2.setCharAt(i,temp);
            }
        }
        result.add(new Individual(child1.toString()));
        result.add(new Individual(child2.toString()));
        return result;
    }

    public List<Individual> singlePointCrossover(Individual other, Random rng){
        List<Individual> result = new ArrayList<>();
        StringBuilder child1 = new StringBuilder();
        StringBuilder child2 = new StringBuilder();
        int crossPoint = (int) Math.round(rng.nextDouble() * (geneticCode.length() - 2));
        for(int i = 0; i<geneticCode.length(); i++){
            if(i<=crossPoint) {
                child1.append(this.geneticCode.charAt(i));
                child2.append(other.getGeneticCode().charAt(i));
            }
            else {
                child1.append(other.getGeneticCode().charAt(i));
                child2.append(this.getGeneticCode().charAt(i));
            }
        }
        result.add(new Individual(child1.toString()));
        result.add(new Individual(child2.toString()));
        return result;
    }


    public Individual mutation(Random rng, Double pm) {
        StringBuilder code = new StringBuilder(geneticCode);
        for (int i = 0; i < code.length(); i++) {
            double r = rng.nextDouble();
            char gene = code.charAt(i);
            if (r < pm) {
                char flip = gene == '1' ? '0' : '1';
                code.setCharAt(i, flip);
            }
        }

        return new Individual(code.toString());
    }


    public String getGeneticCode(){
        return geneticCode;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return geneticCode;
    }
}
