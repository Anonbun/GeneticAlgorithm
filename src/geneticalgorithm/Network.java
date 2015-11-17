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

import static java.lang.Math.*;
import static java.lang.System.out;

/**
 *
 * @author Anonbun
 */
public class Network
{

    private final Node[][] nodeLayers;

    Network()
    {
        this(new Node[0][0]);
    }

    Network(Node[][] nodeLayers)
    {
        this.nodeLayers = nodeLayers;
    }

    public void run()
    {
        for (Node[] nodeLayer : nodeLayers)
        {
            for (Node nodes : nodeLayer)
            {
                nodes.send();
            }
        }
    }

    Node[][] copyNodeLayers()
    {
        return (Node[][]) ObjectCopy.copy(nodeLayers);
    }

    void write(float[] in) throws IllegalArgumentException
    {
        if (in.length != nodeLayers[0].length)
        {
            throw new IllegalArgumentException(
                    "Input array length does not match input layer length");
        }

        for (int i = 0; i < in.length; i++)
        {
            nodeLayers[0][i].add(in[i]);
        }
    }

    float[] read(float[] out)
    {
        int lastIndex = nodeLayers.length - 1;

        if (out.length != nodeLayers[lastIndex].length)
        {
            throw new IllegalArgumentException(
                    "Output array length does not match output layer length");
        }

        for (int i = 0; i < out.length; i++)
        {
            out[i] = nodeLayers[lastIndex][i].value;
        }

        return out;
    }
}
