/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import static java.lang.Math.*;
import static java.lang.System.out;

/**
 *
 * @author max
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
