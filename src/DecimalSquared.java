public class DecimalSquared implements IProblem{

    @Override
    public double fitness(Individual individual) { //returns the square of the decimal value of the chromosome
        int decimal = Integer.parseInt(individual.toString(),2);
        return Math.pow(decimal,2);
    }
}
