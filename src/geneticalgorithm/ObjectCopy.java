/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import com.esotericsoftware.kryo.Kryo;

/**
 *
 * @author Anonbun
 */
public class ObjectCopy
{
    private static final Kryo kryo = new Kryo();
    
    public static Object copy(Object obj)
    {
        return kryo.copy(obj);
    }
}
