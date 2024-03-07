import java.io.*;

public class Human implements Externalizable {

    String name;

    String passportNumber;
    private static final long serialVersionUID = -5895768866511166697L;
    @Override
    public String toString() {
        return "Human \n" +
                "name = " + name  +
                ", passportNumber = " + passportNumber;
    }

    public String getName() {
        return name;
    }

    public Human() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.encryptPassportNumber(this.passportNumber));

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        passportNumber = this.decryptPassportNumber((String) in.readObject());

    }
    public static String encryptPassportNumber(String passportNumber) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < passportNumber.length() - 1; i++) {
            int encryptedNumber = (Character.getNumericValue(passportNumber.charAt(i))
                    + Character.getNumericValue(passportNumber.charAt(i + 1))) % 10;
            encrypted.append(encryptedNumber);
        }
        encrypted.append(passportNumber.charAt(passportNumber.length() - 1)); // добавляем последний символ без изменений
        return encrypted.toString();
    }

    public static String decryptPassportNumber(String encryptedPassportNumber) {
        StringBuilder decrypted = new StringBuilder();
        int lastNumber = Character.getNumericValue(encryptedPassportNumber.charAt(encryptedPassportNumber.length() - 1));
        int originalLastNumber = lastNumber; //сохраняем оригинальное значение lastNumber
        decrypted.append(originalLastNumber); // добавляем последний символ без изменений
        for (int i = encryptedPassportNumber.length() - 2; i >= 0; i--) {
            int decryptedNumber = (Character.getNumericValue(encryptedPassportNumber.charAt(i)) -
            lastNumber + 10) % 10;
            decrypted.insert(0, decryptedNumber); //записываем в обратном порядке
            lastNumber = decryptedNumber;
        }

        return decrypted.toString();
    }
}
/*
Написать код, который позволит при сериализации зашифровать номер паспорта путем прибавления следующей цифры к предыдущей с остатком от деления на десять.
Последнюю цифру шифровать не нужно.
Пример: 1789 -> 8579

Так же предусмотреть десериализацию обратно в объект
см Externalizible
 */
