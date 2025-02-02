package Lesson11_13.LocalInnerClasses;

public class LocalInnerClasses {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.setLocalInnerClass();
    }
}

class Outer {
    void setLocalInnerClass() {
        class Inner {
            final String userName;
            final String password;

            public Inner(String userName, String password) {
                this.userName = userName;
                this.password = password;
            }
        }

        Inner inner = new Inner("Ceko24", "2009");
        String result = String.format("""
                Username is : %s
                Password is : %s
                """, inner.password, inner.userName);
        System.out.println(result);
    }
}
