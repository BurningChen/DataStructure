package classloader;

public class MyClassLoader extends ClassLoader {
    private String myName = "";
    public MyClassLoader(String myName){
        this.myName = myName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    private byte[] loadClassData(String clsName){
        byte[] data = null;
        return data;
    }
}
