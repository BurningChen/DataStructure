package classloader;

public class ClassLoaderStudy {
    public static void main(String[] args) throws Exception {
        // 启动类加载器
        String str = "Hello Class Loader";
        System.out.println("str class loader =="+str.getClass().getClassLoader());

        // 平台类加载器
        Class driver = Class.forName("java.sql.Driver");
        System.out.println("driver class loader="+driver.getClassLoader());
        System.out.println("driver parent class loader="+driver.getClassLoader().getParent());

        // 应用程序加载器
        ClassLoaderStudy t = new ClassLoaderStudy();
        System.out.println("t class loader="+t.getClass().getClassLoader());
        System.out.println("t parent class loader="+t.getClass().getClassLoader().getParent());
    }
}
