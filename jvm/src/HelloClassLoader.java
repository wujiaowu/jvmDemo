import java.io.File;
import java.io.FileInputStream;


public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        new HelloClassLoader().findClass("Hello").newInstance();
        Class<?> clazz=new HelloClassLoader().loadClass("Hello");
        System.out.println(clazz.getClassLoader());  //打印类加载器

        Object newInstance = clazz.newInstance();
        clazz.getMethod("hello").invoke(newInstance);  //调用方法


    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("jvm/src/Hello.xlass");
        byte[] data = new byte[(int) file.length()];
        byte[] result = new byte[(int) file.length()];

        try {
            new FileInputStream(file).read(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 1; i <= file.length(); i++) {
            result[i - 1] = (byte) (255 - data[i - 1]);
        }
        return defineClass(name, result, 0, result.length);
    }
}