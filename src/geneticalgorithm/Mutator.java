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

import java.util.Random;

/**
 *
 * @author Anonbun
 */
public class Mutator
{

    private Random rnd;
    float rate;

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
        int nodesToMutate;
        long nodeCount = 0;

        for (Node[] nodes : nodeLayer)
        {
            nodeCount += nodes.length;
        }

        nodesToMutate = (int) (nodeCount * rate);
        if ((nodeCount * rate) - nodesToMutate > rnd.nextFloat())
        {
            nodesToMutate++;
        }

        /*
         * Select nodesToMutate amount of nodes from nodeLayer, so that the
         * selection is uniform across the 2d array. Each node can only be
         * selected to be mutate once.
         */
        return nodeLayer;
    }

    private Node mutate(Node node, Node[][] nodeLayer)
    {
        int conToMutate;

        node.value = mutate(node.value);

        conToMutate = (int) (node.conArray.length * rate);
        if ((node.conArray.length * rate) - conToMutate > rnd.nextFloat())
        {
            conToMutate++;
        }

        /*
         * Same as selecting nodes but with this nodes connections.
         */
        return node;
    }

    private Connection mutate(Connection con, Node[][] nodeLayer)
    {
        con.threshold = mutate(con.threshold);
        con.weight = mutate(con.weight);

        /*
         * Select a random node from nodeLayer to change the target node into.
         */
        return con;
    }

    private float mutate(float value)
    {
        return (value != 0) ? value * (rate * (rnd.nextFloat() * 2 - 1) + 1)
                : rate * (rnd.nextFloat() * 2 - 1) + 1;
    }
}
