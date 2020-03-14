public class Phonetic {

	public static String getPhoneticGode(String str) 
    {
        char[] x = str.toLowerCase().toCharArray();
         
         
        char firstLetter = x[0];

        //Convert letters to numeric code
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
            case 'b':
            case 'f':
            case 'p':
            case 'v': {
                x[i] = '1';
                break;
            }
 
            case 'c':
            case 'g':
            case 'j':
            case 'k':
            case 'q':
            case 's':
            case 'x':
            case 'z': {
                x[i] = '2';
                break;
            }
 
            case 'd':
            case 't': {
                x[i] = '3';
                break;
            }
 
            case 'l': {
                x[i] = '4';
                break;
            }
 
            case 'm':
            case 'n': {
                x[i] = '5';
                break;
            }
 
            case 'r': {
                x[i] = '6';
                break;
            }
 
            default: {
                x[i] = '0';
                break;
            }
            }
        }
 
        //Remove duplicates
        String output = "" + firstLetter;
         
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i - 1] && x[i] != '0')
                output += x[i];

        //generate code
        output = output + "0000";
        
        return output.substring(0, 4);
    }

    public static void main(String[] args) {
    	
    	String name1 = "dear";
        String name2 = "deer";
        String name3 = "dearest";
        
        String code1 = getPhoneticGode(name1);
        String code2 = getPhoneticGode(name2);
        String code3 = getPhoneticGode(name3);
         
        System.out.println(name1 + " and " + name2 + " is phonetic : " + code1.equals(code2));
        System.out.println(name1 + " and " + name3 + " is phonetic : " + code1.equals(code3));
        
    }
}
