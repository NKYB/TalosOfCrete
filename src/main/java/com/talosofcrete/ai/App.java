package com.talosofcrete.ai;

public class App {
    public static void main( String[] args ){
        Config config = new Config();
//        config.randomize();                60650000
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
//        data.importCSVData();
        data.createRandom(10, 5);
        
        Population population = new Population(config, data);
        
        Aparapi aparapi = new Aparapi(config, data);
        aparapi.evolve(population.programs[0]);
    }
}
