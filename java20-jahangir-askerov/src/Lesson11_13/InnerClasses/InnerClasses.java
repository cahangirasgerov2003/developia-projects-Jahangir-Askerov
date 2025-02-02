package Lesson11_13.InnerClasses;

public class InnerClasses {
    public static void main(String[] args) {
        Outer.Inner inner = new Outer.Inner("0000", "cekoos2121");
        System.out.println(inner.getUserInfo());
    }
}

class Outer {
    static class Inner {
        String password;
        String userName;

        public String getUserInfo() {
            return String.format("""
                    Password is `%s`
                    User name is `%s`
                    """, password, userName);
        }

        public Inner(String password, String userName) {
            this.password = password;
            this.userName = userName;
        }
    }
}
