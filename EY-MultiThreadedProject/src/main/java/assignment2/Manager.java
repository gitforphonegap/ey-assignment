package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The below class is responsible for orchestrating Dag.
 */
public class Manager implements Runnable{
    private String bodmasExpression;

    public Manager(String bodmasExpression) {
        this.bodmasExpression = bodmasExpression;
    }

    @Override
    public void run() {
        char[] nodeValues = bodmasExpression.toCharArray();

        List<String> listOfString = new CopyOnWriteArrayList<>();

        for (int i = 0; i < nodeValues.length; i++) {
            listOfString.add(String.valueOf(nodeValues[i]));
        }

        constructDAG(listOfString);
    }

    private void constructDAG(List<String> listOfString) {
        for(String string : listOfString){
            if(string.equals("(")){
                int indexOfStart = listOfString.indexOf(string);
                int indexOfEnd = listOfString.indexOf(")");

                if(indexOfEnd - indexOfStart == 3){
                    Node node = new Node(listOfString.get(indexOfStart), listOfString.get(indexOfStart+2),
                            listOfString.get(indexOfStart+1) );
                }

                listOfString.remove(indexOfStart);
                listOfString.remove(indexOfEnd);
                listOfString.remove(indexOfEnd+1);
                listOfString.remove(indexOfEnd+2);
            } else if(string.equals("*")){

            }
        }
    }
}
