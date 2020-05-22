package controller.handlers;

public class RequestHandlerFactory {
    public static RequestHandler createHandler(String command) {
        /*if (command.equalsIgnoreCase("")){
            try {
                RequestHandler requestHandler = (RequestHandler) Home.class.newInstance();
                return handler;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }*/
        try{
            Class c = Class.forName("controller.handlers."+ command);
            Object o = c.newInstance();
            RequestHandler handler = (RequestHandler) o;
            return handler;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}