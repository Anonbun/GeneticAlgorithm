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
