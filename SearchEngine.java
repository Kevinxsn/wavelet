
import java.net.URI;
import java.util.ArrayList;
import java.io.IOException;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String  inputs ="";
    ArrayList<String> another_inputs= new ArrayList<>();
    ArrayList<String> result= new ArrayList<>();


    public String handleRequest(URI url) {
        
        if (url.getPath().contains("add")){
            String[] things_useful = url.getQuery().split("=");
            another_inputs.add(things_useful[1]);
            String need_to_return = String.format("String added: %s", things_useful[1]);
            return need_to_return;
            
        } else if (url.getPath().contains("search")){
            String[] things_useful = url.getQuery().split("=");
            String exists = things_useful[1];
            String result_to_return = "";
            for(String s: another_inputs){
                
                    result_to_return += s + " "; 
                
            }
            return result_to_return; 
               
        }
        else{
            return "404 Not Found!";
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}







