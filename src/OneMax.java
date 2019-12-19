public class OneMax implements IProblem {

    @Override
    public double fitness(Individual individual) { //returns number of 1's in chromosome
        int result=0;
        String code = individual.toString();
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            result += c == '1' ? 1 : 0;
        }
        return result;
    }
}
