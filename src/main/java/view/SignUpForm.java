package view;

public class SignUpForm {
    private static final String contentAfter = """
            <!DOCTYPE html>
            <html>
            <head>
            
                <meta charset="UTF-8">
                <title>Sign up successful</title>
            </head>
            <body>
                    <div>
                        Ми надіслали вам листа на пошту, підтвердіть вашу пошту перейшовши за посиланням.
                    </div>
                </form>
            </body>
            </html>""";

    private static final String content = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Sign up</title>
            </head>
            <body>
                <form action="http://localhost/firstWeb/signup" method="POST">
                    <div>
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username">
                    </div>
                    <div>
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password">
                    </div>
                    <div>
                        <label for="password">Email:</label>
                        <input type="email" id="email" name="email">
                    </div>
                    <div>
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </body>
            </html>""";

    private SignUpForm() {
    }
    public static String getContent(){
        return content;
    }
    public static String getContentAfter(){

        return contentAfter;
    }
}
