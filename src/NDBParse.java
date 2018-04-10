import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The Class Ndb.
 */
//Class for parsing .ndb files and constructing a pseudo-database out of them.
public class NDBParse {
    
    // phase one: reads the file and creates objects from the entries.
    public static void parse(String filename) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        String args = "";
        String line = file.readLine();
        while (line != null) {
            if (line.startsWith(" ")) {
                args = args.concat(line);
            } else {
                args = line;
                String[] params = args.split("\\s+");
                DataList<Pair<String, String>> object = new DataList<Pair<String, String>>(
                        new Pair<String, String>(params[0].split("=")[0], params[0].split("=")[1]));
                for (int i = 1; i < params.length; i++) {
                    Pair<String, String> temp = new Pair<String, String>(params[i].split("=")[0],
                            params[i].split("=")[1]);
                    object.append(temp);
                }
                convert(object);
                args = "";
            }
            line = file.readLine();
        }
        file.close();
    }

    // method which parses the type of the entry and calls the corresponding
    /**
     * Convert.
     *
     * @param object
     *            the object
     * @throws Exception
     *             the exception
     */
    // conversion procedure.
    private static void convert(DataList<Pair<String, String>> object) throws Exception {
        String type = extract(object, "type");
        if (type.equals("actor")) {
            actor(object);
        } else if (type.equals("movie")) {
            movie(object);
        } else if (type.equals("role")) {
            role(object);
        } else {
            throw (new Exception("Invalid type."));
        }
    }