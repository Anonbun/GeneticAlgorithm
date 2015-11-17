/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anonbun
 */
public class CrossoverEngine
{
    private Random rnd;
    private Mutator mut;
    
    CrossoverEngine(boolean mutate)
    {
        this(new Random(), mutate);
    }
    
    CrossoverEngine(Random rnd, boolean mutate)
    {
        this.rnd = rnd;
        if (mutate)
            this.mut = new Mutator(rnd);
    }
    
    Network[] Crossover(Network net1, Network net2, int crossovers)
    {
        Network[] nets = new Network[crossovers];
        
        for (int i = 0; i < nets.length; i++)
        {
            nets[i] = crossover(net1, net2);
        }
        
        return nets;
    }
    
    Network crossover(Network net1, Network net2)
    {
        Node[][] nodeLayer1 = net1.copyNodeLayers();
        Node[][] nodeLayer2 = net2.copyNodeLayers();
        
        if (nodeLayer1.length < nodeLayer2.length)
        {
            Node[][] temp = nodeLayer1;
            nodeLayer1 = nodeLayer2;
            nodeLayer2 = temp;
        }
        
        for (int i = 0; i < nodeLayer1.length; i++)
        {
            Node[] nodes;
            boolean bool = rnd.nextBoolean();
            
            if (bool)
            {
                nodes = new Node[nodeLayer1[i].length];
                
                for (int j = 0; j < nodes.length; j++)
                {
                    try
                    {
                        nodeLayer1[i][j].NodeMorph(nodeLayer2[i][j], nodeLayer1, nodeLayer2);
                    } catch (Exception ex)
                    {
                        Logger.getLogger(CrossoverEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        return new Network(mut == null?nodeLayer1:mut.mutate(nodeLayer1));
    }
    
    @Deprecated
    Network CrossoverOld(Network net1, Network net2)
    {
        Node[][] nodeLayer1 = net1.copyNodeLayers();
        Node[][] nodeLayer2 = net2.copyNodeLayers();
        //Node[][] nodeLayer3 = new Node[nodeLayer1.length][nodeLayer2.length];
        
        for (int i = 0; i < nodeLayer1[0].length; i++)
        {
            try
            {
                nodeLayer1[0][i].NodeMorph(nodeLayer2[0][i], nodeLayer1, nodeLayer2);
            } catch (Exception ex)
            {
                Logger.getLogger(CrossoverEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Network(nodeLayer1);
    }
}
