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
 * @author Anonbun
 */
public class Connection
{

    Node node;
    float threshold;
    float weight;

    public Connection()
    {
        this(null, 0, 1);
    }

    public Connection(Node node, float threshold, float weight)
    {
        this.node = node;
        this.weight = weight;
        this.threshold = threshold;
    }
}
