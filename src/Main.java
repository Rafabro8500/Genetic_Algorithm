
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int populationSize = Integer.parseInt(sc.nextLine());
        int chromosomeLength = Integer.parseInt(sc.nextLine());
        int tournamentSize = Integer.parseInt(sc.nextLine());
        GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, chromosomeLength, tournamentSize);
        ga.MUTATION_PROBABILITY = Double.parseDouble(sc.nextLine());
        ga.CROSSOVER_PROBABILITY = Double.parseDouble(sc.nextLine());
        int g = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < g + 1; i++) {
            System.out.format(Locale.US, "%d: %.2f %.2f %.2f\n",
                    i,
                    ga.getPopulation().getMaxFitness(),
                    ga.getPopulation().getAvgFitness(),
                    ga.getPopulation().getMinFitness());
            //System.out.println(i + " Old Gen:\n" + ga.getPopulation().toString());
            ga.nextGeneration();
            //System.out.println(i + " New Gen:\n" + ga.getPopulation().toString());
        }
    }
}
//---------------------------------K--------------------------
    /*Random rng = new Random(0);
    Scanner sc = new Scanner(System.in);
    List<String> geneticCodes = new ArrayList<>();
    List<Double> fitnesses = new ArrayList<>();
        while (sc.hasNext()){
                String line = sc.nextLine();
                String[] split = line.split(" ");
                geneticCodes.add(split[0]);
                fitnesses.add(Double.valueOf(split[1]));
                }
                Population population = new Population(geneticCodes,fitnesses);
                Population newGeneration = population.SUS(rng);
                System.out.print(newGeneration);*/


//---------------------------------- J -----------------
   /*Random rng = new Random(0);
      Scanner sc = new Scanner(System.in);
      List<String> geneticCodes = new ArrayList<>();
      List<Double> fitnesses = new ArrayList<>();
      while (sc.hasNext()){
          String line = sc.nextLine();
          String[] split = line.split(" ");
          geneticCodes.add(split[0]);
          fitnesses.add(Double.valueOf(split[1]));
        }
        Population population = new Population(geneticCodes,fitnesses);
        Population newGeneration = population.rouletteWheelSelection(rng);
        String[] split = newGeneration.toString().split("\n");
        Arrays.sort(split);
        StringBuilder out = new StringBuilder();
        for(String s : split) {
            out.append(s);
            out.append(System.lineSeparator());
        }
        System.out.println(out);*/

//------------------------------ I --------------------
/*  Scanner sc = new Scanner(System.in);
        Random rng = new Random(0);
        int n = sc.nextInt();
        int l = sc.nextInt();
        List<Double> fitnesses = new ArrayList<>();
        for(int i = 0; i < n ; i++)
            fitnesses.add(sc.nextDouble());
        Population population = new Population(n,l,rng, fitnesses);
        Population newGen = population.binaryTournamentSelection(rng);
        System.out.println(newGen.toString());
        sc.close();
        */

//--------------------------------------------- F ---------
        /*Random rng = new Random(0);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        Population population = new Population(n,l,rng);
        System.out.print(population.toString());
        sc.close();*/

//------------------------------------ H -------------
        /*Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        double d;

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        for (int i=0; i< n; i++) {
            d = generator.nextDouble();
            System.out.println(df.format(d));
        }
        sc.close();*/
