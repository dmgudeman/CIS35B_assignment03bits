
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.IntSummaryStatistics;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The input functions of the program. The readXML method takes a text xml
 * file and returns a document of nodes.
 *
 * The GetNodes method makes the nodes available
 * Created by davidgudeman on 7/9/15.
 */

/**
 * Takes a string name of a xml text file can return a Nodelist
 */

public class XMLReader
{
    public Document doc;
    public String sfile;
    public BitSet bitSet;

    public XMLReader(String sfile) throws ParserConfigurationException, IOException, SAXException
    {
        this.sfile = sfile;
    }

    // takes in xml file parses it into nodes
    public Document ReadXML() throws ParserConfigurationException, IOException, SAXException
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(sfile);

            doc.getDocumentElement().normalize();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return doc;
    }

    // allow for retreval of the nodes
    public NodeList GetNodes(Document doc)
    {
        NodeList nList = doc.getElementsByTagName("command");
        for (int i = 0; i < nList.getLength(); i++)
        {
            Node child = nList.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) child;
            }
        }
        return nList;
    }

    /**
     * This method takes in a NodeList, extracts the nodes, creates
     * a String[] of nodes, packs the array into an ArrayList and
     * Returns the List
     * @param: NodeList list
     * @return: ArrayList<String[]>
     */
    public ArrayList<String[]> getArrayList(NodeList list)
    {
        ArrayList<String[]> arrayList = new ArrayList<>();
        try
        {
            for (int i = 0; i < list.getLength(); i++)
            {
                Node child = list.item(i);
                String[] tagNameArray = {null, null, null, null, null, null};
                if (child.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) child;


                    String robot = eElement.getElementsByTagName("robot").item(0).getTextContent();
                    String offon = eElement.getElementsByTagName("offon").item(0).getTextContent();
                    String speed = eElement.getElementsByTagName("speed").item(0).getTextContent();
                    String horizontal = eElement.getElementsByTagName("horizontal").item(0).getTextContent();
                    String vertical = eElement.getElementsByTagName("vertical").item(0).getTextContent();
                    String time = eElement.getElementsByTagName("time").item(0).getTextContent();
                    tagNameArray[0] = robot;
                    tagNameArray[1] = offon;
                    tagNameArray[2] = speed;
                    tagNameArray[3] = horizontal;
                    tagNameArray[4] = vertical;
                    tagNameArray[5] = time;
                    arrayList.add(tagNameArray);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * This method takes in an ArrayList<String[]> that represents the robot arm commands.
     *
     * It extracts the name of the robot arm in BitSet(0,5). The boolean commands in the next
     * 4 bits: onOff=BitSet(5), Speed=BitSet(6), Horizontal=BitSet(7), Vertical=BitSet(8).
     *
     * The number of milliseconds are in BitSet(9,17)
     *
     * @param: ArrayList<String[]> list
     * @return: ArrayList<BitSet>
     */

    public ArrayList<BitSet> setBits(ArrayList<String[]> list)
    {
        ArrayList<BitSet> BSlist = new ArrayList<>();
        try
        {
            long temp = 0;

            for (int i = 0; i <=list.size()-1; i++)
            {
                bitSet = new BitSet(16);
                if (list.get(i)[0].equals("A1")){ bitSet.set(1);}
                else if (list.get(i)[0].equals("A2")){ bitSet.set(2);}
                else if (list.get(i)[0].equals("A3")){ bitSet.set(3);}
                else { bitSet.set(4);}

                if (list.get(i)[1].equals("On")){ bitSet.set(5);}
                if (list.get(i)[2].equals("High")){ bitSet.set(6);}
                if (list.get(i)[3].equals("Clockwise")){ bitSet.set(7);}
                if (list.get(i)[4].equals("Up")){ bitSet.set(8);}

                // converting the milliseconds in String form to Integer
                // then putting it into the BitSet
                temp = Integer.parseInt(list.get(i)[5]);
                int index = 9;
                while (temp != 0)
                {
                    if (temp % 2 != 0)
                    {
                        bitSet.set(index);
                    }
                    ++index;
                    temp = temp >>> 1;
                }

                BSlist.add(i, bitSet);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return BSlist;
    }

}






