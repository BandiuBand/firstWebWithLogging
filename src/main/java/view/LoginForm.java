package view;

public class LoginForm {
    private static final String content = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Login</title>
            </head>
            <body>
                <form action="login" method="POST">
                    <div>
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username">
                    </div>
                    <div>
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password">
                    </div>
                    <div>
                        <button type="submit">Login</button>
                        <a href="/firstWeb/signup" style="display: inline-block; padding: 8px 15px; background-color: #007bff; color: white; text-align: center; text-decoration: none; border-radius: 5px; font-weight: bold;">Sign Up</a>
                    </div>
                </form>
            </body>
            </html>""";

    private LoginForm() {
    }
    public static String getContent(){
        return content;
    }
}
