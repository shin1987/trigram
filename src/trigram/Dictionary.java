package trigram;

import java.util.Hashtable;
import java.util.ArrayList;

/**
 * An implementation of TrigramDictionary using HashTable lookup.
 */
public class Dictionary implements trigram.core.TrigramDictionary
{
    // Main dictionary storage.
    private Hashtable<Key, ArrayList<String>> dict =
            new Hashtable<Key, ArrayList<String>>();
    
    // Cache all keys in this dictionary to speed up lookup by index
    private ArrayList<Key> keys = new ArrayList<Key>();

    @Override
    public String [] getKey(final int index)
    {
        return this.keys.get(index).toArray();
    }

    @Override
    public int getNumOfEntries()
    {
        return this.keys.size();
    }

    @Override
    public String [] lookup(final String key1, final String key2)
    {
        var list = this.dict.get(new Key(key1, key2));
        if (list == null) {
            return null;
        }
        else {
            String [] ret = new String[list.size()];
            list.toArray(ret);
            return ret;
        }
    }

    @Override
    public void addEntry(final String key1,
                         final String key2,
                         final String word)
    {
        var key = new Key(key1, key2);
        var list = this.dict.get(key);
        
        if (list == null) // the key has never been seen before
        {
            // Initialise an entry in the hash table
            list = new ArrayList<String>();
            this.keys.add(key);
            this.dict.put(key, list);
        }

        list.add(word);
    }
}

// Internal class that to organise words into hashtable keys
class Key
{
    final String key1, key2;

    Key(final String key1, final String key2)
    {
        this.key1 = key1;
        this.key2 = key2;
    }

    String [] toArray()
    {
        String [] ret = new String[2];
        ret[0] = this.key1;
        ret[1] = this.key2;
        return ret;
    }

    // Required by Hashtable
    @Override
    public boolean equals(final Object other)
    {
        if (!(other instanceof Key))
            return false;

        var rhs = (Key)other;
        return key1 == rhs.key1 && key2 == rhs.key2;
    }

    // Required by Hashtable
    @Override
    public int hashCode()
    {
        return (key1 + key2).hashCode();
    }
}
