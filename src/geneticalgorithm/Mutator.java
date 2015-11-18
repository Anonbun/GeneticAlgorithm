/*
 * Copyright (C) 2015 Anonbun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package geneticalgorithm;

import com.carrotsearch.hppc.IntHashSet;
import com.carrotsearch.hppc.LongHashSet;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Anonbun
 */
public class Mutator
{

    private Random rng;
    float rate = 1f;

    Mutator()
    {
        this(new Random());
    }

    Mutator(Random rnd)
    {
        this.rng = rnd;
    }

    public Network mutate(Network net)
    {
        return new Network(mutate(net.copyNodeLayers()));
    }

    Node[][] mutate(Node[][] nodeLayer)
    {
        int nodesToMutate;
        long nodeCount = 0;
        long[] nodesM;
        
        for (Node[] nodes : nodeLayer)
        {
            nodeCount += nodes.length;
        }

        nodesToMutate = (int) (nodeCount * rate);
        if ((nodeCount * rate) - nodesToMutate > rng.nextFloat())
        {
            nodesToMutate++;
        }
        
        nodesM = gen(nodesToMutate, nodeCount);
        
        System.out.println(Arrays.toString(nodesM));
        
        for (int i = 0; i < nodesM.length; i++)
        {
            long temp = 0;
            int outerIndex = -1;
            int innerIndex = -1;
            
            for (int j = 0; j < nodeLayer.length; j++)
            {
                temp += nodeLayer[j].length;
                
                if (nodesM[i] < temp)
                {
                    outerIndex = j;
                    innerIndex = (int) (nodesM[i] - (temp - nodeLayer[j].length));
                    break;
                }
            }
            
            System.out.println(outerIndex + " " + innerIndex);
            
            mutate(nodeLayer[outerIndex][innerIndex], nodeLayer);
        }
        
        /*
         * Add node addition and subtraction mutaion
         */
        
        return nodeLayer;
    }

    private Node mutate(Node node, Node[][] nodeLayer)
    {
        int conToMutate;
        long[] consM;
        
        node.value = mutate(node.value);

        conToMutate = (int) (node.conArray.length * rate);
        if ((node.conArray.length * rate) - conToMutate > rng.nextFloat())
        {
            conToMutate++;
        }
        
        consM = gen(conToMutate, node.conArray.length);
        
        for (long mutIndex : consM)
        {            
            mutate(node.conArray[(int) mutIndex], nodeLayer);
        }
        
        /*
         * Same as add/sub nodes but with this nodes connections.
         */
        
        return node;
    }

    private Connection mutate(Connection con, Node[][] nodeLayer)
    {
        con.threshold = mutate(con.threshold);
        con.weight = mutate(con.weight);
        
        
        
        return con;
    }

    private float mutate(float value)
    {
        return (value != 0) ? value * (rate * (rng.nextFloat() * 2 - 1) + 1)
                : rate * (rng.nextFloat() * 2 - 1) + 1;
    }
    
    public long[] gen(long length, long max)
    {
        LongHashSet candidates = new LongHashSet();
        for (long i = max - length; i < max; i++)
        {
            if (!candidates.add(nextLong(i + 1))) {
                candidates.add(i);
            }
        }

        return candidates.toArray();
    }
    
    long nextLong(long n)
    {
        long bits, val;
        
        do
        {
           bits = (rng.nextLong() << 1) >>> 1;
           val = bits % n;
        } while (bits-val+(n-1) < 0L);
        
        return val;
    }
}
