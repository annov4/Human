
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
/*
        FileOutputStream fileOutputStream = new FileOutputStream("human.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        Human human = new Human("Ivan", "1789");

        objectOutputStream.writeObject(human);
        objectOutputStream.close();

    }
}
*/

        FileInputStream fileInputStream = new FileInputStream("human.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Human human = (Human) objectInputStream.readObject();
        System.out.println(human);
        objectInputStream.close();

    }
}



