package uj.java.pwj2020.spreadsheet;

public class Spreadsheet {

    private static String referenceToValue(String reference,String[][] input,int i,int j){
        char row=reference.charAt(1);
        int column= Integer.parseInt(reference.substring(2))-1;
        int rowAscii=(int) row-65;

        input[i][j]=input[column][rowAscii];
        if(input[i][j].charAt(0)=='$'){
            return referenceToValue(input[i][j],input,column, rowAscii);
        }
        return input[i][j];
    }

    private static String[] returnValue(String value, String[][] input,int i,int j){
        String onlyValue=value.substring(value.indexOf("(") + 1, value.indexOf(")"));//zostawiamy tylko np. 10,$A3
        String[] substring=onlyValue.split(",");//zostawiamy tylko tablice z liczbą/referencją
        String[] result=new String[2];
        if(substring[0].charAt(0)=='$'){
            result[0]=referenceToValue(substring[0],input,i,j);
        }else
            result[0]=substring[0];

        if(substring[1].charAt(0)=='$'){
            result[1]=referenceToValue(substring[1],input,i,j);
        }else
            result[1]=substring[1];

        return result;
    }

    private static int add(String firstAddend,String secondAddend){
        return Integer.parseInt(firstAddend)+Integer.parseInt(secondAddend);
    }
    private static int sub(String minued,String subtrahend){
        return Integer.parseInt(minued)-Integer.parseInt(subtrahend);
    }
    private static int mul(String multiplicand,String multiplier){
        return Integer.parseInt(multiplicand)*Integer.parseInt(multiplier);
    }
    private static int div(String dividend,String divisor){
        return Integer.parseInt(dividend)/Integer.parseInt(divisor);
    }
    private static int mod(String dividend,String divisor){
        return Integer.parseInt(dividend)%Integer.parseInt(divisor);
    }

    private static int whichOperation(String operation,String[][] input,int i,int j){
        String[] reference= returnValue(operation,input,i,j);
        if(operation.charAt(1)=='A')
            return add(reference[0],reference[1]);
        else if(operation.charAt(1)=='S')
            return sub(reference[0],reference[1]);
        else if(operation.charAt(1)=='D')
            return div(reference[0],reference[1]);
        else{
            if(operation.charAt(2)=='U')
                return mul(reference[0],reference[1]);
            else
                return mod(reference[0],reference[1]);
        }
    }

    public static String[][] calculate(String[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j].charAt(0) == '=')
                    input[i][j]= String.valueOf(whichOperation(input[i][j],input,i,j));
                else if(input[i][j].charAt(0) == '$')
                    input[i][j]=referenceToValue(input[i][j], input,i,j);
            }

        }
        return input;
    }
    public static void main(String[] args){
//        String reference="=ADD(10,$A1)";
//        String[][] test = new String[100][100];
//
//        test[0][1]="5";
////        String[] test2;
////        test2=whereReferenceInFormula(reference,test);
//        System.out.print(whichOperation(reference,test));

    }

}
