import java.util.*;

public class Population {
    private List<Individual> population;
    private int populationSize;


    public Population(int populationSize, int chromosomeLength, Random rng) {
        population = new ArrayList<>();
        this.populationSize = populationSize;
        for (int i = 0; i < populationSize; i++) {
            population.add(new Individual(chromosomeLength, rng));
        }
    }

    public Population(int populationSize, int chromosomeLength, Random rng, List<Double> fitness) {
        this.populationSize = populationSize;
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Individual(chromosomeLength, rng, fitness.get(i)));
        }
    }

    public Population(List<Individual> population, int populationSize) {
        this.population = population;
        this.populationSize = populationSize;
    }

    public Population(List<String> geneticCodes, List<Double> fitnesses) {
        populationSize = geneticCodes.size();
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
            population.add(new Individual(geneticCodes.get(i), fitnesses.get(i)));
    }

    public Population(List<Individual> population) {
        this.population = population;
        this.populationSize = population.size();
    }

    public Population binaryTournamentSelection(Random rng) {
        List<Individual> newGeneration = new ArrayList<>();
        for (Individual individual : population) {
            int index1 = (int) Math.round(rng.nextDouble() * (populationSize - 1));
            int index2 = (int) Math.round(rng.nextDouble() * (populationSize - 1));
            Individual i_1 = population.get(index1);
            Individual i_2 = population.get(index2);
            Individual winner = battle(i_1, i_2);
            newGeneration.add(winner);
        }

        return new Population(newGeneration, populationSize);
    }

    public Individual battle(Individual a, Individual b) {
        return a.getFitness() >= b.getFitness() ? a : b;
    }

    public Population rouletteWheelSelection(Random rng) {
        double totalFitness = getTotalFitness();
        List<Individual> newGeneration = new ArrayList<>();
        List<Individual> wheel = sort(Comparator.comparingDouble(Individual::getFitness).reversed()).getPopulation();
        for (int i = 0; i < populationSize; i++) {
            double d = rng.nextDouble();
            Individual selected = spinWheel(wheel, totalFitness, d);
            newGeneration.add(selected);
        }
        return new Population(newGeneration);
    }

    private Individual spinWheel(List<Individual> wheel, double totalFitness, double threshold) {
        double sumRelativeFitness = 0;
        for (Individual individual : wheel) {
            double relativeFitness = individual.getFitness() / totalFitness;
            sumRelativeFitness += relativeFitness;
            if (threshold < sumRelativeFitness)
                return individual;
        }
        return null;
    }

    public Population SUS(Random rng) {
        Double[] pointers = new Double[populationSize];
        double pointerGap = getTotalFitness() / populationSize;
        double start = rng.nextDouble() * pointerGap;
        pointers[0] = start;
        for (int i = 1; i < populationSize; i++)
            pointers[i] = pointers[i - 1] + pointerGap;
        return spinWheel(this.getPopulation(), pointers);
    }

    private Population spinWheel(List<Individual> wheel, Double[] pointers) {
        List<Individual> selected = new ArrayList<>();
        for (Double p : pointers) {
            double sum = 0;
            for (Individual i : wheel) { //for each individual
                if (sum + i.getFitness() > p) { //if sum of fitnesses until i'th individual exceeds the pointer "position"
                    selected.add(i); //select i
                    break;  //go to next pointer
                } else sum += i.getFitness();
            }

        }
        return new Population(selected);
    }


    public Population tournamentWithoutReplacement(Random rng, int s) {
        List<Individual> selected = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            Population permutation = randomPermutation(rng);
            for (int j = 0; j < populationSize/s; j++) { // j = group number
                List<Individual> contenders = new ArrayList<>();
                for (int k = 0; k < s; k++) {
                    contenders.add(permutation.getPopulation().get(k + j*s));//j*s = index of first contender in the group
                }
                Individual winner = tournament(contenders);
                selected.add(winner);
            }
        }
        return new Population(selected);
    }

    private Individual tournament(List<Individual> contenders){
        return contenders.stream()
                .max(Comparator.comparingDouble(Individual::getFitness))
                .orElse(null);
    }

    public double getMaxFitness(){
        return population.stream()
                .mapToDouble(Individual::getFitness)
                .max()
                .orElse(0);
    }

    public double getAvgFitness(){
        return population.stream()
                .mapToDouble(Individual::getFitness)
                .average()
                .orElse(0);
    }

    public double getMinFitness(){
        return population.stream()
                .mapToDouble(Individual::getFitness)
                .min()
                .orElse(0);
    }

    private Population randomPermutation(Random rng) {
        List<Individual> result = new ArrayList<>(population);
        for (int i = 0; i < populationSize - 1; i++) {
            int r = i + (int) Math.round(rng.nextDouble() * (populationSize - 1 - i));
            Individual temp = result.get(i);
            result.set(i, result.get(r));
            result.set(r, temp);
        }
        return new Population(result);
    }

    public Population mutation(Random rng) {
        List<Individual> result = new ArrayList<>();
        for (Individual individual : population) {
            result.add(individual.mutation(rng, GeneticAlgorithm.MUTATION_PROBABILITY));
        }
        return new Population(result);
    }

    public Population crossover(Random rng) {// Single Point crossover between every 2 individuals in population with CROSSOVER_PROBABILITY
        List<Individual> result = new ArrayList<>();
        for (int i = 0; i < population.size() - 1; i += 2) {
            double r = rng.nextDouble();
            if (r < GeneticAlgorithm.CROSSOVER_PROBABILITY) {
                List<Individual> children = population.get(i).singlePointCrossover(population.get(i + 1), rng);
                result.addAll(children);
            } else {
                result.add(population.get(i));
                result.add(population.get(i + 1));
            }
        }
        return new Population(result);
    }

    public double getTotalFitness() {
        double result = 0;
        for (Individual i : population)
            result += i.getFitness();
        return result;
    }

    public Population sort(Comparator<? super Individual> cmp) {
        List<Individual> population = new ArrayList<>(this.population);
        population.sort(cmp);
        return new Population(population);
    }

    public List<Individual> getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Individual individual : population) {
            result.append(individual.toString());
            result.append(System.lineSeparator());
        }
        return result.toString();
    }


}
