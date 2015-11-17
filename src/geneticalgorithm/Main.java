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
import java.util.Arrays;

/**
 *
 * @author Anonbun
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        Node[][] nodes1 = blankNodes(new int[]{2,2});
        Node[][] nodes2 = blankNodes(new int[]{2,2});

        nodes1[0][0].setValue(1).setCon(new Connection[]{
            new Connection(nodes1[0][1], 2, 1)});
        nodes1[0][1].setValue(0).setCon(new Connection[]{
            new Connection(nodes1[1][1], 0, 1)});
        nodes1[1][0].setValue(2).setCon(new Connection[]{
            new Connection(nodes1[0][1], 1, 1)});
        nodes1[1][1].setValue(1).setCon(new Connection[]{
            new Connection(nodes1[1][0], 0, 1)});

        nodes2[0][0].setValue(1).setCon(new Connection[]{
            new Connection(nodes2[1][1], 2, 2)});
        nodes2[0][1].setValue(1);
        nodes2[1][0].setValue(2).setCon(new Connection[]{
            new Connection(nodes2[0][0], 0, 1)});
        nodes2[1][1].setValue(0).setCon(new Connection[]{
            new Connection(nodes2[0][0], 1, 1)});

        Network net1 = new Network((Node[][]) ObjectCopy.copy(nodes1));
        Network net2 = new Network((Node[][]) ObjectCopy.copy(nodes2));

        System.out.println("Net1 before run: " + Arrays.toString(net1.read(new float[2])));
        net1.run();
        net1.run();
        System.out.println("Net1 after 2 runs: " + Arrays.toString(net1.read(new float[2])));

        System.out.println("Net2 before run: " + Arrays.toString(net2.read(new float[2])));
        net2.run();
        net2.run();
        System.out.println("Net2 after 2 runs: " + Arrays.toString(net2.read(new float[2])));

        //* test
        CrossoverEngine cr = new CrossoverEngine(true);
        Network net3 = cr.crossover(
                new Network((Node[][]) ObjectCopy.copy(nodes1)),
                new Network((Node[][]) ObjectCopy.copy(nodes2)));

        System.out.println("Net3 before run: " + Arrays.toString(net3.read(new float[2])));
        net3.run();
        net3.run();
        System.out.println("Net3 after 2 runs: " + Arrays.toString(net3.read(new float[2])));
        //*/
    }
    
    public static Node[][] blankNodes(int[] innerArraySize)
    {
        Node[][] returnNodes = new Node[innerArraySize.length][];
        
        for (int i = 0; i < returnNodes.length; i++)
        {
            returnNodes[i] = new Node[innerArraySize[i]];
            
            for (int j = 0; j < returnNodes[i].length; j++)
            {
                returnNodes[i][j] = new Node();
            }
        }
        
        return returnNodes;
    }
    
    /**
     * @param args the command line arguments
     */
    
    /* XOR test
    public static void main(String[] args)
    {
        Node[][] nodes = new Node[][]{
            {null, null},
            {null, null},
            {null}
        };
        
        // XOR network
        nodes[2][0] = new Node();
        nodes[1][1] = new Node(0, new Connection[]{new Connection(nodes[2][0], 0.5f, 1)});
        nodes[1][0] = new Node(0, new Connection[]{new Connection(nodes[2][0], 1.5f, -1)});
        nodes[0][1] = new Node(0, new Connection[]{new Connection(nodes[1][0], 0, 1), new Connection(nodes[1][1], 0, 1)});
        nodes[0][0] = new Node(0, new Connection[]{new Connection(nodes[1][0], 0, 1), new Connection(nodes[1][1], 0, 1)});
        
        nodes[2][0].conArray = new Connection[]{new Connection(nodes[2][0], 0, 5)};
        
        float[][] in = new float[][]{{0,0}, {0,1}, {1,0}, {1,1}};
        float[][] out = new float[4][1];
        
        /*
        for (int i = 3; i < 4; i++)
        {
            Network net = new Network(copy(nodes));
                        
            net.write(in[i]);
            net.run();
            out[i] = net.read(out[i]);
        }
        *//*
    
        Network net = new Network((Node[][]) ObjectCopy.copy(nodes));
        net.write(new float[]{0, 1});
        net.run();
        
        System.out.println(Arrays.toString(net.read(out[0])));
        
    }
    */
}
