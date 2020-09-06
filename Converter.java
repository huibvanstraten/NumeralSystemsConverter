import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean insert = false;

        while (!insert) {
            try {
                System.out.println("Please insert your source radix (between 1 and 36): ");
                int sourceRad = Integer.parseInt(scanner.nextLine());
                System.out.println("Now enter the number you would like to be converted: ");
                String sourceNum = scanner.nextLine();
                System.out.println("In which radix would you like the number to be outputted?(between 1 and 36): ");
                int targetRad = Integer.parseInt(scanner.nextLine());

                if (sourceRad < 1 || sourceRad > 36 || targetRad < 1 || targetRad > 36) {
                    throw new NumberFormatException("Radix should be between 1 and 36");
                } else if (sourceNum.contains(".")) {
                    System.out.print("The number " + sourceNum + " in radix " + targetRad + " is: ");
                    Converters.fractionalConvert(sourceRad, sourceNum, targetRad);
                    insert = true;
                } else {
                    System.out.print("The number " + sourceNum + " in radix " + targetRad + " is: ");
                    Converters.converterInt(sourceRad, sourceNum, targetRad);
                    insert = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Source radix doesn't match to inserted number");
            }
        }
    }
}

class Converters {
    public static String fractionalDecimalConvert(String sourceNum, int sourceRad) {
        String integer = sourceNum.split("\\.")[0];
        String fractional = sourceNum.split("\\.")[1];
        int integerDec = Integer.parseInt(integer, sourceRad);
        double fractionalDec = 0;
        for (int i = 1; i <= fractional.length(); i++) {
            double count =  Integer.parseInt(String.valueOf(fractional.charAt(i - 1)), sourceRad) / Math.pow(sourceRad, i);
            fractionalDec += count;
        }
        return String.valueOf(integerDec + fractionalDec);
    }

    public static void converterInt(int sourceRad, String sourceNum, int targetRad) {
        if (sourceRad != 1 && targetRad != 1) {
            if (sourceRad == 10) {
                System.out.println(Integer.toString(Integer.parseInt(sourceNum), targetRad));
            } else {
                int num = Integer.parseInt(sourceNum, sourceRad);
                System.out.println(Integer.toString(num, targetRad));
            }
        } else if (sourceRad == 1) {
            int num = sourceNum.length();
            System.out.println(Integer.toString(num, targetRad));
        } else {
            int num = Integer.parseInt(sourceNum, sourceRad);
            System.out.println("1".repeat(Math.max(0, num)));
        }
    }

    public static void fractionalConvert(int sourceRad, String sourceNum, int targetRad) {
        String num = fractionalDecimalConvert(sourceNum, sourceRad);
        int x = Integer.parseInt(num.split("\\.")[0]);
        String numInt = Integer.toString(x, targetRad);
        StringBuilder sb = new StringBuilder();
        if (targetRad == 1) {
            System.out.println("1".repeat(Math.max(0, x)));
        } else {
            sb.append(".");
            double value = Double.parseDouble("0." + num.split("\\.")[1]);
            for (int i = 0; i < 5; i++) {
                double count = value * targetRad;
                int newNum = (int) count;
                sb.append(Integer.toString(newNum, targetRad));
                value = count - newNum;
            }
            System.out.println(numInt + sb.toString());
        }
    }
}