class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) { 
            map.put(pair.get(0), pair.get(1)); 
        }
        char[] str = s.toCharArray(); 
        int i = 0;
        String result = "";
        while(i<n){
            if(str[i] != '('){
                result += str[i];
                i++;
            }
            else{
                i++;
                String temp = "";
                while(str[i]!=')'){
                    temp += str[i];
                    i++;
                }
                if(map.containsKey(temp)){
                    result += map.get(temp);
                }
                else{
                    result += "?";
                }
                i++;
            }

        }
        return result;

    }
}