public class Sieve
{
    private boolean [] primes;

    // Constructor initializes prime list for [0, n]
    Sieve(int n)
    {
        this.primes = new boolean[n + 1];
    }

    // Getter Method
    public boolean [] getPrimeList()
    {
        return primes;
    }

    // Sieve a list of booleans to hold only primes
    // This is done by using prime number indices
    void sieveOfEratosthenes()
    {
        int i, p;

        // Set all primes to true
        for (i = 0; i < primes.length; i++)
        {
            primes[i] = true;
        }

        // Loop through each number
        for (p = 2; p * p < primes.length; p++)
        {

            // If the current number hasn't been cut out of prime list, it is a prime number
            if (primes[p])
            {
                // Cut out the multiples of the current prime number p
                for (i = p * p; i < primes.length; i += p)
                {
                    primes[i] = false;
                }
            }
            
        }
    }

    void printPrimes()
    {
        int i;

        for (i = 2; i < primes.length; i++)
        {
            if (primes[i])
            {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // List 27 prime table lengths, starting at table length of 11
    // The next table length is the next prime value after doubling the current table length
    public static void main(String [] args)
    {
        // 9/10 of max int doesn't go out of bounds in memory
        int i, n = (Integer.MAX_VALUE / 10) * 9;
        int numPrimes = 27;
        int tableLength = 11;
        int index = 0;

        // Create new sieve and filter out for all prime numbers
        Sieve sieve = new Sieve(n);
        sieve.sieveOfEratosthenes();

        // Loop through how many prime table lengths we want (max 27)
        for (i = 0; i < numPrimes; i++)
        {
            // Starter value condition
            if (tableLength == 11)
            {
                System.out.println(tableLength);
            }

            // Double the current table length and find the next prime number
            // Note: hold index so that we don't need to repeat through already done indices
            tableLength *= 2;
            for (int j = index; j < sieve.getPrimeList().length; j++)
            {
                // If current number is prime and is greater than the current table length,
                // that is our new table length
                if (sieve.getPrimeList()[j] && j > tableLength)
                {
                    System.out.println(j);
                    tableLength = index = j;
                    break;
                }
            }
        }
    }
}