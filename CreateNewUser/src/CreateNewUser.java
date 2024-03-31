import java.util.Locale;
import java.util.Scanner;

/**
 * HW-03 -- CreateNewUser
 *
 * This program takes in input from System.in
 * performs string manipulation and returns a user String
 *
 * @author Shivi Pandey, L01
 *
 * @version Jan 30, 2024
 *
 */

public class CreateNewUser {

    //List of public static final strings provided to prevent typos

    public static final String PROMPT_ONE = "Enter Customer First Name:";
    public static final String PROMPT_TWO = "Enter Customer Last Name:";
    public static final String PROMPT_THREE = "Enter Customer Age:";
    public static final String PROMPT_FOUR = "Enter Customer Street Address:";
    public static final String PROMPT_FIVE = "Enter Customer City:";
    public static final String PROMPT_SIX = "Enter Customer State:";
    public static final String PROMPT_SEVEN = "Enter Customer Zip Code:";
    public static final String PROMPT_EIGHT = "Enter Customer Phone Number:";
    public static final String OUTPUT = "The Assigned User String is ";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int inputCounter = 0;

        System.out.println(PROMPT_ONE);
        String firstname = scan.nextLine();

        int n = firstname.length();

        inputCounter += n;

        char firstname1 = firstname.charAt(0);

        char firstname2 = firstname.charAt(n - 1);

        String first = new StringBuilder().append(firstname1).append(firstname2).toString();

        String firsts = first.toLowerCase();


        System.out.println(PROMPT_TWO);

        String lastname = scan.nextLine();

        int n2 = lastname.length();

        inputCounter += n2;

        char lastname1 = lastname.charAt(0);

        char lastname2 = lastname.charAt(1);

        String last = new StringBuilder().append(lastname1).append(lastname2).toString();

        String lasts = last.toUpperCase();

        System.out.println(PROMPT_THREE);

        String age = scan.nextLine();

        int n3 = age.length();

        inputCounter += n3;

        char firstDigit = age.charAt(0);

        char secondDigit = age.charAt(1);

        int ageInt = Character.getNumericValue(secondDigit);

        int digit2 = ageInt % 10;

        String binaryString = String.format("%4s", Integer.toBinaryString(digit2)).replace(' ', '0');

        System.out.println(PROMPT_FOUR);
        String address = scan.nextLine();

        int n4 = address.length();

        inputCounter += n4;

        address = address.replaceAll("\\s" , "").toUpperCase();

        System.out.println(PROMPT_FIVE);
        String city = scan.nextLine();

        int n5 = city.length();

        inputCounter += n5;

        char city1 = city.charAt(0);

        char city2 = city.charAt(1);

        String cities = new StringBuilder().append(city1).append(city2).toString();

        cities = cities.toUpperCase();

        System.out.println(PROMPT_SIX);
        String state = scan.nextLine();

        int n6 = state.length();

        inputCounter += n6;

        state = state.toUpperCase();

        char state1 = state.charAt(0);

        char state2 = state.charAt(1);

        int stateASCII = state1 + state2;

        String stateASCIIString = Integer.toString(stateASCII);

        System.out.println(PROMPT_SEVEN);

        String zip = scan.nextLine();

        int n7 = zip.length();

        inputCounter += n7;

        char zip1 = zip.charAt(0);

        int zip11 = Character.getNumericValue(zip1);

        char zip3 = zip.charAt(2);

        int zip31 = Character.getNumericValue(zip3);

        int zipTotal = zip11 + zip31;

        String zipTotalString = Integer.toString(zipTotal);

        System.out.println(PROMPT_EIGHT);

        String phone = scan.nextLine();


        int num = phone.length();

        inputCounter += num;

        char phone1 = phone.charAt(num - 1);

        char phone2 = phone.charAt(num - 2);

        char phone3 = phone.charAt(num - 3);

        char phone4 = phone.charAt(num - 4);


        String phoneNum = new StringBuilder().append(phone4).append(phone3).append(phone2).append(phone1).toString();


        String result = new StringBuilder().append(firstDigit).append(lasts).append(firsts).toString();

        result = result.concat(binaryString).concat(address).concat(" ").toString();

        result = result.concat(cities).concat(stateASCIIString).concat(zipTotalString).concat(phoneNum).toString();


        String result2 = new StringBuilder().append(inputCounter).append(result).toString();

        String resultO = result2.replaceAll("O" , "");

        String resultI = resultO.replaceAll("I" , "");














        //System.out.println(firstDigit+lasts+firsts+binaryString+address+" "+cities+stateASCII+zipTotal+phoneNum);

        System.out.println("The Assigned User String is " + resultI);


    } //End Main method


} //End CreateNewUser.class