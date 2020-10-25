package Chapter_7.HashTable;

import java.util.ArrayList;

public class Hash_Table<K,V>
{
    class HashNode<K,V>
    {
        K key;
        V val;

        HashNode<K,V> next;

        public HashNode(K key, V value)
        {
            this.key = key;
            this.val = value;
        }
    }

    class Map<K,V>
    {
        private  ArrayList<HashNode<K,V>> bucket;

        private int numBuckets;

        private int size;

        public Map()
        {
            bucket = new ArrayList<>();
            numBuckets = 10;
            size = 0;

            for(int i = 0; i < numBuckets; i++)
            {
                bucket.add(null);
            }
        }

        public int size()
        {
            return size;
        }

        public boolean isEmpty()
        {
            return size() == 0;
        }

        private int getBucketIndex(K key)
        {
            int hash = key.hashCode();
            int index = hash % numBuckets;
            return index;
        }

        public V remove(K key)
        {
            int bucketIndex = getBucketIndex(key);

            HashNode<K,V> head = bucket.get(bucketIndex);

            HashNode<K,V> prev = null;
            while(head != null)
            {
                if(head.key.equals(key))
                {
                    break;
                }

                prev = head;
                head = head.next;
            }

            if(head == null)
            {
                return null;
            }

            size--;

            if(prev != null)
            {
                prev.next = head.next;
            }
            else
            {
                bucket.set(bucketIndex, head.next);
            }

            return head.val;
        }

        public V get(K key)
        {
            int bucketIndex = getBucketIndex(key);
            HashNode<K,V> head = bucket.get(bucketIndex);

            while(head != null)
            {
                if(head.key.equals(key))
                {
                    return head.val;
                }
                head = head.next;
            }

            return null;
        }

        public void add(K key, V value)
        {
            int bucketIndex = getBucketIndex(key);
            HashNode<K,V> head = bucket.get(bucketIndex);

            while(head != null)
            {
                if(head.key.equals(key))
                {
                    head.val = value;
                    return;
                }
                head = head.next;
            }
            size++;
            head = bucket.get(bucketIndex);
            HashNode<K,V> newNode = new HashNode<>(key,value);
            newNode.next = head;
            bucket.set(bucketIndex, newNode);

            if((1.0 * size) / numBuckets >= 0.7)
            {
                ArrayList<HashNode<K,V>> temp = bucket;
                bucket = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;
                for(int i = 0; i < numBuckets; i++)
                {
                    bucket.add(null);
                }

                for(HashNode<K,V> headNode : temp)
                {
                    while(headNode != null)
                    {
                        add(headNode.key, headNode.val);
                        headNode = headNode.next;
                    }
                }
            }

        }
    }
}
