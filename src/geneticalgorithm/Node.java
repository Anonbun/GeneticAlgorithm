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
public class Node
{

    float value;
    Connection[] conArray;
    boolean waiting = false; // to be used when threading
    boolean exists = true;
    
    Node()
    {
        this(0, new Connection[0]);
    }

    Node(float value, Connection[] conArray)
    {
        this.value = value;
        this.conArray = conArray;
    }
    
    Node NodeMorph(Node copy, Node[][] thisOrg, Node[][] copyOrg) throws Exception
    {
        this.value = copy.value;
        this.conArray = new Connection[copy.conArray.length];
        int jIndex, kIndex;
        
        for (int i = 0; i < this.conArray.length; i++)
        {
            Connection conCopy = copy.conArray[i];
            
            jIndex = kIndex = -1;
            for (int j = 0; j < copyOrg.length; j++)
            {
                for (int k = 0; k < copyOrg[j].length; k++)
                {
                    if (copyOrg[j][k] == conCopy.node)
                    {
                        jIndex = j;
                        kIndex = k;
                        
                        break;
                    }
                }
            }
            
            if (jIndex == -1 || kIndex == -1)
            {
                throw new Exception("Node copy, was not found in copyOrg");
            }
            
            this.conArray[i] = new Connection(thisOrg[jIndex][kIndex], conCopy.threshold, conCopy.weight);
        }
        
        return this;
    }
    
    Node setValue(float value)
    {
        this.value = value;
        
        return this;
    }
    
    Node setCon(Connection[] conArray)
    {
        this.conArray = conArray;
        
        return this;
    }
    
    void send()
    {
        for (int i = 0; i < conArray.length; i++)
        {
            Connection tempCon = conArray[i];

            if (tempCon.node == null)
            {                
                conArray[i] = null;
            } else if (tempCon.threshold <= abs(value))
            {                
                tempCon.node.add(value * tempCon.weight);
            }
        }
    }

    void add(float value)
    {
        this.value += value;
    }
}
