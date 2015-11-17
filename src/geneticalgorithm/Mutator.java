/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;

/**
 *
 * @author max
 */
public class Mutator
{
    private Random rnd;
    double min;
    double max;
    
    Mutator()
    {
        this(new Random());
    }
    
    Mutator(Random rnd)
    {
        this.rnd = rnd;
    }
    
    public Network mutate(Network net)
    {
        return new Network(mutate(net.copyNodeLayers()));
    }

    Node[][] mutate(Node[][] nodeLayer)
    {
        
    }
}
