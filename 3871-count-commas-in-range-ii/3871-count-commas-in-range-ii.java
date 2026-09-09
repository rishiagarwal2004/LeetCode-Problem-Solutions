class Solution {
    public long countCommas(long n) {
        long commas = 0;
        long threshold = 1000; // Pehla comma 1,000 par aata hai
        
        // Jab tak n is threshold se bada hai, tab tak commas add karte raho
        while (n >= threshold) {
            commas += (n - threshold + 1);
            threshold *= 1000; // agla comma 1,000,000 par aayega
        }
        
        return commas;
    }
}
